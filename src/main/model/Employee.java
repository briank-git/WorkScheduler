package model;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Employee {
    protected String name;
    protected String dayWorking;
    protected String shift;
    protected int experience;


    protected static Scanner scanner = new Scanner(System.in);

    public Employee() {
        dayWorking = "";
        shift = "";
        name = "";
        experience = 0;
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
}
