package ua.artcode.eshop.db;

import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.Product;
import ua.artcode.eshop.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by serhii on 24.09.15.
 */
public class AppDb {

    private static int count = 0;

    private Map<Integer, User> userMap = new HashMap<>();
    private Map<Integer,Product> productMap = new HashMap<>();

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


    public List<User> getUserList(){
        return userMap.values().stream().collect(Collectors.toList()); // java 8
    }


    public User findUserByEmail(String email) throws NoUserFoundException {
        Optional<User> found = userMap.values().stream().filter((user) -> user.getEmail().equals(email)).findFirst();
        if( found.isPresent()) {
            return found.get();
        } else {
            throw new NoUserFoundException("no user with email " + email);
        }
    }
}
