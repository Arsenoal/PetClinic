package com.arsen.petclinic.service.owner;

import com.arsen.petclinic.dao.owner.OwnerDao;
import com.arsen.petclinic.model.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerDao ownerDao;

    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    @Transactional
    public Owner getOwner(int id) {
        return ownerDao.getOwner(id);
    }

    @Override
    @Transactional
    public int addOwner(Owner owner) {
        return ownerDao.addOwner(owner);
    }

    @Override
    @Transactional
    public void updateOwner(Owner owner) {
        ownerDao.addOwner(owner);
    }

    @Override
    @Transactional
    public void deleteOwner(int id) {
        ownerDao.deleteOwner(id);
    }

    @Override
    @Transactional
    public List<Owner> listOwners() {
        return ownerDao.listOwners();
    }
}
