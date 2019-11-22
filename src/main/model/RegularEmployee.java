package model;


import exceptions.EmptyFieldException;

public class RegularEmployee extends Employee {

    // EFFECTS: prints out which day and which shift they are working
    @Override
    public String confirmDayAndShift() throws EmptyFieldException {
        super.confirmDayAndShift();
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
        return name + " will be working on " + dayWorking + " " + shift + " shift.";
    }

}

