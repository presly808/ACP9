package ua.artcode.week1.tree;

import java.util.Comparator;

/**
 * Created by serhii on 25.09.15.
 */
public class BTreeTest {

    private BTree<Integer> bTree = null;

    public BTreeTest() {

        bTree = new BST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    private void testSimpleAdd(){
        bTree.add(1);
        bTree.add(2);
        bTree.add(3);

        if(bTree.size() != 3){
            System.out.println("Test add failed");
        } else {
            System.out.println("Test add success");
        }
    }


    private void testContains(){
        bTree.add(88);
        if(bTree.contains(88)){
            System.out.println("Test contains success");
        } else {
            System.out.println("Test contains failed");
        }
    }

    public void start(){
        testSimpleAdd();
        testContains();
    }



}
