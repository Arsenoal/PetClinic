package com.arsen.petclinic.dao.vet;

import com.arsen.petclinic.model.Vet;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VetDaoImpl implements VetDao{
    private Logger logger = Logger.getLogger(VetDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Vet getVet(int id) {
        Session session = sessionFactory.getCurrentSession();
        Vet vet = session.load(Vet.class, new Integer(id));

        logger.info("get pet by id: " + id);

        return vet;
    }

    @Override
    public int addVet(Vet vet) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(vet);
        session.flush();

        logger.info("vet is added: " + vet);

        return vet.getId();
    }

    @Override
    public void deleteVet(int id) {
        Session session = sessionFactory.getCurrentSession();
        Vet vet = session.load(Vet.class, new Integer(id));

        if(vet != null) {
            session.remove(vet);
            logger.info("vet is removed: " + id);
        }else{
            logger.info("no vet found with id: " + id);
        }
    }

    @Override
    public void updateVet(Vet vet) {
        Session session = sessionFactory.getCurrentSession();
        session.update(vet);
        logger.info("vet updated: " + vet);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Vet> listVets() {
        Session session = sessionFactory.getCurrentSession();
        List<Vet> list = session.createQuery("FROM Vet").list();

        logger.info("get list of vets");

        return list;
    }
}
