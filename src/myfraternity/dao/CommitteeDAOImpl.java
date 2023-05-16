package org.myfraternity.dao;

import org.myfraternity.entity.Chapter;
import org.myfraternity.entity.Committee;
import org.myfraternity.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommitteeDAOImpl {
    public static List<Committee> getAllCommittees() {
        List<Committee> committeeList = new ArrayList<>();
        try {
            String query = "select * from committee";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Committee committee = new Committee();
                committee.setCommitteeId(resultSet.getInt("committee_id"));
                committee.setName(resultSet.getString("name"));
                committee.setDescription(resultSet.getString("description"));
                committee.setChapterId(resultSet.getInt("chapter_id"));
                committeeList.add(committee);

            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return committeeList;
    }

    public static void addCommittee(Committee committee) {
        String query = "INSERT INTO committee(committee_id, name, description, chapter_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, committee.getCommitteeId());
            preparedStatement.setString(2, committee.getName());
            preparedStatement.setString(3, committee.getDescription());
            preparedStatement.setInt(4, committee.getChapterId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding committee");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCommittee(Committee committee) {
        String query = "UPDATE committee SET name = ?, description = ?, chapter_id = ? WHERE committee_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, committee.getName());
            preparedStatement.setString(2, committee.getDescription());
            preparedStatement.setInt(3, committee.getChapterId());
            preparedStatement.setInt(4, committee.getCommitteeId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Committee FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteCommittee(Committee committee) {
        String query = "DELETE FROM committee WHERE committee_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, committee.getCommitteeId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Committee FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
