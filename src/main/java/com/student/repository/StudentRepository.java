package com.student.repository;

import com.student.model.BaseStudent;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for student persistence.
 * DIP: High-level code depends on this abstraction, not concrete implementations.
 */
public interface StudentRepository {
    /**
     * Saves a student.
     */
    void save(BaseStudent student);
    
    /**
     * Finds a student by name.
     */
    Optional<BaseStudent> findByName(String name);
    
    /**
     * Gets all students.
     */
    List<BaseStudent> findAll();
    
    /**
     * Deletes a student by name.
     */
    boolean deleteByName(String name);
    
    /**
     * Gets the total count of students.
     */
    int count();
    
    /**
     * Clears all students.
     */
    void clear();
}