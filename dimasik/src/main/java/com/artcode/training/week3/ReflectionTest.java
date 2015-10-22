package com.artcode.training.week3;

import com.artcode.training.week1.tree.MyTree;

import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args) {
        System.out.println(info(new MyTree<>(Integer::compareTo)));
    }

    public static String info(Object o) {
        StringBuilder result = new StringBuilder();
        Class aClass = o.getClass();
        result.append("Class name:\n").append(aClass.getSimpleName()).append("\n")
                .append("Fields:\n").append(getString(aClass.getDeclaredFields())).append("\n")
                .append("Constructors:\n").append(getString(aClass.getConstructors())).append("\n")
                .append("Methods:\n").append(getString(aClass.getMethods()));
        return result.toString();
    }

    private static String getString(Object[] elements) {
        return String.join("\n", Arrays.stream(elements).map(Object::toString).toArray(String[]::new));
    }
}
