package model;


import java.util.ArrayList;

public class RegularEmployee extends Employee {

    // REQUIRES: fields dayWorking, shift, and name are not empty
    // EFFECTS: prints out which day and which shift they are working
    @Override
    public void confirmDayAndShift() {
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
    }

}

