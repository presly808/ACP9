package ua.artcode.week7.ioc.run;

import ua.artcode.week7.ioc.di.DependencyInjector;
import ua.artcode.week7.ioc.entities.ServiceA;
import ua.artcode.week7.ioc.entities.ViewA;

/**
 * Created by serhii on 12.11.15.
 */
public class TestDI {

    public static void main(String[] args) {
        DependencyInjector dependencyInjector = new DependencyInjector();

        ViewA viewA = dependencyInjector.getBean("ua.artcode.week7.ioc.entities.ViewA", ViewA.class);

        System.out.println(viewA);
    }
}
