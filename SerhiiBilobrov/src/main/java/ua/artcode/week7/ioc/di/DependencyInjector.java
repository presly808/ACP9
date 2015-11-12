package ua.artcode.week7.ioc.di;

import ua.artcode.week7.ioc.entities.DataFormatterImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by serhii on 12.11.15.
 */
public class DependencyInjector {

    public static final String CONTEXT_PATH = "/week7/context.properties";
    private Map<String,Object> context;

    public DependencyInjector() {

        initContext();
    }
///home/serhii/dev/ACP9_CLONE/ACP9/SerhiiBilobrov/src/main/resources/week7.week7
    // get from file
    private void initContext() {
        context = new HashMap<>();

        Properties properties = new Properties();
        try {
            properties.load(DependencyInjector.class.getResourceAsStream(CONTEXT_PATH));

            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                String className = properties.getProperty(key);
                context.put(key, createObj(className));
            }

            makeLinks();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void makeLinks() {
        context.values().stream().forEach((bean) -> doInjection(bean));
    }

    private Object createObj(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return Class.forName(className).newInstance();
    }

    public void doInjection(Object injectable){

        Class cl = injectable.getClass();

        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // find setter and call

            ForInject forInject = field.getAnnotation(ForInject.class);

            if(forInject != null){
                try {
                    field.set(injectable,context.get(field.getType().getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public<T> T getBean(String key, Class<T> type){
        return type.cast(context.get(key));
    }

}
