package ua.artcode.week9.soap;

import ua.artcode.week9.soap.endpoint.UserServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by serhii on 26.11.15.
 */
public class RunServer {

    public static void main(String[] args) {
        Endpoint.publish("http://192.168.1.55:9999/UserService", new UserServiceImpl());
    }
}
