package com.student.validation;

import com.student.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinimumNameLengthValidatorTest {
    
    @Test
    public void testValidateValidName() {
        MinimumNameLengthValidator validator = new MinimumNameLengthValidator(3);
        Student student = new Student("John", 85.0);
        
        assertDoesNotThrow(() -> validator.validate(student));
    }
    
    @Test
    public void testValidateShortName() {
        MinimumNameLengthValidator validator = new MinimumNameLengthValidator(5);
        Student student = new Student("Bob", 85.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(student);
        });
    }
    
    @Test
    public void testValidateExactMinimumLength() {
        MinimumNameLengthValidator validator = new MinimumNameLengthValidator(4);
        Student student = new Student("John", 85.0);
        
        assertDoesNotThrow(() -> validator.validate(student));
    }
    
    @Test
    public void testGetValidatorName() {
        MinimumNameLengthValidator validator = new MinimumNameLengthValidator(3);
        assertEquals("MinimumNameLengthValidator", validator.getValidatorName());
    }
}