package ua.artcode.homeWork_1.search;

public class TestTestBinarySearch {
    public static void main(String[] args) {
        //System.out.println(new BinarySearch<>(String::compareTo).search(new String[]{"A", "B", "C", "D", "E", "R", "Z", "X"}, "A"));
        System.out.println(new BinarySearch<>(Integer::compareTo).search(new Integer[]{1,2,3,4,5,6,7,8}, 2));
    }
}
