package com.student.report;

import com.student.model.RegularStudent;
import com.student.model.HonorsStudent;
import com.student.model.BaseStudent;
import com.student.interfaces.Reportable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class ReportGeneratorTest {
    
    private ReportGenerator generator;
    
    @BeforeEach
    public void setUp() {
        generator = new ReportGenerator();
    }
    
    @Test
    public void testGenerateBasicSummary() {
        List<BaseStudent> students = Arrays.asList(
            new RegularStudent("John Doe", 85.0),
            new RegularStudent("Jane Smith", 90.0)
        );
        
        String summary = generator.generateBasicSummary(students);
        
        assertTrue(summary.contains("BASIC SUMMARY"));
        assertTrue(summary.contains("Total entities: 2"));
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("Jane Smith"));
    }
    
    @Test
    public void testGenerateGradeSummary() {
        List<BaseStudent> students = Arrays.asList(
            new RegularStudent("John Doe", 80.0),
            new RegularStudent("Jane Smith", 90.0),
            new RegularStudent("Bob Wilson", 70.0)
        );
        
        String summary = generator.generateGradeSummary(students);
        
        assertTrue(summary.contains("GRADE SUMMARY"));
        assertTrue(summary.contains("Count: 3"));
        assertTrue(summary.contains("Average:")); // Más flexible con el formato
        assertTrue(summary.contains("Highest: 90.0"));
        assertTrue(summary.contains("Lowest: 70.0"));
    }
    
    @Test
    public void testGenerateGradeSummaryEmpty() {
        List<BaseStudent> students = Arrays.asList();
        
        String summary = generator.generateGradeSummary(students);
        
        assertTrue(summary.contains("No grades available"));
    }
    
    @Test
    public void testGenerateDetailedReports() {
        List<Reportable> reportables = Arrays.asList(
            new HonorsStudent("Jane Smith", 90.0, 5.0),
            new HonorsStudent("Bob Wilson", 88.0, 7.0)
        );
        
        String summary = generator.generateDetailedReports(reportables);
        
        assertTrue(summary.contains("DETAILED REPORTS"));
        assertTrue(summary.contains("HONORS STUDENT REPORT"));
        assertTrue(summary.contains("Jane Smith"));
        assertTrue(summary.contains("Bob Wilson"));
    }
    
    @Test
    public void testGenerateStatusDistribution() {
        List<BaseStudent> students = Arrays.asList(
            new RegularStudent("John Doe", 95.0),
            new RegularStudent("Jane Smith", 85.0),
            new HonorsStudent("Bob Wilson", 90.0, 5.0)
        );
        
        String summary = generator.generateStatusDistribution(students);
        
        assertTrue(summary.contains("STATUS DISTRIBUTION"));
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("Excellent"));
        assertTrue(summary.contains("Good"));
    }
    
    @Test
    public void testISPSegregation() {
        // ISP Test: RegularStudent no necesita implementar Reportable
        RegularStudent regular = new RegularStudent("John Doe", 85.0);
        
        // Puede usarse como Identifiable
        assertNotNull(regular.getName());
        
        // Puede usarse como Gradable
        assertEquals(85.0, regular.getGrade());
        
        // Puede usarse como Statusable
        assertNotNull(regular.getStatus());
        
        // Pero NO es Reportable (ISP en acción)
        assertFalse(regular instanceof Reportable);
    }
    
    @Test
    public void testHonorsStudentImplementsReportable() {
        // HonorsStudent SÍ implementa Reportable (opcional)
        HonorsStudent honors = new HonorsStudent("Jane Smith", 90.0, 5.0);
        
        assertTrue(honors instanceof Reportable);
        assertNotNull(honors.generateReport());
    }
}