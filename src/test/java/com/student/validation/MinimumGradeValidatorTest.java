package com.student.validation;

import com.student.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinimumGradeValidatorTest {
    
    @Test
    public void testValidateValidGrade() {
        MinimumGradeValidator validator = new MinimumGradeValidator(60.0);
        Student student = new Student("John Doe", 85.0);
        
        assertDoesNotThrow(() -> validator.validate(student));
    }
    
    @Test
    public void testValidateLowGrade() {
        MinimumGradeValidator validator = new MinimumGradeValidator(60.0);
        Student student = new Student("John Doe", 50.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(student);
        });
    }
    
    @Test
    public void testValidateExactMinimumGrade() {
        MinimumGradeValidator validator = new MinimumGradeValidator(60.0);
        Student student = new Student("John Doe", 60.0);
        
        assertDoesNotThrow(() -> validator.validate(student));
    }
    
    @Test
    public void testGetValidatorName() {
        MinimumGradeValidator validator = new MinimumGradeValidator(60.0);
        assertEquals("MinimumGradeValidator", validator.getValidatorName());
    }
}