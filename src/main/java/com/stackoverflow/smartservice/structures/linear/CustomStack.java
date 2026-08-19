package com.stackoverflow.smartservice.structures.linear;

import java.util.EmptyStackException;

public class CustomStack<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomStack() {
        this.data = (T[]) new Object[10];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = data[--size];
        data[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = data.length == 0 ? 1 : data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
