package endpoint;

import exception.UserAlreadyExistsException;
import model.User;

import javax.ws.rs.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/user")
public class UserServiceImpl implements UserService {

    private Map<Integer, User> userMap = new ConcurrentHashMap<>();

    @Path("/time")
    @GET
    @Override
    public Date getCurrentTime() {
        return new Date();
    }

    @Path("/all")
    @GET
    @Produces("application/xml")
    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Path("/info/{name}") // /info/kolia
    @GET
    @Override
    public String infoFromServer(@PathParam("name") String name) {
        return "Hello  " + name;
    }

    @Path("/create")
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if(!userMap.containsKey(user.getId())){
            throw new UserAlreadyExistsException("User with id " + user.getId() + " already exists");
        }
        return userMap.put(user.getId(), user);
    }

    @Path("/get") // /user/get?id=23
    @GET
    @Produces("application/xml")
    @Override
    public User findUser(@QueryParam("id") int id) {
        return new User(22,1234,"Andrey",2000);
    }
}
