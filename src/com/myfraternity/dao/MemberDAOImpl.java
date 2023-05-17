package com.myfraternity.dao;

import com.myfraternity.entity.Chapter;
import com.myfraternity.entity.Member;
import com.myfraternity.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl {
    public static List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        try {
            String query = "select * from member";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Member member = new Member();
                member.setMemberId(resultSet.getInt("member_id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setPhoneNumber(resultSet.getString("phone_number"));
                member.setDateOfBirth(resultSet.getDate("date_of_birth"));
                member.setStatus(resultSet.getString("status"));
                member.setChapterId(resultSet.getInt("chapter_id"));
                memberList.add(member);

            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public static void addMember(Member member) {
        String query = "INSERT INTO member(member_id, first_name, last_name, email, phone_number, date_of_birth, status, chapter_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, member.getMemberId()); // FIXME: member_id should not be getting inserted, the primary key is database generated
            preparedStatement.setString(2, member.getFirstName());
            preparedStatement.setString(3, member.getLastName());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getPhoneNumber());
            preparedStatement.setDate(6, member.getDateOfBirth());
            preparedStatement.setString(7, member.getStatus());
            preparedStatement.setInt(8, member.getChapterId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding member");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMember(Member member) {
        String query = "UPDATE member SET first_name = ?, last_name = ?, email = ?, phone_number = ?, date_of_birth = ?, status = ?, chapter_id = ? WHERE member_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getEmail());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setDate(5, member.getDateOfBirth());
            preparedStatement.setString(6, member.getStatus());
            preparedStatement.setInt(7, member.getChapterId());
            preparedStatement.setInt(8, member.getMemberId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Member FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteMember(Member member) {
        String query = "DELETE FROM member WHERE member_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, member.getMemberId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Member FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
