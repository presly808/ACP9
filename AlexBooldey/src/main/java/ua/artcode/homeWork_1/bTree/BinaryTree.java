package ua.artcode.homeWork_1.bTree;

public interface BinaryTree<T> {

    void add(T object);

    boolean contains(T object);

    T remove(T object);

    int size();

    T min();

    T max();
}
