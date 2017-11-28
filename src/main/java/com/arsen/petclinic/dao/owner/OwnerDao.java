package com.arsen.petclinic.dao.owner;

import com.arsen.petclinic.model.Owner;

import java.util.List;

public interface OwnerDao {
    Owner getOwner(int id);
    int addOwner(Owner owner);
    void deleteOwner(int id);
    void updateOwner(Owner owner);
    List<Owner> listOwners();
}
