package com.artcode.myproject;

import com.artcode.myproject.model.AppartmentsType;
import com.artcode.myproject.model.RentalRequirements;
import com.artcode.myproject.service.UserService;
import com.artcode.myproject.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        UserService service = applicationContext.getBean(UserServiceImpl.class);

        System.out.println(service.register("AspectUser", "pass", 20,
                new RentalRequirements(5000, "bath", Collections.singletonList(AppartmentsType.TWO_ROOM))));
    }
}
