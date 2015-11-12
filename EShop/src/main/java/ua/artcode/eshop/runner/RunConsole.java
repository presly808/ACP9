package ua.artcode.eshop.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.eshop.dao.InnerUserDao;
import ua.artcode.eshop.dao.JPAUserDao;
import ua.artcode.eshop.dao.UserDao;
import ua.artcode.eshop.db.AppDb;
import ua.artcode.eshop.service.UserService;
import ua.artcode.eshop.service.UserServiceImpl;
import ua.artcode.eshop.view.UserMenu;

import javax.persistence.EntityManagerFactory;

/**
 * Created by serhii on 08.10.15.
 */
public class RunConsole {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-context.xml");

        EntityManagerFactory f2 = applicationContext.getBean(EntityManagerFactory.class);
        EntityManagerFactory f3 = applicationContext.getBean("entityManagerFactory", EntityManagerFactory.class);

        UserDao userDao = new JPAUserDao(f2);

        UserService userService = new UserServiceImpl(userDao);

        UserMenu userMenu = new UserMenu(userService);


        userMenu.start();
    }
}
