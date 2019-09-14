package people;

import java.util.ArrayList;

public class Employee {
    private String name;
    private String dayWorking; //"Monday" to "Sunday"
    private String shift;   // "day" for day shift, "night" for night, "graveyard" for graveyard

    public Employee() {
        dayWorking = "not working";
        shift = "no shift";
        name = "no name";
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

    //EFFECTS: prints out which day and which shift they are working;
    public void confirmDayAndShift() {
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
    }

    //EFFECTS: returns true if fields dayWorking and shift are not their default values false otherwise
    public boolean isWorking() {
        return false; //stub
    }
}

