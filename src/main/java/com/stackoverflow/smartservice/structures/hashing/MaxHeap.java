package com.stackoverflow.smartservice.structures.hashing;

import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<T>> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MaxHeap() {
        this.data = (T[]) new Comparable[16];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.data = (T[]) new Comparable[Math.max(capacity, 1)];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return data[0];
    }

    public void insert(T element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
        siftUp(size);
        size++;
    }

    public T extractMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T max = data[0];
        size--;
        data[0] = data[size];
        data[size] = null;
        if (size > 0) {
            siftDown(0);
        }
        return max;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> MaxHeap<T> heapify(T[] array) {
        MaxHeap<T> heap = new MaxHeap<>(array.length);
        for (int i = 0; i < array.length; i++) {
            heap.data[i] = array[i];
        }
        heap.size = array.length;
        for (int i = (heap.size / 2) - 1; i >= 0; i--) {
            heap.siftDown(i);
        }
        return heap;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[index].compareTo(data[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && data[left].compareTo(data[largest]) > 0) {
                largest = left;
            }
            if (right < size && data[right].compareTo(data[largest]) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Comparable[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
