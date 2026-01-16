package com.student.model;

import com.student.interfaces.Reportable;

/**
 * Represents an honors student with weighted grade calculation.
 * Implements Reportable (ISP) - optional capability.
 */
public class HonorsStudent extends BaseStudent implements Reportable {
    private final double baseGrade;
    private final double bonusPoints;
    
    public HonorsStudent(String name, double baseGrade, double bonusPoints) {
        super(name);
        validateGrade(baseGrade);
        validateBonus(bonusPoints);
        this.baseGrade = baseGrade;
        this.bonusPoints = bonusPoints;
    }
    
    private void validateGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Base grade must be between 0 and 100");
        }
    }
    
    private void validateBonus(double bonus) {
        if (bonus < 0.0 || bonus > 10.0) {
            throw new IllegalArgumentException("Bonus points must be between 0 and 10");
        }
    }
    
    @Override
    public double getGrade() {
        return Math.min(100.0, baseGrade + bonusPoints);
    }
    
    @Override
    public String getStatus() {
        double finalGrade = getGrade();
        if (finalGrade >= 95.0) return "Honors with Distinction";
        if (finalGrade >= 90.0) return "Honors";
        if (finalGrade >= 85.0) return "High Honors";
        return "Honors Candidate";
    }
    
    public double getBaseGrade() {
        return baseGrade;
    }
    
    public double getBonusPoints() {
        return bonusPoints;
    }
    
    /**
     * Generates detailed report (Reportable interface).
     * ISP: Only HonorsStudent implements this, RegularStudent doesn't need it.
     */
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== HONORS STUDENT REPORT ===\n");
        report.append("Name: ").append(getName()).append("\n");
        report.append("Base Grade: ").append(baseGrade).append("\n");
        report.append("Bonus Points: ").append(bonusPoints).append("\n");
        report.append("Final Grade: ").append(getGrade()).append("\n");
        report.append("Status: ").append(getStatus()).append("\n");
        report.append("Performance: ");
        
        if (getGrade() >= 95.0) {
            report.append("Outstanding performance with distinction");
        } else if (getGrade() >= 90.0) {
            report.append("Excellent honors level achievement");
        } else {
            report.append("Strong honors candidate");
        }
        
        return report.toString();
    }
    
    @Override
    public String toString() {
        return "Honors Student: " + getName() + ", Base Grade: " + baseGrade + 
               ", Bonus: " + bonusPoints + ", Final: " + getGrade() + ", Status: " + getStatus();
    }
}