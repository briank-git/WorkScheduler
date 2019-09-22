package TestPackage;

import models.Job;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJob {
    Job job;

    @BeforeEach
    public void setUpJob() {
        job = new Job("",0);
    }

    @Test
    public void testSetAndGetJobName() {
        job.setJobName("Cook");
        assertEquals("Cook", job.getJobName());
    }

    @Test
    public void testSetAndGetDifficulty() {
        job.setDifficulty(-10);
        assertEquals(0, job.getDifficulty());
        job.setDifficulty(0);
        assertEquals(0, job.getDifficulty());
        job.setDifficulty(3);
        assertEquals(3,job.getDifficulty());

    }

    @Test
    public void testIsCompetent() {
        job.setDifficulty(10);
        assertTrue(job.isCompetent(100));
        assertTrue(job.isCompetent(11));
        assertFalse(job.isCompetent(5));
        assertFalse(job.isCompetent(0));
        assertTrue(job.isCompetent(10));
    }


}
