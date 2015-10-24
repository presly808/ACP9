package ua.artcode.eshop.service;

import ua.artcode.eshop.dao.UserDao;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import java.util.List;

/**
 *
 */
public class UserServiceImpl implements UserService {

    // SOLID - Dependency Invertion
    public UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User register(String email, String fullname, String phone, String pass) {
        // generate

        // with id
        return userDao.create(new User(email,fullname,phone,pass));
    }

    @Override
    public String login(String email, String pass) throws NoUserFoundException {
        User user = userDao.find(email);

        // generate access key
        String accessKey = "123456";

        return accessKey;
    }

    @Override
    public User showUserInfo(int id) throws NoUserFoundException {
        return userDao.find(id);
    }

    @Override
    public void addUserFriend(String accessKey, int id) {

    }

    @Override
    public List<User> showFollowersByUserId(int id) {
        return null;
    }
}
