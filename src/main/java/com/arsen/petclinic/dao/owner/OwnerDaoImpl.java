package com.arsen.petclinic.dao.owner;

import com.arsen.petclinic.model.Owner;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerDaoImpl implements OwnerDao {
    private Logger logger = Logger.getLogger(OwnerDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Owner getOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner = session.load(Owner.class, new Integer(id));

        logger.info("get owner: " + owner);

        return owner;
    }

    @Override
    public int addOwner(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(owner);
        session.flush();

        logger.info("owner added: " + owner);

        return owner.getId();
    }

    @Override
    public void deleteOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner = session.load(Owner.class, new Integer(id));
        session.remove(owner);

        logger.info("owner removed: " + owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.update(owner);

        logger.info("owner updated: " + owner);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Owner> listOwners() {
        Session session = sessionFactory.getCurrentSession();
        List<Owner> owners = session.createQuery("FROM Owner").list();

        logger.info("get all owners: " + owners);

        return owners;
    }
}
