package org.myfraternity.gui;

import org.myfraternity.dao.CommitteeMemberDAOImpl;
import org.myfraternity.dao.MemberDAOImpl;
import org.myfraternity.dao.PaymentDAOImpl;
import org.myfraternity.entity.CommitteeMember;
import org.myfraternity.entity.Member;
import org.myfraternity.entity.Payment;
import org.myfraternity.entity.Payment;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.*;

public class MemberOnCommitteesWindow {

    private JTable table;
    private final JPanel paymentViewPanel;
    private final JFrame paymentViewFrame;
    private JScrollPane jScrollPane;
    private final Vector<String> columnNames;
    private final float threshold;
    private Map<Member, Payment> map;
    public MemberOnCommitteesWindow() {
        table = null;
        paymentViewFrame = new JFrame("MemberOnCommitteesView");
        paymentViewFrame.setSize(750, 750);
        paymentViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentViewFrame.setLocationRelativeTo(null);
        paymentViewPanel = new JPanel();
        threshold = (float)0.0;

        // Add JTable
        java.util.List<Payment> paymentList = PaymentDAOImpl.getAllPayments();
        Vector<Vector<Object>> data = CommitteeMemberDAOImpl.getMembersOnCommittees(); // 2d vector
        columnNames = new Vector<>();
        columnNames.add("Member Name");
        columnNames.add("Committee Name");
        if(table != null) { // table is filled
            //PaymentViewPanel.removeAll();
            paymentViewPanel.invalidate();
            paymentViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);


        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        paymentViewPanel.add(jScrollPane);
        //PaymentViewPanel.add(jTable);
        refreshTable();



        paymentViewFrame.add(paymentViewPanel);
        paymentViewFrame.setVisible(true);
    }



    public void refreshTable() {
        Vector<Vector<Object>> data = CommitteeMemberDAOImpl.getMembersOnCommittees(); // 2d vector
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        jScrollPane.repaint();
    }
}
