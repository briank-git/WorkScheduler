package model;

import java.util.ArrayList;
import java.util.Arrays;
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

    // REQUIRES: must be one of the days of the week Sun to Sat
    // MODIFIES: this
    // EFFECTS: sets the dayWorking of this
    public void setDayWorking(String dayWorking) {
        this.dayWorking = dayWorking;
    }

    // REQUIRES: must be one of shifts day, night, graveyard
    // MODIFIES: this
    // EFFECTS: sets the shift of this
    public void setShift(String shift) {
        this.shift = shift;
    }

    // MODIFIES: this
    // EFFECTS: sets the experience level of this to a positive integer or zero
    public void setExperience(int exp) {
        if (exp < 0) {
            this.experience = 0;
        } else {
            this.experience = exp;
        }
    }


    // REQUIRES: input has size 4
    // MODIFIES: this
    // EFFECTS: takes list of field values, sets fields of this to those found in list, and then
    //          returns this
    public Employee scheduleEmployee(ArrayList<String> fields) {
//        ArrayList<String> userInput = userInputFields();

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

    // REQUIRES: fields dayWorking, shift, and name are not empty
    // EFFECTS: prints out which day and which shift they are working
    public abstract void confirmDayAndShift();
}
