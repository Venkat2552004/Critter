package com.udacity.jdnd.course3.critter.entities;

import java.time.DayOfWeek;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
}
