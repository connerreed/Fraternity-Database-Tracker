package com.myfraternity.gui;

import com.myfraternity.dao.PaymentDAOImpl;
import com.myfraternity.entity.Payment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class OverdueWindow {

    private JTable table;
    private final JPanel paymentViewPanel;
    private final JFrame paymentViewFrame;
    private JScrollPane jScrollPane;
    private final Vector<String> columnNames;
    private final float threshold;
    public OverdueWindow() {
        table = null;
        paymentViewFrame = new JFrame("OverdueView");
        paymentViewFrame.setSize(750, 750);
        paymentViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentViewFrame.setLocationRelativeTo(null);
        paymentViewPanel = new JPanel();
        threshold = (float)20.0;

        // Add JTable
        java.util.List<Payment> paymentList = PaymentDAOImpl.getAllPayments();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Payment Payment : paymentList) {
            data.add(Payment.getVector());
        }
        columnNames = new Vector<>();
        columnNames.add("Member Name");
        columnNames.add("Amount Due");
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
        Vector<Vector<Object>> data = PaymentDAOImpl.getPaymentsOverThreshold(threshold); // 2d vector
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        jScrollPane.repaint();
    }
}
