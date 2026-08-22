package com.stackoverflow.smartservice.algorithms.searchsort;

import com.stackoverflow.smartservice.structures.linear.DynamicArray;
import com.stackoverflow.smartservice.structures.linear.SinglyLinkedList;
import com.stackoverflow.smartservice.structures.linear.DoublyLinkedList;

/**
 * Role 8: Search & Sort Specialist -- Renny
 *
 * Linear search checks each item one at a time until it finds what
 * it's looking for. It has a time complexity of O(n).
 * It can be used even when the data isn't sorted, and works with
 * both structures created by Role 4 (Samuel & Caleb).
 *
 * UG campus example: searching service requests by source location
 * or finding a specific location without sorting the data first.
 */
public class LinearSearch {

    /**
     * Returns the index of target in arr, or -1 if not found.
     */
    public static <T> int search(DynamicArray<T> arr, T target) {
        if (arr == null || target == null) {
            throw new IllegalArgumentException("Array and target must not be null");
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if target is found anywhere in the linked list.
     * (SinglyLinkedList does not support random-access indexing, so we
     * report presence rather than a position -- matches the "chain of
     * maintenance requests" use case from the guide.)
     */
    public static <T> boolean search(SinglyLinkedList<T> list, T target) {
        if (list == null || target == null) {
            throw new IllegalArgumentException("List and target must not be null");
        }
        return list.contains(target);
    }

    /**
     * Returns true if target is found anywhere in the doubly linked list.
     * DoublyLinkedList has no built-in contains(), so this walks the list
     * via its iterator (backed by CustomIterator, which now supports
     * DoubleNode as well as SinglyLinkedList's Node).
     *
     * UG campus use case: search through request history that can also
     * be browsed forwards/backwards.
     */
    public static <T> boolean search(DoublyLinkedList<T> list, T target) {
        if (list == null || target == null) {
            throw new IllegalArgumentException("List and target must not be null");
        }
        for (T item : list) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DynamicArray<String> locations = new DynamicArray<>();
        locations.add("Balme Library");
        locations.add("Akuafo Hall");
        locations.add("JQB");
        locations.add("UGCS");
        locations.add("N-Block");

        String target = "UGCS";
        int index = search(locations, target);
        System.out.println("Linear search (DynamicArray) for \"" + target + "\": "
                + (index != -1 ? "found at index " + index : "not found"));

        SinglyLinkedList<String> requestChain = new SinglyLinkedList<>();
        requestChain.addLast("Fix broken projector in JQB Room 2");
        requestChain.addLast("Plumbing issue at Akuafo Hall Block C");
        requestChain.addLast("Wi-Fi down at UGCS Lab 3");

        String requestTarget = "Wi-Fi down at UGCS Lab 3";
        boolean found = search(requestChain, requestTarget);
        System.out.println("Linear search (SinglyLinkedList) for request: "
                + (found ? "found" : "not found"));

        DoublyLinkedList<String> requestHistory = new DoublyLinkedList<>();
        requestHistory.addLast("Room booking for Legon Hall seminar room");
        requestHistory.addLast("Fix broken projector in JQB Room 2");
        requestHistory.addLast("Wi-Fi down at UGCS Lab 3");

        boolean foundInHistory = search(requestHistory, requestTarget);
        System.out.println("Linear search (DoublyLinkedList) for request: "
                + (foundInHistory ? "found" : "not found"));
    }
}
