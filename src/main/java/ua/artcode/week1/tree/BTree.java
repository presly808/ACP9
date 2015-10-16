package ua.artcode.week1.tree;

/**
 *
 */
public interface BTree<T> {


    void add(T obj);

    boolean contains(T obj);

    boolean remove(T obj);

    int size();

    int getDeep();


}
