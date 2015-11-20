package com.artcode.myproject.dao;

import com.artcode.myproject.aspect.Transactional;
import com.artcode.myproject.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository()
public class PostgreUserDao {

    private static final Logger LOG = Logger.getLogger(PostgreUserDao.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public PostgreUserDao() {
    }

    public PostgreUserDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public User create(User user) {
        return create(entityManagerFactory.createEntityManager(), user);
    }

    @Transactional
    public User create(EntityManager entityManager, User user) {
        entityManager.persist(user);
        return user;
    }
}
