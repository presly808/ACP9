package ua.artcode.week3.reflect;

import ua.artcode.eshop.model.User;

/**
 * Created by serhii on 09.10.15.
 */
public class ShowClassInfoTest {

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(ReflectUtils.info(User.class));

        System.out.println();

        User user = new User("sdfsdf","Andriy","+2342342","1234");

        ReflectUtils.getFieldsValues(user);
    }
}
