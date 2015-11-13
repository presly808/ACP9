package ua.artcode.week7.proxy.client;

import ua.artcode.week7.proxy.service.IMath;
import ua.artcode.week7.proxy.service.IMathImpl;
import ua.artcode.week7.proxy.service.ValidationMathProxy;

/**
 * Created by serhii on 13.11.15.
 */
public class IMathFactory {

    public static IMath create() {
        return new ValidationMathProxy(new IMathImpl());
    }

}
