package com.stackoverflow.smartservice.algorithms.searchsort;

import com.stackoverflow.smartservice.structures.linear.DynamicArray;

/**
 * Role 8: Search & Sort Specialist -- Hamidu
 *
 * Merge sort splits the data into smaller parts, sorts each part,
 * and then combines them back together in the correct order.
 * It has a time complexity of O(n log n), even when the data is
 * already sorted or in a random order.
 *
 * UG campus example: efficiently sorting 300 service requests by
 * submission time. It can also be useful for Emelia's (Role 13)
 * trace table because the splitting and merging steps are easy to follow.
 */
public class MergeSort {

    public static <T extends Comparable<T>> void sort(DynamicArray<T> arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (arr.size() <= 1) {
            return;
        }
        Object[] buffer = new Object[arr.size()];
        mergeSort(arr, buffer, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(DynamicArray<T> arr, Object[] buffer, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(DynamicArray<T> arr, Object[] buffer, int left, int mid, int right) {
        for (int k = left; k <= right; k++) {
            buffer[k] = arr.get(k);
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            T leftVal = (T) buffer[i];
            T rightVal = (T) buffer[j];
            if (leftVal.compareTo(rightVal) <= 0) {
                arr.set(k++, leftVal);
                i++;
            } else {
                arr.set(k++, rightVal);
                j++;
            }
        }
        while (i <= mid) {
            arr.set(k++, (T) buffer[i++]);
        }
        while (j <= right) {
            arr.set(k++, (T) buffer[j++]);
        }
    }

    public static void main(String[] args) {
        // 8 campus locations, unsorted -- good size for a trace table too
        DynamicArray<String> locations = new DynamicArray<>();
        locations.add("Balme Library");
        locations.add("Akuafo Hall");
        locations.add("JQB");
        locations.add("UGCS");
        locations.add("N-Block");
        locations.add("Legon Hall");
        locations.add("Pentagon Hall");
        locations.add("Volta Hall");

        System.out.print("Before: ");
        printArray(locations);

        sort(locations);

        System.out.print("After:  ");
        printArray(locations);
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
