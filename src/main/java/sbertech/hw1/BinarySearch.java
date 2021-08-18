package sbertech.hw1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearch {
    public static<T extends Comparable<T>> int binarySearch(T elem, List<T> sorted) {
        if (sorted == null) throw new IllegalArgumentException("Array must not be null");
        if (sorted.size() == 0) return -1;
        int leftBound = 0;
        int rightBound = sorted.size() - 1;
        int pivot = 0;
        while (leftBound <= rightBound) {
            pivot = leftBound + (rightBound - leftBound) / 2;
            if (sorted.get(pivot).compareTo(elem) > 0) {
                rightBound = pivot - 1;
            } else if (sorted.get(pivot).compareTo(elem) < 0) {
                leftBound = pivot + 1;
            } else if (sorted.get(pivot).compareTo(elem) == 0) {
                return pivot;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(5, BubbleSort.bubbleSort(new ArrayList<>(List.of(1, 3, 2, 0)))));
    }
}
