package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long>{
    List<Schedule> getAllByEmployeesContains(Employee employee);
    List<Schedule> getAllByPetsContains(Pet pet);
    List<Schedule> getAllByPetsIn(List<Pet> pets);
}
