package ui;

import exceptions.NegativeInputException;
import model.Employee;
import model.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private Job headChef = new Job("Head Nurse", 10);
    private Job chef = new Job("Senior Nurse", 6);
    private Job lineCook = new Job("Nurse", 3);
    private Job newCook = new Job("New Nurse", 1);
    private Job[] jobs = {headChef,chef,lineCook,newCook};

    private JComboBox cbDays = new JComboBox(days);
    private JComboBox cbShifts = new JComboBox(shifts);
    private JComboBox cbJobs = new JComboBox(jobs);
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private JMenuItem clearAll = new JMenuItem("Clear All");
    private JTextField nameInput = new JTextField("Name", 10);
    private JTextField expInput = new JTextField("Exp", 3);
    private JTextArea outputText = new JTextArea(20,30);
    private JLabel jobSelection = new JLabel("Select job to fill");

    private RegularButtonListener regularButtonListener = new RegularButtonListener();
    private TraineeButtonListener traineeButtonListener = new TraineeButtonListener();
    private PrintButtonListener printButtonListener = new PrintButtonListener();
    private SaveListener saveItemListener = new SaveListener();
    private LoadListener loadItemListener = new LoadListener();
    private ClearAllListener clearAllItemListener = new ClearAllListener();
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
        workScheduler.setJob(jobs[0]);
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
        clearAll.addActionListener(clearAllItemListener);

        cbJobs.addItemListener(jobChangeListener);
    }

    private void setMainPanel() {
        file.add(save);
        file.add(load);
        file.add(clearAll);
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

    public ArrayList<String> getFieldInputs() {
        String name = nameInput.getText();
        String dayWorking = (String) cbDays.getSelectedItem();
        String shift = (String) cbShifts.getSelectedItem();
        String experience = expInput.getText();
        return new ArrayList<>(Arrays.asList(name,dayWorking,shift,experience));
    }


    public static void main(String[] args) throws NegativeInputException {
        new WorkSchedulerUI();
    }

    private class RegularButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> fieldInputs = getFieldInputs();
            try {
                if (!workScheduler.scheduleRegular(fieldInputs)) {
                    outputText.append(fieldInputs.get(0) + " does not have enough experience.\n");
                } else {
                    outputText.append(fieldInputs.get(0) + " was successfully added.\n");
                }
            } catch (NumberFormatException | NegativeInputException exception) {
                outputText.append("Experience must be a positive integer.\n");
            }
        }
    }

    private class TraineeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> fieldInputs = getFieldInputs();
            try {
                if (!workScheduler.scheduleTrainee(fieldInputs)) {
                    outputText.append("There is no one with 6 experience to train " + fieldInputs.get(0) + ".\n");
                } else {
                    outputText.append(fieldInputs.get(0) + " was successfully added.\n");
                }
            } catch (NumberFormatException exception) {
                outputText.append("Experience must be a positive integer.\n");
            }
        }
    }

    private class PrintButtonListener implements ActionListener {
        JLabel sun = new JLabel("Sunday");
        JLabel mon = new JLabel("Monday");
        JLabel tue = new JLabel("Tuesday");
        JLabel wed = new JLabel("Wednesday");
        JLabel thu = new JLabel("Thursday");
        JLabel fri = new JLabel("Friday");
        JLabel sat = new JLabel("Saturday");
        JLabel mt = new JLabel("");
        ArrayList<JLabel> labels = new ArrayList<JLabel>(Arrays.asList(sun,mon,tue,wed,thu,fri,sat,mt));
        JPanel schedule = new JPanel(new GridLayout(5,1,3,3));
        JPanel days = new JPanel(new GridLayout(1,8,3,3));
        JPanel dayShift = new JPanel(new GridLayout(1,8,3,3));
        ArrayList<JTextArea> dayShiftTextAreas = new ArrayList<>();
        JPanel nightShift = new JPanel(new GridLayout(1,8,3,3));
        ArrayList<JTextArea> nightShiftTextAreas = new ArrayList<>();
        JPanel graveyardShift = new JPanel(new GridLayout(1,8,3,3));
        ArrayList<JTextArea> graveyardShiftTextAreas = new ArrayList<>();
        JLabel day = new JLabel("Day");
        JLabel night = new JLabel("Night");
        JLabel graveyard = new JLabel("Graveyard");

        PrintButtonListener() {
            for (int i = 0; i <= 6; i++) {
                dayShiftTextAreas.add(new JTextArea(10,10));
                nightShiftTextAreas.add(new JTextArea(10,10));
                graveyardShiftTextAreas.add(new JTextArea(10,10));
            }
            for (JLabel label : labels) {
                days.add(label);
            }
            for (int i = 0; i <= 6; i++) {
                dayShiftTextAreas.get(i).setEditable(false);
                nightShiftTextAreas.get(i).setEditable(false);
                graveyardShiftTextAreas.get(i).setEditable(false);
                dayShift.add(dayShiftTextAreas.get(i));
                nightShift.add(nightShiftTextAreas.get(i));
                graveyardShift.add(graveyardShiftTextAreas.get(i));
            }
            dayShift.add(day);
            nightShift.add(night);
            graveyardShift.add(graveyard);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame popUp = new JFrame("Print");

            schedule.add(days);
            schedule.add(dayShift);
            schedule.add(nightShift);
            schedule.add(graveyardShift);
            popUp.add(schedule);

            popUp.setSize(1200,800);
            popUp.setResizable(false);
            popUp.setVisible(true);
        }
    }

    private class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                workScheduler.save();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                workScheduler.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ClearAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            workScheduler.setEmployees(new ArrayList<Employee>());
        }
    }

    private class JobChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Job item = (Job) event.getItem();
                outputText.append(constructJobString(item));
                workScheduler.setJob(item);
            }
        }
    }
}
