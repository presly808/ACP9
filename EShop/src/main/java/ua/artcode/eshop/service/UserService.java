package ua.artcode.eshop.service;

import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    User register(String email, String fullname, String phone, String pass);

    // access key
    String login(String email, String pass) throws NoUserFoundException;

    User findByEmail(String email) throws NoUserFoundException;

    User showUserInfo(int id) throws NoUserFoundException;

    // update info
    void addUserFriend(String accessKey, int id);

    List<User> showFollowersByUserId(int id);


}
