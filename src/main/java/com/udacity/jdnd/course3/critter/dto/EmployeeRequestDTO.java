package com.udacity.jdnd.course3.critter.dto;

import java.time.LocalDate;
import java.util.Set;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    private Set<EmployeeSkill> skills;
    private LocalDate date;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}