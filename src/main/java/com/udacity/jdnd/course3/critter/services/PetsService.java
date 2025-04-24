package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PetsService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private CustomerRepo custRepo;

    // Fetch all pets from the database
    public List<Pet> fetchAllPets() {
        List<Pet> pets = petRepo.findAll();
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            result.add(pet);
        }
        return result;
    }

    // Fetch all pets associated with a specific customer by customer ID
    public List<Pet> fetchPetsByCustomerId(long customerId) {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : petRepo.findAll()) {
            if (pet.getCustomer() != null && pet.getCustomer().getId() == customerId) {
                result.add(pet);
            }
        }
        return result;
    }

    // Fetch a pet by its ID
    public Pet fetchPetById(long petId) {
        Pet pet = petRepo.findById(petId)
                .orElseThrow(() -> {
                    String message = "Pet with ID " + petId + " could not be located";
                    return new NotFoundException(message);
                });
        return pet;
    }

    // Create or update a pet and associate it with a customer
    public Pet createOrUpdatePet(Pet pet, long ownerId) {
        Customer customer = custRepo.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + ownerId + " is not registered in the system"));
        pet.setCustomer(customer);
        Pet savedPet = petRepo.saveAndFlush(pet);
        customer.insertPet(savedPet);
        custRepo.saveAndFlush(customer);
        return savedPet;
    }
}