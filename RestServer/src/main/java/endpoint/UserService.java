package endpoint;

import exception.UserAlreadyExistsException;
import model.User;

import java.util.Collection;
import java.util.Date;

public interface UserService {

    Date getCurrentTime();

    Collection<User> getAll();

    String infoFromServer(String name);

    User createUser(User user) throws UserAlreadyExistsException;

    User findUser(int id);



}
