package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    // Fetch all schedules from the database
    public List<Schedule> fetchAllSchedules() {
        List<Schedule> schedules = scheduleRepo.findAll();
        return schedules;
    }

    // Fetch schedules associated with a specific pet by pet ID
    public List<Schedule> fetchSchedulesByPetId(long petId) {
        Pet pet = petRepo.findById(petId)
                .orElseThrow(() -> {
                    String message = "Unable to locate Pet with ID: " + petId;
                    return new NotFoundException(message);
                });
        List<Schedule> result = new ArrayList<>();
        List<Schedule> allSchedules = scheduleRepo.findAll();
        for (Schedule schedule : allSchedules) {
            if (schedule.getPets().contains(pet)) {
                result.add(schedule);
            }
        }
        return result;
    }

    // Fetch schedules associated with a specific employee by employee ID
    public List<Schedule> fetchSchedulesByEmployeeId(long employeeId) {
        Employee employee = empRepo.findById(employeeId)
                .orElseThrow(() -> {
                    String message = "Employee not found for ID: " + employeeId;
                    return new NotFoundException(message);
                });
        List<Schedule> result = new ArrayList<>();
        List<Schedule> allSchedules = scheduleRepo.findAll();
        for (Schedule schedule : allSchedules) {
            if (schedule.getEmployees().contains(employee)) {
                result.add(schedule);
            }
        }
        return result;
    }

    // Fetch schedules associated with a specific customer by customer ID
    public List<Schedule> fetchSchedulesByCustomerId(long customerId) {
        Customer customer = custRepo.findById(customerId)
                .orElseThrow(() -> {
                    String message = "Customer with ID " + customerId + " does not exist in the system";
                    return new NotFoundException(message);
                });
        List<Pet> customerPets = customer.getPets();
        List<Schedule> result = new ArrayList<>();
        List<Schedule> allSchedules = scheduleRepo.findAll();
        for (Schedule schedule : allSchedules) {
            for (Pet pet : schedule.getPets()) {
                if (customerPets.contains(pet)) {
                    result.add(schedule);
                    break;
                }
            }
        }
        return result;
    }

    // Create and save a new schedule with associated employees and pets
    public Schedule createAndSaveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = new ArrayList<>();
        for (Long id : employeeIds) {
            Employee employee = empRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("No Employee found with ID: " + id));
            employees.add(employee);
        }

        List<Pet> pets = new ArrayList<>();
        for (Long id : petIds) {
            Pet pet = petRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Pet not found for ID: " + id));
            pets.add(pet);
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepo.save(schedule);
    }
}