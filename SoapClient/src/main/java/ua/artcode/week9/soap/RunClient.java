package ua.artcode.week9.soap;

import ua.artcode.week9.soap.endpoint.User;
import ua.artcode.week9.soap.endpoint.UserAlreadyExistsException_Exception;
import ua.artcode.week9.soap.endpoint.UserService;
import ua.artcode.week9.soap.endpoint.UserServiceImplService;

import java.util.List;

/**
 * Created by serhii on 26.11.15.
 */
public class RunClient {

    public static void main(String[] args) {
        UserService userService = new UserServiceImplService().getUserServiceImplPort();

        User user = new User();
        user.setId((int)(Math.random() * 100000));
        user.setName("Ivan");
        user.setAge(34);
        user.setSalary(2323);

        try {
            userService.createUser(user);
        } catch (UserAlreadyExistsException_Exception e) {
            e.printStackTrace();
        }


        List<User> userList = userService.getAll();
        userList.stream().forEach(u -> System.out.printf("%d,%s,%d,%.2f\n",
                u.getId(),u.getName(),u.getAge(),u.getSalary()));
    }

}
