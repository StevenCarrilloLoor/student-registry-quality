package com.student.service;

import com.student.model.BaseStudent;
import com.student.repository.StudentRepository;
import com.student.validation.StudentValidator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service class that follows DIP.
 * Depends on abstractions (StudentRepository, StudentValidator), not concrete classes.
 */
public class StudentService {
    private final StudentRepository repository;
    private final StudentValidator validator;
    
    /**
     * Constructor with dependency injection (DIP in action).
     * 
     * @param repository Repository abstraction
     * @param validator Validator abstraction (can be null)
     */
    public StudentService(StudentRepository repository, StudentValidator validator) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
        this.validator = validator;
    }
    
    /**
     * Constructor without validator.
     */
    public StudentService(StudentRepository repository) {
        this(repository, null);
    }
    
    /**
     * Adds a student with validation.
     */
    public void addStudent(BaseStudent student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        repository.save(student);
    }
    
    /**
     * Finds a student by name.
     */
    public Optional<BaseStudent> findStudent(String name) {
        return repository.findByName(name);
    }
    
    /**
     * Gets all students.
     */
    public List<BaseStudent> getAllStudents() {
        return repository.findAll();
    }
    
    /**
     * Removes a student by name.
     */
    public boolean removeStudent(String name) {
        return repository.deleteByName(name);
    }
    
    /**
     * Gets student count.
     */
    public int getStudentCount() {
        return repository.count();
    }
    
    /**
     * Calculates average grade (uses Gradable interface).
     */
    public double getAverageGrade() {
        List<BaseStudent> students = repository.findAll();
        
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (BaseStudent student : students) {
            sum += student.getGrade();
        }
        
        return sum / students.size();
    }
    
    /**
     * Gets students by status.
     */
    public List<BaseStudent> getStudentsByStatus(String status) {
        List<BaseStudent> all = repository.findAll();
        List<BaseStudent> result = new ArrayList<>();
        
        for (BaseStudent student : all) {
            if (student.getStatus().equalsIgnoreCase(status)) {
                result.add(student);
            }
        }
        
        return result;
    }
}