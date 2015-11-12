package ua.artcode.week7.ioc.di;

import ua.artcode.week7.ioc.entities.DataFormatterImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 12.11.15.
 */
public class DependencyInjector {

    private Map<String,Object> context;

    public DependencyInjector() {

        initContext();
    }

    // get from file
    private void initContext() {
        context = new HashMap<>();
        // load from file, properties
        context.put("formatter", new DataFormatterImpl());
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

    public Object getBean(String key){
        return context.get(key);
    }

}
