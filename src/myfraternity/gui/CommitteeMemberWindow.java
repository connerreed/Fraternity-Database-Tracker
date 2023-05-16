package org.myfraternity.gui;

import org.myfraternity.dao.*;
import org.myfraternity.dao.CommitteeMemberDAOImpl;
import org.myfraternity.entity.*;
import org.myfraternity.entity.CommitteeMember;
import org.myfraternity.entity.CommitteeMember;
import org.myfraternity.entity.Event;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class CommitteeMemberWindow {

    private boolean addEditFrameOpen = false;
    private int memberId;
    private int committeeId;
    private java.util.List<CommitteeMember> committeeMemberList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel committeeMemberViewPanel;
    private final JFrame committeeMemberViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public CommitteeMemberWindow() {
        table = null;
        committeeMemberViewFrame = new JFrame("CommitteeMemberView");
        committeeMemberViewFrame.setSize(750, 750);
        committeeMemberViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        committeeMemberViewFrame.setLocationRelativeTo(null);
        committeeMemberViewPanel = new JPanel();

        // Add CommitteeMember Button
        JButton addCommitteeMemberButton = new JButton("Add");
        addCommitteeMemberButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        committeeMemberViewPanel.add(addCommitteeMemberButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            CommitteeMember committeeMember = new CommitteeMember();
            committeeMember.setMemberId((Integer)rowData.get(0));
            committeeMember.setCommitteeId((Integer)rowData.get(1));

            CommitteeMemberDAOImpl.deleteCommitteeMember(committeeMember);
            refreshTable();
        });
        committeeMemberViewPanel.add(deleteButton);

        // Add JTable
        committeeMemberList = CommitteeMemberDAOImpl.getAllCommitteeMembers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(CommitteeMember CommitteeMember : committeeMemberList) {
            data.add(CommitteeMember.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("Committee ID");
        if(table != null) { // table is filled
            //CommitteeMemberViewPanel.removeAll();
            committeeMemberViewPanel.invalidate();
            committeeMemberViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //CommitteeMemberViewPanel.add(table);
        //addTableListener(); // FIXME: add table listener back if update is added back

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        committeeMemberViewPanel.add(jScrollPane);
        //CommitteeMemberViewPanel.add(jTable);



        committeeMemberViewFrame.add(committeeMemberViewPanel);
        committeeMemberViewFrame.setVisible(true);
    }

    /*public void addTableListener() {  FIXME: put this method back in if wanting to add update back
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
                    CommitteeMember committeeMember = new CommitteeMember();
                    committeeMember.setCommitteeMemberId((Integer)rowData.get(0));
                    committeeMember.setFirstName((String)rowData.get(1));
                    committeeMember.setLastName((String)rowData.get(2));
                    committeeMember.setEmail((String)rowData.get(3));
                    committeeMember.setPhoneNumber((String)rowData.get(4));
                    committeeMember.setDateOfBirth(Date.valueOf(rowData.get(5).toString()));
                    committeeMember.setStatus((String)rowData.get(6));
                    committeeMember.setChapterId((Integer)rowData.get(7));

                    CommitteeMemberDAOImpl.updateCommitteeMember(committeeMember);
                }
            }
        });
    }

     */

    public void createAddEditFrame() {
        JFrame CommitteeMemberEditFrame = new JFrame();
        JPanel CommitteeMemberEditPanel = new JPanel(new GridLayout(3, 3)); // FIXME: on other classes, this needs to be able to hold amount of attributes
        //CommitteeMemberEditPanel.setLayout(new BoxLayout(CommitteeMemberEditPanel, BoxLayout.PAGE_AXIS));
        CommitteeMemberEditFrame.setSize(750, 750);
        CommitteeMemberEditFrame.setLocationRelativeTo(null);
        CommitteeMemberEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CommitteeMemberEditFrame.setTitle("CommitteeMember");

        // Member ID TextField
        /*JTextField memberIdTextField = new JTextField();
        //nameTextField.setName("Name:");
        memberIdTextField.setPreferredSize(textFieldDimension);
        memberIdTextField.addActionListener(e -> {
            memberId = Integer.parseInt(e.getActionCommand());
        });
        JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel firstNameLabel = new JLabel("Member ID:"); // Create a new JLabel
        firstNamePanel.add(firstNameLabel); // Add the JLabel to the JPanel
        firstNamePanel.add(memberIdTextField); // Add the JTextField to the JPanel
        CommitteeMemberEditPanel.add(firstNamePanel);*/
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
        CommitteeMemberEditPanel.add(memberPanel);

        // Committee ID TextField
        /*JTextField committeeIdTextField = new JTextField();
        //nameTextField.setName("Name:");
        committeeIdTextField.setPreferredSize(textFieldDimension);
        committeeIdTextField.addActionListener(e -> {
            committeeId = Integer.parseInt(e.getActionCommand());
        });
        JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel lastNameLabel = new JLabel("Committee ID:"); // Create a new JLabel
        lastNamePanel.add(lastNameLabel); // Add the JLabel to the JPanel
        lastNamePanel.add(committeeIdTextField); // Add the JTextField to the JPanel
        CommitteeMemberEditPanel.add(lastNamePanel);*/
        JPanel committeePanel = new JPanel();
        JLabel committeeLabel = new JLabel("Committee");
        committeePanel.add(committeeLabel);
        java.util.List<Committee> committeeList = CommitteeDAOImpl.getAllCommittees();
        String[] committeeString = new String[committeeList.size()];
        for(int i = 0; i < committeeList.size(); i++) {
            committeeString[i] = committeeList.get(i).getCommitteeId() + " " + committeeList.get(i).getName();
        }

        JComboBox committeeComboBox = new JComboBox(committeeString);
        committeePanel.add(committeeComboBox);
        CommitteeMemberEditPanel.add(committeePanel);


        JButton addButton = new JButton("Add");
        JPanel addPanel = new JPanel();
        addButton.addActionListener(e -> {
            Scanner scn = new Scanner((String)memberComboBox.getSelectedItem());
            memberId = scn.nextInt();
            scn = new Scanner((String)committeeComboBox.getSelectedItem());
            committeeId = scn.nextInt();

            CommitteeMember committeeMember = new CommitteeMember();
            committeeMember.setMemberId(memberId);
            committeeMember.setCommitteeId(committeeId);

            CommitteeMemberDAOImpl.addCommitteeMember(committeeMember);
            CommitteeMemberEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        CommitteeMemberEditPanel.add(addPanel);

        CommitteeMemberEditFrame.add(CommitteeMemberEditPanel);
        CommitteeMemberEditFrame.setVisible(true);
    }

    public void refreshTable() {
        committeeMemberList = CommitteeMemberDAOImpl.getAllCommitteeMembers();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(CommitteeMember CommitteeMember : committeeMemberList) {
            data.add(CommitteeMember.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Member ID");
        columnNames.add("Committee ID");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}

