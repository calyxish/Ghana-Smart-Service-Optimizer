package com.stackoverflow.smartservice.structures.hashing;

public class HashingDemo {

    public static void main(String[] args) {
        System.out.println("=== UG Campus Smart Service Optimizer ===");
        System.out.println("=== Role 6: Priority & Hash Structures Demo ===\n");

        demoMinHeap();
        demoMaxHeap();
        demoPriorityQueue();
        demoHashTable();
        demoHashSet();
        demoHashMap();
    }

    private static void demoMinHeap() {
        System.out.println("--- MinHeap Demo ---");
        System.out.println("Inserting campus distances: 5.2, 1.1, 3.8, 0.3, 2.5");
        MinHeap<Double> heap = new MinHeap<>();
        heap.insert(5.2);
        heap.insert(1.1);
        heap.insert(3.8);
        heap.insert(0.3);
        heap.insert(2.5);

        System.out.println("Size: " + heap.size());
        System.out.println("Extracting in order (shortest distance first):");
        while (!heap.isEmpty()) {
            System.out.println("  " + heap.extractMin() + " km");
        }
        System.out.println();
    }

    private static void demoMaxHeap() {
        System.out.println("--- MaxHeap Demo ---");
        System.out.println("Inserting request urgency values: 3, 7, 1, 9, 5");
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        heap.insert(5);

        System.out.println("Size: " + heap.size());
        System.out.println("Extracting in order (most urgent first):");
        while (!heap.isEmpty()) {
            System.out.println("  Urgency: " + heap.extractMax());
        }
        System.out.println();
    }

    private static void demoPriorityQueue() {
        System.out.println("--- CustomPriorityQueue Demo ---");
        System.out.println("Campus service request dispatch queue:\n");

        CustomPriorityQueue<String> pq = new CustomPriorityQueue<>();
        pq.enqueue("Replace light bulb at N-Block", 1);
        pq.enqueue("Fix broken projector at JQB Room 2", 5);
        pq.enqueue("Water main burst at Volta Hall", 10);
        pq.enqueue("Wi-Fi down at UGCS Lab 3", 7);
        pq.enqueue("Clean seminar room at Legon Hall", 2);

        System.out.println("Dispatch order (highest priority first):");
        while (!pq.isEmpty()) {
            System.out.println("  -> " + pq.dequeue());
        }
        System.out.println();
    }

    private static void demoHashTable() {
        System.out.println("--- HashTable Demo ---");
        System.out.println("Initial capacity: 227 (derived from team index numbers)");

        HashTable<Integer, String> table = new HashTable<>();
        String[] locations = {
            "Balme Library", "JQB", "UGCS", "N-Block", "Akuafo Hall",
            "Legon Hall", "Volta Hall", "Commonwealth Hall", "Pentagon",
            "ISH", "Athletic Oval", "Night Market", "Science Faculty",
            "Engineering Faculty", "Business School"
        };
        for (int i = 0; i < locations.length; i++) {
            table.put(i + 1, locations[i]);
        }

        System.out.println("Stored " + table.size() + " locations");
        System.out.println("Load factor: " + String.format("%.3f", table.getLoadFactor()));
        System.out.println("Collisions: " + table.getCollisionCount());
        System.out.println("Lookup locationId=3: " + table.get(3));
        System.out.println("Lookup locationId=7: " + table.get(7));
        System.out.println("Contains locationId=50: " + table.containsKey(50));

        table.remove(3);
        System.out.println("After removing locationId=3, size: " + table.size());
        System.out.println("Lookup locationId=3: " + table.get(3));
        System.out.println();
    }

    private static void demoHashSet() {
        System.out.println("--- CustomHashSet Demo ---");
        System.out.println("Tracking visited locations during graph traversal:");

        CustomHashSet<String> visited = new CustomHashSet<>();
        visited.add("Balme Library");
        visited.add("JQB");
        visited.add("UGCS");
        System.out.println("Visited: Balme Library, JQB, UGCS");

        boolean duplicate = visited.add("JQB");
        System.out.println("Add JQB again: " + (duplicate ? "added" : "already visited"));

        System.out.println("Contains UGCS: " + visited.contains("UGCS"));
        System.out.println("Contains Pentagon: " + visited.contains("Pentagon"));
        System.out.println("Total visited: " + visited.size());
        System.out.println();
    }

    private static void demoHashMap() {
        System.out.println("--- CustomHashMap Demo ---");
        System.out.println("Mapping location IDs to names:");

        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "Balme Library");
        map.put(2, "JQB");
        map.put(3, "UGCS");
        map.put(4, "N-Block");
        map.put(5, "Akuafo Hall");

        System.out.println("map.get(1) = " + map.get(1));
        System.out.println("map.get(3) = " + map.get(3));
        System.out.println("map.containsKey(5) = " + map.containsKey(5));
        System.out.println("map.containsKey(99) = " + map.containsKey(99));

        map.remove(3);
        System.out.println("After removing key 3, map.get(3) = " + map.get(3));
        System.out.println("Size: " + map.size());
        System.out.println("Collisions: " + map.getCollisionCount());
        System.out.println("Load factor: " + String.format("%.3f", map.getLoadFactor()));
    }
}
