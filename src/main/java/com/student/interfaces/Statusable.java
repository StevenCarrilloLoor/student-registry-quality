package com.student.interfaces;

/**
 * Interface for entities that have a status.
 * ISP: Segregated from other capabilities.
 */
public interface Statusable {
    /**
     * Gets the status.
     * @return The status description
     */
    String getStatus();
}