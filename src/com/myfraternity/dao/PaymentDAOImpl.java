package com.myfraternity.dao;

import com.myfraternity.entity.Member;
import com.myfraternity.entity.Payment;
import com.myfraternity.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PaymentDAOImpl {
    public static List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        try {
            String query = "select * from payment";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Payment payment = new Payment();
                payment.setPayment_id(resultSet.getInt("payment_id"));
                payment.setMember_id(resultSet.getInt("member_id"));
                payment.setAmount_due(resultSet.getFloat("amount_due"));
                payment.setAmount_initial(resultSet.getFloat("amount_initial"));
                payment.setPayment_date(resultSet.getDate("payment_date"));
                payment.setDescription(resultSet.getString("description"));
                paymentList.add(payment);

            }
            DBUtil.closeConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    public static void addPayment(Payment payment) {
        String query = "INSERT INTO payment(member_id, amount_due, amount_initial, payment_date, description) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, payment.getMember_id());
            preparedStatement.setFloat(2, payment.getAmount_due());
            preparedStatement.setFloat(3, payment.getAmount_initial());
            preparedStatement.setDate(4, payment.getPayment_date());
            preparedStatement.setString(5, payment.getDescription());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Zero rows affected when adding payment");
            }
            DBUtil.closeConnection();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePayment(Payment payment) {
        String query = "UPDATE payment SET member_id = ?, amount_due = ?, amount_initial = ?, payment_date = ?, description = ? WHERE payment_id = ?";
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, payment.getMember_id());
            preparedStatement.setFloat(2, payment.getAmount_due());
            preparedStatement.setFloat(3, payment.getAmount_initial());
            preparedStatement.setDate(4, payment.getPayment_date());
            preparedStatement.setString(5, payment.getDescription());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Update on Payment FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deletePayment(Payment payment) {
        String query = "DELETE FROM payment WHERE payment_id = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, payment.getPayment_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Delete on Payment FAILED");
            }
            preparedStatement.close();
            DBUtil.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<Vector<Object>> getPaymentsOverThreshold(float threshold) {
        Vector<Vector<Object>> vector = new Vector<>();
        String query = "SELECT m.first_name, m.last_name, p.amount_due FROM Payment p INNER JOIN Member m ON p.member_id = m.member_id WHERE p.amount_due > " + threshold;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DBUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Vector<Object> currRow = new Vector<>();
                Member member = new Member();
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                currRow.add(member.getFirstName() + " " + member.getLastName());
                Payment payment = new Payment();
                payment.setAmount_due(resultSet.getFloat("amount_due"));
                currRow.add(payment.getAmount_due());
                vector.add(currRow);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }
}
