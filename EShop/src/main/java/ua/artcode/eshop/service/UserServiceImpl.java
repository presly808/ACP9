package ua.artcode.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.eshop.dao.InnerUserDao;
import ua.artcode.eshop.dao.UserDao;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    // SOLID - Dependency Invertion
    @Autowired
    @Qualifier(value = "jpaUserDao")
    public UserDao userDao;

    public UserServiceImpl() {
    }

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
        return UUID.randomUUID().toString();
    }

    @Override
    public User findByEmail(String email) throws NoUserFoundException {
        return userDao.find(email);
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
