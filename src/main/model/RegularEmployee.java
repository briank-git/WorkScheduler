package model;


import java.util.ArrayList;

public class RegularEmployee extends Employee {

    // REQUIRES: fields dayWorking, shift, and name are not empty
    // EFFECTS: prints out which day and which shift they are working
    @Override
    public void confirmDayAndShift() {
        System.out.println(name + " will be working on " + dayWorking + " " + shift + " shift.");
    }

    // EFFECTS: returns a list using user input (name, dayWorking, shift, and experience), input is redone if it's
    //          empty or is not contained in the list of days or shifts.
    @Override
    public ArrayList<String> userInputFields() {
        ArrayList<String> input = new ArrayList<String>();

        while (true) {

            System.out.println("Enter the employee's name:");
            input.add(0, scanner.nextLine());
            System.out.println("Enter the 3 letter day of the week they will be working (sun to sat):");
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

