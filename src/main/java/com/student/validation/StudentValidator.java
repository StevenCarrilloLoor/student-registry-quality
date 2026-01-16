package com.student.validation;

import com.student.Student;

/**
 * Interface for student validation strategies.
 * Follows OCP: New validators can be added without modifying existing code.
 */
public interface StudentValidator {
    /**
     * Validates a student.
     * 
     * @param student The student to validate
     * @throws IllegalArgumentException if validation fails
     */
    void validate(Student student);
    
    /**
     * Gets the name of this validator.
     * 
     * @return Validator name
     */
    String getValidatorName();
}