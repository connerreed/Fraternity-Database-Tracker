package com.myfraternity.gui;

import com.myfraternity.dao.ChapterDAOImpl;
import com.myfraternity.dao.EventDAOImpl;
import com.myfraternity.entity.Chapter;
import com.myfraternity.entity.Event;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class EventWindow {

    private boolean addEditFrameOpen = false;
    private String name;
    private String location;
    private java.sql.Date date;
    private String description;
    private int chapterId;
    private java.util.List<Event> eventList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel eventViewPanel;
    private final JFrame eventViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public EventWindow() {
        table = null;
        eventViewFrame = new JFrame("EventView");
        eventViewFrame.setSize(750, 750);
        eventViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventViewFrame.setLocationRelativeTo(null);
        eventViewPanel = new JPanel();

        // Add Event Button
        JButton addEventButton = new JButton("Add");
        addEventButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        eventViewPanel.add(addEventButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Event Event = new Event();
            Event.setEvent_id((Integer)rowData.get(0));
            EventDAOImpl.deleteEvent(Event);
            refreshTable();
        });
        eventViewPanel.add(deleteButton);

        // Add JTable
        eventList = EventDAOImpl.getAllEvents();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Event Event : eventList) {
            data.add(Event.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Event ID");
        columnNames.add("Name");
        columnNames.add("Date");
        columnNames.add("Location");
        columnNames.add("Description");
        columnNames.add("Chapter ID");
        if(table != null) { // table is filled
            //EventViewPanel.removeAll();
            eventViewPanel.invalidate();
            eventViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //EventViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        eventViewPanel.add(jScrollPane);
        //EventViewPanel.add(jTable);



        eventViewFrame.add(eventViewPanel);
        eventViewFrame.setVisible(true);
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
                    Event Event = new Event();
                    Event.setEvent_id((Integer)rowData.get(0));
                    Event.setName((String)rowData.get(1));
                    Event.setDate(Date.valueOf(rowData.get(2).toString()));
                    Event.setLocation((String)rowData.get(3));
                    Event.setDescription((String)rowData.get(4));
                    Event.setChapter_id((Integer)rowData.get(5));
                    EventDAOImpl.updateEvent(Event);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame EventEditFrame = new JFrame();
        JPanel EventEditPanel = new JPanel(new GridLayout(3, 2));
        //EventEditPanel.setLayout(new BoxLayout(EventEditPanel, BoxLayout.PAGE_AXIS));
        EventEditFrame.setSize(750, 750);
        EventEditFrame.setLocationRelativeTo(null);
        EventEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        EventEditFrame.setTitle("Event");

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
        EventEditPanel.add(namePanel);

        // Loction TextField
        JTextField locationTextField = new JTextField();
        locationTextField.setPreferredSize(textFieldDimension);
        locationTextField.addActionListener(e -> {
            location = e.getActionCommand();
        });
        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel updateLabel = new JLabel("Location:"); // Create a new JLabel
        updatePanel.add(updateLabel); // Add the JLabel to the JPanel
        updatePanel.add(locationTextField); // Add the JTextField to the JPanel
        EventEditPanel.add(updatePanel);


        JTextField dateTextField = new JTextField();
        dateTextField.setPreferredSize(textFieldDimension);
        dateTextField.addActionListener(e -> {
            date = Date.valueOf(e.getActionCommand());
        });
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel dateLabel = new JLabel("Date:"); // Create a new JLabel
        datePanel.add(dateLabel); // Add the JLabel to the JPanel
        datePanel.add(dateTextField); // Add the JTextField to the JPanel
        EventEditPanel.add(datePanel);


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
        EventEditPanel.add(descriptionPanel);

        /*// Chapter ID TextField
        JTextField chapterIdTextField = new JTextField();
        chapterIdTextField.setPreferredSize(textFieldDimension);
        chapterIdTextField.addActionListener(e -> {
            chapterId = Integer.parseInt(e.getActionCommand());
        });
        JPanel chapterIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel chapterIdLabel = new JLabel("Chapter ID:"); // Create a new JLabel
        chapterIdPanel.add(chapterIdLabel); // Add the JLabel to the JPanel
        chapterIdPanel.add(chapterIdTextField); // Add the JTextField to the JPanel
        EventEditPanel.add(chapterIdPanel);*/
        JPanel chapterPanel = new JPanel();
        chapterPanel.add(new JLabel("Chapter"));
        java.util.List<Chapter> chapterList = ChapterDAOImpl.getAllChapters();
        String[] strings = new String[chapterList.size()];
        for(int i = 0; i < chapterList.size(); i++) {
            strings[i] = chapterList.get(i).getChapter_id() + " " + chapterList.get(i).getName();
        }

        JComboBox<String> chapterComboBox = new JComboBox<>(strings);
        chapterPanel.add(chapterComboBox);
        EventEditPanel.add(chapterPanel);

        // Add Button
        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            name = nameTextField.getText();
            location = locationTextField.getText();
            date = Date.valueOf(dateTextField.getText());
            description = descriptionTextField.getText();
            Scanner scn = new Scanner((String)chapterComboBox.getSelectedItem());
            chapterId = scn.nextInt();
            scn.close();

            Event Event = new Event();
            Event.setName(name);
            Event.setDate(date);
            Event.setLocation(location);
            Event.setDescription(description);
            Event.setChapter_id(chapterId);

            EventDAOImpl.addEvent(Event);
            EventEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        EventEditPanel.add(addPanel);

        EventEditFrame.add(EventEditPanel);
        EventEditFrame.setVisible(true);
    }

    public void refreshTable() {
        eventList = EventDAOImpl.getAllEvents();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Event Event : eventList) {
            data.add(Event.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Event ID");
        columnNames.add("Name");
        columnNames.add("Date");
        columnNames.add("Location");
        columnNames.add("Description");
        columnNames.add("Chapter ID");
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}
