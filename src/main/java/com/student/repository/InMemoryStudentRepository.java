package com.student.repository;

import com.student.model.BaseStudent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of StudentRepository.
 * DIP: This is a concrete implementation that can be swapped easily.
 */
public class InMemoryStudentRepository implements StudentRepository {
    private final List<BaseStudent> students;
    
    public InMemoryStudentRepository() {
        this.students = new ArrayList<>();
    }
    
    @Override
    public void save(BaseStudent student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // Check for duplicates
        if (findByName(student.getName()).isPresent()) {
            throw new IllegalArgumentException("Student already exists: " + student.getName());
        }
        
        students.add(student);
    }
    
    @Override
    public Optional<BaseStudent> findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst();
    }
    
    @Override
    public List<BaseStudent> findAll() {
        return new ArrayList<>(students);
    }
    
    @Override
    public boolean deleteByName(String name) {
        return students.removeIf(s -> s.getName().equals(name));
    }
    
    @Override
    public int count() {
        return students.size();
    }
    
    @Override
    public void clear() {
        students.clear();
    }
}