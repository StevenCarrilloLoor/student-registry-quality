package com.student.model;

/**
 * Represents a regular student with a fixed grade.
 * Follows LSP: Can substitute BaseStudent without issues.
 */
public class RegularStudent extends BaseStudent {
    private final double grade;
    
    public RegularStudent(String name, double grade) {
        super(name);
        validateGrade(grade);
        this.grade = grade;
    }
    
    private void validateGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }
    
    @Override
    public double getGrade() {
        return grade;
    }
    
    @Override
    public String getStatus() {
        if (grade >= 90.0) return "Excellent";
        if (grade >= 80.0) return "Good";
        if (grade >= 70.0) return "Average";
        if (grade >= 60.0) return "Pass";
        return "Fail";
    }
    
    @Override
    public String toString() {
        return "Regular Student: " + getName() + ", Grade: " + grade + ", Status: " + getStatus();
    }
}