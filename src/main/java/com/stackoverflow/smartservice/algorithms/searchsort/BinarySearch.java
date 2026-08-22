package com.stackoverflow.smartservice.algorithms.searchsort;

import com.stackoverflow.smartservice.structures.linear.DynamicArray;

/**
 * Role 8: Search & Sort Specialist -- Renny
 *
 * Binary search works by repeatedly cutting a sorted array in half
 * to find the item we're looking for. This gives it a time complexity
 * of O(log n).
 * 
 * The array must be sorted first, such as with MergeSort or QuickSort.
 *
 * UG campus example: quickly finding a location by its ID in a sorted
 * list, such as searching for locationId = 23 among 50 campus locations.
 */
public class BinarySearch {

    /**
     * Returns the index of target in a SORTED arr, or -1 if not found.
     */
    public static <T extends Comparable<T>> int search(DynamicArray<T> arr, T target) {
        if (arr == null || target == null) {
            throw new IllegalArgumentException("Array and target must not be null");
        }
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr.get(mid).compareTo(target);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Sorted UG campus location IDs (would come from Merge/QuickSort in practice)
        DynamicArray<Integer> locationIds = new DynamicArray<>();
        locationIds.add(1);
        locationIds.add(3);
        locationIds.add(5);
        locationIds.add(8);
        locationIds.add(12);
        locationIds.add(23);
        locationIds.add(31);
        locationIds.add(42);
        locationIds.add(50);

        int target = 23;
        int index = search(locationIds, target);
        System.out.println("Binary search for locationId " + target + ": "
                + (index != -1 ? "found at index " + index : "not found"));
    }
}
