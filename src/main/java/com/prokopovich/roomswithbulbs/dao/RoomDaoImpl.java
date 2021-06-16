package com.prokopovich.roomswithbulbs.dao;

import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class RoomDaoImpl implements RoomDao {

    private static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);
    private static final String SQL_SELECT_FILTER = "SELECT e FROM Room e " +
            "WHERE e.name LIKE :name AND e.country LIKE :country AND e.bulbStatus LIKE :bulbStatus";

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

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            Root<Room> tRoot = criteriaQuery.from(Room.class);
            Predicate predicate = criteriaBuilder.equal(tRoot.get("name"), name);
            criteriaQuery.where(predicate);
            if(entityManager.createQuery(criteriaQuery).getResultList().isEmpty()) {
                return null;
            }
            return entityManager.createQuery(criteriaQuery).getResultList().iterator().next();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Room> findByNameAndCountryAndStatus(String name, String country, String bulbStatus) throws DaoException {
        LOGGER.info("findByUsernameAndRoleAndStatus method is executed - name = " + name +
                ", country = " + country + ", bulStatus = " + bulbStatus);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery(SQL_SELECT_FILTER);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("country", "%"+country+"%");
            query.setParameter("bulbStatus", "%"+bulbStatus+"%");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
