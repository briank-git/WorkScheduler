package tests;

import model.Employee;
import exceptions.NegativeInputException;
import model.RegularEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Saveable;
import ui.WorkScheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSaveable {
    Saveable workScheduler;
    PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        workScheduler = new WorkScheduler();
        writer = new PrintWriter("outputfile.txt", "UTF-8");
    }

    @Test
    void testSave() throws IOException, NegativeInputException {
        ArrayList<Employee> emps = new ArrayList<Employee>();
        ArrayList<Employee> savedEmployees = new ArrayList<Employee>();
        RegularEmployee employee0 = new RegularEmployee();
        employee0.setName("Joey");
        employee0.setDayWorking("Wed");
        employee0.setShift("night");
        employee0.setExperience(5);
        emps.add(employee0);

        RegularEmployee employee1 = new RegularEmployee();
        employee1.setName("Trish");
        employee1.setDayWorking("Sat");
        employee1.setShift("graveyard");
        employee1.setExperience(23);
        emps.add(employee1);
        workScheduler.save(emps);

        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        for (String line : lines) {
            RegularEmployee e = new RegularEmployee();
            ArrayList<String> partsOfLine = splitOnSpace(line);
            e.setName(partsOfLine.get(0));
            e.setDayWorking(partsOfLine.get(1));
            e.setShift(partsOfLine.get(2));
            e.setExperience(Integer.parseInt(partsOfLine.get(3)));
            savedEmployees.add(e);
        }
        Employee savedEmployee1 = savedEmployees.get(0);
        Employee savedEmployee2 = savedEmployees.get(1);
        assertEquals("Joey", savedEmployee1.getName());
        assertEquals("Wed", savedEmployee1.getDayWorking());
        assertEquals("night", savedEmployee1.getShift());
        assertEquals(5, savedEmployee1.getExperience());
        assertEquals("Trish", savedEmployee2.getName());
        assertEquals("Sat", savedEmployee2.getDayWorking());
        assertEquals("graveyard", savedEmployee2.getShift());
        assertEquals(23, savedEmployee2.getExperience());


    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
