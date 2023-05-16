package org.myfraternity.dao;

import org.myfraternity.entity.Chapter;
import org.myfraternity.entity.Officer;
import org.myfraternity.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OfficerDAOImpl {
    public static List<Officer> getAllOfficers() {
        List<Officer> officerList = new ArrayList<>();
        try {
            String query = "select * from officer";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Officer officer = new Officer();
                officer.setOfficerId(resultSet.getInt("officer_id"));
                officer.setPosition(resultSet.getString("position"));
                officer.setStartDate(resultSet.getDate("start_date"));
                officer.setEndDate(resultSet.getDate("end_date"));
                officer.setMemberId(resultSet.getInt("member_id"));
                officerList.add(officer);

            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return officerList;
    }

    public static void addOfficer(Officer officer) {
        String query = "INSERT INTO officer(position, start_date, end_date, member_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, officer.getPosition());
            preparedStatement.setDate(2, officer.getStartDate());
            preparedStatement.setDate(3, officer.getEndDate());
            preparedStatement.setInt(4, officer.getMemberId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding officer");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOfficer(Officer officer) {
        String query = "UPDATE officer SET position = ?, start_date = ?, end_date = ?, member_id = ? WHERE officer_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, officer.getPosition());
            preparedStatement.setDate(2, officer.getStartDate());
            preparedStatement.setDate(3, officer.getEndDate());
            preparedStatement.setInt(4, officer.getMemberId());
            preparedStatement.setInt(8, officer.getOfficerId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Officer FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteOfficer(Officer officer) {
        String query = "DELETE FROM officer WHERE officer_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, officer.getOfficerId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Officer FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<Vector<Object>> getCurrentOfficers() {
        Vector<Vector<Object>> vector = new Vector<>();
        String query = "select concat(first_name, ' ', last_name) as name, o.position, c.name as chapter_name\n" +
                "from member m\n" +
                "inner join chapter c on m.chapter_id = c.chapter_id\n" +
                "inner join officer o on m.member_id = o.member_id\n" +
                "where m.member_id = any (select o.member_id\n" +
                "\t\t\t\t\t\tfrom officer o\n" +
                "\t\t\t\t\t\twhere start_date >= '2023-01-01' and end_date >= curdate());";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Vector<Object> currRow = new Vector<>();
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                String chapter = resultSet.getString("chapter_name");
                currRow.add(name);
                currRow.add(position);
                currRow.add(chapter);
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
