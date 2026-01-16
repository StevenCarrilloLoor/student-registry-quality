package com.student.validation;

import com.student.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeValidatorTest {
    
    private CompositeValidator compositeValidator;
    
    @BeforeEach
    public void setUp() {
        compositeValidator = new CompositeValidator();
    }
    
    @Test
    public void testValidateWithNoValidators() {
        Student student = new Student("Bob", 50.0);
        
        assertDoesNotThrow(() -> compositeValidator.validate(student));
    }
    
    @Test
    public void testValidateWithOneValidator() {
        compositeValidator.addValidator(new MinimumGradeValidator(60.0));
        Student student = new Student("John Doe", 85.0);
        
        assertDoesNotThrow(() -> compositeValidator.validate(student));
    }
    
    @Test
    public void testValidateWithMultipleValidators() {
        compositeValidator.addValidator(new MinimumNameLengthValidator(5));
        compositeValidator.addValidator(new MinimumGradeValidator(60.0));
        
        Student student = new Student("John Doe", 85.0);
        
        assertDoesNotThrow(() -> compositeValidator.validate(student));
    }
    
    @Test
    public void testValidateFailsOnFirstValidator() {
        compositeValidator.addValidator(new MinimumNameLengthValidator(10));
        compositeValidator.addValidator(new MinimumGradeValidator(60.0));
        
        Student student = new Student("Bob", 85.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            compositeValidator.validate(student);
        });
    }
    
    @Test
    public void testValidateFailsOnSecondValidator() {
        compositeValidator.addValidator(new MinimumNameLengthValidator(3));
        compositeValidator.addValidator(new MinimumGradeValidator(90.0));
        
        Student student = new Student("John Doe", 85.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            compositeValidator.validate(student);
        });
    }
    
    @Test
    public void testGetValidatorCount() {
        assertEquals(0, compositeValidator.getValidatorCount());
        
        compositeValidator.addValidator(new MinimumNameLengthValidator(3));
        assertEquals(1, compositeValidator.getValidatorCount());
        
        compositeValidator.addValidator(new MinimumGradeValidator(60.0));
        assertEquals(2, compositeValidator.getValidatorCount());
    }
    
    @Test
    public void testGetValidatorName() {
        compositeValidator.addValidator(new MinimumNameLengthValidator(3));
        compositeValidator.addValidator(new MinimumGradeValidator(60.0));
        
        assertEquals("CompositeValidator (2 validators)", compositeValidator.getValidatorName());
    }
}