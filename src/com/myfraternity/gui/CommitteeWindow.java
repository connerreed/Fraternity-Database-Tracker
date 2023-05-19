package com.myfraternity.gui;

import com.myfraternity.dao.ChapterDAOImpl;
import com.myfraternity.dao.CommitteeDAOImpl;
import com.myfraternity.entity.Chapter;
import com.myfraternity.entity.Committee;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class CommitteeWindow {

    private boolean addEditFrameOpen = false;
    private String name;
    private String description;
    private int chapterId;
    private java.util.List<Committee> committeeList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel committeeViewPanel;
    private final JFrame committeeViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public CommitteeWindow() {
        table = null;
        committeeViewFrame = new JFrame("CommitteeView");
        committeeViewFrame.setSize(750, 750);
        committeeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        committeeViewFrame.setLocationRelativeTo(null);
        committeeViewPanel = new JPanel();

        // Add Committee Button
        JButton addCommitteeButton = new JButton("Add");
        addCommitteeButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        committeeViewPanel.add(addCommitteeButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Committee Committee = new Committee();
            Committee.setCommitteeId((Integer)rowData.get(0));
            CommitteeDAOImpl.deleteCommittee(Committee);
            refreshTable();
        });
        committeeViewPanel.add(deleteButton);

        // Add JTable
        committeeList = CommitteeDAOImpl.getAllCommittees();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Committee Committee : committeeList) {
            data.add(Committee.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Committee ID");
        columnNames.add("Name");
        columnNames.add("Description");
        columnNames.add("Chapter ID");
        if(table != null) { // table is filled
            //CommitteeViewPanel.removeAll();
            committeeViewPanel.invalidate();
            committeeViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //CommitteeViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        committeeViewPanel.add(jScrollPane);
        //CommitteeViewPanel.add(jTable);



        committeeViewFrame.add(committeeViewPanel);
        committeeViewFrame.setVisible(true);
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
                    Committee committee = new Committee();
                    committee.setCommitteeId((Integer)rowData.get(0));
                    committee.setName((String)rowData.get(1));
                    committee.setDescription((String)rowData.get(2));
                    committee.setChapterId((Integer)rowData.get(3));

                    CommitteeDAOImpl.updateCommittee(committee);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame CommitteeEditFrame = new JFrame();
        JPanel CommitteeEditPanel = new JPanel(new GridLayout(2, 2));
        //CommitteeEditPanel.setLayout(new BoxLayout(CommitteeEditPanel, BoxLayout.PAGE_AXIS));
        CommitteeEditFrame.setSize(750, 750);
        CommitteeEditFrame.setLocationRelativeTo(null);
        CommitteeEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CommitteeEditFrame.setTitle("Committee");

        // Name TextField
        JTextField nameTextField = new JTextField();
        //nameTextField.setName("Name:");
        nameTextField.setPreferredSize(textFieldDimension);
        nameTextField.addActionListener(e -> {
            name = e.getActionCommand();
        });
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel nameLabel = new JLabel("Name:"); // Create a new JLabel
        namePanel.add(nameLabel); // Add the JLabel to the JPanel
        namePanel.add(nameTextField); // Add the JTextField to the JPanel
        CommitteeEditPanel.add(namePanel);


        // Description TextField
        JTextField descriptionTextField = new JTextField();
        //nameTextField.setName("Name:");
        descriptionTextField.setPreferredSize(textFieldDimension);
        descriptionTextField.addActionListener(e -> {
            description = e.getActionCommand();
        });
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel descriptionLabel = new JLabel("Description:"); // Create a new JLabel
        descriptionPanel.add(descriptionLabel); // Add the JLabel to the JPanel
        descriptionPanel.add(descriptionTextField); // Add the JTextField to the JPanel
        CommitteeEditPanel.add(descriptionPanel);

        // Chapter ID TextField
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
        CommitteeEditPanel.add(chapterIdPanel);*/
        JPanel chapterPanel = new JPanel();
        JLabel chapterLabel = new JLabel("Chapter");
        chapterPanel.add(chapterLabel);
        java.util.List<Chapter> chapterList = ChapterDAOImpl.getAllChapters();
        String[] chapterString = new String[chapterList.size()];
        for(int i = 0; i < chapterList.size(); i++) {
            chapterString[i] = chapterList.get(i).getChapter_id() + " " + chapterList.get(i).getName();
        }

        JComboBox<String> chapterComboBox = new JComboBox<>(chapterString);
        chapterPanel.add(chapterComboBox);
        CommitteeEditPanel.add(chapterPanel);


        JButton addButton = new JButton("Add");
        JPanel addPanel = new JPanel();
        addButton.addActionListener(e -> {
            name = nameTextField.getText();
            description = descriptionTextField.getText();
            Scanner scn = new Scanner((String)chapterComboBox.getSelectedItem());
            chapterId = scn.nextInt();
            scn.close();

            Committee committee = new Committee();
            committee.setName(name);
            committee.setDescription(description);
            committee.setChapterId(chapterId);

            CommitteeDAOImpl.addCommittee(committee);
            CommitteeEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);

        CommitteeEditPanel.add(addPanel);

        CommitteeEditFrame.add(CommitteeEditPanel);
        CommitteeEditFrame.setVisible(true);
    }

    public void refreshTable() {
        committeeList = CommitteeDAOImpl.getAllCommittees();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Committee Committee : committeeList) {
            data.add(Committee.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Committee ID");
        columnNames.add("Name");
        columnNames.add("Description");
        columnNames.add("Chapter ID");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}

