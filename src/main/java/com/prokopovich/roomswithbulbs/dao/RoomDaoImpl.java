package com.prokopovich.roomswithbulbs.dao;

import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class RoomDaoImpl implements RoomDao {

    private static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public RoomDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Room create(Room newRoom) throws DaoException {
        LOGGER.trace("create room method is executed - new room: " + newRoom);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newRoom);
            entityManager.getTransaction().commit();
            entityManager.close();
            return newRoom;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(Room room) throws DaoException {
        LOGGER.trace("update room method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(room);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Room findOne(int id) throws DaoException {
        LOGGER.trace("findOne method is executed - id: " + id);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Room.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Room> findAll() throws DaoException {
        LOGGER.trace("findAll method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select e from Room e")
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Room findByName(String name) throws DaoException {
        LOGGER.info("findByName method is executed - name = " + name);
        return findAllByOneParameter("name", name).iterator().next();
    }

    @Override
    public Collection<Room> findAllByCountry(String country) throws DaoException {
        LOGGER.info("findAllByCountry method is executed - country = " + country);
        return findAllByOneParameter("country", country);
    }

    private Collection<Room> findAllByOneParameter(String field, String parameter) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            Root<Room> tRoot = criteriaQuery.from(Room.class);
            Predicate predicate = criteriaBuilder.equal(tRoot.get(field), parameter);
            criteriaQuery.where(predicate);
            if(entityManager.createQuery(criteriaQuery).getResultList().isEmpty()) {
                return null;
            }
            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
