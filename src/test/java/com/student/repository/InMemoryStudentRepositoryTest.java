package com.student.repository;

import com.student.model.BaseStudent;
import com.student.model.RegularStudent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class InMemoryStudentRepositoryTest {
    
    private StudentRepository repository;
    
    @BeforeEach
    public void setUp() {
        repository = new InMemoryStudentRepository();
    }
    
    @Test
    public void testSaveStudent() {
        BaseStudent student = new RegularStudent("John Doe", 85.0);
        repository.save(student);
        
        assertEquals(1, repository.count());
    }
    
    @Test
    public void testSaveNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> {
            repository.save(null);
        });
    }
    
    @Test
    public void testSaveDuplicateStudent() {
        BaseStudent student = new RegularStudent("John Doe", 85.0);
        repository.save(student);
        
        assertThrows(IllegalArgumentException.class, () -> {
            repository.save(new RegularStudent("John Doe", 90.0));
        });
    }
    
    @Test
    public void testFindByName() {
        BaseStudent student = new RegularStudent("John Doe", 85.0);
        repository.save(student);
        
        Optional<BaseStudent> found = repository.findByName("John Doe");
        
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }
    
    @Test
    public void testFindByNameNotFound() {
        Optional<BaseStudent> found = repository.findByName("NonExistent");
        
        assertFalse(found.isPresent());
    }
    
    @Test
    public void testFindAll() {
        repository.save(new RegularStudent("John Doe", 85.0));
        repository.save(new RegularStudent("Jane Smith", 90.0));
        
        assertEquals(2, repository.findAll().size());
    }
    
    @Test
    public void testFindAllEmpty() {
        assertTrue(repository.findAll().isEmpty());
    }
    
    @Test
    public void testDeleteByName() {
        repository.save(new RegularStudent("John Doe", 85.0));
        
        boolean deleted = repository.deleteByName("John Doe");
        
        assertTrue(deleted);
        assertEquals(0, repository.count());
    }
    
    @Test
    public void testDeleteByNameNotFound() {
        boolean deleted = repository.deleteByName("NonExistent");
        
        assertFalse(deleted);
    }
    
    @Test
    public void testCount() {
        assertEquals(0, repository.count());
        
        repository.save(new RegularStudent("John Doe", 85.0));
        assertEquals(1, repository.count());
        
        repository.save(new RegularStudent("Jane Smith", 90.0));
        assertEquals(2, repository.count());
    }
    
    @Test
    public void testClear() {
        repository.save(new RegularStudent("John Doe", 85.0));
        repository.save(new RegularStudent("Jane Smith", 90.0));
        
        repository.clear();
        
        assertEquals(0, repository.count());
        assertTrue(repository.findAll().isEmpty());
    }
}