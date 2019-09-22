package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Employee {
    private String name;
    private String dayWorking;
    private String shift;
    private int experience;
    private ArrayList<String> days = new ArrayList<String>(Arrays.asList("Sun","Mon","Tue","Wed","Thu","Fri","Sat"));
    private ArrayList<String> shifts = new ArrayList<String>(Arrays.asList("day","night","graveyard"));

    private static Scanner scanner = new Scanner(System.in);


    // MODIFIES: this
    // EFFECTS: creates employee object and sets the name, DayWorking, and shift fields to their default values.
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

    // REQUIRES: must be one of the days of the week Sunday to Saturday
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
    // EFFECTS: sets the experience level of this, negative inputs are set to zero
    public void setExperience(int exp) {
        if (exp < 0) {
            this.experience = 0;
        } else {
            this.experience = exp;
        }
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
    public void confirmDayAndShift() {
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
    }

    // REQUIRES: input list is of size 4 and name, dayWorking, and shift are not empty and have
    // valid inputs (Sun to Sat) for dayWorking and day, night, graveyard for shift.
    // MODIFIES: this
    // EFFECTS: takes list of user input, sets fields of this to input, and then
    //          returns this
    public Employee scheduleEmployee(ArrayList<String> fields) {
//        ArrayList<String> userInput = userInputFields();

        name = fields.get(0);
        dayWorking = fields.get(1);
        shift = fields.get(2);
        experience = Integer.parseInt(fields.get(3));

        return this;
    }


    // EFFECTS: returns a list using user input (name, dayWorking, shift, and experience), input is redone if it's
    //          empty or is not contained in the list of days or shifts.
    public ArrayList<String> userInputFields() {
        ArrayList<String> input = new ArrayList<String>();

        while (true) {

            System.out.println("Enter the employee's name:");
            input.add(0, scanner.nextLine());
            System.out.println("Enter the capitalized 3 letter day of the week they will be working (Sun to Sat):");
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
}

