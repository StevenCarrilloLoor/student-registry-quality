package com.student.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HonorsStudentTest {
    
    @Test
    public void testCreateHonorsStudent() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 88.0, 5.0);
        
        assertEquals("Jane Smith", student.getName());
        assertEquals(88.0, student.getBaseGrade());
        assertEquals(5.0, student.getBonusPoints());
        assertEquals(93.0, student.getGrade());
    }
    
    @Test
    public void testGradeWithBonus() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 85.0, 10.0);
        assertEquals(95.0, student.getGrade());
    }
    
    @Test
    public void testGradeCappedAt100() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 95.0, 10.0);
        assertEquals(100.0, student.getGrade());
    }
    
    @Test
    public void testStatusHonorsWithDistinction() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 90.0, 8.0);
        assertEquals("Honors with Distinction", student.getStatus());
    }
    
    @Test
    public void testStatusHonors() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 88.0, 5.0);
        assertEquals("Honors", student.getStatus());
    }
    
    @Test
    public void testStatusHighHonors() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 85.0, 3.0);
        assertEquals("High Honors", student.getStatus());
    }
    
    @Test
    public void testStatusHonorsCandidate() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 80.0, 2.0);
        assertEquals("Honors Candidate", student.getStatus());
    }
    
    @Test
    public void testInvalidBaseGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HonorsStudent("Jane Smith", 150.0, 5.0);
        });
    }
    
    @Test
    public void testInvalidBonus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HonorsStudent("Jane Smith", 85.0, 15.0);
        });
    }
    
    @Test
    public void testNegativeBonus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HonorsStudent("Jane Smith", 85.0, -5.0);
        });
    }
}