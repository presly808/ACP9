package com.artcode.myproject.service;

import com.artcode.myproject.dao.PostgreUserDao;
import com.artcode.myproject.model.RentalRequirements;
import com.artcode.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public PostgreUserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(PostgreUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User register(String username, String password, int age, RentalRequirements rentalRequirements) {
        // generate

        // with id
        return userDao.create(new User(username, password, rentalRequirements, age));
    }
}
