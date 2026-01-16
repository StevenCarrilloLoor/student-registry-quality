package com.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {
    
    private StudentManager manager;
    
    @BeforeEach
    public void setUp() {
        manager = new StudentManager();
    }
    
    @Test
    public void testAddStudentByNameAndGrade() {
        manager.addStudent("John Doe", 85.5);
        
        assertEquals(1, manager.getStudentCount());
        assertEquals("John Doe", manager.getStudents().get(0).getName());
        assertEquals(85.5, manager.getStudents().get(0).getGrade());
    }
    
    @Test
    public void testAddStudentObject() {
        Student student = new Student("Jane Smith", 92.0);
        manager.addStudent(student);
        
        assertEquals(1, manager.getStudentCount());
        assertTrue(manager.getStudents().contains(student));
    }
    
    @Test
    public void testAddNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addStudent((Student) null);
        });
    }
    
    @Test
    public void testAddDuplicateStudent() {
        manager.addStudent("John Doe", 85.5);
        
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addStudent("John Doe", 90.0);
        });
    }
    
    @Test
    public void testGetStudentsReturnsNewList() {
        manager.addStudent("John Doe", 85.5);
        
        var students1 = manager.getStudents();
        var students2 = manager.getStudents();
        
        assertNotSame(students1, students2);
    }
    
    @Test
    public void testGetStudentCount() {
        assertEquals(0, manager.getStudentCount());
        
        manager.addStudent("John Doe", 85.5);
        assertEquals(1, manager.getStudentCount());
        
        manager.addStudent("Jane Smith", 92.0);
        assertEquals(2, manager.getStudentCount());
    }
    
    @Test
    public void testGetAverageGradeWithNoStudents() {
        assertEquals(0.0, manager.getAverageGrade());
    }
    
    @Test
    public void testGetAverageGradeWithOneStudent() {
        manager.addStudent("John Doe", 85.5);
        assertEquals(85.5, manager.getAverageGrade());
    }
    
    @Test
    public void testGetAverageGradeWithMultipleStudents() {
        manager.addStudent("John Doe", 80.0);
        manager.addStudent("Jane Smith", 90.0);
        manager.addStudent("Bob Johnson", 70.0);
        
        assertEquals(80.0, manager.getAverageGrade(), 0.01);
    }
}