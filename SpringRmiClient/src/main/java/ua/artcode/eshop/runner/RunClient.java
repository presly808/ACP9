package ua.artcode.eshop.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;
import ua.artcode.eshop.service.UserService;

/**
 * Created by serhii on 13.11.15.
 */
public class RunClient {


    public static void main(String[] args) {
        ApplicationContext clientContext = new ClassPathXmlApplicationContext("client-app-context.xml");

        UserService userService = clientContext.getBean(UserService.class);

        try {
            User user = userService.showUserInfo(1);
            System.out.println(user);
        } catch (NoUserFoundException e) {
            e.printStackTrace();
        }
    }
}
