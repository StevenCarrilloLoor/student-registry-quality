package com.student.report;

import com.student.interfaces.Gradable;
import com.student.interfaces.Identifiable;
import com.student.interfaces.Reportable;
import com.student.interfaces.Statusable;
import java.util.List;

/**
 * Report generator that uses segregated interfaces (ISP).
 * Only depends on the specific interfaces it needs.
 */
public class ReportGenerator {
    
    /**
     * Generates basic summary (only needs Identifiable and Gradable).
     * ISP: Doesn't depend on unnecessary methods.
     */
    public String generateBasicSummary(List<? extends Identifiable> entities) {
        StringBuilder summary = new StringBuilder();
        summary.append("=== BASIC SUMMARY ===\n");
        summary.append("Total entities: ").append(entities.size()).append("\n");
        
        for (Identifiable entity : entities) {
            summary.append("- ").append(entity.getName()).append("\n");
        }
        
        return summary.toString();
    }
    
    /**
     * Generates grade summary (needs Identifiable and Gradable).
     */
    public String generateGradeSummary(List<? extends Gradable> gradables) {
        StringBuilder summary = new StringBuilder();
        summary.append("=== GRADE SUMMARY ===\n");
        
        if (gradables.isEmpty()) {
            summary.append("No grades available.\n");
            return summary.toString();
        }
        
        double total = 0.0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        
        for (Gradable gradable : gradables) {
            double grade = gradable.getGrade();
            total += grade;
            highest = Math.max(highest, grade);
            lowest = Math.min(lowest, grade);
        }
        
        summary.append("Count: ").append(gradables.size()).append("\n");
        summary.append("Average: ").append(String.format("%.2f", total / gradables.size())).append("\n");
        summary.append("Highest: ").append(highest).append("\n");
        summary.append("Lowest: ").append(lowest).append("\n");
        
        return summary.toString();
    }
    
    /**
     * Generates detailed reports (only for Reportable entities).
     * ISP: Only works with entities that implement Reportable.
     */
    public String generateDetailedReports(List<? extends Reportable> reportables) {
        StringBuilder summary = new StringBuilder();
        summary.append("=== DETAILED REPORTS ===\n\n");
        
        for (Reportable reportable : reportables) {
            summary.append(reportable.generateReport()).append("\n\n");
        }
        
        return summary.toString();
    }
    
    /**
     * Generates status distribution (needs Identifiable and Statusable).
     */
    public String generateStatusDistribution(List<? extends Statusable> statusables) {
        StringBuilder summary = new StringBuilder();
        summary.append("=== STATUS DISTRIBUTION ===\n");
        
        // Simple count by status
        for (Statusable statusable : statusables) {
            if (statusable instanceof Identifiable) {
                Identifiable id = (Identifiable) statusable;
                summary.append(id.getName()).append(": ").append(statusable.getStatus()).append("\n");
            }
        }
        
        return summary.toString();
    }
}