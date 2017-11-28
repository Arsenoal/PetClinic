package com.arsen.petclinic.service.vet;

import com.arsen.petclinic.model.Vet;

import java.util.List;

public interface VetService {
    Vet getVet(int id);
    int addVet(Vet vet);
    void updateVet(Vet vet);
    void deleteVet(int id);
    List<Vet> listVets();
}
