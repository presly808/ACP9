package ua.artcode.eshop.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by serhii on 13.11.15.
 */
public class RunRmiServer {


    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server-app-context.xml");
    }
}
