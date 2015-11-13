package ua.artcode.week7.proxy.service;

/**
 * Created by serhii on 13.11.15.
 */
public class IMathImpl implements IMath {
    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double div(double a, double b) {
        return a / b;
    }

    @Override
    public double mul(double a, double b) {
        return a * b;
    }
}
