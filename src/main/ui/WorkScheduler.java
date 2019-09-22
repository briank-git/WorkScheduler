package ui;


import models.Employee;
import models.Job;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkScheduler {
    private ArrayList<Employee> employees;
    private Scanner scanner;
    private Job jobOfTheWeek = new Job("Head chef", 3);


    // EFFECTS: initializes fields employees and scanner, calls the makeSchedule method to being creating a schedule
    public WorkScheduler() {
        employees = new ArrayList<Employee>();
        scanner = new Scanner(System.in);
        makeSchedule();
    }

    // MODIFIES: this, Employee
    // EFFECTS: makes a weekly (Sun to Sat) schedule by giving employees certain days and shifts with user input
    public void makeSchedule() {
        String operation;

        while (true) {
            Employee employee = new Employee();
            System.out.println("Please input an option (add employee or quit):");
            operation = scanner.nextLine();

            if (operation.equals("quit")) {
                System.out.println("Quitting and printing a work schedule.");
                break;
            }

            System.out.println("Scheduling employee for " + jobOfTheWeek.getJobName());
            employee.scheduleEmployee(employee.userInputFields());
            addEmployee(employee);

        }

        System.out.println("This is the schedule for " + jobOfTheWeek.getJobName() + "s next week:");
        printEmployees(employees);
    }

    // MODIFIES: this
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements, else
    //          tells user that employee does not meet requirements
    public void addEmployee(Employee e) {
        if (jobOfTheWeek.isCompetent(e.getExperience())) {
            employees.add(e);
            e.confirmDayAndShift();
        } else {
            System.out.println(e.getName() + " does not have enough experience, please choose another employee.");
        }
    }


    //EFFECTS: has each employee announce their name, day working, and shift
    public void printEmployees(ArrayList<Employee> employees) {
        for (Employee e:employees) {
            e.confirmDayAndShift();
        }
    }

    // EFFECTS: creates a new instance of WorkScheduler to start the application
    public static void main(String[] args) {
        new WorkScheduler();
    }
}
