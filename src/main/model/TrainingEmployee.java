package model;

import java.util.ArrayList;

public class TrainingEmployee extends Employee {
    private int trainingPoints;
    private final int pointsPerShift = 50;
    private final int pointsPerExperience = 100;

    public TrainingEmployee() {
        super();
        trainingPoints = 0;
    }

    // REQUIRES: fields dayWorking, shift, and name are not empty
    // EFFECTS: prints out which day and which shift they are training
    @Override
    public void confirmDayAndShift() {
        System.out.println(name + " will be training on " + dayWorking + " " + shift + " shift.");
    }

    // EFFECTS: returns a list using user input (name, dayWorking, shift), input is redone if it's
    //          empty or is not contained in the list of days or shifts. Experience stays at default 0.
    //          Adds 50 training points.
    @Override
    public ArrayList<String> userInputFields() {
        ArrayList<String> input = new ArrayList<String>();

        while (true) {

            System.out.println("Enter the training employee's name:");
            input.add(0, scanner.nextLine());
            System.out.println("Enter the 3 letter day of the week they will be training (sun to sat):");
            input.add(1, scanner.nextLine());
            System.out.println("Enter the shift they will be training on that day (day, night, graveyard):");
            input.add(2, scanner.nextLine());
            input.add(3, "0");
            if (!input.get(0).isEmpty() & !input.get(1).isEmpty() & !input.get(2).isEmpty()) {
                if (days.contains(input.get(1)) & shifts.contains(input.get(2))) {
                    break;
                }
            }
            System.out.println("Day working or shift were empty or did not have expected values. Please try again.");
        }
        addTrainingPoints();
        addExperiencePoints();
        return input;
    }

    //EFFECTS: checks if a regular employee has enough experience (at least 5) to train the training employee
    public boolean isSuitableTrainer(Employee e) {
        int minExp = 5;
        if (e.getExperience() >= minExp) {
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: for each training shift scheduled trainee receives 50 training points
    public void addTrainingPoints() {
        trainingPoints = trainingPoints + pointsPerShift;
    }

    //MODIFIES: this
    //EFFECTS: if trainingPoints is greater than or equal to 100, convert 100 training points into 1 experience point
    public void addExperiencePoints() {
        if (trainingPoints >= pointsPerExperience) {
            experience = experience + (trainingPoints / pointsPerExperience);
            trainingPoints = trainingPoints - pointsPerExperience * (trainingPoints / pointsPerExperience);
        }
    }

    public int getTrainingPoints() {
        return trainingPoints;
    }

}
