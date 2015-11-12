package ua.artcode.week7.ioc.entities;

import ua.artcode.week7.ioc.di.ForInject;

import java.util.Date;

/**
 * Created by serhii on 12.11.15.
 */
public class ServiceA implements IService {

    @ForInject
    private DataFormatter formatter;

    @Override
    public void run() {
        System.out.println(formatter.formatDate(new Date()));
    }
}
