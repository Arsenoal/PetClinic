package com.arsen.petclinic.service.vet;

import com.arsen.petclinic.dao.vet.VetDao;
import com.arsen.petclinic.model.Vet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VetServiceImpl implements VetService {
    private VetDao vetDao;

    public void setVetDao(VetDao vetDao) {
        this.vetDao = vetDao;
    }

    @Override
    @Transactional
    public Vet getVet(int id) {
        return vetDao.getVet(id);
    }

    @Override
    @Transactional
    public int addVet(Vet vet) {
        return vetDao.addVet(vet);
    }

    @Override
    @Transactional
    public void updateVet(Vet vet) {
        vetDao.updateVet(vet);
    }

    @Override
    @Transactional
    public void deleteVet(int id) {
        vetDao.deleteVet(id);
    }

    @Override
    @Transactional
    public List<Vet> listVets() {
        return vetDao.listVets();
    }
}
