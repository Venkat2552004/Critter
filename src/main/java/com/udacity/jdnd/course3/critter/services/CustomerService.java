package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PetRepo petRepo;

    public List<Customer> retrieveAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer findCustomerByPetId(long petId) {
        return petRepo.getOne(petId).getCustomer();
    }

    @Transactional
    public Customer createOrUpdateCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map(petRepo::getOne).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customerRepo.save(customer);
    }
}
