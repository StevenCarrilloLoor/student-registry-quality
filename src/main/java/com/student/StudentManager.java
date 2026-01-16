package com.student;

import com.student.model.BaseStudent;
import com.student.model.RegularStudent;
import com.student.model.HonorsStudent;
import com.student.repository.InMemoryStudentRepository;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;
import com.student.validation.StudentValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * Main manager class that now uses DIP through StudentService.
 * Depends on abstractions, not concrete implementations.
 */
public class StudentManager {
    private final StudentService studentService;
    
    /**
     * Constructor with dependency injection (DIP).
     */
    public StudentManager(StudentRepository repository, StudentValidator validator) {
        this.studentService = new StudentService(repository, validator);
    }
    
    /**
     * Default constructor (for backward compatibility).
     */
    public StudentManager() {
        this(new InMemoryStudentRepository(), null);
    }
    
    public void setValidator(StudentValidator validator) {
        // Note: In pure DIP, validator should be injected in constructor
        // This is kept for backward compatibility
    }

    public void addStudent(BaseStudent student) {
        studentService.addStudent(student);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        addStudent(new RegularStudent(student.getName(), student.getGrade()));
    }

    public void addStudent(String name, double grade) {
        Student student = new Student(name, grade);
        addStudent(student);
    }

    public List<BaseStudent> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    public List<Student> getStudents() {
        List<BaseStudent> all = studentService.getAllStudents();
        List<Student> legacyList = new ArrayList<>();
        for (BaseStudent bs : all) {
            legacyList.add(new Student(bs.getName(), bs.getGrade()));
        }
        return legacyList;
    }

    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    public double getAverageGrade() {
        return studentService.getAverageGrade();
    }
    
    public List<BaseStudent> getStudentsByStatus(String status) {
        return studentService.getStudentsByStatus(status);
    }

    public static void main(String[] args) {
        // DIP Demo: Using dependency injection
        StudentRepository repository = new InMemoryStudentRepository();
        StudentManager manager = new StudentManager(repository, null);
        
        try {
            manager.addStudent(new RegularStudent("John Doe", 85.5));
            manager.addStudent(new HonorsStudent("Jane Smith", 88.0, 5.0));
            manager.addStudent(new RegularStudent("Bob Wilson", 72.0));
            
            System.out.println("=== Student Registry (DIP Demo) ===");
            for (BaseStudent student : manager.getAllStudents()) {
                System.out.println(student);
            }
            
            System.out.println("\nTotal students: " + manager.getStudentCount());
            System.out.println("Average grade: " + manager.getAverageGrade());
            
            System.out.println("\nDIP Benefits:");
            System.out.println("- StudentManager depends on StudentRepository interface");
            System.out.println("- Can easily swap InMemoryRepository for DatabaseRepository");
            System.out.println("- High-level modules don't depend on low-level details");
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}