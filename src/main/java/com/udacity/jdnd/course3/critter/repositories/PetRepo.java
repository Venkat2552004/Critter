package com.udacity.jdnd.course3.critter.repositories;


import com.udacity.jdnd.course3.critter.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet, Long>{
    List<Pet> getAllByCustomerId(Long customerId);
}
