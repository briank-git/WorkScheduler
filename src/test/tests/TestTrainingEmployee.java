package tests;

import exceptions.EmptyFieldException;
import exceptions.NegativeInputException;
import model.RegularEmployee;
import model.TrainingEmployee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrainingEmployee {
    TrainingEmployee te;

    @BeforeEach
    public void setUp() throws NegativeInputException {
        te = new TrainingEmployee();
    }

    @Test
    public void testIsSuitableTrainerTrue() throws NegativeInputException {
        RegularEmployee re = new RegularEmployee();
        re.setName("rich");
        re.setDayWorking("Wed");
        re.setShift("graveyard");
        re.setExperience(5);
        assertTrue(te.isSuitableTrainer(re));
    }

    @Test
    public void testIsSuitableTrainerFalse() throws NegativeInputException {
        RegularEmployee re = new RegularEmployee();
        re.setName("rich");
        re.setDayWorking("Wed");
        re.setShift("graveyard");
        re.setExperience(2);
        assertFalse(te.isSuitableTrainer(re));
    }

    @Test
    public void testAddTrainingPoints() {
        te.addTrainingPoints();
        assertEquals(50,te.getTrainingPoints());
        te.addTrainingPoints();
        assertEquals(100,te.getTrainingPoints());
    }

    @Test
    public void testAddExperiencePoints_0_50_100() {
        te.addExperiencePoints();
        assertEquals(0,te.getExperience());
        te.addTrainingPoints();
        te.addExperiencePoints();
        assertEquals(0,te.getExperience());
        te.addTrainingPoints();
        te.addExperiencePoints();
        assertEquals(1,te.getExperience());
        assertEquals(0,te.getTrainingPoints());
    }

    @Test
    public void testAddExperiencePoints_150_200() {
        te.addTrainingPoints();
        te.addTrainingPoints();
        te.addTrainingPoints();
        te.addExperiencePoints();
        assertEquals(1,te.getExperience());
        assertEquals(50,te.getTrainingPoints());
        te.addTrainingPoints();
        te.addTrainingPoints();
        te.addTrainingPoints();
        te.addExperiencePoints();
        assertEquals(3, te.getExperience());
        assertEquals(0,te.getTrainingPoints());

    }

    @Test
    public void testConfirmDayAndShiftThrowEmptyFieldException() {
        te.setName("Bobby");
        te.setDayWorking("Tue");
        te.setShift(null);
        try {
            te.confirmDayAndShift();
            fail("No exception thrown");
        } catch (EmptyFieldException e) {

        }
    }

    @Test
    public void testConfirmDayAndShiftNoException() {
        te.setName("rich");
        te.setDayWorking("Wed");
        te.setShift("graveyard");
        try {
            te.confirmDayAndShift();
        } catch (EmptyFieldException e) {
            fail();
        }
    }
}
