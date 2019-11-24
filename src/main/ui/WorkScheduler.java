package ui;

import exceptions.ArraySizeException;
import exceptions.NegativeInputException;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class WorkScheduler implements Saveable, Loadable {
    private Job job;
    private EmployeeManager employeeManager = new EmployeeManager();

    public void setJob(Job job) {
        this.job = job;
    }

    public ArrayList<Employee> getEmployees() {
        return employeeManager.getEmployees();
    }

    public void setEmployees(ArrayList<Employee> employees) {
        employeeManager.setEmployees(employees);
    }

    //MODIFIES: TrainingEmployee, EmployeeManager
    //EFFECTS: Takes a list of employee fields and calls scheduleEmployee  on a TrainingEmployee with it.
    //Adds employee to EmployeeManager and returns true if there is an employee in the same shift with at least 6
    //experience to train the trainee.
    public boolean scheduleTrainee(ArrayList<String> inputFields) throws NegativeInputException {
        TrainingEmployee te = new TrainingEmployee();
        try {
            te.scheduleEmployee(inputFields);
        } catch (ArraySizeException e) {
            System.out.println("Size of array too long.");
        }
        return addTrainingEmployee(te);
    }

    //MODIFIES: RegularEmployee, EmployeeManager
    //EFFECTS: Takes a list of employee fields ans calls scheduleEmployee on a RegularEmployee with it.
    //Adds employee to EmployeeManager and returns true if the employee has enough experience for the job
    //set to this WorkScheduler.
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


    // MODIFIES: EmployeeManager, Job
    // EFFECTS: Adds an employee to ArrayList employees in EmployeeManager if experience requirements met,
    //sets job field of employee to job set for this WorkScheduler
    public boolean addEmployee(Employee e, Job job) throws NegativeInputException {
        return employeeManager.addEmployee(e,job);
    }

    // MODIFIES: EmployeeManager
    // EFFECTS: Checks if there is a regular employee with at least 6 experience scheduled at the same time as a
    //          training employee, if true then add the trainee to the schedule return true. Set job field of
    //          employee as Trainee.
    public boolean addTrainingEmployee(TrainingEmployee te) {
        return employeeManager.addTrainingEmployee(te);
    }

    //EFFECTS: saves employee data from a list to outputfile.txt 
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
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
