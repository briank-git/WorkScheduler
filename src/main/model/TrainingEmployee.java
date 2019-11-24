package model;

import exceptions.EmptyFieldException;
import exceptions.NegativeInputException;

public class TrainingEmployee extends Employee {
    private int trainingPoints;

    public TrainingEmployee() throws NegativeInputException {
        super();
        trainingPoints = 0;
        job = new Job("Trainee", 0);
    }

    public int getTrainingPoints() {
        return trainingPoints;
    }

    // EFFECTS: prints out which day and which shift they are training
    @Override
    public String confirmDayAndShift() throws EmptyFieldException {
        super.confirmDayAndShift();
        System.out.println(name + " will be training on " + dayWorking + " " + shift + " shift.");
        return name + " will be training on " + dayWorking + " " + shift + " shift.";
    }


    //EFFECTS: checks if a regular employee has enough experience (at least 6) to train the training employee
    public boolean isSuitableTrainer(Employee e) {
        int minExp = 6;
        return e.getExperience() >= minExp;
    }

    //MODIFIES: this
    //EFFECTS: for each training shift scheduled trainee receives 50 training points
    public void addTrainingPoints() {
        int pointsPerShift = 50;
        trainingPoints = trainingPoints + pointsPerShift;
    }

    //MODIFIES: this
    //EFFECTS: if trainingPoints is greater than or equal to 100, convert 100 training points into 1 experience point
    public void addExperiencePoints() {
        int pointsPerExperience = 100;
        if (trainingPoints >= pointsPerExperience) {
            experience = experience + (trainingPoints / pointsPerExperience);
            trainingPoints = trainingPoints - pointsPerExperience * (trainingPoints / pointsPerExperience);
        }
    }

}
