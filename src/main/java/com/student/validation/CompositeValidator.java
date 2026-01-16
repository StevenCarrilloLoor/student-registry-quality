package com.student.validation;

import com.student.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite validator that runs multiple validators.
 * Follows OCP: Can add new validators without modifying this class.
 */
public class CompositeValidator implements StudentValidator {
    private final List<StudentValidator> validators;
    
    public CompositeValidator() {
        this.validators = new ArrayList<>();
    }
    
    public void addValidator(StudentValidator validator) {
        validators.add(validator);
    }
    
    @Override
    public void validate(Student student) {
        for (StudentValidator validator : validators) {
            validator.validate(student);
        }
    }
    
    @Override
    public String getValidatorName() {
        return "CompositeValidator (" + validators.size() + " validators)";
    }
    
    public int getValidatorCount() {
        return validators.size();
    }
}