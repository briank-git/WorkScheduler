package ui;


import people.Employee;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkScheduler {
    private ArrayList<Employee> employees;
    private Scanner scanner;

    public WorkScheduler() {
        employees = new ArrayList<Employee>();
        scanner = new Scanner(System.in);
        makeSchedule();
    }

    //EFFECTS: makes a weekly (monday to sunday) schedule by giving employees certain days and shifts with user input
    public void makeSchedule() {
        String operation;
        Employee employee;

        while (true) {
            System.out.println("Please input an option (add employee or quit):");
            operation = scanner.nextLine();

            if (operation.equals("quit")) {
                System.out.println("Quitting and printing a work schedule.");
                break;
            }

            System.out.println("Scheduling employee.");
            employee = scheduleEmployee();
            employees.add(employee);
        }

        System.out.println("These are your employees for the week:");
        for (Employee e: employees) {
            e.confirmDayAndShift();
        }
    }

    //EFFECTS: returns a new employee with their inputted name, day they're working, and shift
    public Employee scheduleEmployee() {
        Employee e = new Employee();
        String name;
        String dayWorking;
        String shift;

        System.out.println("Enter the employee's name:");
        name = scanner.nextLine();
        System.out.println("Enter the day of the week they will be working:");
        dayWorking = scanner.nextLine();
        System.out.println("Enter the shift they will be working on that day (day, night, graveyard):");
        shift = scanner.nextLine();

        e.setName(name);
        e.setDayWorking(dayWorking);
        e.setShift(shift);
        e.confirmDayAndShift();

        return e;
    }

    public static void main(String[] args) {
        new WorkScheduler();
    }
}
