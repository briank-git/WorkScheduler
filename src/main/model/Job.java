package model;

import exceptions.NegativeInputException;

public class Job {
    private String jobName;
    private int difficulty;

    //MODIFIES: this
    //EFFECTS: creates a job and sets fields with input parameters
    public Job(String jobName, int difficulty) throws NegativeInputException {
        if (difficulty < 0) {
            throw new NegativeInputException();
        }
        this.jobName = jobName;
        this.difficulty = difficulty;
    }

    public void setJobName(String name) {
        this.jobName = name;
    }

    public String getJobName() {
        return this.jobName;
    }

    //MODIFIES: this
    //EFFECTS: sets field difficulty to a positive integer or zero
    public void setDifficulty(int difficulty) throws NegativeInputException {
        if (difficulty < 0) {
            throw new NegativeInputException();
        } else {
            this.difficulty = difficulty;
        }
    }

    //EFFECTS: returns field difficulty
    public int getDifficulty() {
        return this.difficulty;
    }

    //EFFECTS: if experience is greater or equal than difficulty return true, if not then return false
    public boolean isCompetent(int exp) throws NegativeInputException {
        boolean competent = false;
        if (exp < 0) {
            throw new NegativeInputException();
        }
        if (exp >= this.difficulty) {
            competent = true;
        }
        return competent;
    }

    @Override
    public String toString() {
        return jobName;
    }
}
