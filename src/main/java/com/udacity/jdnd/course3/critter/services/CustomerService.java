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

@Transactional
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PetRepo petRepo;

    public List<Customer> retrieveAllCustomers() {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customerRepo.findAll()) {
            result.add(customer); // Include all customers, regardless of pets
        }
        return result;
    }

    public Customer findCustomerByPetId(long petId) {
        Pet pet = petRepo.findById(petId).orElse(null);
        return pet != null ? pet.getCustomer() : null;
    }

    public Customer createOrUpdateCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            for (Long petId : petIds) {
                Pet pet = petRepo.findById(petId).orElse(null);
                if (pet != null) {
                    pets.add(pet);
                }
            }
        }
        customer.setPets(pets); // Ensure pets list is set, even if empty
        return customerRepo.save(customer); // Save customer regardless of pets
    }
}
