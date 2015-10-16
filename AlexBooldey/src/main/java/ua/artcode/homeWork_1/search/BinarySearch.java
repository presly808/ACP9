package ua.artcode.homeWork_1.search;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch<T> {

    private Comparator<T> comparator;

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T search(T[] input, T value) {
        int key = comparator.compare(input[input.length / 2], value);
        T result = null;
        switch (key) {
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
        if (key != 0 && input.length == 1) result = null;
        return result;
    }
}
