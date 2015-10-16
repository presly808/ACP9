package ua.artcode.week3.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class ReflectUtils {


    public static void getFieldsValues(Object el) throws IllegalAccessException {
        Class cl = el.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(el);
            //field.set(el);
            System.out.println(val);
        }
    }

    public static String info(Class cl){

        // 1) arr to stream
        // 20 map - convert from A to B
        // 3) some -> {} lambdas
        // 4)

        return new StringBuilder()
                .append("CLASSNAME\n"+cl.getName())
                .append("\nFIELDS\n")
                .append(toString(cl.getDeclaredFields()))
                .append("\nMETHODS\n")
                .append(toString(cl.getDeclaredMethods()))
                .append("\n").toString();



    }

    public static String toString(Object[] mas){
        String[] lines = Arrays.stream(mas).map(el -> el.toString()).toArray(String[]::new);
        String res = String.join("\n", lines);
        return res;

    }

}
