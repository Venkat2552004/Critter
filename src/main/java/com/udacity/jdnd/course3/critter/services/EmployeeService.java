package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo empRepo;

    public Employee findEmployeeById(long empId) { 
        return empRepo.getOne(empId);
    }

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) { 
        return empRepo.findAll().stream()
                .filter(employee -> employee.getDaysAvailable().contains(date.getDayOfWeek()) &&
                        employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public Employee createOrUpdateEmployee(Employee employee) { 
        return empRepo.save(employee);
    }

    public void updateEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = empRepo.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        empRepo.save(employee);
    }
}
