package com.udacity.jdnd.course3.critter.entities;

import java.util.List;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    public void insertPet(Pet pet){
        pets.add(pet);
    }

}
