package ua.artcode.week1.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 */

// declare generic -        use generic
public class BST<E> implements BTree<E> {

    private TNode<E> root;
    private Comparator<E> comparator;
    private int size = 0;


    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST(Comparator<E> comparator, E...arr) {
        this.comparator = comparator;
        for (E e : arr) {
            add(e);
        }
    }

    @Override
    public void add(E obj) {
        // compareTo with root
        // <  hasFreeLeftPlace -> create new node link with parent
        // > hasFreeRightPlace -> create new node link with parent
        // false, recursion comparing ->

        if (root == null) {
            root = new TNode<E>(obj, null, null, null);
            size++;
        } else {
            TNode<E> iter = root;

            while (iter != null) {

                int compareRes = comparator.compare(obj, iter.value);
                if (compareRes < 0) {
                    if (iter.left == null) {
                        iter.left = new TNode<E>(obj, null, null, iter);
                        size++;
                        return;
                    } else {
                        iter = iter.left;
                    }
                } else if (compareRes > 0) {
                    if (iter.right == null) {
                        iter.right = new TNode<E>(obj, null, null, iter);
                        size++;
                        return;
                    } else {
                        iter = iter.right;
                    }
                } else {
                    return;
                }
            }

        }


    }

    @Override
    public boolean contains(E obj) {
        return find(obj, root) != null;
    }


    private TNode<E> find(E el, TNode<E> iter) {

        if (iter == null) {
            return null;
        }

        int compareRes = comparator.compare(el, iter.value);

        if (compareRes < 0) {
            return find(el, iter.left);
        } else if (compareRes > 0) {
            return find(el, iter.right);
        }

        //return compareRes < 0 ? find(el, iter.left) : compareRes > 0 ? find(el, iter.left) : iter;

        return iter;
    }

    /**
     *
     *
     * @return true if left child, false if right and null if parent has not that child
     * */
    private Boolean isLeftChild(TNode child, TNode parent){
        return parent.left == child;
    }

    @Override
    public boolean remove(E obj) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getDeep() {
        return getDeep(root);
    }

    private int getDeep(TNode curr){
        if(curr == null){
            return 0;
        }
        return Math.max(getDeep(curr.left), getDeep(curr.right)) + 1;
    }

    private int maxLengthSymbolValue(TNode curr){
        if(curr == null){
            return 0;
        }

        int a = maxLengthSymbolValue(curr.left);
        int b = maxLengthSymbolValue(curr.right);
        int max = Math.max(a, b);

        int currLength = curr.value.toString().length();
        return max < currLength ? currLength : max;
    }

    private String toString(TNode<E> node) {

        String res = "";

        if (node == null) {
            return "";
        }

        String left = toString(node.left);
        String me = node.value.toString() + ",";
        String right = toString(node.right);

        return left + me + right;
    }

    @Override
    public String toString() {
        return new TreeTableFormatter().getTable();
    }

    private static class TNode<X> {

        X value;
        TNode left;
        TNode right;
        TNode parent;

        public TNode(X value, TNode left,
                     TNode right, TNode parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private class TreeTableFormatter {

        private String[][] table;

        private int columnLength;


        public TreeTableFormatter() {
            initTable();
        }

        public void initTable(){
            int biggerValueSizeInTree = maxLengthSymbolValue(root);
            columnLength = biggerValueSizeInTree;

            int deep = getDeep();

            int size = (int) (Math.pow(2, deep - 1) * 2);

            table = new String[size][size];
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    table[i][j] = makeSpaces(columnLength,'*');
                }
            }

        }

        public String makeSpaces(int size, char symbol){
            char[] mas = new char[size];
            Arrays.fill(mas,symbol);
            return String.valueOf(mas);
        }


        private String formatColumn(String value){
            if(value.length() == columnLength){
                return value;
            } else {
                int spacesCount = (columnLength - value.length());
                String spaces = makeSpaces(spacesCount,' ');
                return  spaces + value;
            }
        }


        // todo refactor this place
        public void setPoint(TNode curr, int level, int number){
            String currValue = formatColumn(curr == null ? "n" : curr.value.toString());



            if(curr == null){
                return;
            }

            int step = (int)(table.length / (2 * level));
            // find parent index
            int pos = -1;
            int parentIndex = -1;
            if(curr.parent == null){ // child is root
                pos = step;
            } else {
                parentIndex = searchParentIndex(curr.parent,level-1);
                if(isLeftChild(curr,curr.parent)){
                    pos = parentIndex - step;
                } else {
                    pos = parentIndex + step;
                }

            }

            // define left or right
            // do offset
            if(level < table.length && pos >= 0 && pos < table.length){
                table[level][pos] = currValue;
            } else {
                System.out.printf("table[%d][%d] = %s; ", level,pos, currValue);
            }


        }

        // if return null, that means no parent in this row
        private int searchParentIndex(TNode parent, int tableRow) {
            for (int i = 0; i < table[tableRow].length; i++) {
                if(table[tableRow][i].equals(parent.value.toString())){
                    return i;
                }
            }
            return -1;
        }


        // fill in table from tree
        public void fillIntTable(){

            int levelOfNodeQueue = 1;
            Queue<TNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);

            // using iteration deep(through each node, level by level)
            while(!nodeQueue.isEmpty()){
                int size = nodeQueue.size();
                for (int i = 0; i < size; i++) {
                    TNode curr = nodeQueue.remove();
                    if(curr != null){
                        nodeQueue.add(curr.left);
                        nodeQueue.add(curr.right);
                    }
                    setPoint(curr,levelOfNodeQueue, i);
                }
                levelOfNodeQueue++;
            }
        }

        public String getTable(){
            fillIntTable();
            return formatTable();
        }

        private String formatTable(){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    sb.append(table[i][j]);
                }
                sb.append("\n");
            }
            return sb.toString();
        }

    }
}
