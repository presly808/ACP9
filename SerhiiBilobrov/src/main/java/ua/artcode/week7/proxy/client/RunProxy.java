package ua.artcode.week7.proxy.client;

import ua.artcode.week7.ioc.entities.IService;
import ua.artcode.week7.ioc.entities.ServiceA;
import ua.artcode.week7.proxy.service.IMath;
import ua.artcode.week7.proxy.service.IMathImpl;

/**
 * Created by serhii on 13.11.15.
 */
public class RunProxy {


    public static void main(String[] args) {
        IMath math = IMathFactory.create();

        math.add(12,34);
        math.div(23, 0);
        math.mul(22, 9);

    }

}
