package tests;

import exceptions.ArraySizeException;
import exceptions.EmptyFieldException;
import exceptions.NegativeInputException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployeeManager {
    EmployeeManager em;

    @BeforeEach
    void setUp() {
        em = new EmployeeManager();
    }

    @Test
    void testGetEmployees() throws ArraySizeException, NegativeInputException {
        RegularEmployee re = new RegularEmployee();
        Job job = new Job("driver",5);
        re.scheduleEmployee(new ArrayList<String>(Arrays.asList("Ron","sun","night","5")));
        em.addEmployee(re,job);
        assertEquals(1,em.getEmployees().size());
        assertEquals(re,em.getEmployees().get(0));
    }

    @Test
    void testSetEmployees() throws ArraySizeException {
        RegularEmployee re1 = new RegularEmployee();
        re1.scheduleEmployee(new ArrayList<String>(Arrays.asList("Ron","sun","night","5")));
        RegularEmployee re2 = new RegularEmployee();
        re2.scheduleEmployee(new ArrayList<String>(Arrays.asList("Dan","wed","day","10")));
        em.setEmployees(new ArrayList<Employee>(Arrays.asList(re1,re2)));
        assertEquals(2,em.getEmployees().size());
        assertEquals(re1,em.getEmployees().get(0));
        assertEquals(re2,em.getEmployees().get(1));

    }

    @Test
    void testRemoveEmployee() throws ArraySizeException, NegativeInputException {
        RegularEmployee re = new RegularEmployee();
        Job job = new Job("driver",5);
        re.scheduleEmployee(new ArrayList<String>(Arrays.asList("Ron","sun","night","5")));
        em.addEmployee(re,job);
        assertEquals(em,re.getEmployeeManager());
        em.removeEmployee(re);
        assertFalse(em.getEmployees().contains(re));
        assertNull(re.getEmployeeManager());
    }

    @Test
    void testPrintEmployees() throws ArraySizeException, EmptyFieldException {
        RegularEmployee re1 = new RegularEmployee();
        re1.scheduleEmployee(new ArrayList<String>(Arrays.asList("Ron","sun","night","5")));
        re1.confirmDayAndShift();
        RegularEmployee re2 = new RegularEmployee();
        re2.scheduleEmployee(new ArrayList<String>(Arrays.asList("Dan","wed","day","10")));
        re2.confirmDayAndShift();
        em.setEmployees(new ArrayList<Employee>(Arrays.asList(re1,re2)));
        em.printEmployees();
    }

    @Test
    void testAddEmployeeTrue() throws NegativeInputException, ArraySizeException {
        RegularEmployee re = new RegularEmployee();
        Job job = new Job("driver",5);
        re.scheduleEmployee(new ArrayList<String>(Arrays.asList("Ron","sun","night","5")));
        assertTrue(em.addEmployee(re,job));
        assertTrue(em.getEmployees().contains(re));
        assertEquals(em,re.getEmployeeManager());
    }

    @Test
    void testAddEmployeeFalse() throws NegativeInputException, ArraySizeException {
        RegularEmployee re = new RegularEmployee();
        Job job = new Job("driver",10);
        re.scheduleEmployee(new ArrayList<String>(Arrays.asList("Max","sun","night","3")));
        assertFalse(em.addEmployee(re,job));
        assertFalse(em.getEmployees().contains(re));
        assertNull(re.getEmployeeManager());
    }

    @Test
    void testAddTrainingEmployeeTrue() throws NegativeInputException, ArraySizeException {
        TrainingEmployee te = new TrainingEmployee();
        te.scheduleEmployee(new ArrayList<String>(Arrays.asList("Sam","sun","night","0")));
        RegularEmployee re = new RegularEmployee();
        Job job = new Job("driver",10);
        re.scheduleEmployee(new ArrayList<String>(Arrays.asList("Max","sun","night","10")));
        em.addEmployee(re,job);
        assertTrue(em.addTrainingEmployee(te));
        assertTrue(em.getEmployees().contains(te));
        assertEquals(te,em.getEmployees().get(1));
        assertEquals(em,te.getEmployeeManager());
    }

    @Test
    void testAddTrainingEmployeeFalse() throws NegativeInputException, ArraySizeException {
        TrainingEmployee te = new TrainingEmployee();
        te.scheduleEmployee(new ArrayList<String>(Arrays.asList("Sam","sun","night","0")));
        assertFalse(em.addTrainingEmployee(te));
        assertFalse(em.getEmployees().contains(te));
        assertNull(te.getEmployeeManager());
    }

}
