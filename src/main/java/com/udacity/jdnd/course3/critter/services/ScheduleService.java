package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private EmployeeRepo empRepo;

    @Autowired
    private CustomerRepo custRepo;

    public List<Schedule> fetchAllSchedules() { 
        return scheduleRepo.findAll();
    }

    public List<Schedule> fetchSchedulesByPetId(long petId) { 
        Pet pet = petRepo.getOne(petId);
        return scheduleRepo.getAllByPetsContains(pet);
    }

    public List<Schedule> fetchSchedulesByEmployeeId(long employeeId) { 
        Employee employee = empRepo.getOne(employeeId);
        return scheduleRepo.getAllByEmployeesContains(employee);
    }

    public List<Schedule> fetchSchedulesByCustomerId(long customerId) { 
        Customer customer = custRepo.getOne(customerId);
        return scheduleRepo.getAllByPetsIn(customer.getPets());
    }

    public Schedule createAndSaveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) { 
        List<Employee> employees = empRepo.findAllById(employeeIds);
        List<Pet> pets = petRepo.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepo.save(schedule);
    }
}