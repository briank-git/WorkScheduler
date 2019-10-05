package TestPackage;

import model.RegularEmployee;


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
    public void testSetAndGetExperience() {
        e.setExperience(-1);
        assertEquals(0,e.getExperience());
        e.setExperience(0);
        assertEquals(0,e.getExperience());
        e.setExperience(22);
        assertEquals(22,e.getExperience());
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
    public void testScheduleEmployee() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("Bob","Wed","night","4"));
    e.scheduleEmployee(fields);
    assertEquals("Bob",e.getName());
    assertEquals("Wed",e.getDayWorking());
    assertEquals("night",e.getShift());
    assertEquals(4,e.getExperience());
    }
}
