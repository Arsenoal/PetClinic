package com.arsen.petclinic.service.pet;

import com.arsen.petclinic.dao.pet.PetDao;
import com.arsen.petclinic.model.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{
    private PetDao petDao;

    public void setPetDao(PetDao petDao) {
        this.petDao = petDao;
    }

    @Override
    @Transactional
    public Pet getPet(int id) {
        return petDao.getPet(id);
    }

    @Override
    @Transactional
    public int addPet(Pet pet) {
        return petDao.addPet(pet);
    }

    @Override
    @Transactional
    public void deletePet(int id) {
        petDao.deletePet(id);
    }

    @Override
    @Transactional
    public List<Pet> listPets() {
        return petDao.listPets();
    }

    @Override
    public void updatePet(Pet pet) {
        petDao.updatePet(pet);
    }
}
