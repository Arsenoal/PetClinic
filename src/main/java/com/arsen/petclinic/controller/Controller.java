package com.arsen.petclinic.controller;

import com.arsen.petclinic.model.Owner;
import com.arsen.petclinic.model.Pet;
import com.arsen.petclinic.model.Vet;
import com.arsen.petclinic.service.owner.OwnerService;
import com.arsen.petclinic.service.pet.PetService;
import com.arsen.petclinic.service.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final static Logger logger = Logger.getLogger(Controller.class.getSimpleName());
    private PetService petService;
    private OwnerService ownerService;
    private VetService vetService;

    @Autowired
    @Qualifier(value = "petService")
    public void setPetService(PetService petService){
        this.petService = petService;
    }

    @Autowired
    @Qualifier(value = "ownerService")
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Autowired
    @Qualifier(value = "vetService")
    public void setVetService(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> listPets(){
        List<Pet> pets = petService.listPets();

        if(pets.isEmpty()) {
            logger.info("no pet found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") int id){
        Pet pet = petService.getPet(id);

        if(pet == null){
            logger.info("no pet found by id: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet){

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

        Pet pet = petService.getPet(id);

        if(pet == null){
            logger.info("no pet found with id: " + id );

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        petService.deletePet(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> listOwners(){
        List<Owner> list = ownerService.listOwners();

        if(list.isEmpty()){
            logger.info("no owners found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwnerBiId(@PathVariable("id") int id){
        Owner owner = ownerService.getOwner(id);

        if(owner == null){
            logger.info("owner with id: " +id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping("/owner")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){

        if(owner.getId() == 0){
            int id = ownerService.addOwner(owner);

            Owner storedOwner = ownerService.getOwner(id);

            if(storedOwner == null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(owner, HttpStatus.OK);
        }else{
            ownerService.updateOwner(owner);

            return new ResponseEntity<>(owner, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/owner/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable("id") int id, @RequestBody Owner owner){
        Owner updatableOwner = ownerService.getOwner(id);

        if(updatableOwner == null) {
            logger.info("not owner found with id: " + id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatableOwner.setId(owner.getId());
        updatableOwner.setAge(owner.getAge());
        updatableOwner.setPetId(owner.getPetId());
        updatableOwner.setName(owner.getName());
        updatableOwner.setSex(owner.getSex());

        ownerService.updateOwner(updatableOwner);

        return new ResponseEntity<>(updatableOwner, HttpStatus.OK);
    }

    @DeleteMapping("/owner/{id}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable("id") int id){
        Owner owner = ownerService.getOwner(id);

        if(owner == null){
            logger.info("not owner found with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ownerService.deleteOwner(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/vets")
    public ResponseEntity<List<Vet>> listVets(){
        List<Vet> vets = vetService.listVets();

        if(vets.isEmpty()){
            logger.info("no vets found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vets, HttpStatus.OK);
    }

    @GetMapping("/vet/{id}")
    public ResponseEntity<Vet> getVetById(@PathVariable int id){
        Vet vet = vetService.getVet(id);

        if(vet == null){
            logger.info("no vet found with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vet, HttpStatus.OK);
    }

    @PostMapping("/vet")
    public ResponseEntity<Vet> addVet(@RequestBody Vet vet){

        if(vet.getId() == 0) {
            vetService.addVet(vet);

            Vet storedVet = vetService.getVet(vet.getId());

            if(storedVet == null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(vet, HttpStatus.OK);
        }else{
            vetService.updateVet(vet);

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/vet/{id}")
    public ResponseEntity<Vet> updateVet(@PathVariable int id,@RequestBody Vet vet){
        Vet updatableVet = vetService.getVet(id);

        if(updatableVet == null) {
            logger.info("no vet found with id: " + id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatableVet.setId(vet.getId());
        updatableVet.setAge(vet.getAge());
        updatableVet.setName(vet.getName());
        updatableVet.setOwnerId(vet.getOwnerId());
        updatableVet.setSex(vet.getSex());

        vetService.updateVet(updatableVet);

        return new ResponseEntity<>(updatableVet, HttpStatus.OK);
    }

    @DeleteMapping("/vet/{id}")
    public ResponseEntity<Vet> deleteVet(@PathVariable int id){
        Vet vet = vetService.getVet(id);

        if(vet == null){
            logger.info("no vet found with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vetService.deleteVet(id);

        return new ResponseEntity<>(vet, HttpStatus.OK);
    }
}