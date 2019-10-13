package TestPackage;

import model.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegularEmployee {
    RegularEmployee e;

    @BeforeEach
    public void makeEmployee() {
        e = new RegularEmployee();
    }

    @Test
    public void testSetAndGetName() {
        e.setName("Bob");
        assertEquals("Bob",e.getName());
        e.setName("travis");
        assertEquals("travis", e.getName());
    }

    @Test
    public void testSetAndGetDayWorking() {
        e.setDayWorking("Tue");
        assertEquals("Tue", e.getDayWorking());
    }

    @Test
    public void testSetAndGetShift() {
        e.setShift("day");
        assertEquals("day", e.getShift());
    }

    @Test
    public void testSetAndGetExperience() throws NegativeInputException {
        e.setExperience(0);
        assertEquals(0,e.getExperience());
        e.setExperience(22);
        assertEquals(22,e.getExperience());
    }

    @Test
    public void testSetExperienceThrowNegativeInputException() {
        try {
            e.setExperience(-11);
            fail("No exception thrown");
        } catch (NegativeInputException e) {}
    }

    @Test
    public void testSetExperienceNoException() {
        try {
            e.setExperience(0);
        } catch (NegativeInputException e) {
            fail("Caught unexpected exception");
        }

    }

    @Test
    public void testConfirmDayAndShift() {
        e.setName("Bobby");
        e.setDayWorking("Tue");
        e.setShift("night");
        String printOutput = e.getName() + " will be working on " + e.getDayWorking() + " " + e.getShift() + " shift.";
        assertEquals("Bobby will be working on Tue night shift.", printOutput);

        e.setName("rich");
        e.setDayWorking("Wed");
        e.setShift("graveyard");
        String printOutput2 = e.getName() + " will be working on " + e.getDayWorking() + " " + e.getShift() + " shift.";
        assertEquals("rich will be working on Wed graveyard shift.", printOutput2);
    }

    @Test
    public void testConfirmDayAndShiftThrowEmptyFieldException() {
        e.setName("Bobby");
        e.setDayWorking("Tue");
        e.setShift(null);
        try {
            e.confirmDayAndShift();
            fail("No exception thrown");
        } catch (EmptyFieldException e) {

        }
    }

    @Test
    public void testConfirmDayAndShiftNoException() {
        e.setName("rich");
        e.setDayWorking("Wed");
        e.setShift("graveyard");
        try {
            e.confirmDayAndShift();
        } catch (EmptyFieldException e) {
            fail();
        }
    }

    @Test
    public void testScheduleEmployee() throws ArraySizeException {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("Bob","Wed","night","4"));
    e.scheduleEmployee(fields);
    assertEquals("Bob",e.getName());
    assertEquals("Wed",e.getDayWorking());
    assertEquals("night",e.getShift());
    assertEquals(4,e.getExperience());
    }

    @Test
    public void testScheduleEmployeeThrowArraySizeException() {
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("Bob","Wed","night","4","hamburger"));
        try {
            e.scheduleEmployee(fields);
            fail("Exception not thrown");
        } catch (ArraySizeException e) {

        }
    }

    @Test
    public void testScheduleEmployeeNoException() {
        ArrayList<String> fields = new ArrayList<String>(Arrays.asList("Bob","Wed","night","4"));
        try {
            e.scheduleEmployee(fields);
        } catch (ArraySizeException e) {
            fail("Caught unexpected exception.");
        }
    }
}
