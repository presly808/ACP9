package ua.artcode.eshop.db;

import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.Product;
import ua.artcode.eshop.model.User;
import ua.artcode.week1.hashstructure.MyHashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by serhii on 24.09.15.
 */
public class AppDb {

    private static int count = 0;

    private Map<Integer, User> userMap = new LinkedHashMap<>();
    private Map<Integer,Product> productMap;


    public User addUser(User user){
        user.setId(count);
        userMap.put(count, user);
        count++;

        return user;
    }

    public User getById(int id) throws NoUserFoundException {
        User user = userMap.get(id);


        if(user == null) {
            throw new NoUserFoundException("no user with id " + id);
        }

        return user;
    }





}
