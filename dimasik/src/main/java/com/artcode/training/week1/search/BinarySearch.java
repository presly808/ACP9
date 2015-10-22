package com.artcode.training.week1.search;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by dmitriyg on 9/28/2015.
 */
public class BinarySearch<T> {
    private Comparator<T> comparator;
    private static int counter;

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T search(T[] input, T value) {
        System.out.println(++counter);
        int comparisonResult = comparator.compare(input[input.length / 2], value);
        T result = null;
        switch (comparisonResult) {
            case 0:
                result = input[input.length / 2];
                break;
            case 1:
                result = search(Arrays.copyOfRange(input, 0, input.length / 2), value);
                break;
            case -1:
                result = search(Arrays.copyOfRange(input, input.length / 2 + 1, input.length), value);
                break;
        }
        if (comparisonResult != 0 && input.length == 1) result = null;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch<>(Integer::compareTo).search(new Integer[]{1,2,3,4,5,6,7,8}, 7));
    }
}
