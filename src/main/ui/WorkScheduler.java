package ui;

import model.Employee;
import model.TrainingEmployee;
import model.RegularEmployee;
import model.Job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WorkScheduler implements Saveable, Loadable {
    private ArrayList<Employee> employees;
    private Scanner scanner;
    private Job job = new Job("driver", 3);
    PrintWriter writer;

    //MODIFIES:this
    // EFFECTS: initializes fields employees and scanner, calls the makeSchedule method to being creating a schedule
    public WorkScheduler() {
        employees = new ArrayList<Employee>();
        scanner = new Scanner(System.in);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // MODIFIES: this, Employee
    // EFFECTS: makes a weekly (Sun to Sat) schedule by giving employees certain days and shifts with user input
    public void makeSchedule() throws IOException {
        operationHelper();

        System.out.println("This is the schedule for " + job.getJobName() + "s next week:");
        printEmployees(employees);
        save(employees);
    }

    //EFFECTS: asks for user input, creates an employee using it, then adds it to list of employees
    private void operationHelper() throws IOException {
        String operation;
        while (true) {
            System.out.println("Please input an option (add reg. employee (are), add trainee (at), load, or quit):");
            operation = scanner.nextLine();

            if (operation.equals("quit")) {
                System.out.println("Quitting and printing a work schedule.");
                break;
            } else if (operation.equals("load")) {
                employees = load();
                System.out.println("Loading employees from previous session.");
            } else if (operation.equals("at")) {
                operationAtHelper();
            } else if (operation.equals("are")) {
                operationAreHelper();
            } else {
                System.out.println(operation + " is not a valid choice.");
            }
        }
    }

    private void operationAtHelper() {
        TrainingEmployee te = new TrainingEmployee();
        System.out.println("Scheduling trainee for " + job.getJobName());
        te.scheduleEmployee(te.userInputFields());
        addTrainingEmployee(te);
    }

    private void operationAreHelper() {
        RegularEmployee employee = new RegularEmployee();
        System.out.println("Scheduling employee for " + job.getJobName());
        employee.scheduleEmployee(employee.userInputFields());
        addEmployee(employee);
    }


    // MODIFIES: this
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements returns true,
    //          else tells user that employee does not meet requirements returns false
    public boolean addEmployee(Employee e) {
        if (job.isCompetent(e.getExperience())) {
            employees.add(e);
            e.confirmDayAndShift();
            return true;
        } else {
            System.out.println(e.getName() + " does not have at least " + job.getDifficulty() + " experience.");
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if there is a regular employee with at least 5 experience scheduled at the same time as a
    //          training employee, if true then add the trainee to the schedule return true, else return false
    public boolean addTrainingEmployee(TrainingEmployee te) {
        for (Employee e : employees) {
            if (e.getDayWorking().equals(te.getDayWorking())
                    & e.getShift().equals(te.getShift()) & te.isSuitableTrainer(e)) {
                te.confirmDayAndShift();
                employees.add(te);
                return true;
            }
        }
        System.out.println("There is no employee with min. 5 experience to train " + te.getName() + " at that time.");
        return false;
    }


    //EFFECTS: has each employee announce their name, day working, and shift
    public void printEmployees(ArrayList<Employee> employees) {
        for (Employee e : employees) {
            e.confirmDayAndShift();
        }
    }

    //EFFECTS: saves employee data from a list to outputfile.txt 
    public void save(ArrayList<Employee> employees) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("outputfile.txt", "UTF-8");
        for (Employee e : employees) {
            writer.println(e.getName() + " " + e.getDayWorking() + " " + e.getShift() + " " + e.getExperience());
        }
        writer.close();
    }

    //EFFECTS: loads information from a outputfile.txt and returns a list of employees created from that information
    public ArrayList<Employee> load() throws IOException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        for (String line : lines) {
            Employee e = new RegularEmployee();
            ArrayList<String> partsOfLine = splitOnSpace(line);
            e.setName(partsOfLine.get(0));
            e.setDayWorking(partsOfLine.get(1));
            e.setShift(partsOfLine.get(2));
            e.setExperience(Integer.parseInt(partsOfLine.get(3)));
            employees.add(e);
        }

        return employees;
    }

    //EFFECTS: splits input string into its parts and puts it into an arraylist which is returned
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    //MODIFIES: this
    // EFFECTS: creates a new instance of WorkScheduler and calls makeSchedule to start the application
    public static void main(String[] args) throws IOException {
        WorkScheduler ws = new WorkScheduler();
        ws.makeSchedule();
    }
}
