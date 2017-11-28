package com.arsen.petclinic.service.owner;


import com.arsen.petclinic.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner getOwner(int id);
    int addOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(int id);
    List<Owner> listOwners();
}
