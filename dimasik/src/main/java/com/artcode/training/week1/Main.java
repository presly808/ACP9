package com.artcode.training.week1;

import com.artcode.training.week1.hash.MyMap;

import java.util.Map;

/**
 * Created by dmitriyg on 9/25/2015.
 */
public class Main {
    public static void main(String[] args) {
//        BinaryTree<Integer> binaryTree = new MyTree<>(Integer::compareTo);
//        binaryTree.add(50);
//        binaryTree.add(25);
//        binaryTree.add(75);
//        binaryTree.add(40);
//        binaryTree.add(60);
//        binaryTree.add(24);
//        binaryTree.add(76);
//        binaryTree.add(91);
//        binaryTree.add(11);
//        binaryTree.add(8);
//        binaryTree.add(22);
//        binaryTree.add(100);
//        System.out.println(binaryTree);
//        System.out.println(binaryTree.size());
//        System.out.println(binaryTree.contains(75));
//        System.out.println(binaryTree.remove(75));
//        System.out.println(binaryTree.contains(75));
//        System.out.println(binaryTree.size());
//        System.out.println(binaryTree);
//        ((MyTree) binaryTree).depth();
//        ((MyTree) binaryTree).rotateLeft();
//        ((MyTree) binaryTree).depth();
//        ((MyTree) binaryTree).rotateRight();
//        ((MyTree) binaryTree).depth();
//        System.out.println(binaryTree);

        Map<Integer, Integer> map = new MyMap<>();
        System.out.println(map.put(1, 10));
        System.out.println(map.put(2, 10));
        System.out.println(map.put(2, 20));
        System.out.println(map.put(5, 50));
        System.out.println(map.put(6, 60));
        System.out.println(map.put(4, 45));
        System.out.println(map.put(8, 80));
        System.out.println(map.put(9, 45));
        System.out.println(map.size());
        System.out.println(map.remove(9));
        System.out.println(map.remove(11));
        System.out.println(map.containsKey(5));
        System.out.println(map.containsKey(15));
        System.out.println(map.containsValue(15));
        System.out.println(map.containsValue(80));
        System.out.println(map.size());
        System.out.println(map);
        map.clear();
        System.out.println(map.size());
        System.out.println(map);
    }
}