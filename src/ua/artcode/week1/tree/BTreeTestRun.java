package ua.artcode.week1.tree;

import com.sun.org.apache.xml.internal.utils.StringComparable;

/**
 * Created by serhii on 25.09.15.
 */
public class BTreeTestRun {

    public static void main(String[] args) {
        BTreeTest test = new BTreeTest();
        test.start();

        // arg    body
        // () -> {}
        BTree<Integer> bTree1 = new BST<>((Integer i1,Integer i2) -> {return i1 - i2;});
        BTree<String> bTree2 = new BST<>(String::compareTo);

        bTree1.add(5);
        bTree1.add(2);
        bTree1.add(8);
        bTree1.add(1);
        bTree1.add(4);
        bTree1.add(7);
        bTree1.add(9);
        bTree1.add(0);
        bTree1.add(3);
        bTree1.add(6);
        bTree1.add(-5);
        bTree1.add(15);
        bTree1.add(10);
        bTree1.add(8);


        BTree<Integer> bTree3 = new BST<>((Integer::compare), 100, 50,25,60,20,150,140,170,130,55, 30, 10,25);
        System.out.println(bTree3.toString());


//        System.out.println(bTree1.toString());



    }
}
