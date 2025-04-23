package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PetsService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private CustomerRepo custRepo;

    public List<Pet> fetchAllPets() { 
        return petRepo.findAll();
    }

    public List<Pet> fetchPetsByCustomerId(long customerId) {
        return petRepo.getAllByCustomerId(customerId);
    }

    public Pet fetchPetById(long petId) { 
        return petRepo.getOne(petId);
    }

    public Pet createOrUpdatePet(Pet pet, long ownerId) {
        Customer customer = custRepo.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petRepo.save(pet);
        customer.insertPet(pet);
        custRepo.save(customer);
        return pet;
    }
}