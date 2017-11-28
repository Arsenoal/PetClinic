package com.arsen.petclinic.dao.vet;

import com.arsen.petclinic.model.Vet;

import java.util.List;

public interface VetDao {
    Vet getVet(int id);
    int addVet(Vet vet);
    void deleteVet(int id);
    void updateVet(Vet vet);
    List<Vet> listVets();
}
