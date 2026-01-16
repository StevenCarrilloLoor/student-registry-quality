package com.student.validation;

import com.student.Student;

/**
 * Validates that student grade meets minimum requirement.
 */
public class MinimumGradeValidator implements StudentValidator {
    private final double minimumGrade;
    
    public MinimumGradeValidator(double minimumGrade) {
        this.minimumGrade = minimumGrade;
    }
    
    @Override
    public void validate(Student student) {
        if (student.getGrade() < minimumGrade) {
            throw new IllegalArgumentException(
                "Student grade must be at least " + minimumGrade
            );
        }
    }
    
    @Override
    public String getValidatorName() {
        return "MinimumGradeValidator";
    }
}