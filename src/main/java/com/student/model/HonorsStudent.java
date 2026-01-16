package com.student.model;

/**
 * Represents an honors student with weighted grade calculation.
 * Follows LSP: Can substitute BaseStudent, adds bonus without breaking behavior.
 */
public class HonorsStudent extends BaseStudent {
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
        // Weighted grade with bonus, capped at 100
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
    
    @Override
    public String toString() {
        return "Honors Student: " + getName() + ", Base Grade: " + baseGrade + 
               ", Bonus: " + bonusPoints + ", Final: " + getGrade() + ", Status: " + getStatus();
    }
}