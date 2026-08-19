package com.stackoverflow.smartservice.structures.linear;

public class LinearDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Custom Linear Structures Verification ===");

        // 1. DynamicArray Test
        System.out.println("\n--- Testing DynamicArray ---");
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Balme Library");
        arr.add("JQB");
        arr.add("UGCS");
        arr.insert(1, "N-Block");
        System.out.println("Size: " + arr.size()); // Expect 4
        System.out.println("At index 1: " + arr.get(1)); // Expect N-Block
        arr.remove(2); // Remove JQB
        for (String s : arr) {
            System.out.println("Location: " + s);
        }

        // 2. SinglyLinkedList Test
        System.out.println("\n--- Testing SinglyLinkedList ---");
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        sll.addLast("Balme Library");
        sll.addLast("UGCS");
        sll.addFirst("Akuafo Hall");
        sll.insertAfter("Balme Library", "JQB");
        System.out.println("Size: " + sll.size()); // Expect 4
        sll.remove("Balme Library");
        for (String s : sll) {
            System.out.println("Location: " + s);
        }

        // 3. DoublyLinkedList Test
        System.out.println("\n--- Testing DoublyLinkedList ---");
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        dll.addLast("Balme Library");
        dll.addLast("UGCS");
        dll.addFirst("Volta Hall");
        System.out.println("Forward:");
        for (String s : dll) {
            System.out.println("Location: " + s);
        }
        dll.reverse();
        System.out.println("Reversed:");
        for (String s : dll) {
            System.out.println("Location: " + s);
        }

        // 4. CustomStack Test
        System.out.println("\n--- Testing CustomStack ---");
        CustomStack<String> stack = new CustomStack<>();
        stack.push("Balme Library");
        stack.push("JQB");
        stack.push("UGCS");
        System.out.println("Top: " + stack.peek()); // Expect UGCS
        System.out.println("Popped: " + stack.pop()); // Expect UGCS
        System.out.println("Popped: " + stack.pop()); // Expect JQB
        System.out.println("Size: " + stack.size()); // Expect 1

        // 5. CustomQueue Test
        System.out.println("\n--- Testing CustomQueue ---");
        CustomQueue<String> queue = new CustomQueue<>();
        queue.enqueue("Balme Library");
        queue.enqueue("JQB");
        queue.enqueue("UGCS");
        System.out.println("Front: " + queue.peek()); // Expect Balme Library
        System.out.println("Dequeued: " + queue.dequeue()); // Expect Balme Library
        System.out.println("Dequeued: " + queue.dequeue()); // Expect JQB
        System.out.println("Size: " + queue.size()); // Expect 1

        // 6. CustomDeque Test
        System.out.println("\n--- Testing CustomDeque ---");
        CustomDeque<String> deque = new CustomDeque<>();
        deque.addRear("JQB");
        deque.addFront("Balme Library");
        deque.addRear("UGCS");
        System.out.println("Front: " + deque.peekFront()); // Expect Balme Library
        System.out.println("Rear: " + deque.peekRear()); // Expect UGCS
        System.out.println("Removed Front: " + deque.removeFront()); // Expect Balme Library
        System.out.println("Removed Rear: " + deque.removeRear()); // Expect UGCS
        System.out.println("Size: " + deque.size()); // Expect 1

        System.out.println("\n=== All Linear Structures Tests Completed ===");
    }
}
