package ua.artcode.week9.soap.endpoint;

import ua.artcode.week9.soap.exception.UserAlreadyExistsException;
import ua.artcode.week9.soap.model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;

@WebService
public interface UserService {

    @WebMethod
    Date getCurrentTime();

    Collection<User> getAll();

    @WebMethod
    String infoFromServer(String name);

    @WebMethod
    User createUser(User user) throws UserAlreadyExistsException;

    @WebMethod
    User findUser(int id);



}
