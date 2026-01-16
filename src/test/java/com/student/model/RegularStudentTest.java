package com.student.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegularStudentTest {
    
    @Test
    public void testCreateRegularStudent() {
        RegularStudent student = new RegularStudent("John Doe", 85.5);
        
        assertEquals("John Doe", student.getName());
        assertEquals(85.5, student.getGrade());
    }
    
    @Test
    public void testStatusExcellent() {
        RegularStudent student = new RegularStudent("John Doe", 95.0);
        assertEquals("Excellent", student.getStatus());
    }
    
    @Test
    public void testStatusGood() {
        RegularStudent student = new RegularStudent("John Doe", 85.0);
        assertEquals("Good", student.getStatus());
    }
    
    @Test
    public void testStatusAverage() {
        RegularStudent student = new RegularStudent("John Doe", 75.0);
        assertEquals("Average", student.getStatus());
    }
    
    @Test
    public void testStatusPass() {
        RegularStudent student = new RegularStudent("John Doe", 65.0);
        assertEquals("Pass", student.getStatus());
    }
    
    @Test
    public void testStatusFail() {
        RegularStudent student = new RegularStudent("John Doe", 50.0);
        assertEquals("Fail", student.getStatus());
    }
    
    @Test
    public void testInvalidGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RegularStudent("John Doe", 150.0);
        });
    }
    
    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RegularStudent("", 85.0);
        });
    }
}