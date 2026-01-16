package com.student.validation;

import com.student.Student;

/**
 * Validates that student name has minimum length.
 */
public class MinimumNameLengthValidator implements StudentValidator {
    private final int minimumLength;
    
    public MinimumNameLengthValidator(int minimumLength) {
        this.minimumLength = minimumLength;
    }
    
    @Override
    public void validate(Student student) {
        if (student.getName().length() < minimumLength) {
            throw new IllegalArgumentException(
                "Student name must have at least " + minimumLength + " characters"
            );
        }
    }
    
    @Override
    public String getValidatorName() {
        return "MinimumNameLengthValidator";
    }
}