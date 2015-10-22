package ua.artcode.home.week3home.objtoxml;

import java.util.Map;
import ua.artcode.home.week3home.reflection.Man;

/**
 * User: huyti
 * Date: 14.10.15
 */
public class ManEntry implements Map.Entry {
    Integer key;
    Man value;

    public ManEntry(Integer key, Man value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public Man getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        return null;

    }
}
