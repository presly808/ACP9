package ua.artcode.week7.ioc.entities;

import java.util.Date;

/**
 * Created by serhii on 12.11.15.
 */
public class ServiceB implements IService {

    private DataFormatter formatter;

    public ServiceB() {
    }

    public DataFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DataFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        formatter.formatDate(new Date());
    }
}
