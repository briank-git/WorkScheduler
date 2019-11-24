package model;

import exceptions.EmptyFieldException;
import exceptions.NegativeInputException;
import model.Employee;
import model.Job;
import model.TrainingEmployee;

import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    // MODIFIES: this, Employee
    // EFFECTS: removes employee from ArrayList employees, removes this from workScheduler field in employee
    public void removeEmployee(Employee e) {
        if (employees.contains(e)) {
            employees.remove(e);
            e.removeEmployeeManager(this);
        }
    }


    // MODIFIES: this, Employee
    // EFFECTS: adds an employee to ArrayList employees if they meet the job's experience requirements returns true,
    //          else tells user that employee does not meet requirements returns false
    public boolean addEmployee(Employee e, Job job) throws NegativeInputException {
        if (job.isCompetent(e.getExperience())) {
            employees.add(e);
            e.addEmployeeManager(this);
            e.setJob(job);
            try {
                e.confirmDayAndShift();
            } catch (EmptyFieldException ex) {
                System.out.println("One of the fields are empty.");
            } finally {
                System.out.println("Attempting to add employee.");
            }
            e.totalShifts();
            return true;
        } else {
            System.out.println(e.getName() + " does not have at least " + job.getDifficulty() + " experience.");
            return false;
        }
    }

    // MODIFIES: this, TrainingEmployee
    // EFFECTS: checks if there is a regular employee with at least 6 experience scheduled at the same time as a
    //          training employee, if true then add the trainee to the schedule return true, else return false
    public boolean addTrainingEmployee(TrainingEmployee te) {
        for (Employee e : employees) {
            if (e.getDayWorking().equals(te.getDayWorking())
                    & e.getShift().equals(te.getShift()) & te.isSuitableTrainer(e)) {
                try {
                    te.confirmDayAndShift();
                } catch (EmptyFieldException ef) {
                    System.out.println("One of the fields are empty.");
                }
                te.addTrainingPoints();
                te.addExperiencePoints();
                employees.add(te);
                te.addEmployeeManager(this);
                te.totalShifts();
                return true;
            }
        }
        System.out.println("There is no employee with min. 5 experience to train " + te.getName() + " at that time.");
        return false;
    }


    //EFFECTS: has each employee announce their name, day working, and shift to console
    public void printEmployees() {
        for (Employee e : employees) {
            try {
                e.confirmDayAndShift();
            } catch (EmptyFieldException ef) {
                System.out.println("One of the fields are empty.");
            }
        }
    }

}
