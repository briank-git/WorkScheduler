package ui;

import model.*;
import sun.invoke.empty.Empty;

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
    private Job job;
    PrintWriter writer;
    private ArrayList<String> days = new ArrayList<>(Arrays.asList("sun", "mon", "tue", "wed", "thu", "fri", "sat"));
    private ArrayList<String> shifts = new ArrayList<>(Arrays.asList("day", "night", "graveyard"));

    //MODIFIES:this
    // EFFECTS: initializes fields employees and scanner, calls the makeSchedule method to being creating a schedule
    public WorkScheduler() {
        employees = new ArrayList<Employee>();
        scanner = new Scanner(System.in);
        try {
            job = new Job("driver", 3);
        } catch (NegativeInputException ne) {
            System.out.println("Difficulty is negative.");
        }
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
        try {
            te.scheduleEmployee(userInputFieldsTrainingEmployee(te));
        } catch (ArraySizeException e) {
            System.out.println("Size of array too long.");
        }
        addTrainingEmployee(te);
    }

    private void operationAreHelper() {
        RegularEmployee re = new RegularEmployee();
        System.out.println("Scheduling employee for " + job.getJobName());
        try {
            re.scheduleEmployee(userInputFieldsRegEmployee(re));
        } catch (ArraySizeException e) {
            System.out.println("Size of array too long.");
        }
        try {
            addEmployee(re);
        } catch (NegativeInputException ne) {
            System.out.println("Experience is negative");
        }
    }


    // MODIFIES: this
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements returns true,
    //          else tells user that employee does not meet requirements returns false
    public boolean addEmployee(Employee e) throws NegativeInputException {
        if (job.isCompetent(e.getExperience())) {
            employees.add(e);
            try {
                e.confirmDayAndShift();
            } catch (EmptyFieldException ex) {
                System.out.println("One of the fields are empty.");
            } finally {
                System.out.println("Attempting to add employee.");
            }
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
                try {
                    te.confirmDayAndShift();
                } catch (EmptyFieldException ef) {
                    System.out.println("One of the fields are empty.");
                }
                te.addTrainingPoints();
                te.addExperiencePoints();
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
            try {
                e.confirmDayAndShift();
            } catch (EmptyFieldException ef) {
                System.out.println("One of the fields are empty.");
            }
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
            try {
                e.setExperience(Integer.parseInt(partsOfLine.get(3)));
            } catch (NegativeInputException ne) {
                System.out.println("Experience is negative.");
            }
            employees.add(e);
        }

        return employees;
    }

    //EFFECTS: splits input string into its parts and puts it into an arraylist which is returned
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public ArrayList<String> userInputFieldsTrainingEmployee(TrainingEmployee te) {
        ArrayList<String> input = new ArrayList<String>();

        while (true) {

            System.out.println("Enter the training employee's name:");
            input.add(0, scanner.nextLine());
            System.out.println("Enter the 3 letter day of the week they will be training (sun to sat):");
            input.add(1, scanner.nextLine());
            System.out.println("Enter the shift they will be training on that day (day, night, graveyard):");
            input.add(2, scanner.nextLine());
            input.add(3, "0");
            if (!input.get(0).isEmpty() & !input.get(1).isEmpty() & !input.get(2).isEmpty()) {
                if (days.contains(input.get(1)) & shifts.contains(input.get(2))) {
                    break;
                }
            }
            System.out.println("Day working or shift were empty or did not have expected values. Please try again.");
        }
        return input;
    }

    public ArrayList<String> userInputFieldsRegEmployee(RegularEmployee re) {
        ArrayList<String> input = new ArrayList<String>();

        while (true) {

            System.out.println("Enter the employee's name:");
            input.add(0, scanner.nextLine());
            System.out.println("Enter the 3 letter day of the week they will be working (sun to sat):");
            input.add(1, scanner.nextLine());
            System.out.println("Enter the shift they will be working on that day (day, night, graveyard):");
            input.add(2, scanner.nextLine());
            System.out.println("Enter the experience level of the employee:");
            input.add(3, scanner.nextLine());
            if (!input.get(0).isEmpty() & !input.get(1).isEmpty() & !input.get(2).isEmpty() & !input.get(3).isEmpty()) {
                if (days.contains(input.get(1)) & shifts.contains(input.get(2))) {
                    break;
                }
            }
            System.out.println("One of the inputs were empty or did not have expected values. Please try again.");
        }

        return input;
    }


    //MODIFIES: this
    // EFFECTS: creates a new instance of WorkScheduler and calls makeSchedule to start the application
    public static void main(String[] args) throws IOException {
        WorkScheduler ws = new WorkScheduler();
        ws.makeSchedule();
    }
}
