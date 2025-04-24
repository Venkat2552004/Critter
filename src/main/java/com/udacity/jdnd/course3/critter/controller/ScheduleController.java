package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        Schedule createdSchedule = scheduleService.createAndSaveSchedule(
            schedule, 
            scheduleDTO.getEmployeeIds(), 
            scheduleDTO.getPetIds()
        );

        ScheduleDTO result = getScheduleDTO(createdSchedule);
        return result;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.fetchAllSchedules();
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }

        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.fetchSchedulesByEmployeeId(employeeId);
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }

        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.fetchSchedulesByPetId(petId);
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }

        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.fetchSchedulesByCustomerId(customerId);
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }

        return scheduleDTOs;
    }

    private ScheduleDTO getScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());

        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee : schedule.getEmployees()) {
            employeeIds.add(employee.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIds);

        List<Long> petIds = new ArrayList<>();
        for (Pet pet : schedule.getPets()) {
            petIds.add(pet.getId());
        }
        scheduleDTO.setPetIds(petIds);

        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());

        return scheduleDTO;
    }
}