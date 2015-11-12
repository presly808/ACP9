package ua.artcode.week7.ioc.entities;

import java.util.Date;

/**
 * Created by serhii on 12.11.15.
 */
public interface DataFormatter {

    String format(Object o);

    String formatDate(Date date);



}
