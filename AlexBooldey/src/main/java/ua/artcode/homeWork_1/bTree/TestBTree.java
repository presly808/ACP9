package ua.artcode.homeWork_1.bTree;

public class TestBTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new MyBTree<>(Integer::compareTo);

        for (int i = -4; i < 100 ; i++) {
            binaryTree.add(i);
        }
        System.out.println(binaryTree.size());
        System.out.println(binaryTree.contains(-1));
        System.out.println(binaryTree.remove(-1));
        System.out.println(binaryTree.contains(-1));
        System.out.println(binaryTree.size());
    }
}
