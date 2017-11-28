package com.arsen.petclinic.service.pet;


import com.arsen.petclinic.model.Pet;

import java.util.List;

public interface PetService {
    Pet getPet(int id);
    int addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(int id);
    List<Pet> listPets();
}
