package ua.artcode.eshop.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

//<bean id="jPAUserDao" class="">
@Repository("jpaUserDao")
public class JPAUserDao implements UserDao {

    private static final Logger LOG = Logger.getLogger(JPAUserDao.class);

    @Autowired
    private EntityManagerFactory factory;


    public JPAUserDao() {
    }

    public JPAUserDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public User create(User user) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(user);
            transaction.commit();
        }catch (Throwable e){
            LOG.error(e);
            transaction.rollback();
        } finally {
            manager.clear();
        }

        return user;
    }

    @Override
    public User find(int id) throws NoUserFoundException {
        EntityManager manager = factory.createEntityManager();
        try{
            User found = manager.find(User.class, id);

            if(found != null){
                return found;
            } else {
                throw new NoUserFoundException("user with id " + id + " doesn't exist");
            }

        } finally {
            manager.clear();
        }

    }

    @Override
    public User find(String email) throws NoUserFoundException {
        // jpql -> sql
        EntityManager manager = factory.createEntityManager();
        TypedQuery<User> typedQuery =
                manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);

        User user = typedQuery.setParameter("email", email).getSingleResult();

        if(user != null){
            return user;
        }

        throw new NoUserFoundException("user with email " + email + " doesn't exist");
    }

    @Override
    public List<User> findAll() {

        EntityManager manager = factory.createEntityManager();
        // jpql -> sql
        TypedQuery<User> typedQuery =
                manager.createQuery("SELECT u FROM User u", User.class);

        return typedQuery.getResultList();
    }

    @Override
    public User update(User user) {

        EntityManager manager = factory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        User found = null;
        try {
            transaction.begin();

            found = manager.find(User.class, user.getId());

            found.setUserType(user.getUserType());
            found.setEmail(user.getEmail());
            found.setFullname(user.getFullname());
            found.setPass(user.getPass());

            manager.merge(found);

            transaction.commit();
        } catch (Throwable e) {
            LOG.error(e);
            transaction.rollback();
        } finally {
            manager.clear();
        }

        return found;
    }

    @Override
    public boolean delete(User user) {

        EntityManager manager = factory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        User found = null;
        try {
            transaction.begin();

            found = manager.find(User.class, user.getId());

            manager.remove(found);
            transaction.commit();
        } catch (Throwable e) {

            LOG.error(e);

            transaction.rollback();

            return false;

        } finally {
            manager.clear();
        }

        return true;
    }
}
