package com.myfraternity.dao;

import com.myfraternity.entity.*;
import com.myfraternity.util.DBUtil;

//import java.awt.*;
import java.sql.*;
import java.util.*;

public class ChapterDAOImpl {

    public static List<Chapter> getAllChapters()  {
        List<Chapter> chapterList = new ArrayList<>();
        try {
            String query = "select * from chapter";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Chapter chapter = new Chapter();
                chapter.setChapter_id(resultSet.getInt("chapter_id"));
                chapter.setName(resultSet.getString("name"));
                chapter.setLocation(resultSet.getString("location"));
                chapter.setCharter_date(resultSet.getDate("charter_date"));
                chapterList.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapterList;
    }

    public static void addChapter(Chapter chapter) {
        String query = "INSERT INTO chapter(name, location, charter_date) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, chapter.getName());
            preparedStatement.setString(2, chapter.getLocation());
            preparedStatement.setDate(3, chapter.getCharter_date());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding Chapter");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateChapter(Chapter chapter) {
        String query = "UPDATE chapter SET name = ?, location = ?, charter_date = ? WHERE chapter_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, chapter.getName());
            preparedStatement.setString(2, chapter.getLocation());
            preparedStatement.setDate(3, chapter.getCharter_date());
            preparedStatement.setInt(4, chapter.getChapter_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Chapter FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteChapter(Chapter chapter) {
        String query = "DELETE FROM chapter WHERE chapter_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chapter.getChapter_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Chapter FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
