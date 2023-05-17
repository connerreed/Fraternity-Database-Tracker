package com.myfraternity.gui;

import com.myfraternity.dao.AttendanceDAOImpl;
import com.myfraternity.dao.EventDAOImpl;
import com.myfraternity.dao.MemberDAOImpl;
import com.myfraternity.entity.*;
import com.myfraternity.entity.Attendance;
import com.myfraternity.entity.Event;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class AttendanceWindow {

    private boolean addEditFrameOpen = false;
    private int memberId;
    private int eventId;
    private String status;
    private java.util.List<Attendance> memberList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel attendanceViewPanel;
    private final JFrame attendanceViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public AttendanceWindow() {
        table = null;
        attendanceViewFrame = new JFrame("AttendanceView");
        attendanceViewFrame.setSize(750, 750);
        attendanceViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        attendanceViewFrame.setLocationRelativeTo(null);
        attendanceViewPanel = new JPanel();

        // Main Add Button
        JButton addAttendanceButton = new JButton("Add");
        addAttendanceButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        attendanceViewPanel.add(addAttendanceButton);

        // Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Attendance attendance = new Attendance();
            attendance.setMemberId((Integer)rowData.get(0));
            attendance.setEventId((Integer)rowData.get(1));

            AttendanceDAOImpl.deleteAttendance(attendance);
            refreshTable();
        });
        attendanceViewPanel.add(deleteButton);

        // Add JTable
        memberList = AttendanceDAOImpl.getAllAttendances();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Attendance Attendance : memberList) {
            data.add(Attendance.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("Event ID");
        columnNames.add("Status");
        if(table != null) { // table is filled
            //AttendanceViewPanel.removeAll();
            attendanceViewPanel.invalidate();
            attendanceViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //AttendanceViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        attendanceViewPanel.add(jScrollPane);
        //AttendanceViewPanel.add(jTable);



        attendanceViewFrame.add(attendanceViewPanel);
        attendanceViewFrame.setVisible(true);
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
                    Attendance attendance = new Attendance();
                    attendance.setMemberId((Integer)rowData.get(0));
                    attendance.setEventId((Integer)rowData.get(1));
                    attendance.setStatus((String)rowData.get(2));

                    AttendanceDAOImpl.updateAttendance(attendance);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame AttendanceEditFrame = new JFrame();
        JPanel AttendanceEditPanel = new JPanel(new GridLayout(2, 2)); // FIXME: on other classes, this needs to be able to hold amount of attributes
        //AttendanceEditPanel.setLayout(new BoxLayout(AttendanceEditPanel, BoxLayout.PAGE_AXIS));
        AttendanceEditFrame.setSize(750, 750);
        AttendanceEditFrame.setLocationRelativeTo(null);
        AttendanceEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AttendanceEditFrame.setTitle("Attendance");

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
        AttendanceEditPanel.add(memberIdPanel);*/
        JPanel memberPanel = new JPanel();
        JLabel memberLabel = new JLabel("Member");
        memberPanel.add(memberLabel);
        java.util.List<Member> memberList = MemberDAOImpl.getAllMembers();
        String[] memberString = new String[memberList.size()];
        for(int i = 0; i < memberList.size(); i++) {
            memberString[i] = memberList.get(i).getMemberId() + " " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName();
        }

        JComboBox memberComboBox = new JComboBox(memberString);
        memberPanel.add(memberComboBox);
        AttendanceEditPanel.add(memberPanel);


        // Event ID TextField
        /*JTextField eventIdTextField = new JTextField();
        //nameTextField.setName("Name:");
        eventIdTextField.setPreferredSize(textFieldDimension);
        eventIdTextField.addActionListener(e -> {
            eventId = Integer.parseInt(e.getActionCommand());
        });
        JPanel eventIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel eventIdLabel = new JLabel("Event ID:"); // Create a new JLabel
        eventIdPanel.add(eventIdLabel); // Add the JLabel to the JPanel
        eventIdPanel.add(eventIdTextField); // Add the JTextField to the JPanel
        AttendanceEditPanel.add(eventIdPanel);*/
        JPanel eventPanel = new JPanel();
        JLabel eventLabel = new JLabel("Event");
        eventPanel.add(eventLabel);
        java.util.List<Event> eventList = EventDAOImpl.getAllEvents();
        String[] eventString = new String[eventList.size()];
        for(int i = 0; i < eventList.size(); i++) {
            eventString[i] = eventList.get(i).getEvent_id() + " " + eventList.get(i).getName();
        }

        JComboBox eventComboBox = new JComboBox(eventString);
        eventPanel.add(eventComboBox);
        AttendanceEditPanel.add(eventPanel);

        // Status TextField
        JTextField statusTextField = new JTextField();
        //nameTextField.setName("Name:");
        statusTextField.setPreferredSize(textFieldDimension);
        statusTextField.addActionListener(e -> {
            status = e.getActionCommand();
        });
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel statusLabel = new JLabel("Status:"); // Create a new JLabel
        statusPanel.add(statusLabel); // Add the JLabel to the JPanel
        statusPanel.add(statusTextField); // Add the JTextField to the JPanel
        AttendanceEditPanel.add(statusPanel);



        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addPanel.add(addButton);
        addButton.addActionListener(e -> {
            Scanner scn = new Scanner((String)memberComboBox.getSelectedItem());
            memberId = scn.nextInt();
            scn = new Scanner((String)eventComboBox.getSelectedItem());
            eventId = scn.nextInt();
            status = statusTextField.getText();

            Attendance attendance = new Attendance();
            attendance.setMemberId(memberId);
            attendance.setEventId(eventId);
            attendance.setStatus(status);

            AttendanceDAOImpl.addAttendance(attendance);
            AttendanceEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        AttendanceEditPanel.add(addPanel);

        AttendanceEditFrame.add(AttendanceEditPanel);
        AttendanceEditFrame.setVisible(true);
    }

    public void refreshTable() {
        memberList = AttendanceDAOImpl.getAllAttendances();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Attendance Attendance : memberList) {
            data.add(Attendance.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("Event ID");
        columnNames.add("Status");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}

