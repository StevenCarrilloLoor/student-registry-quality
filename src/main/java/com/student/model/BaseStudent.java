package com.student.model;

/**
 * Abstract base class for all student types.
 * Follows LSP: All subclasses can be substituted without breaking behavior.
 */
public abstract class BaseStudent {
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
    
    public String getName() {
        return name;
    }
    
    /**
     * Gets the student's grade.
     * All students must be able to provide a grade.
     */
    public abstract double getGrade();
    
    /**
     * Gets the student's status.
     */
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