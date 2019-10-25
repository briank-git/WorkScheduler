package model;

import ui.WorkScheduler;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Employee {
    protected String name;
    protected String dayWorking;
    protected String shift;
    protected int experience;
    protected WorkScheduler workScheduler;


    protected static Scanner scanner = new Scanner(System.in);

    public Employee() {
        dayWorking = "";
        shift = "";
        name = "";
        experience = 0;
    }

    // EFFECTS: tells user how many shifts this employee is working
    public void totalShifts() {
        int totalShifts = 0;
        for (Employee e : workScheduler.getEmployees()) {
            if (this.equals(e)) {
                totalShifts++;
            }
        }
        System.out.println(name + " will be working " + totalShifts + " shift(s) next week");
    }

    // MODIFIES: this, WorkScheduler
    // EFFECTS: sets workScheduler to a new workScheduler object if input is equals to field, removes this from input
    // workScheduler's list of employees
    public void removeWorkScheduler(WorkScheduler ws) {
        if (ws.equals(workScheduler)) {
            ws.removeEmployee(this);
            workScheduler = new WorkScheduler();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets field workScheduler to a reference to the workScheduler object that added this employee
    public void addWorkScheduler(WorkScheduler ws) {
        this.workScheduler = ws;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of this
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the dayWorking of this
    public void setDayWorking(String dayWorking) {
        this.dayWorking = dayWorking;
    }

    // MODIFIES: this
    // EFFECTS: sets the shift of this
    public void setShift(String shift) {
        this.shift = shift;
    }

    // MODIFIES: this
    // EFFECTS: sets the experience level of this to a positive integer or zero
    public void setExperience(int exp) throws NegativeInputException {
        if (exp < 0) {
            throw new NegativeInputException();
        } else {
            this.experience = exp;
        }
    }

    // MODIFIES: this
    // EFFECTS: takes list of field values, sets fields of this to those found in list, and then
    //          returns this
    public Employee scheduleEmployee(ArrayList<String> fields) throws ArraySizeException {
//        ArrayList<String> userInput = userInputFields();
        if (fields.size() > 4) {
            throw new ArraySizeException();
        }
        name = fields.get(0);
        dayWorking = fields.get(1);
        shift = fields.get(2);
        experience = Integer.parseInt(fields.get(3));

        return this;
    }

    // EFFECTS: returns field name
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns field dayWorking
    public String getDayWorking() {
        return this.dayWorking;
    }

    // EFFECTS: returns field shift
    public String getShift() {
        return this.shift;
    }

    // EFFECTS: returns field experience
    public int getExperience() {
        return this.experience;
    }

    // EFFECTS: prints out which day and which shift they are working
    public void confirmDayAndShift() throws EmptyFieldException {
        if (dayWorking == null | shift == null | name == null) {
            throw new EmptyFieldException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return experience == employee.experience && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience);
    }
}
