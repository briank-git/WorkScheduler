package ui;

import model.Employee;
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
    private Job job = new Job("Head chef", 3);
    PrintWriter writer;

    //MODIFIES:this
    // EFFECTS: initializes fields employees and scanner, calls the makeSchedule method to being creating a schedule
    public WorkScheduler() {
        employees = new ArrayList<Employee>();
        scanner = new Scanner(System.in);
    }

    // MODIFIES: this, Employee
    // EFFECTS: makes a weekly (Sun to Sat) schedule by giving employees certain days and shifts with user input
    public void makeSchedule() throws IOException {
        operationHelper();

//        while (true) {
//            Employee employee = new Employee();
//            System.out.println("Please input an option (add employee, load, or quit):");
//            operation = scanner.nextLine();
//
//            if (operation.equals("quit")) {
//                System.out.println("Quitting and printing a work schedule.");
//                break;
//            } else if (operation.equals("load")) {
//                employees = load();
//                System.out.println("Loading employees from previous session.");
//            }
//
//            System.out.println("Scheduling employee for " + job.getJobName());
//            employee.scheduleEmployee(employee.userInputFields());
//            addEmployee(employee);
//        }

        System.out.println("This is the schedule for " + job.getJobName() + "s next week:");
        printEmployees(employees);
        save(employees);
    }

    private void operationHelper() throws IOException {
        String operation;
        while (true) {
            Employee employee = new Employee();
            System.out.println("Please input an option (add employee, load, or quit):");
            operation = scanner.nextLine();

            if (operation.equals("quit")) {
                System.out.println("Quitting and printing a work schedule.");
                break;
            } else if (operation.equals("load")) {
                employees = load();
                System.out.println("Loading employees from previous session.");
            }

            System.out.println("Scheduling employee for " + job.getJobName());
            employee.scheduleEmployee(employee.userInputFields());
            addEmployee(employee);
        }
    }


    // MODIFIES: this
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements, else
    //          tells user that employee does not meet requirements
    public void addEmployee(Employee e) {
        if (job.isCompetent(e.getExperience())) {
            employees.add(e);
            e.confirmDayAndShift();
        } else {
            System.out.println(e.getName() + " does not have at least " + job.getDifficulty() + " experience.");
        }
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
            Employee e = new Employee();
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
