package com.arsen.petclinic.controller;

import com.arsen.petclinic.model.Pet;
import com.arsen.petclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    private final static Logger logger = Logger.getLogger(PetController.class.getSimpleName());
    private PetService petService;

    @Autowired
    @Qualifier(value ="petService")
    public void setPetService(PetService petService){
        this.petService = petService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> listPets(){
        logger.info("get all pets");
        List<Pet> pets = petService.listPets();

        if(pets.isEmpty()) {
            logger.info("no pet found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") int id){
        logger.info("get pet by id");
        Pet pet = petService.getPet(id);

        if(pet == null){
            logger.info("no pet found by id: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet){
        logger.info("add pet: " + pet);

        if(pet.getId() == 0){
            int id = petService.addPet(pet);

            Pet storedPet = petService.getPet(id);

            if(storedPet == null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(pet, HttpStatus.OK);
        }else{
            petService.updatePet(pet);
            return new ResponseEntity<>(pet, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") int id, @RequestBody Pet pet){
        logger.info("update pet by id: " + id);

        Pet updatablePet = petService.getPet(id);

        if(updatablePet == null){
            logger.info("no pet found with id: " + id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatablePet.setId(pet.getId());
        updatablePet.setAge(pet.getAge());
        updatablePet.setName(pet.getName());
        updatablePet.setRegistrationDate(pet.getRegistrationDate());
        updatablePet.setType(pet.getType());

        petService.updatePet(updatablePet);

        return new ResponseEntity<>(updatablePet, HttpStatus.OK);
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable int id){
        logger.info("delete pet byd id: " + id);

        Pet pet = petService.getPet(id);

        if(pet == null){
            logger.info("no pet found with id: " + id );

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        petService.deletePet(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
}