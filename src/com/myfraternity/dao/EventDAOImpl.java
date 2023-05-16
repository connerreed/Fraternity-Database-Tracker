package com.myfraternity.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.myfraternity.entity.Chapter;
import com.myfraternity.entity.Event;
import com.myfraternity.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class EventDAOImpl {
    public static List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        try {
            String query = "select * from event";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Event event = new Event();
                event.setEvent_id(resultSet.getInt("event_id"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getDate("date"));
                event.setLocation(resultSet.getString("location"));
                event.setDescription(resultSet.getString("description"));
                event.setChapter_id(resultSet.getInt("chapter_id"));
                eventList.add(event);

            }
            DBUtil.closeConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static void addEvent(Event event) {
        String query = "INSERT INTO event(name, date, location, description, chapter_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setDate(2, event.getDate());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setInt(5, event.getChapter_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding event");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEvent(Event event) {
        String query = "UPDATE event SET name = ?, date = ?, location = ?, description = ?, chapter_id = ? WHERE event_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setDate(2, event.getDate());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setInt(5, event.getChapter_id());
            preparedStatement.setInt(6, event.getEvent_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Event FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteEvent(Event event) {
        String query = "DELETE FROM event WHERE event_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, event.getEvent_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Event FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<Vector<Object>> getEventsByChapter() {
        Vector<Vector<Object>> vector = new Vector<>();
        String query = "select c.name, count(e.event_id) as count\n" +
                "from event e, chapter c\n" +
                "where e.chapter_id in (select c.chapter_id)\n" +
                "group by c.chapter_id";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Vector<Object> currRow = new Vector<>();
                currRow.add(resultSet.getString("name"));
                currRow.add(resultSet.getInt("count"));
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
