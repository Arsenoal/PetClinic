package com.arsen.petclinic.dao.pet;


import com.arsen.petclinic.model.Pet;

import java.util.List;

public interface PetDao {
    Pet getPet(int id);
    int addPet(Pet pet);
    void deletePet(int id);
    void updatePet(Pet pet);
    List<Pet> listPets();
}
