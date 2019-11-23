package ui;

import exceptions.ArraySizeException;
import exceptions.NegativeInputException;
import model.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class WorkScheduler implements Saveable, Loadable {
    private Scanner scanner;
    private Job job;
    private PrintWriter writer;
    private EmployeeManager employeeManager = new EmployeeManager();
    private ArrayList<String> days = new ArrayList<>(Arrays.asList("sun", "mon", "tue", "wed", "thu", "fri", "sat"));
    private ArrayList<String> shifts = new ArrayList<>(Arrays.asList("day", "night", "graveyard"));

    //MODIFIES:this
    // EFFECTS: initializes fields employees and scanner, calls the makeSchedule method to being creating a schedule
    public WorkScheduler() {
        scanner = new Scanner(System.in);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ArrayList<Employee> getEmployees() {
        return employeeManager.getEmployees();
    }

    public void setEmployees(ArrayList<Employee> employees) {
        employeeManager.setEmployees(employees);
    }

    public boolean scheduleTrainee(ArrayList<String> inputFields) throws NegativeInputException {
        TrainingEmployee te = new TrainingEmployee();
        try {
            te.scheduleEmployee(inputFields);
        } catch (ArraySizeException e) {
            System.out.println("Size of array too long.");
        }
        return addTrainingEmployee(te);
    }

    public boolean scheduleRegular(ArrayList<String> inputFields) throws NegativeInputException {
        boolean isSuccess = false;
        RegularEmployee re = new RegularEmployee();
        try {
            re.scheduleEmployee(inputFields);
        } catch (ArraySizeException e) {
            System.out.println("Size of array too long.");
        }

        isSuccess = addEmployee(re, job);

        return isSuccess;
    }


    // MODIFIES: this. Employee
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements returns true,
    //          else tells user that employee does not meet requirements returns false
    public boolean addEmployee(Employee e, Job job) throws NegativeInputException {
        return employeeManager.addEmployee(e,job);
    }

    // MODIFIES: this, TrainingEmployee
    // EFFECTS: checks if there is a regular employee with at least 6 experience scheduled at the same time as a
    //          training employee, if true then add the trainee to the schedule return true, else return false
    public boolean addTrainingEmployee(TrainingEmployee te) {
        return employeeManager.addTrainingEmployee(te);
    }

    //EFFECTS: saves employee data from a list to outputfile.txt 
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("outputfile.txt", "UTF-8");
        for (Employee e : getEmployees()) {
            Job job = e.getJob();
            writer.println(e.getName() + " " + e.getDayWorking() + " " + e.getShift() + " "
                    + e.getExperience() + " " + job + " " + job.getDifficulty());
        }
        writer.close();
    }

    //EFFECTS: loads information from a outputfile.txt and returns a list of employees created from that information
    public ArrayList<Employee> load() throws IOException, NegativeInputException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        for (String line : lines) {
            Employee e = new RegularEmployee();
            ArrayList<String> partsOfLine = splitOnSpace(line);
            e.setName(partsOfLine.get(0));
            e.setDayWorking(partsOfLine.get(1));
            e.setShift(partsOfLine.get(2));
            e.setExperience(Integer.parseInt(partsOfLine.get(3)));
            Job job = new Job(partsOfLine.get(4),Integer.parseInt(partsOfLine.get(5)));
            e.setJob(job);
            employees.add(e);
        }
        employeeManager.setEmployees(employees);
        return employees;
    }

    //EFFECTS: splits input string into its parts and puts it into an arraylist which is returned
    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
