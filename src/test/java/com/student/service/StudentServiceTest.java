package com.student.service;

import com.student.model.BaseStudent;
import com.student.model.RegularStudent;
import com.student.model.HonorsStudent;
import com.student.repository.InMemoryStudentRepository;
import com.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    
    private StudentRepository repository;
    private StudentService service;
    
    @BeforeEach
    public void setUp() {
        repository = new InMemoryStudentRepository();
        service = new StudentService(repository);
    }
    
    @Test
    public void testConstructorWithNullRepository() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudentService(null);
        });
    }
    
    @Test
    public void testAddStudent() {
        BaseStudent student = new RegularStudent("John Doe", 85.0);
        service.addStudent(student);
        
        assertEquals(1, service.getStudentCount());
    }
    
    @Test
    public void testAddNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(null);
        });
    }
    
    @Test
    public void testFindStudent() {
        BaseStudent student = new RegularStudent("John Doe", 85.0);
        service.addStudent(student);
        
        var found = service.findStudent("John Doe");
        
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }
    
    @Test
    public void testFindStudentNotFound() {
        var found = service.findStudent("NonExistent");
        
        assertFalse(found.isPresent());
    }
    
    @Test
    public void testGetAllStudents() {
        service.addStudent(new RegularStudent("John Doe", 85.0));
        service.addStudent(new RegularStudent("Jane Smith", 90.0));
        
        assertEquals(2, service.getAllStudents().size());
    }
    
    @Test
    public void testRemoveStudent() {
        service.addStudent(new RegularStudent("John Doe", 85.0));
        
        boolean removed = service.removeStudent("John Doe");
        
        assertTrue(removed);
        assertEquals(0, service.getStudentCount());
    }
    
    @Test
    public void testRemoveStudentNotFound() {
        boolean removed = service.removeStudent("NonExistent");
        
        assertFalse(removed);
    }
    
    @Test
    public void testGetAverageGrade() {
        service.addStudent(new RegularStudent("John Doe", 80.0));
        service.addStudent(new RegularStudent("Jane Smith", 90.0));
        service.addStudent(new RegularStudent("Bob Wilson", 70.0));
        
        assertEquals(80.0, service.getAverageGrade(), 0.01);
    }
    
    @Test
    public void testGetAverageGradeEmpty() {
        assertEquals(0.0, service.getAverageGrade());
    }
    
    @Test
    public void testGetStudentsByStatus() {
        service.addStudent(new RegularStudent("John Doe", 95.0));
        service.addStudent(new RegularStudent("Jane Smith", 85.0));
        service.addStudent(new HonorsStudent("Bob Wilson", 90.0, 5.0));
        
        var excellent = service.getStudentsByStatus("Excellent");
        assertEquals(1, excellent.size());
        
        var good = service.getStudentsByStatus("Good");
        assertEquals(1, good.size());
    }
    
    @Test
    public void testDIPWithDifferentRepository() {
        // DIP Test: Service works with any StudentRepository implementation
        StudentRepository customRepo = new InMemoryStudentRepository();
        StudentService customService = new StudentService(customRepo);
        
        customService.addStudent(new RegularStudent("Test Student", 100.0));
        
        assertEquals(1, customService.getStudentCount());
    }
}