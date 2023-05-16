package org.myfraternity.gui;

import org.myfraternity.dao.ChapterDAOImpl;
import org.myfraternity.entity.Chapter;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

public class ChapterWindow {

    private boolean addEditFrameOpen = false;
    private int chapterId;
    private String name;
    private String location;
    private java.sql.Date date;
    private java.util.List<Chapter> chapterList;
    private final Dimension textFieldDimension = new Dimension(200, 20);
    private JTable table;
    private final JPanel chapterViewPanel;
    private final JFrame chapterViewFrame;
    private JScrollPane jScrollPane;
    private final JButton deleteButton;
    public ChapterWindow() {
        table = null;
        chapterViewFrame = new JFrame("ChapterView");
        chapterViewFrame.setSize(750, 750);
        chapterViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chapterViewFrame.setLocationRelativeTo(null);
        chapterViewPanel = new JPanel();

        // Add Chapter Button
        JButton addChapterButton = new JButton("Add");
        addChapterButton.addActionListener(e -> {
            if(!addEditFrameOpen) {
                createAddEditFrame();
                addEditFrameOpen = true;
            }
        });
        chapterViewPanel.add(addChapterButton);

        // Add Delete JButton
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // Get the values of all the cells in the edited row
            java.util.List<Object> rowData = new ArrayList<>();
            for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                rowData.add(table.getModel().getValueAt(row, i));
            }
            Chapter chapter = new Chapter();
            chapter.setChapter_id((Integer)rowData.get(0));
            ChapterDAOImpl.deleteChapter(chapter);
            refreshTable();
        });
        chapterViewPanel.add(deleteButton);

        // Add JTable
        chapterList = ChapterDAOImpl.getAllChapters();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Chapter chapter : chapterList) {
            data.add(chapter.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Chapter ID");
        columnNames.add("Name");
        columnNames.add("Location");
        columnNames.add("Charter Date");
        if(table != null) { // table is filled
            //chapterViewPanel.removeAll();
            chapterViewPanel.invalidate();
            chapterViewPanel.revalidate();
            return;
        }
        table = new JTable(data, columnNames);
        //chapterViewPanel.add(table);
        addTableListener();

        // Add JScrollPane
        jScrollPane = new JScrollPane(table);
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        chapterViewPanel.add(jScrollPane);
        //chapterViewPanel.add(jTable);



        chapterViewFrame.add(chapterViewPanel);
        chapterViewFrame.setVisible(true);
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
                    Chapter chapter = new Chapter();
                    chapter.setChapter_id((Integer)rowData.get(0));
                    chapter.setName((String)rowData.get(1));
                    chapter.setLocation((String)rowData.get(2));
                    chapter.setCharter_date(Date.valueOf(rowData.get(3).toString()));
                    ChapterDAOImpl.updateChapter(chapter);
                }
            }
        });
    }

    public void createAddEditFrame() {
        JFrame chapterEditFrame = new JFrame();
        JPanel chapterEditPanel = new JPanel(new GridLayout(2, 2)); // FIXME: on other classes, this needs to be able to hold amount of attributes
        //chapterEditPanel.setLayout(new BoxLayout(chapterEditPanel, BoxLayout.PAGE_AXIS));
        chapterEditFrame.setSize(750, 750);
        chapterEditFrame.setLocationRelativeTo(null);
        chapterEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chapterEditFrame.setTitle("Chapter");

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
        chapterEditPanel.add(namePanel);



        JTextField locationTextField = new JTextField();
        locationTextField.setPreferredSize(textFieldDimension);
        locationTextField.addActionListener(e -> {
            location = e.getActionCommand();
        });
        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel updateLabel = new JLabel("Location:"); // Create a new JLabel
        updatePanel.add(updateLabel); // Add the JLabel to the JPanel
        updatePanel.add(locationTextField); // Add the JTextField to the JPanel
        chapterEditPanel.add(updatePanel);


        JTextField charterDateTextField = new JTextField();
        charterDateTextField.setPreferredSize(textFieldDimension);
        charterDateTextField.addActionListener(e -> {
            date = Date.valueOf(e.getActionCommand());
        });
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new JPanel with a FlowLayout
        JLabel dateLabel = new JLabel("Charter Date:"); // Create a new JLabel
        datePanel.add(dateLabel); // Add the JLabel to the JPanel
        datePanel.add(charterDateTextField); // Add the JTextField to the JPanel
        chapterEditPanel.add(datePanel);

        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addPanel.add(addButton);
        addButton.addActionListener(e -> {
            name = nameTextField.getText();
            location = locationTextField.getText();
            date = Date.valueOf(charterDateTextField.getText());
            Chapter chapter = new Chapter();
            chapter.setName(name);
            chapter.setLocation(location);
            chapter.setCharter_date(date);
            ChapterDAOImpl.addChapter(chapter);
            chapterEditFrame.dispose();
            addEditFrameOpen = false;
            refreshTable();
        });
        addPanel.add(addButton);
        chapterEditPanel.add(addPanel);





        chapterEditFrame.add(chapterEditPanel);
        chapterEditFrame.setVisible(true);
    }

    public void refreshTable() {
        chapterList = ChapterDAOImpl.getAllChapters();
        Vector<Vector<Object>> data = new Vector<>(); // 2d vector
        for(Chapter chapter : chapterList) {
            data.add(chapter.getVector());
        }
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Chapter ID");
        columnNames.add("Name");
        columnNames.add("Location");
        columnNames.add("Charter Date");
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table.setModel(model);
        jScrollPane.repaint();



    }
}
