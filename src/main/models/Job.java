package models;

public class Job {
    private String jobName;
    private int difficulty;

    //EFFECTS: creates a job and replaces fields with default values
    public Job(String jobName, int difficulty) {
        this.jobName = jobName;
        this.difficulty = difficulty;
    }

    //MODIFIES: this
    //EFFECTS: sets field jobName
    public void setJobName(String name) {
        this.jobName = name;
    }

    //EFFECTS: returns field jobName
    public String getJobName() {
        return this.jobName;
    }

    //MODIFIES: this
    //EFFECTS: sets field difficulty, negative inputs are set to zero
    public void setDifficulty(int difficulty) {
        if (difficulty < 0) {
            this.difficulty = 0;
        } else {
            this.difficulty = difficulty;
        }
    }

    //EFFECTS: returns field difficulty
    public int getDifficulty() {
        return this.difficulty;
    }

    //REQUIRES: experience or difficulty are not negative numbers
    //EFFECTS: if experience is greater or equal than difficulty return true, if not then return false
    public boolean isCompetent(int exp) {
        boolean competent = false;
        if (exp >= this.difficulty) {
            competent = true;
        }
        return competent;
    }

}
