package com.stackoverflow.smartservice.algorithms.searchsort;

import com.stackoverflow.smartservice.structures.linear.DynamicArray;

/**
 * Role 8: Search & Sort Specialist -- Hamidu
 *
 * Insertion sort takes each item and places it in the right position
 * among the items that are already sorted. It has a time complexity
 * of O(n^2), but it can be quite fast when the data is already
 * mostly sorted.
 *
 * UG campus example: adding a few new service requests to an already
 * sorted list by submission time, instead of sorting the whole list again.
 */
public class InsertionSort {

    public static <T extends Comparable<T>> void sort(DynamicArray<T> arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            T key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j).compareTo(key) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        // A mostly-sorted batch of new requests arriving, by submission order
        DynamicArray<Integer> requestOrder = new DynamicArray<>();
        requestOrder.add(1);
        requestOrder.add(2);
        requestOrder.add(3);
        requestOrder.add(2); // late arrival, needs to shift back into place
        requestOrder.add(5);

        System.out.print("Before: ");
        printArray(requestOrder);

        sort(requestOrder);

        System.out.print("After:  ");
        printArray(requestOrder);
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
