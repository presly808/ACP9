package ua.artcode.eshop.experemental;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.artcode.eshop.model.User;
import ua.artcode.eshop.view.UserMenu;

/**
 * Created by serhii on 13.11.15.
 */
public class InitAnnotConfigSpring {


    public static void main(String[] args) {
        ApplicationContext ap = new AnnotationConfigApplicationContext("ua.artcode");

        //new UserMenu().showMenu();

    }
}
