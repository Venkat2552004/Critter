package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo empRepo;

    // Create or update an employee record
    public Employee createOrUpdateEmployee(Employee employee) {
        return empRepo.saveAndFlush(employee);
    }

    // Find an employee by their ID
    public Employee findEmployeeById(long empId) {
        return empRepo.findById(empId)
                .orElseThrow(() -> new NotFoundException("Could not find Employee with ID: " + empId));
    }

    // Find employees available for a specific service on a given date
    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek day = date.getDayOfWeek();
        List<Employee> result = new ArrayList<>();
        for (Employee employee : empRepo.findAll()) {
            Set<DayOfWeek> availableDays = employee.getDaysAvailable();
            Set<EmployeeSkill> employeeSkills = employee.getSkills();
            if (availableDays != null && availableDays.contains(day) &&
                    employeeSkills != null && employeeSkills.containsAll(skills)) {
                result.add(employee);
            }
        }
        return result;
    }

    // Update the availability of an employee
    public void updateEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = empRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee unavailable for ID: " + employeeId));
        employee.setDaysAvailable(daysAvailable);
        empRepo.saveAndFlush(employee);
    }
}
