package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;
}
