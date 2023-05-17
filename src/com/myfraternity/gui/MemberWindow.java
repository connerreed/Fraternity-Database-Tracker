package com.myfraternity.gui;

import com.myfraternity.dao.ChapterDAOImpl;
import com.myfraternity.dao.MemberDAOImpl;
import com.myfraternity.dao.MemberDAOImpl;
import com.myfraternity.entity.Chapter;
import com.myfraternity.entity.Member;
import com.myfraternity.entity.Member;
import com.myfraternity.entity.Member;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class MemberWindow {

    private boolean addEditFrameOpen = false;
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private java.sql.Date dateOfBirth;
    private String status;
    private int chapterId;
    private java.util.List<Member> memberList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel memberViewPanel;
    private final JFrame memberViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public MemberWindow() {
        table = null;
        memberViewFrame = new JFrame("MemberView");
        memberViewFrame.setSize(750, 750);
        memberViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberViewFrame.setLocationRelativeTo(null);
        memberViewPanel = new JPanel();

        // Add Member Button
        JButton addMemberButton = new JButton("Add");
        addMemberButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        memberViewPanel.add(addMemberButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Member Member = new Member();
            Member.setMemberId((Integer)rowData.get(0));
            MemberDAOImpl.deleteMember(Member);
            refreshTable();
        });
        memberViewPanel.add(deleteButton);

        // Add JTable
        memberList = MemberDAOImpl.getAllMembers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Member Member : memberList) {
            data.add(Member.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Email");
        columnNames.add("Phone Number");
        columnNames.add("Date of Birth");
        columnNames.add("Status");
        columnNames.add("Chapter ID");
        if(table != null) { // table is filled
            //MemberViewPanel.removeAll();
            memberViewPanel.invalidate();
            memberViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //MemberViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        memberViewPanel.add(jScrollPane);
        //MemberViewPanel.add(jTable);



        memberViewFrame.add(memberViewPanel);
        memberViewFrame.setVisible(true);
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
                    Member member = new Member();
                    member.setMemberId((Integer)rowData.get(0));
                    member.setFirstName((String)rowData.get(1));
                    member.setLastName((String)rowData.get(2));
                    member.setEmail((String)rowData.get(3));
                    member.setPhoneNumber((String)rowData.get(4));
                    member.setDateOfBirth(Date.valueOf(rowData.get(5).toString()));
                    member.setStatus((String)rowData.get(6));
                    member.setChapterId((Integer)rowData.get(7));

                    MemberDAOImpl.updateMember(member);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame MemberEditFrame = new JFrame();
        JPanel MemberEditPanel = new JPanel(new GridLayout(3, 3)); // FIXME: on other classes, this needs to be able to hold amount of attributes
        //MemberEditPanel.setLayout(new BoxLayout(MemberEditPanel, BoxLayout.PAGE_AXIS));
        MemberEditFrame.setSize(750, 750);
        MemberEditFrame.setLocationRelativeTo(null);
        MemberEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MemberEditFrame.setTitle("Member");

        // First Name TextField
        JTextField firstNameTextField = new JTextField();
        //nameTextField.setName("Name:");
        firstNameTextField.setPreferredSize(textFieldDimension);
        firstNameTextField.addActionListener(e -> {
            firstName = e.getActionCommand();
        });
        JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel firstNameLabel = new JLabel("First Name:"); // Create a new JLabel
        firstNamePanel.add(firstNameLabel); // Add the JLabel to the JPanel
        firstNamePanel.add(firstNameTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(firstNamePanel);


        // Last Name TextField
        JTextField lastNameTextField = new JTextField();
        //nameTextField.setName("Name:");
        lastNameTextField.setPreferredSize(textFieldDimension);
        lastNameTextField.addActionListener(e -> {
            lastName = e.getActionCommand();
        });
        JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel lastNameLabel = new JLabel("Last Name:"); // Create a new JLabel
        lastNamePanel.add(lastNameLabel); // Add the JLabel to the JPanel
        lastNamePanel.add(lastNameTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(lastNamePanel);

        // Email TextField
        JTextField emailTextField = new JTextField();
        //nameTextField.setName("Name:");
        emailTextField.setPreferredSize(textFieldDimension);
        emailTextField.addActionListener(e -> {
            email = e.getActionCommand();
        });
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel emailLabel = new JLabel("Email:"); // Create a new JLabel
        emailPanel.add(emailLabel); // Add the JLabel to the JPanel
        emailPanel.add(emailTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(emailPanel);

        // Phone Number TextField
        JTextField phoneNumberTextField = new JTextField();
        //nameTextField.setName("Name:");
        phoneNumberTextField.setPreferredSize(textFieldDimension);
        phoneNumberTextField.addActionListener(e -> {
            phoneNumber = e.getActionCommand();
        });
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel phoneNumberLabel = new JLabel("Phone Number:"); // Create a new JLabel
        phoneNumberPanel.add(phoneNumberLabel); // Add the JLabel to the JPanel
        phoneNumberPanel.add(phoneNumberTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(phoneNumberPanel);

        // Date of Birth TextField
        JTextField dateTextField = new JTextField();
        dateTextField.setPreferredSize(textFieldDimension);
        dateTextField.addActionListener(e -> {
            dateOfBirth = Date.valueOf(e.getActionCommand());
        });
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel dateLabel = new JLabel("Date of Birth:"); // Create a new JLabel
        datePanel.add(dateLabel); // Add the JLabel to the JPanel
        datePanel.add(dateTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(datePanel);

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
        MemberEditPanel.add(statusPanel);

        //Chapter ID TextField
        /*JTextField chapterIdTextField = new JTextField();
        //nameTextField.setName("Name:");
        chapterIdTextField.setPreferredSize(textFieldDimension);
        chapterIdTextField.addActionListener(e -> {
            chapterId = Integer.parseInt(e.getActionCommand());
        });
        JPanel chapterIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel chapterIdLabel = new JLabel("Chapter ID:"); // Create a new JLabel
        chapterIdPanel.add(chapterIdLabel); // Add the JLabel to the JPanel
        chapterIdPanel.add(chapterIdTextField); // Add the JTextField to the JPanel
        MemberEditPanel.add(chapterIdPanel);*/
        JPanel chapterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel chatperLabel = new JLabel("Chapter");
        chapterPanel.add(chatperLabel);
        java.util.List<Chapter> chapterList = ChapterDAOImpl.getAllChapters();
        String[] strings = new String[chapterList.size()];
        for(int i = 0; i < chapterList.size(); i++) {
            strings[i] = chapterList.get(i).getChapter_id() + " " + chapterList.get(i).getName();
        }

        JComboBox chapterComboBox = new JComboBox(strings);
        chapterPanel.add(chapterComboBox);

        MemberEditPanel.add(chapterPanel);


        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            email = emailTextField.getText();
            phoneNumber = statusTextField.getText();
            dateOfBirth = Date.valueOf(dateTextField.getText());
            status = statusTextField.getText();
            Scanner scn = new Scanner( (String)chapterComboBox.getSelectedItem());
            chapterId = scn.nextInt();

            Member member = new Member();
            member.setFirstName(firstName);
            member.setLastName(lastName);
            member.setEmail(email);
            member.setPhoneNumber(phoneNumber);
            member.setDateOfBirth(dateOfBirth);
            member.setStatus(status);
            member.setChapterId(chapterId);

            MemberDAOImpl.addMember(member);
            MemberEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        MemberEditPanel.add(addPanel);

        MemberEditFrame.add(MemberEditPanel);
        MemberEditFrame.setVisible(true);
    }

    public void refreshTable() {
        memberList = MemberDAOImpl.getAllMembers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Member Member : memberList) {
            data.add(Member.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Email");
        columnNames.add("Phone Number");
        columnNames.add("Date of Birth");
        columnNames.add("Status");
        columnNames.add("Chapter ID");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}

