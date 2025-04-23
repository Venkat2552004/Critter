package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
    
}
