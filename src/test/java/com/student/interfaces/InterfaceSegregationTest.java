package com.student.interfaces;

import com.student.model.RegularStudent;
import com.student.model.HonorsStudent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify ISP is correctly applied.
 */
public class InterfaceSegregationTest {
    
    @Test
    public void testRegularStudentImplementsRequiredInterfaces() {
        RegularStudent student = new RegularStudent("John Doe", 85.0);
        
        // RegularStudent implementa las interfaces básicas
        assertTrue(student instanceof Identifiable);
        assertTrue(student instanceof Gradable);
        assertTrue(student instanceof Statusable);
        
        // Pero NO implementa Reportable (ISP)
        assertFalse(student instanceof Reportable);
    }
    
    @Test
    public void testHonorsStudentImplementsAllInterfaces() {
        HonorsStudent student = new HonorsStudent("Jane Smith", 90.0, 5.0);
        
        // HonorsStudent implementa todas las interfaces
        assertTrue(student instanceof Identifiable);
        assertTrue(student instanceof Gradable);
        assertTrue(student instanceof Statusable);
        assertTrue(student instanceof Reportable);
    }
    
    @Test
    public void testIdentifiableInterface() {
        Identifiable student = new RegularStudent("John Doe", 85.0);
        
        assertEquals("John Doe", student.getName());
    }
    
    @Test
    public void testGradableInterface() {
        Gradable student = new RegularStudent("John Doe", 85.0);
        
        assertEquals(85.0, student.getGrade());
    }
    
    @Test
    public void testStatusableInterface() {
        Statusable student = new RegularStudent("John Doe", 85.0);
        
        assertEquals("Good", student.getStatus());
    }
    
    @Test
    public void testReportableInterface() {
        Reportable student = new HonorsStudent("Jane Smith", 90.0, 5.0);
        
        String report = student.generateReport();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }
}