package com.myfraternity.dao;

import com.myfraternity.entity.CommitteeMember;
import com.myfraternity.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CommitteeMemberDAOImpl {
    public static List<CommitteeMember> getAllCommitteeMembers() {
        List<CommitteeMember> committeememberList = new ArrayList<>();
        try {
            String query = "select * from committee_member";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                CommitteeMember committeemember = new CommitteeMember();
                committeemember.setMemberId(resultSet.getInt("member_id"));
                committeemember.setCommitteeId(resultSet.getInt("committee_id"));
                committeememberList.add(committeemember);

            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return committeememberList;
    }

    public static void addCommitteeMember(CommitteeMember committeemember) {
        String query = "INSERT INTO committee_member(member_id, committee_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, committeemember.getMemberId());
            preparedStatement.setInt(2, committeemember.getCommitteeId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding committeemember");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteCommitteeMember(CommitteeMember committeemember) {
        String query = "DELETE FROM committee_member WHERE member_id = ? AND committee_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, committeemember.getMemberId());
            preparedStatement.setInt(2, committeemember.getCommitteeId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on CommitteeMember FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<Vector<Object>> getMembersOnCommittees() {
        Vector<Vector<Object>> vector = new Vector<>();
        String query = "select * from vw_members_on_committees";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Vector<Object> currRow = new Vector<>();
                String memberName = resultSet.getString("member_name");
                String committeeName = resultSet.getString("committee_name");
                currRow.add(memberName);
                currRow.add(committeeName);
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
