package com.artcode.training.week7.ioc.run;


import com.artcode.training.week7.ioc.di.DependencyInjector;
import com.artcode.training.week7.ioc.entities.ServiceB;

/**
 * Created by serhii on 12.11.15.
 */
public class TestDI {

    public static void main(String[] args) {
        DependencyInjector dependencyInjector = new DependencyInjector();

        ServiceB serviceB = new ServiceB();

        dependencyInjector.doInjection(serviceB);

        serviceB.run();

    }
}
