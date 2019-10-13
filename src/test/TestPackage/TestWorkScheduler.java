package TestPackage;

import model.NegativeInputException;
import model.RegularEmployee;
import model.TrainingEmployee;
import model.Employee;
import ui.WorkScheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestWorkScheduler {
    WorkScheduler ws;

    @BeforeEach
    public void setUp() {
        ws = new WorkScheduler();
    }

    @Test
    public void testAddEmployeeSuccess() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Tue");
        re.setShift("night");
        re.setExperience(3);
        assertTrue(ws.addEmployee(re));
        assertEquals(re, ws.getEmployees().get(0));
    }

    @Test
    public void testAddEmployeeFail() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Tue");
        re.setShift("night");
        re.setExperience(2);
        assertFalse(ws.addEmployee(re));
        assertTrue(ws.getEmployees().isEmpty());
    }

    @Test
    public void testAddTrainingEmployeeSuccess() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Tue");
        re.setShift("night");
        re.setExperience(5);
        ws.addEmployee(re);
        TrainingEmployee te = new TrainingEmployee();
        te.setName("Trainee");
        te.setDayWorking("Tue");
        te.setShift("night");
        assertTrue(ws.addTrainingEmployee(te));
        assertEquals(te, ws.getEmployees().get(1));
    }

    @Test
    public void testAddTrainingEmployeeExperienceFail() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Tue");
        re.setShift("night");
        re.setExperience(4);
        ws.addEmployee(re);
        TrainingEmployee te = new TrainingEmployee();
        te.setName("Trainee");
        te.setDayWorking("Tue");
        te.setShift("night");
        assertFalse(ws.addTrainingEmployee(te));
        assertEquals(1, ws.getEmployees().size());
    }

    @Test
    public void testAddTrainingEmployeeDayWorkingFail() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Wed");
        re.setShift("night");
        re.setExperience(5);
        ws.addEmployee(re);
        TrainingEmployee te = new TrainingEmployee();
        te.setName("Trainee");
        te.setDayWorking("Tue");
        te.setShift("night");
        assertFalse(ws.addTrainingEmployee(te));
        assertEquals(1, ws.getEmployees().size());
    }

    @Test
    public void testAddTrainingEmployeeShiftFail() throws NegativeInputException {
        Employee re = new RegularEmployee();
        re.setName("Bobby");
        re.setDayWorking("Tue");
        re.setShift("night");
        re.setExperience(5);
        ws.addEmployee(re);
        TrainingEmployee te = new TrainingEmployee();
        te.setName("Trainee");
        te.setDayWorking("Tue");
        te.setShift("graveyard");
        assertFalse(ws.addTrainingEmployee(te));
        assertEquals(1, ws.getEmployees().size());
    }
}
