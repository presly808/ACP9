package ua.artcode.week7.proxy.service;

/**
 * Created by serhii on 13.11.15.
 */
public class ValidationMathProxy implements IMath {

    private IMath iMath;

    public ValidationMathProxy(IMath iMath) {
        this.iMath = iMath;
    }

    @Override
    public double add(double a, double b) {
        System.out.println("Proxy add");
        // validation
        return iMath.add(a,b);
    }

    @Override
    public double div(double a, double b) {
        System.out.println("Proxy div");
        if(b == 0){
            System.err.println("can not divide by zero");
            return 0;
        }

        return iMath.div(a , b);
    }

    @Override
    public double mul(double a, double b) {
        System.out.println("Proxy mul");
        return iMath.mul(a,b);
    }
}
