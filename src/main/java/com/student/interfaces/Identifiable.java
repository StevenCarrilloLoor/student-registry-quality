package com.student.interfaces;

/**
 * Interface for entities that have an identifier (name).
 * ISP: Segregated from other capabilities.
 */
public interface Identifiable {
    /**
     * Gets the name/identifier.
     * @return The name
     */
    String getName();
}