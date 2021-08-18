package sbertech.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BubbleSort {
    public static<T extends Comparable<T>> List<T> bubbleSort(List<T> arr) {
        if (arr == null) throw new IllegalArgumentException("Array must not be null");
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).compareTo(arr.get(j)) > 0) {
                    T temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(bubbleSort(new ArrayList<>(List.of(1.0,3.0,2.0))));
    }
}
