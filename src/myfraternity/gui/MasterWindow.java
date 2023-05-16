package org.myfraternity.gui;

import javax.swing.*;
import java.awt.*;

public class MasterWindow {
    private final JFrame frame;
    private final JPanel managePanel;
    private final Dimension windowSize;
    public MasterWindow() {
        windowSize = new Dimension(750, 750);
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(windowSize);
        frame.setResizable(false);
        frame.setTitle("Fraternity Database");
        managePanel = new JPanel();
        managePanel.setPreferredSize(new Dimension(frame.getWidth() / 2 + 50, 300));
        managePanel.setLayout(new GridLayout(4, 2));


        JPanel topLabelPanel = new JPanel();
        topLabelPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, 25));
        JLabel topLabel = new JLabel("Select to manage");
        topLabelPanel.add(topLabel);

        JPanel bottomLabelPanel = new JPanel();
        bottomLabelPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, 25));
        JLabel bottomLabel = new JLabel("Select to view");
        bottomLabelPanel.add(bottomLabel);

        // Member JButton
        JButton memberButton = new JButton("Members");
        memberButton.addActionListener(e -> {
            new MemberWindow();
        });
        managePanel.add(memberButton);

        // Officer JButton
        JButton officerButton = new JButton("Officers");
        officerButton.addActionListener(e -> {
            new OfficerWindow();
        });
        managePanel.add(officerButton);

        // Committee JButton
        JButton committeeButton = new JButton("Committees");
        committeeButton.addActionListener(e -> {
            new CommitteeWindow();
        });
        managePanel.add(committeeButton);

        // CommitteeMember JButton
        JButton committeeMemberButton = new JButton("Assign Committee Members");
        committeeMemberButton.addActionListener(e -> {
            new CommitteeMemberWindow();
        });
        managePanel.add(committeeMemberButton);

        // Chapter JButton
        JButton chapterButton = new JButton("Chapters");
        chapterButton.addActionListener(e -> {
            new ChapterWindow();
        });
        managePanel.add(chapterButton);

        // Attendance JButton
        JButton attendanceButton = new JButton("Record attendance");
        attendanceButton.addActionListener(e -> {
            new AttendanceWindow();
        });
        managePanel.add(attendanceButton);

        // Payment JButton
        JButton paymentButton = new JButton("Payments");
        paymentButton.addActionListener(e -> {
            new PaymentWindow();
        });
        managePanel.add(paymentButton);

        // Event JButton
        JButton eventButton = new JButton("Events");
        eventButton.addActionListener(e -> {
            new EventWindow();
        });
        managePanel.add(eventButton);


        // Overdue Button
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(3, 2));
        viewPanel.setPreferredSize(new Dimension(frame.getWidth() / 2 + 50, 200));
        JButton overdueButton = new JButton("Overdue Members");
        overdueButton.addActionListener(e -> {
            new OverdueWindow();
        });
        viewPanel.add(overdueButton);

        // Events By Chapter Button
        JButton eventsByChapterButton = new JButton("Events By Chapter");
        eventsByChapterButton.addActionListener(e -> {
            new EventsByChapterWindow();
        });
        viewPanel.add(eventsByChapterButton);

        // Members Absent Button
        JButton membersAbsentButton = new JButton("Members Absent");
        membersAbsentButton.addActionListener(e -> {
            new AbsentWindow();
        });
        viewPanel.add(membersAbsentButton);

        // Members on Committees Button
        JButton membersOnCommitteesButton = new JButton("Members on Committees");
        membersOnCommitteesButton.addActionListener(e -> {
            new MemberOnCommitteesWindow();
        });
        viewPanel.add(membersOnCommitteesButton);

        // Current Members Button
        JButton currentOfficersButton = new JButton("Current Officers");
        currentOfficersButton.addActionListener(e -> {
            new CurrentOfficerWindow();
        });
        viewPanel.add(currentOfficersButton);

        frame.add(topLabelPanel);
        frame.add(managePanel);
        JPanel paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        frame.add(paddingPanel);
        frame.add(bottomLabelPanel);
        frame.add(viewPanel);
        frame.setVisible(true);
    }
}
