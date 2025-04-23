package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.*;

import java.time.DayOfWeek;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);
}
