package tests;

import model.Job;


import exceptions.NegativeInputException;
import exceptions.WorkSchedulerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestJob {
    Job job;

    @BeforeEach
    public void setUpJob() throws NegativeInputException {
        job = new Job("",0);
    }

    @Test
    public void testConstructorNegativeInputException() {
        try {
            job = new Job("",-1);
            fail("No exception thrown");
        } catch (NegativeInputException e) {

        }
    }

    @Test
    public void testConstructorNoException() {
        try {
            job = new Job("",0);
        } catch (WorkSchedulerException e) {
            fail("Caught unexpected exception");
        }
    }

    @Test
    public void testSetAndGetJobName() {
        job.setJobName("Cook");
        assertEquals("Cook", job.getJobName());
    }

    @Test
    public void testSetAndGetDifficulty() throws NegativeInputException {
        try {
            job.setDifficulty(-10);
            fail("Did not catch exception");
        } catch (NegativeInputException e) {}
        job.setDifficulty(0);
        assertEquals(0, job.getDifficulty());
        job.setDifficulty(3);
        assertEquals(3,job.getDifficulty());

    }

    @Test
    public void testIsCompetent() throws NegativeInputException {
        job.setDifficulty(10);
        assertTrue(job.isCompetent(100));
        assertTrue(job.isCompetent(11));
        assertFalse(job.isCompetent(5));
        assertFalse(job.isCompetent(0));
        assertTrue(job.isCompetent(10));
    }

    @Test
    public void testIsCompetentThrowNegativeInputException() {
        try {
            job.isCompetent(-10);
            fail("No exception thrown");
        } catch (NegativeInputException e) {

        }
    }

    @Test
    public void testIsCompetentNoException() {
        try {
            job.isCompetent(10);
        } catch (WorkSchedulerException e) {
            fail("Caught unexpected exception");
        }
    }


}
