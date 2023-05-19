package com.myfraternity.gui;

import com.myfraternity.dao.MemberDAOImpl;
import com.myfraternity.dao.OfficerDAOImpl;
import com.myfraternity.entity.*;
import com.myfraternity.entity.Officer;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class OfficerWindow {

    private boolean addEditFrameOpen = false;
    private String position;
    private Date startDate;
    private Date endDate;
    private int memberId;
    private java.util.List<Officer> officerList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel officerViewPanel;
    private final JFrame officerViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public OfficerWindow() {
        table = null;
        officerViewFrame = new JFrame("OfficerView");
        officerViewFrame.setSize(750, 750);
        officerViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        officerViewFrame.setLocationRelativeTo(null);
        officerViewPanel = new JPanel();

        // Add Officer Button
        JButton addOfficerButton = new JButton("Add");
        addOfficerButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        officerViewPanel.add(addOfficerButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Officer Officer = new Officer();
            Officer.setOfficerId((Integer)rowData.get(0));
            OfficerDAOImpl.deleteOfficer(Officer);
            refreshTable();
        });
        officerViewPanel.add(deleteButton);

        // Add JTable
        officerList = OfficerDAOImpl.getAllOfficers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Officer Officer : officerList) {
            data.add(Officer.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Officer ID");
        columnNames.add("Position");
        columnNames.add("Start Date");
        columnNames.add("End Date");
        columnNames.add("Member ID");
        if(table != null) { // table is filled
            //OfficerViewPanel.removeAll();
            officerViewPanel.invalidate();
            officerViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //OfficerViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        officerViewPanel.add(jScrollPane);
        //OfficerViewPanel.add(jTable);



        officerViewFrame.add(officerViewPanel);
        officerViewFrame.setVisible(true);
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
                    Officer officer = new Officer();
                    officer.setOfficerId((Integer)rowData.get(0)); // FIXME: not sure if primary key should be added here
                    officer.setPosition((String)rowData.get(1));
                    officer.setStartDate((Date)rowData.get(2));
                    officer.setEndDate((Date)rowData.get(3));
                    officer.setMemberId((Integer)rowData.get(4));

                    OfficerDAOImpl.updateOfficer(officer);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame OfficerEditFrame = new JFrame();
        JPanel OfficerEditPanel = new JPanel(new GridLayout(2, 3));
        //OfficerEditPanel.setLayout(new BoxLayout(OfficerEditPanel, BoxLayout.PAGE_AXIS));
        OfficerEditFrame.setSize(750, 750);
        OfficerEditFrame.setLocationRelativeTo(null);
        OfficerEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        OfficerEditFrame.setTitle("Officer");

        // Position TextField
        JTextField positionTextField = new JTextField();
        //nameTextField.setName("Name:");
        positionTextField.setPreferredSize(textFieldDimension);
        positionTextField.addActionListener(e -> {
            position = e.getActionCommand();
        });
        JPanel positionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel positionLabel = new JLabel("Position:"); // Create a new JLabel
        positionPanel.add(positionLabel); // Add the JLabel to the JPanel
        positionPanel.add(positionTextField); // Add the JTextField to the JPanel
        OfficerEditPanel.add(positionPanel);


        // Start Date TextField
        JTextField startDateTextField = new JTextField();
        //nameTextField.setName("Name:");
        startDateTextField.setPreferredSize(textFieldDimension);
        startDateTextField.addActionListener(e -> {
            startDate = java.sql.Date.valueOf(e.getActionCommand());
        });
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel startDateLabel = new JLabel("Start Date:"); // Create a new JLabel
        startDatePanel.add(startDateLabel); // Add the JLabel to the JPanel
        startDatePanel.add(startDateTextField); // Add the JTextField to the JPanel
        OfficerEditPanel.add(startDatePanel);

        // End Date TextField
        JTextField endDateTextField = new JTextField();
        //nameTextField.setName("Name:");
        endDateTextField.setPreferredSize(textFieldDimension);
        endDateTextField.addActionListener(e -> {
            endDate = java.sql.Date.valueOf(e.getActionCommand());
        });
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel endDateLabel = new JLabel("End Date:"); // Create a new JLabel
        endDatePanel.add(endDateLabel); // Add the JLabel to the JPanel
        endDatePanel.add(endDateTextField); // Add the JTextField to the JPanel
        OfficerEditPanel.add(endDatePanel);


        // Member ID TextField
        /*JTextField memberIdTextField = new JTextField();
        //nameTextField.setName("Name:");
        memberIdTextField.setPreferredSize(textFieldDimension);
        memberIdTextField.addActionListener(e -> {
            memberId = Integer.parseInt(e.getActionCommand());
        });
        JPanel memberIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel memberIdLabel = new JLabel("Member ID:"); // Create a new JLabel
        memberIdPanel.add(memberIdLabel); // Add the JLabel to the JPanel
        memberIdPanel.add(memberIdTextField); // Add the JTextField to the JPanel
        OfficerEditPanel.add(memberIdPanel);*/
        JPanel memberPanel = new JPanel();
        java.util.List<Member> memberList = MemberDAOImpl.getAllMembers();
        String[] memberStrings = new String[memberList.size()];
        for(int i = 0; i < memberList.size(); i++) {
            memberStrings[i] = memberList.get(i).getMemberId() + " " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName();
        }
        JLabel memberLabel = new JLabel("Member");
        memberPanel.add(memberLabel);
        JComboBox<String> memberComboBox = new JComboBox<>(memberStrings);
        memberPanel.add(memberComboBox);
        OfficerEditPanel.add(memberPanel);


        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            position = positionTextField.getText();
            startDate = Date.valueOf(startDateTextField.getText());
            endDate = Date.valueOf(endDateTextField.getText());
            Scanner scn =new Scanner((String)memberComboBox.getSelectedItem());
            memberId = scn.nextInt();
            scn.close();

            Officer officer = new Officer();
            officer.setPosition(position);
            officer.setStartDate(startDate);
            officer.setEndDate(endDate);
            officer.setMemberId(memberId);

            OfficerDAOImpl.addOfficer(officer);
            OfficerEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        OfficerEditPanel.add(addPanel);

        OfficerEditFrame.add(OfficerEditPanel);
        OfficerEditFrame.setVisible(true);
    }

    public void refreshTable() {
        officerList = OfficerDAOImpl.getAllOfficers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for (Officer Officer : officerList) {
            data.add(Officer.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Officer ID");
        columnNames.add("Position");
        columnNames.add("Start Date");
        columnNames.add("End Date");
        columnNames.add("Member ID");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();


    }
}

