package ua.artcode.eshop.runner;

import ua.artcode.eshop.dao.InnerUserDao;
import ua.artcode.eshop.dao.UserDao;
import ua.artcode.eshop.db.AppDb;
import ua.artcode.eshop.service.UserService;
import ua.artcode.eshop.service.UserServiceImpl;
import ua.artcode.eshop.view.UserMenu;

/**
 * Created by serhii on 08.10.15.
 */
public class RunConsole {


    public static void main(String[] args) {
        AppDb appDb = new AppDb();

        UserDao userDao = new InnerUserDao(appDb);

        UserService userService = new UserServiceImpl(userDao);

        UserMenu userMenu = new UserMenu(userService);


        userMenu.start();
    }
}
