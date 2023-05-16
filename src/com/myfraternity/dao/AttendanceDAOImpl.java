package com.myfraternity.dao;

import com.myfraternity.entity.Attendance;
import com.myfraternity.entity.Member;
import com.myfraternity.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AttendanceDAOImpl {
    public static List<Attendance> getAllAttendances() {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            String query = "select * from attendance";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Attendance attendance = new Attendance();
                attendance.setMemberId(resultSet.getInt("member_id"));
                attendance.setEventId(resultSet.getInt("event_id"));
                attendance.setStatus(resultSet.getString("status"));
                attendanceList.add(attendance);
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public static void addAttendance(Attendance attendance) {
        String query = "INSERT INTO attendance(member_id, event_id, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, attendance.getMemberId());
            preparedStatement.setInt(2, attendance.getEventId());
            preparedStatement.setString(3, attendance.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding attendance");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAttendance(Attendance attendance) {
        String query = "UPDATE attendance SET status = ? WHERE member_id = ? AND event_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, attendance.getStatus());
            preparedStatement.setInt(2, attendance.getMemberId());
            preparedStatement.setInt(3, attendance.getEventId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Attendance FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteAttendance(Attendance attendance) {
        String query = "DELETE FROM attendance WHERE member_id = ? AND event_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, attendance.getMemberId());
            preparedStatement.setInt(2, attendance.getEventId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Attendance FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<Vector<Object>> getMembersWithZeroAttendance() {
        Vector<Vector<Object>> vector = new Vector<>();
        String query = "select m.first_name, m.last_name\n" +
                "from member m\n" +
                "where m.member_id not in (select distinct member_id from attendance);";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Vector<Object> currRow = new Vector<>();
                Member member = new Member();
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                currRow.add(member.getFirstName() + " " + member.getLastName());
                vector.add(currRow);
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

}
