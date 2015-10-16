package ua.artcode.homeWork_1.bTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MyBTree<T> implements BinaryTree<T> {

    private Node<T> root;
    private Comparator<T> comparator;
    private int size;

    public MyBTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void rotateLeft(){
        Node<T> temp = root.right;
        root.right = root.right.left;
        temp.left = root;
        root = temp;
    }

    public void rotateRight(){
        Node<T> temp = root.left;
        root.left = root.left.right;
        temp.right = root;
        root = temp;
    }

    @Override
    public void add(T object) {
        root = add(object, root);
    }

    private Node<T> add(T object, Node<T> node) {
        if (node == null) {
            size++;
            return new Node<>(object, null, null);
        } else {
            int compare = comparator.compare(node.value, object);
            if (compare > 0) {
                node.left = add(object, node.left);
            } else {
                node.right = add(object, node.right);
            }
        }
        return node;
    }

    @Override
    public boolean contains(T object) {
        return find(object, root) != null;
    }

    private Node<T> find(T object, Node<T> node) {
        if (node == null || node.value.equals(object)) {
            return node;
        } else {
            int compare = comparator.compare(node.value, object);
            return compare > 0 ? find(object, node.left) : find(object, node.right);
        }
    }

    @Override
    public T remove(T object) {
        if (object == null || root == null) {
            return null;
        } else {
            boolean removed;
            if (root.value.equals(object)) {
                removeFromCurrentNode(root, root, 0);
                removed = true;
            } else {
                removed = remove(object, root);
            }
            if (removed) size--;
            return removed ? object : null;
        }
    }

    private boolean remove(T object, Node<T> node) {
        Node<T> removable;
        if (comparator.compare(node.value, object) > 0) {
            if (node.left == null) return false;
            if (node.left.value.equals(object)) {
                removable = node.left;
                removeFromCurrentNode(node, removable, 1);
                return true;
            } else {
                return remove(object, node.left);
            }
        } else {
            if (node.right == null) return false;
            if (node.right.value.equals(object)) {
                removable = node.right;
                removeFromCurrentNode(node, removable, -1);
                return true;
            } else {
                return remove(object, node.right);
            }
        }
    }

    private void removeFromCurrentNode(Node<T> node, Node<T> removable, int compareResult) {
        if (removable.left == null && removable.right == null) {
            if (compareResult == 1) {
                node.left = null;
            } else if (compareResult == -1) {
                node.right = null;
            } else {
                root = null;
            }
        } else if (removable.left != null && removable.right != null) {
            T min = getMin(removable.right);
            remove(min, removable);
            removable.value = min;
        } else if (removable.left != null) {
            if (compareResult == 1) {
                node.left = removable.left;
            } else if (compareResult == -1) {
                node.right = removable.left;
            } else {
                root = removable.left;
            }
        } else {
            if (compareResult == 1) {
                node.left = removable.right;
            } else if (compareResult == -1) {
                node.right = removable.right;
            } else {
                root = removable.right;
            }
        }
    }

    @Override
    public T max() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }

    private T getMin(Node<T> node) {
        if (node.left != null) return getMin(node.left);
        return node.value;
    }

    private T getMax(Node<T> node) {
        if (node.right != null) return getMax(node.right);
        return node.value;
    }

    @Override
    public T min() {
        if (root == null) {
            return null;
        }
        return getMin(root);
    }

    public int depth() {
        List<Node<T>> nodes = new ArrayList<>();
        nodes.add(root);
        return depth(nodes);
    }

    private int depth(List<Node<T>> nodes) {
        List<Node<T>> currentNodes = new ArrayList<>();
        for (Node<T> node : nodes) {
            if (node != null) {
                currentNodes.add(node.left);
                currentNodes.add(node.right);
            }
        }
        return currentNodes.isEmpty() ? 0 : 1 + depth(currentNodes);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyTree{" + root + '}';
    }

    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" + /*(left == null ? "" : "left=" + left + ", ") +*/ "value=" + value/* + (right == null ? "" : ", right=" + right) + '}'*/;
        }
    }
}