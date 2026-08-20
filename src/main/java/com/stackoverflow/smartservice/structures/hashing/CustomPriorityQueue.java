package com.stackoverflow.smartservice.structures.hashing;

import java.util.NoSuchElementException;

public class CustomPriorityQueue<T> {

    private static class PriorityEntry<T> implements Comparable<PriorityEntry<T>> {
        T item;
        int priority;
        int insertionOrder;

        PriorityEntry(T item, int priority, int insertionOrder) {
            this.item = item;
            this.priority = priority;
            this.insertionOrder = insertionOrder;
        }

        @Override
        public int compareTo(PriorityEntry<T> other) {
            int cmp = Integer.compare(this.priority, other.priority);
            if (cmp != 0) return cmp;
            return Integer.compare(other.insertionOrder, this.insertionOrder);
        }
    }

    private final MaxHeap<PriorityEntry<T>> heap;
    private int counter;

    public CustomPriorityQueue() {
        this.heap = new MaxHeap<>();
        this.counter = 0;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enqueue(T item, int priority) {
        heap.insert(new PriorityEntry<>(item, priority, counter++));
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return heap.extractMax().item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return heap.peek().item;
    }
}
