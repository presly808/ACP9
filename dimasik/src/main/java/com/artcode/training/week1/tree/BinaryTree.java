package com.artcode.training.week1.tree;

/**
 * Created by dmitriyg on 9/25/2015.
 */
public interface BinaryTree<T> {

    void add(T object);

    boolean contains(T object);

    T remove(T object);

    int size();

    T min();

    T max();
}
