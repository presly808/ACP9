package ua.artcode.eshop.view;

import org.apache.log4j.Logger;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;
import ua.artcode.eshop.service.UserService;

import java.util.Scanner;

/**
 * Created by serhii on 08.10.15.
 */
public class UserMenu {

    private static final Logger LOG = Logger.getLogger(UserMenu.class);
    public static final int EXIT_CODE = 5;

    private UserService userService;
    private Scanner input;

    public UserMenu(UserService userService) {
        this.userService = userService;
        input = new Scanner(System.in);
    }

    public void start(){

        int choice = -1;

        while(choice != EXIT_CODE){
            showMenu();
            choice = input.nextInt();
            if(choice == 1){
                showRegisterMenu();
            } else if(choice == 2){
                showLoginMenu();
            } else if(choice == 3){
                showUserInfo();
            } else {
                LOG.warn("Wrong menu choice");
            }
        }

    }

    private void showUserInfo() {
        System.out.println("Input user id");
        int id = input.nextInt();
        try {
            User user = userService.showUserInfo(id);
            System.out.println(user);
        } catch (NoUserFoundException e) {
            LOG.error("NoUserFound " + e.getMessage(),e);
        }
    }

    private void showLoginMenu() {
        System.out.println("Input email");
        String email = input.next();
        System.out.println("Input pass");
        String pass = input.next();

        try {
            String accessKey = userService.login(email,pass);
            LOG.info("In system your key " + accessKey);
        } catch (NoUserFoundException e) {
            LOG.error("NoUserFound " + e.getMessage(),e);
        }
    }

    public void showRegisterMenu(){
        System.out.println("Input email");
        String email = input.next();
        System.out.println("Input fullname");
        String fullname = input.next();
        System.out.println("Input phone");
        String phone = input.next();
        System.out.println("Input pass");
        String pass = input.next();

        User user = userService.register(email,fullname,phone,pass);
        System.out.println("Register successful");
        System.out.println(user);
    }

    public void showMenu(){
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("3.Show user info");
        System.out.println("4.Show all(friends) users");
        System.out.println("5.exit");

    }

}
