package com.myfraternity.gui;

import com.myfraternity.dao.MemberDAOImpl;
import com.myfraternity.dao.PaymentDAOImpl;
import com.myfraternity.entity.Member;
import com.myfraternity.entity.Payment;
import com.myfraternity.entity.Payment;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class PaymentWindow {

    private boolean addEditFrameOpen = false;
    private int paymentId;
    private int memberId;
    private float amountDue;
    private float amountInit;
    private java.sql.Date paymentDate;
    private String description;
    private java.util.List<Payment> paymentList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel paymentViewPanel;
    private final JFrame paymentViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public PaymentWindow() {
        table = null;
        paymentViewFrame = new JFrame("PaymentView");
        paymentViewFrame.setSize(750, 750);
        paymentViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentViewFrame.setLocationRelativeTo(null);
        paymentViewPanel = new JPanel();

        // Add Payment Button
        JButton addPaymentButton = new JButton("Add");
        addPaymentButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        paymentViewPanel.add(addPaymentButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Payment Payment = new Payment();
            Payment.setPayment_id((Integer)rowData.get(0));
            PaymentDAOImpl.deletePayment(Payment);
            refreshTable();
        });
        paymentViewPanel.add(deleteButton);

        // Add JTable
        paymentList = PaymentDAOImpl.getAllPayments();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Payment Payment : paymentList) {
            data.add(Payment.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Payment ID");
        columnNames.add("Member ID");
        columnNames.add("Amount Due");
        columnNames.add("Amount Initial");
        columnNames.add("Payment Date");
        columnNames.add("Description");
        if(table != null) { // table is filled
            //PaymentViewPanel.removeAll();
            paymentViewPanel.invalidate();
            paymentViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //PaymentViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        paymentViewPanel.add(jScrollPane);
        //PaymentViewPanel.add(jTable);



        paymentViewFrame.add(paymentViewPanel);
        paymentViewFrame.setVisible(true);
    }

    public void addTableListener() {
        // Add a TableModelListener to the JTable
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                // Check if the change was in a cell
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    // Get the row index of the edited cell
                    int row = e.getFirstRow();

                    // Get the values of all the cells in the edited row
                    java.util.List<Object> rowData = new ArrayList<>();
                    for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                        rowData.add(table.getModel().getValueAt(row, i));
                    }


                    // Add the row data to a List or Vector
                    // You can then use this List or Vector to store the data for further processing
                    java.util.List<java.util.List<Object>> tableData = new ArrayList<>();
                    tableData.add(rowData); // edited row
                    Payment payment = new Payment();
                    payment.setPayment_id((Integer)rowData.get(0));
                    payment.setMember_id((Integer) rowData.get(1));
                    payment.setAmount_due((Float)rowData.get(2));
                    payment.setAmount_initial((Float)rowData.get(3));
                    payment.setPayment_date(Date.valueOf(rowData.get(4).toString()));
                    payment.setDescription((String)rowData.get(5));
                    PaymentDAOImpl.updatePayment(payment);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame PaymentEditFrame = new JFrame();
        JPanel PaymentEditPanel = new JPanel(new GridLayout(3, 2)); // FIXME: on other classes, this needs to be able to hold amount of attributes
        //PaymentEditPanel.setLayout(new BoxLayout(PaymentEditPanel, BoxLayout.PAGE_AXIS));
        PaymentEditFrame.setSize(750, 750);
        PaymentEditFrame.setLocationRelativeTo(null);
        PaymentEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PaymentEditFrame.setTitle("Payment");

        // Member TextField
        /*JComboBox comboBox = new JComboBox();
        List<Member> memberList = MemberDAOImpl.getAllMembers();
        for(Member member : memberList) {
            JLabel label = new JLabel(member.getMemberId() + ", " + member.getFirstName() + " " + member.getLastName());
            comboBox.add(label);
        }
        paymentViewPanel.add(comboBox);

         */
        /*JTextField memberTextField = new JTextField();
        //nameTextField.setName("Name:");
        memberTextField.setPreferredSize(textFieldDimension);
        memberTextField.addActionListener(e -> {
            memberId = Integer.parseInt(e.getActionCommand());
        });
        JPanel memberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel memberLabel = new JLabel("Member ID:"); // Create a new JLabel
        memberPanel.add(memberLabel); // Add the JLabel to the JPanel
        memberPanel.add(memberTextField); // Add the JTextField to the JPanel
        PaymentEditPanel.add(memberPanel);*/
        JPanel memberPanel = new JPanel();
        memberPanel.add(new JLabel("Member"));
        java.util.List<Member> memberList = MemberDAOImpl.getAllMembers();
        String[] memberStrings = new String[memberList.size()];
        for(int i = 0; i < memberList.size(); i++) {
            memberStrings[i] = memberList.get(i).getMemberId() + " " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName();
        }

        JComboBox memberComboBox = new JComboBox(memberStrings);
        memberPanel.add(memberComboBox);
        PaymentEditPanel.add(memberPanel);


        // Amount Due TextField
        JTextField amountDueTextField = new JTextField();
        amountDueTextField.setPreferredSize(textFieldDimension);
        amountDueTextField.addActionListener(e -> {
            amountDue = Float.parseFloat(e.getActionCommand());
        });
        JPanel amountDuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel amountDueLabel = new JLabel("Amount Due:"); // Create a new JLabel
        amountDuePanel.add(amountDueLabel); // Add the JLabel to the JPanel
        amountDuePanel.add(amountDueTextField); // Add the JTextField to the JPanel
        PaymentEditPanel.add(amountDuePanel);

        // Amount Init TextField
        JTextField amountInitTextField = new JTextField();
        amountInitTextField.setPreferredSize(textFieldDimension);
        amountInitTextField.addActionListener(e -> {
            amountInit = Float.parseFloat(e.getActionCommand());
        });
        JPanel amountInitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel amountInitLabel = new JLabel("Amount Init:"); // Create a new JLabel
        amountInitPanel.add(amountInitLabel); // Add the JLabel to the JPanel
        amountInitPanel.add(amountInitTextField); // Add the JTextField to the JPanel
        PaymentEditPanel.add(amountInitPanel);

        // Payment Date TextField
        JTextField dateTextField = new JTextField();
        dateTextField.setPreferredSize(textFieldDimension);
        dateTextField.addActionListener(e -> {
            paymentDate = Date.valueOf(e.getActionCommand());
        });
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel dateLabel = new JLabel("Payment Date:"); // Create a new JLabel
        datePanel.add(dateLabel); // Add the JLabel to the JPanel
        datePanel.add(dateTextField); // Add the JTextField to the JPanel
        PaymentEditPanel.add(datePanel);


        // Description TextField
        JTextField descriptionTextField = new JTextField();
        descriptionTextField.setPreferredSize(textFieldDimension);
        descriptionTextField.addActionListener(e -> {
            description = e.getActionCommand();
        });
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel descriptionLabel = new JLabel("Description:"); // Create a new JLabel
        descriptionPanel.add(descriptionLabel); // Add the JLabel to the JPanel
        descriptionPanel.add(descriptionTextField); // Add the JTextField to the JPanel
        PaymentEditPanel.add(descriptionPanel);

        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            Scanner scn = new Scanner((String)memberComboBox.getSelectedItem());
            memberId = scn.nextInt();
            amountDue = Float.parseFloat(amountDueTextField.getText());
            amountInit = Float.parseFloat(amountInitTextField.getText());
            paymentDate = Date.valueOf(dateTextField.getText());
            description = descriptionTextField.getText();

            Payment payment = new Payment();
            payment.setMember_id(memberId);
            payment.setAmount_initial(amountInit);
            payment.setAmount_due(amountDue);
            payment.setPayment_date(paymentDate);
            payment.setDescription(description);


            PaymentDAOImpl.addPayment(payment);
            PaymentEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        PaymentEditPanel.add(addPanel);

        PaymentEditFrame.add(PaymentEditPanel);
        PaymentEditFrame.setVisible(true);
    }

    public void refreshTable() {
        paymentList = PaymentDAOImpl.getAllPayments();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Payment Payment : paymentList) {
            data.add(Payment.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Payment ID");
        columnNames.add("Member ID");
        columnNames.add("Amount Due");
        columnNames.add("Amount Initial");
        columnNames.add("Payment Date");
        columnNames.add("Description");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}
