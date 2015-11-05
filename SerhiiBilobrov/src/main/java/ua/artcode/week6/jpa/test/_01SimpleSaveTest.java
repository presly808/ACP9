package ua.artcode.week6.jpa.test;

import ua.artcode.week6.jpa.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by serhii on 05.11.15.
 */
public class _01SimpleSaveTest {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("hibernate-unit");

        Group group1 = new Group("ACP9");
        Group group2 = new Group("ACP8");

        EntityManager manager = factory.createEntityManager();

        List<Group> list = manager.createQuery("SELECT g FROM Group g", Group.class).getResultList();

        list.stream().forEach(System.out::println);

        persist(manager, group1, group2);

        Group found = find(manager, list.get(0).getId());

        delete(manager, found);

        Group forUpdate = list.get(list.size() - 1);
        forUpdate.setName("UPDATE");

        update(manager,forUpdate);



        manager.close();

        factory.close();
    }

    private static Group find(EntityManager manager, Object id) {
        return manager.find(Group.class, id);
    }

    private static void delete(EntityManager manager, Group group) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(group);
            transaction.commit();
        }catch (Throwable th) {
            th.printStackTrace();
            transaction.rollback();
        }

    }

    private static void persist(EntityManager manager, Group...groups) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            for(Group group : groups){
                manager.persist(group);
            }
            transaction.commit();
        }catch (Throwable th) {
            th.printStackTrace();
            transaction.rollback();
        }

    }

    private static void update(EntityManager manager, Group group) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.merge(group);
            transaction.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            transaction.rollback();
        }

    }
}
