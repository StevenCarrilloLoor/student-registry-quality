package com.student;

import com.student.model.BaseStudent;
import com.student.validation.StudentValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing students.
 * Now follows LSP: Can work with any BaseStudent subclass.
 */
public class StudentManager {
    private final List<BaseStudent> students;
    private StudentValidator validator;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.validator = null;
    }
    
    public void setValidator(StudentValidator validator) {
        this.validator = validator;
    }

    /**
     * Adds a BaseStudent to the registry.
     * LSP: Any BaseStudent subclass works correctly.
     */
    public void addStudent(BaseStudent student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student already exists: " + student.getName());
        }
        
        students.add(student);
    }

    /**
     * Adds a student using legacy Student class (backward compatibility).
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // Validate using legacy validator if set
        if (validator != null) {
            validator.validate(student);
        }
        
        // Convert to BaseStudent
        addStudent(new com.student.model.RegularStudent(student.getName(), student.getGrade()));
    }

    /**
     * Adds a student by name and grade (creates RegularStudent).
     */
    public void addStudent(String name, double grade) {
        Student student = new Student(name, grade);
        addStudent(student);
    }

    /**
     * Gets all students in the registry.
     */
    public List<BaseStudent> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Legacy method for backward compatibility.
     */
    public List<Student> getStudents() {
        List<Student> legacyList = new ArrayList<>();
        for (BaseStudent bs : students) {
            legacyList.add(new Student(bs.getName(), bs.getGrade()));
        }
        return legacyList;
    }

    public int getStudentCount() {
        return students.size();
    }

    /**
     * LSP in action: Works with any BaseStudent subclass.
     */
    public double getAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (BaseStudent student : students) {
            sum += student.getGrade(); // Polymorphic call
        }
        
        return sum / students.size();
    }
    
    /**
     * Gets students by status.
     */
    public List<BaseStudent> getStudentsByStatus(String status) {
        List<BaseStudent> result = new ArrayList<>();
        for (BaseStudent student : students) {
            if (student.getStatus().equalsIgnoreCase(status)) {
                result.add(student);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        
        try {
            // Demo con diferentes tipos de estudiantes (LSP en acción)
            manager.addStudent(new com.student.model.RegularStudent("John Doe", 85.5));
            manager.addStudent(new com.student.model.HonorsStudent("Jane Smith", 88.0, 5.0));
            manager.addStudent(new com.student.model.RegularStudent("Bob Wilson", 72.0));
            
            System.out.println("=== Student Registry (LSP Demo) ===");
            for (BaseStudent student : manager.getAllStudents()) {
                System.out.println(student);
            }
            
            System.out.println("\nTotal students: " + manager.getStudentCount());
            System.out.println("Average grade: " + manager.getAverageGrade());
            
            System.out.println("\nHonors students:");
            for (BaseStudent student : manager.getStudentsByStatus("Honors")) {
                System.out.println("  - " + student.getName());
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}