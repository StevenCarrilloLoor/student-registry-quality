package com.student;

import com.student.validation.MinimumGradeValidator;
import com.student.validation.MinimumNameLengthValidator;
import com.student.model.HonorsStudent;
import com.student.model.RegularStudent;
import com.student.validation.CompositeValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.student.model.HonorsStudent;
import com.student.model.RegularStudent;
import com.student.repository.StudentRepository;
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
    
    // Nuevas pruebas para OCP
    // Pruebas para LSP
    
    @Test
    public void testAddRegularStudent() {
        RegularStudent student = new com.student.model.RegularStudent("John Doe", 85.0);
        manager.addStudent(student);
        
        assertEquals(1, manager.getStudentCount());
    }
    
    @Test
    public void testAddHonorsStudent() {
        HonorsStudent student = new com.student.model.HonorsStudent("Jane Smith", 88.0, 5.0);
        manager.addStudent(student);
        
        assertEquals(1, manager.getStudentCount());
    }
    
    @Test
    public void testMixedStudentTypes() {
        manager.addStudent(new com.student.model.RegularStudent("John Doe", 85.0));
        manager.addStudent(new com.student.model.HonorsStudent("Jane Smith", 88.0, 5.0));
        
        assertEquals(2, manager.getStudentCount());
    }
    
    @Test
    public void testAverageGradeWithMixedTypes() {
        manager.addStudent(new com.student.model.RegularStudent("John Doe", 80.0));
        manager.addStudent(new com.student.model.HonorsStudent("Jane Smith", 85.0, 10.0));
        
        // Average: (80 + 95) / 2 = 87.5
        assertEquals(87.5, manager.getAverageGrade(), 0.01);
    }
    
    @Test
    public void testGetStudentsByStatus() {
        manager.addStudent(new com.student.model.RegularStudent("John Doe", 95.0));
        manager.addStudent(new com.student.model.RegularStudent("Bob Wilson", 85.0));
        manager.addStudent(new com.student.model.HonorsStudent("Jane Smith", 90.0, 5.0));
        
        // John Doe tiene 95.0 → Status "Excellent"
        var excellentStudents = manager.getStudentsByStatus("Excellent");
        assertEquals(1, excellentStudents.size());
        assertEquals("John Doe", excellentStudents.get(0).getName());
        
        // Jane Smith tiene 90+5=95 → Status "Honors with Distinction"
        var honorsStudents = manager.getStudentsByStatus("Honors with Distinction");
        assertEquals(1, honorsStudents.size());
        assertEquals("Jane Smith", honorsStudents.get(0).getName());
        
        // Bob Wilson tiene 85.0 → Status "Good"
        var goodStudents = manager.getStudentsByStatus("Good");
        assertEquals(1, goodStudents.size());
        assertEquals("Bob Wilson", goodStudents.get(0).getName());
    }
    // Pruebas para DIP
    
    @Test
    public void testDIPConstructorWithRepository() {
        StudentRepository customRepo = new com.student.repository.InMemoryStudentRepository();
        StudentManager customManager = new StudentManager(customRepo, null);
        
        customManager.addStudent(new com.student.model.RegularStudent("Test", 85.0));
        
        assertEquals(1, customManager.getStudentCount());
    }
    
    @Test
    public void testDIPDependsOnAbstraction() {
        // DIP: StudentManager depends on StudentRepository interface,
        // not concrete InMemoryStudentRepository
        StudentRepository repo = new com.student.repository.InMemoryStudentRepository();
        assertNotNull(repo);
        
        StudentManager manager = new StudentManager(repo, null);
        assertNotNull(manager);
    }
}