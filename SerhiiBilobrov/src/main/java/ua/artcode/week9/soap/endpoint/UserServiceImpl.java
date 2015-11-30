package ua.artcode.week9.soap.endpoint;

import ua.artcode.week9.soap.exception.UserAlreadyExistsException;
import ua.artcode.week9.soap.model.User;

import javax.jws.WebService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@WebService(endpointInterface = "ua.artcode.week9.soap.endpoint.UserService")
public class UserServiceImpl implements UserService {

    private Map<Integer, User> userMap = new ConcurrentHashMap<>();

    @Override
    public Date getCurrentTime() {
        return new Date();
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public String infoFromServer(String name) {
        return "Hello  " + name;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if(userMap.containsKey(user.getId())){
            throw new UserAlreadyExistsException("User with id " + user.getId() + " already exists");
        }
        return userMap.put(user.getId(), user);
    }

    @Override
    public User findUser(int id) {
        return userMap.get(id);
    }
}
