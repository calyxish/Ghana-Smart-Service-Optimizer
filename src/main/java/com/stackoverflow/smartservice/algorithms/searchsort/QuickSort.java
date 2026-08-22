package com.stackoverflow.smartservice.algorithms.searchsort;

import java.util.Random;
import com.stackoverflow.smartservice.structures.linear.DynamicArray;

/**
 * Role 8: Search & Sort Specialist -- Hamidu
 *
 * QuickSort chooses a pivot, puts smaller values on one side and larger
 * values on the other, then repeats the same process on the smaller parts.
 * It runs in O(n log n) on average, but can take O(n^2) in the worst case.
 * Using a random pivot helps reduce the chance of hitting that worst case.
 *
 * UG campus example: sorting campus locations alphabetically.
 *
 * AI-RESISTANCE PARAMETER:
 * The random pivot seed is based on the last two digits of two team members'
 * IDs:
 *   Renny Abbey Mensah (22304259) -> 59
 *   Mohammed Hamidu Abilla (22398905) -> 05
 *   Seed = 5905
 *
 * This should also be recorded in evidence_note.md with Matin's other
 * derived parameters.
 */
public class QuickSort {

    private static final long RANDOM_SEED = 5905L; // 59 (Renny) + 05 (Hamidu)
    private static final Random RANDOM = new Random(RANDOM_SEED);

    public static <T extends Comparable<T>> void sort(DynamicArray<T> arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (arr.size() <= 1) {
            return;
        }
        quickSort(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(DynamicArray<T> arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(DynamicArray<T> arr, int low, int high) {
        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(arr, randomIndex, high);

        T pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(DynamicArray<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        DynamicArray<String> locations = new DynamicArray<>();
        locations.add("Balme Library");
        locations.add("Akuafo Hall");
        locations.add("JQB");
        locations.add("UGCS");
        locations.add("N-Block");

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
