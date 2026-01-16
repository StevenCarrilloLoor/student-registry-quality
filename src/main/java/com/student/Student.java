package com.student;

/**
 * Represents a student with name and grade.
 * This class follows SRP by only handling student data.
 */
public class Student {
    private final String name;
    private final double grade;

    /**
     * Creates a new Student.
     * 
     * @param name  The student's name
     * @param grade The student's grade
     * @throws IllegalArgumentException if name is null/empty or grade is invalid
     */
    public Student(String name, double grade) {
        validateName(name);
        validateGrade(grade);
        
        this.name = name;
        this.grade = grade;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
    }

    private void validateGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student: " + name + ", Grade: " + grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}