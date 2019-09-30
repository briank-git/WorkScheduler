package TestPackage;

import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Loadable;
import ui.WorkScheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    Loadable workScheduler;
    PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        workScheduler = new WorkScheduler();
        writer = new PrintWriter("outputfile.txt", "UTF-8");
    }

    @Test
    void testLoad() throws IOException {
        ArrayList<Employee> emps = new ArrayList<Employee>();
        ArrayList<Employee> loadedEmployees;
        Employee employee0 = new Employee();
        employee0.setName("Max");
        employee0.setDayWorking("Tue");
        employee0.setShift("night");
        employee0.setExperience(11);
        emps.add(employee0);

        Employee employee1 = new Employee();
        employee1.setName("John");
        employee1.setDayWorking("Sun");
        employee1.setShift("day");
        employee1.setExperience(19);
        emps.add(employee1);

        for (Employee e : emps) {
            writer.println(e.getName() + " " + e.getDayWorking() + " " + e.getShift() + " " + e.getExperience());
        }
        writer.close();

        loadedEmployees = workScheduler.load();

        Employee loadedEmployee0 = loadedEmployees.get(0);
        Employee loadedEmployee1 = loadedEmployees.get(1);

        assertEquals("Max", loadedEmployee0.getName());
        assertEquals("Tue", loadedEmployee0.getDayWorking());
        assertEquals("night", loadedEmployee0.getShift());
        assertEquals(11, loadedEmployee0.getExperience());
        assertEquals("John", loadedEmployee1.getName());
        assertEquals("Sun", loadedEmployee1.getDayWorking());
        assertEquals("day", loadedEmployee1.getShift());
        assertEquals(19, loadedEmployee1.getExperience());
    }
}
