package com.stackoverflow.smartservice.algorithms.searchsort;

import com.stackoverflow.smartservice.structures.linear.DynamicArray;

/**
 * Role 8: Search & Sort Specialist -- Renny
 *
 * Selection sort finds the smallest item, puts it at the front,
 * and then keeps repeating the process until everything is sorted.
 * Its time complexity is O(n^2).
 *
 * UG campus example: sorting service requests by urgency by finding
 * the most urgent request and putting it first, then repeating.
 */
public class SelectionSort {

    public static <T extends Comparable<T>> void sort(DynamicArray<T> arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).compareTo(arr.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    private static <T> void swap(DynamicArray<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        // Urgency values for a small batch of service requests
        // (1 = LOW ... 4 = CRITICAL, matching the categories in the dataset)
        DynamicArray<Integer> urgencyValues = new DynamicArray<>();
        urgencyValues.add(3);
        urgencyValues.add(1);
        urgencyValues.add(4);
        urgencyValues.add(2);

        System.out.print("Before: ");
        printArray(urgencyValues);

        sort(urgencyValues);

        System.out.print("After:  ");
        printArray(urgencyValues);
    }

    private static <T> void printArray(DynamicArray<T> arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.size(); i++) {
            sb.append(arr.get(i));
            if (i < arr.size() - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
