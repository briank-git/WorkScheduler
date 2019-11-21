package ui;

import javax.swing.*;
import java.awt.*;

public class WorkSchedulerUI extends JFrame {
    private JPanel mainPanel = new JPanel(new FlowLayout());
    private JPanel menuPanel = new JPanel(new GridLayout(1,1));

    private JMenuBar menuBar = new JMenuBar();
    private JButton scheduleRegularButton = new JButton("Schedule a regular");
    private JButton scheduleTraineeButton = new JButton("Schedule a trainee");
    private JButton printScheduleButton = new JButton("Print next week's schedule");
    private String[] days = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
    private String[] shifts = {"day", "night", "graveyard"};
    private JComboBox cbDays = new JComboBox(days);
    private JComboBox cbShifts = new JComboBox(shifts);
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private JTextField nameInput = new JTextField("Name", 10);
    private JTextField expInput = new JTextField("Exp", 3);
    private JTextArea outputText = new JTextArea(20,30);

    private WorkSchedulerUI() {
        super("Work Scheduler");
        super.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        setMainPanel();
        this.setVisible(true);
    }

    private void setMainPanel() {
        file.add(save);
        file.add(load);
        menuBar.add(file);
        menuPanel.add(menuBar);

        mainPanel.add(menuPanel);
        mainPanel.add(scheduleRegularButton);
        mainPanel.add(scheduleTraineeButton);
        mainPanel.add(cbDays);
        mainPanel.add(cbShifts);
        mainPanel.add(nameInput);
        mainPanel.add(expInput);
        outputText.setEditable(false);
        mainPanel.add(outputText);
        mainPanel.add(printScheduleButton);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        new WorkSchedulerUI();
    }
}
