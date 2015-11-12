package ua.artcode.week7.ioc.entities;

import ua.artcode.week7.ioc.di.ForInject;

/**
 * Created by serhii on 12.11.15.
 */
public class ViewA {

    @ForInject
    private IService service;

}
