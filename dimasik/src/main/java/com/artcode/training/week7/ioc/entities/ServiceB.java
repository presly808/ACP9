package com.artcode.training.week7.ioc.entities;

import com.artcode.training.week7.ioc.di.ForInject;

import java.util.Date;

/**
 * Created by serhii on 12.11.15.
 */
public class ServiceB implements IService {

    @ForInject
    private DataFormatter formatter;

    @ForInject
    private IService serviceA;

    public ServiceB() {
    }

    public IService getServiceA() {
        return serviceA;
    }

    public void setServiceA(IService serviceA) {
        this.serviceA = serviceA;
    }

    public DataFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DataFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        serviceA.run();
        System.out.println(formatter.formatDate(new Date()));
    }
}
