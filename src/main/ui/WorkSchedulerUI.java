package ui;

import exceptions.NegativeInputException;
import model.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WorkSchedulerUI extends JFrame {
    private WorkScheduler workScheduler = new WorkScheduler();

    private JPanel mainPanel = new JPanel(new FlowLayout());
    private JPanel menuPanel = new JPanel(new GridLayout(1,1));
    private JPanel jobPanel = new JPanel(new GridLayout(1,2));

    private JMenuBar menuBar = new JMenuBar();
    private JButton scheduleRegularButton = new JButton("Schedule a regular");
    private JButton scheduleTraineeButton = new JButton("Schedule a trainee");
    private JButton printScheduleButton = new JButton("Print next week's schedule");
    private String[] days = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
    private String[] shifts = {"day", "night", "graveyard"};
    private Job headChef = new Job("Head Chef", 10);
    private Job chef = new Job("Chef", 6);
    private Job lineCook = new Job("Line Cook", 3);
    private Job newCook = new Job("New Cook", 1);
    private Job[] jobs = {headChef,chef,lineCook,newCook};
    private JComboBox cbDays = new JComboBox(days);
    private JComboBox cbShifts = new JComboBox(shifts);
    private JComboBox cbJobs = new JComboBox(jobs);
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private JTextField nameInput = new JTextField("Name", 10);
    private JTextField expInput = new JTextField("Exp", 3);
    private JTextArea outputText = new JTextArea(20,30);
    private JLabel jobSelection = new JLabel("Select job to fill");

    private RegularButtonListener regularButtonListener = new RegularButtonListener();
    private TraineeButtonListener traineeButtonListener = new TraineeButtonListener();
    private PrintButtonListener printButtonListener = new PrintButtonListener();
    private SaveListener saveItemListener = new SaveListener();
    private LoadListener loadItemListener = new LoadListener();
    private JobChangeListener jobChangeListener = new JobChangeListener();

    private WorkSchedulerUI() throws NegativeInputException {
        super("Work Scheduler");
        super.setResizable(false);
        outputText.setEditable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        setListeners();
        setMainPanel();
        outputText.append(constructJobString(jobs[0]));
        this.setVisible(true);
    }

    private String constructJobString(Job job) {
        return "Scheduling for " + job +  ", requires " + job.getDifficulty() + " experience.\n";
    }

    private void setListeners() {
        scheduleRegularButton.addActionListener(regularButtonListener);
        scheduleTraineeButton.addActionListener(traineeButtonListener);
        printScheduleButton.addActionListener(printButtonListener);
        save.addActionListener(saveItemListener);
        load.addActionListener(loadItemListener);
        cbJobs.addItemListener(jobChangeListener);
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

        jobPanel.add(jobSelection);
        jobPanel.add(cbJobs);
        mainPanel.add(jobPanel);

        mainPanel.add(outputText);
        mainPanel.add(printScheduleButton);


        setContentPane(mainPanel);
    }


    public static void main(String[] args) throws NegativeInputException {
        new WorkSchedulerUI();
    }

    private class RegularButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class TraineeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PrintButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class JobChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Job item = (Job) event.getItem();
                outputText.append(constructJobString(item));
            }
        }
    }
}
