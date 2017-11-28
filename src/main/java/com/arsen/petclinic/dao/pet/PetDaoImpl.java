package com.arsen.petclinic.dao.pet;

import com.arsen.petclinic.model.Pet;
import com.arsen.petclinic.service.pet.PetServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao{
    private Logger logger = Logger.getLogger(PetServiceImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pet getPet(int id) {
        Session session = sessionFactory.getCurrentSession();
        Pet pet = session.load(Pet.class, new Integer(id));

        logger.info("get pet by id: " + id);

        return pet;
    }

    @Override
    public int addPet(Pet pet) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(pet);
        session.flush();

        logger.info("pet successfully added: " + pet);

        return pet.getId();
    }

    @Override
    public void deletePet(int id) {
        Session session = sessionFactory.getCurrentSession();
        Pet pet = session.load(Pet.class, new Integer(id));

        if(pet != null) {
            session.delete(pet);
            logger.info("pet successfully removed: " + id);
        }  else{
            logger.info("pet removing failed(no such pet): " + id);
        }
    }

    @Override
    public void updatePet(Pet pet) {
        Session session = sessionFactory.getCurrentSession();
        session.update(pet);

        logger.info("pet updated: " + pet);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pet> listPets() {
        Session session = sessionFactory.getCurrentSession();
        List<Pet> pets = session.createQuery("FROM Pet").list();

        logger.info("get list of pets");

        return pets;
    }
}
