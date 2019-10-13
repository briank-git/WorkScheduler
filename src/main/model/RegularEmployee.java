package model;


import java.util.ArrayList;

public class RegularEmployee extends Employee {

    // EFFECTS: prints out which day and which shift they are working
    @Override
    public void confirmDayAndShift() throws EmptyFieldException {
        super.confirmDayAndShift();
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
    }

}

