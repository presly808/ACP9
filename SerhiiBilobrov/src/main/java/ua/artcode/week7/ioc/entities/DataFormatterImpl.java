package ua.artcode.week7.ioc.entities;

import java.util.Date;

/**
 * Created by serhii on 12.11.15.
 */
public class DataFormatterImpl implements DataFormatter {


    @Override
    public String format(Object o) {
        return String.format("obj = {%s}", o.toString());
    }

    @Override
    public String formatDate(Date date) {
        return String.format("%tc", date);
    }
}
