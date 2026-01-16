package com.student;

import com.student.model.RegularStudent;

/**
 * Represents a student with name and grade.
 * This class is now a wrapper for RegularStudent for backward compatibility.
 * 
 * @deprecated Use RegularStudent or HonorsStudent directly for better type safety
 */
@Deprecated
public class Student {
    private final RegularStudent regularStudent;

    public Student(String name, double grade) {
        this.regularStudent = new RegularStudent(name, grade);
    }

    public String getName() {
        return regularStudent.getName();
    }

    public double getGrade() {
        return regularStudent.getGrade();
    }
    
    public String getStatus() {
        return regularStudent.getStatus();
    }

    @Override
    public String toString() {
        return "Student: " + getName() + ", Grade: " + getGrade();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}