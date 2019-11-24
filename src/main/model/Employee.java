package model;

import exceptions.ArraySizeException;
import exceptions.EmptyFieldException;
import exceptions.NegativeInputException;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Employee {
    protected String name;
    protected String dayWorking;
    protected String shift;
    protected int experience;
    protected EmployeeManager employeeManager;
    protected Job job;

    public Employee() {
        dayWorking = "";
        shift = "";
        name = "";
        experience = 0;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return this.job;
    }


    // EFFECTS: tells user how many shifts this employee is working
    public int totalShifts() {
        int totalShifts = 0;
        for (Employee e : employeeManager.getEmployees()) {
            if (this.equals(e)) {
                totalShifts++;
            }
        }
        System.out.println(name + " is scheduled for " + totalShifts + " shift(s) next week");
        return totalShifts;
    }

    // MODIFIES: this, WorkScheduler
    // EFFECTS: sets workScheduler to a new workScheduler object if input is equals to field, removes this from input
    // workScheduler's list of employees
    public void removeEmployeeManager(EmployeeManager em) {
        if (em.equals(employeeManager)) {
            employeeManager = null;
            em.removeEmployee(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets field workScheduler to a reference to the workScheduler object that added this employee
    public void addEmployeeManager(EmployeeManager em) {
        this.employeeManager = em;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayWorking(String dayWorking) {
        this.dayWorking = dayWorking;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getName() {
        return this.name;
    }

    public String getDayWorking() {
        return this.dayWorking;
    }

    public String getShift() {
        return this.shift;
    }

    public int getExperience() {
        return this.experience;
    }

    public EmployeeManager getEmployeeManager() {
        return this.employeeManager;
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

    // EFFECTS: prints out which day and which shift they are working
    public String confirmDayAndShift() throws EmptyFieldException {
        if (dayWorking == null | shift == null | name == null) {
            throw new EmptyFieldException();
        }
        return "";
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
        return experience == employee.experience & name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience);
    }
}
