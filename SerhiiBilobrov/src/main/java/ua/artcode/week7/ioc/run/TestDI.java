package ua.artcode.week7.ioc.run;

import ua.artcode.week7.ioc.di.DependencyInjector;
import ua.artcode.week7.ioc.entities.ServiceA;

/**
 * Created by serhii on 12.11.15.
 */
public class TestDI {

    public static void main(String[] args) {
        DependencyInjector dependencyInjector = new DependencyInjector();

        ServiceA serviceA = new ServiceA();

        dependencyInjector.doInjection(serviceA);

        serviceA.run();

    }
}
