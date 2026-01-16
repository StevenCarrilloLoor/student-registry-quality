package com.student;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing students.
 * This class follows SRP by focusing only on business logic.
 */
public class StudentManager {
    private final List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a student to the registry.
     * 
     * @param student The student to add
     * @throws IllegalArgumentException if student is null or already exists
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student already exists: " + student.getName());
        }
        
        students.add(student);
    }

    /**
     * Adds a student by name and grade.
     * 
     * @param name  Student's name
     * @param grade Student's grade
     */
    public void addStudent(String name, double grade) {
        Student student = new Student(name, grade);
        addStudent(student);
    }

    /**
     * Gets all students in the registry.
     * 
     * @return Unmodifiable list of students
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Gets the total number of students.
     * 
     * @return Number of students
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Calculates the average grade of all students.
     * 
     * @return Average grade, or 0.0 if no students
     */
    public double getAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        
        return sum / students.size();
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        
        try {
            manager.addStudent("John Doe", 85.5);
            manager.addStudent("Jane Smith", 92.0);
            
            System.out.println("=== Student Registry ===");
            for (Student student : manager.getStudents()) {
                System.out.println(student);
            }
            
            System.out.println("\nTotal students: " + manager.getStudentCount());
            System.out.println("Average grade: " + manager.getAverageGrade());
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}