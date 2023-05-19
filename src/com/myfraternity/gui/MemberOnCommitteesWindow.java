package com.myfraternity.gui;

import com.myfraternity.dao.CommitteeMemberDAOImpl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class MemberOnCommitteesWindow {

    private JTable table;
    private final JPanel paymentViewPanel;
    private final JFrame paymentViewFrame;
    private JScrollPane jScrollPane;
    private final Vector<String> columnNames;
    public MemberOnCommitteesWindow() {
        table = null;
        paymentViewFrame = new JFrame("MemberOnCommitteesView");
        paymentViewFrame.setSize(750, 750);
        paymentViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentViewFrame.setLocationRelativeTo(null);
        paymentViewPanel = new JPanel();

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
