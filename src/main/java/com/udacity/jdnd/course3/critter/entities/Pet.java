package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;

import com.udacity.jdnd.course3.critter.pet.PetType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private PetType type;
    private String name;
    
    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate birthDate;
    private String notes;
}
