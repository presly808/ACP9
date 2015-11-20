package com.artcode.training.week7.ioc.di;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by serhii on 12.11.15.
 */
public class DependencyInjector {

    public static final String CONTEXT_PROPERTIES = "week7.ioc/context.properties";
    private Map<String, Object> context;

    public DependencyInjector() {

        initContext();
    }

    // get from file
    private void initContext() {
        context = new HashMap<>();
        // load from file, properties
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(CONTEXT_PROPERTIES));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        properties.forEach((o, o2) -> {
            try {
                context.put(String.valueOf(o), Class.forName(o2.toString()).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        context.values().forEach(this::doInjection);
    }

    public void doInjection(Object injectable) {

        Class cl = injectable.getClass();

        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // find setter and call

            ForInject forInject = field.getAnnotation(ForInject.class);

            if (forInject != null) {
                try {
                    field.set(injectable, context.get(field.getType().getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Object getBean(String key) {
        return context.get(key);
    }

}
