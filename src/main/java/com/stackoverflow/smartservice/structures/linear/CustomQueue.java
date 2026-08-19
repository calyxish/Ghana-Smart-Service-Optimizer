package com.stackoverflow.smartservice.structures.linear;

import java.util.NoSuchElementException;

public class CustomQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomQueue() {
        this.data = (T[]) new Object[10];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(T element) {
        if (size == data.length) {
            resize();
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T element = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return data[front];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = data.length == 0 ? 1 : data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        rear = size;
    }
}
