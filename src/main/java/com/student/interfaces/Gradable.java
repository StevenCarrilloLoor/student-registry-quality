package com.student.interfaces;

/**
 * Interface for entities that have a grade.
 * ISP: Segregated from other student capabilities.
 */
public interface Gradable {
    /**
     * Gets the grade.
     * @return The grade value
     */
    double getGrade();
}