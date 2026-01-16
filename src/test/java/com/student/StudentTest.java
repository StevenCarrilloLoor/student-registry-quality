package com.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    
    @Test
    public void testCreateValidStudent() {
        Student student = new Student("John Doe", 85.5);
        
        assertEquals("John Doe", student.getName());
        assertEquals(85.5, student.getGrade());
    }
    
    @Test
    public void testCreateStudentWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student(null, 85.5);
        });
    }
    
    @Test
    public void testCreateStudentWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student("", 85.5);
        });
    }
    
    @Test
    public void testCreateStudentWithWhitespaceName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student("   ", 85.5);
        });
    }
    
    @Test
    public void testCreateStudentWithNegativeGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student("John Doe", -10.0);
        });
    }
    
    @Test
    public void testCreateStudentWithGradeAbove100() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student("John Doe", 150.0);
        });
    }
    
    @Test
    public void testCreateStudentWithMinimumGrade() {
        Student student = new Student("John Doe", 0.0);
        assertEquals(0.0, student.getGrade());
    }
    
    @Test
    public void testCreateStudentWithMaximumGrade() {
        Student student = new Student("John Doe", 100.0);
        assertEquals(100.0, student.getGrade());
    }
    
    @Test
    public void testToString() {
        Student student = new Student("John Doe", 85.5);
        String expected = "Student: John Doe, Grade: 85.5";
        
        assertEquals(expected, student.toString());
    }
    
    @Test
    public void testEqualsWithSameName() {
        Student student1 = new Student("John Doe", 85.5);
        Student student2 = new Student("John Doe", 90.0);
        
        assertEquals(student1, student2);
    }
    
    @Test
    public void testEqualsWithDifferentName() {
        Student student1 = new Student("John Doe", 85.5);
        Student student2 = new Student("Jane Smith", 85.5);
        
        assertNotEquals(student1, student2);
    }
    
    @Test
    public void testEqualsWithNull() {
        Student student = new Student("John Doe", 85.5);
        
        assertNotEquals(student, null);
    }
    
    @Test
    public void testEqualsSameObject() {
        Student student = new Student("John Doe", 85.5);
        
        assertEquals(student, student);
    }
    
    @Test
    public void testHashCodeConsistency() {
        Student student1 = new Student("John Doe", 85.5);
        Student student2 = new Student("John Doe", 90.0);
        
        assertEquals(student1.hashCode(), student2.hashCode());
    }
}