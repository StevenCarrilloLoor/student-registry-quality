package com.student.model;

import com.student.interfaces.Gradable;
import com.student.interfaces.Identifiable;
import com.student.interfaces.Statusable;

/**
 * Abstract base class for all student types.
 * Now implements segregated interfaces (ISP).
 */
public abstract class BaseStudent implements Identifiable, Gradable, Statusable {
    private final String name;
    
    protected BaseStudent(String name) {
        validateName(name);
        this.name = name;
    }
    
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Gets the student's grade (Gradable interface).
     */
    @Override
    public abstract double getGrade();
    
    /**
     * Gets the student's status (Statusable interface).
     */
    @Override
    public abstract String getStatus();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseStudent student = (BaseStudent) obj;
        return name.equals(student.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}