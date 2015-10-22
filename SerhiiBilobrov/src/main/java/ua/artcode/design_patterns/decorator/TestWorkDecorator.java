package ua.artcode.design_patterns.decorator;

/**
 * Created by serhii on 10/2/15.
 */
public class TestWorkDecorator {
    public static void main(String[] args) {
        Decorator decorator = new WorkerDecorator("director",
                                    new WorkerDecorator("manager",
                                            new WorkerDecorator("employeer", null)));

        String res = decorator.action();
        System.out.println(res);
    }

}
