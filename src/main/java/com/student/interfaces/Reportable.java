package com.student.interfaces;

/**
 * Interface for entities that can generate reports.
 * ISP: Optional capability, not all students need this.
 */
public interface Reportable {
    /**
     * Generates a detailed report.
     * @return Report as string
     */
    String generateReport();
}