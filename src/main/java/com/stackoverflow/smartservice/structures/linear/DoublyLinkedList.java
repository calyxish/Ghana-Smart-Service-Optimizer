package com.stackoverflow.smartservice.structures.linear;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    static class DoubleNode<T> {
        T data;
        DoubleNode<T> next;
        DoubleNode<T> prev;

        DoubleNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T data) {
        DoubleNode<T> newNode = new DoubleNode<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        DoubleNode<T> newNode = new DoubleNode<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public boolean remove(T targetData) {
        DoubleNode<T> current = head;
        while (current != null) {
            if (current.data == null ? targetData == null : current.data.equals(targetData)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void reverse() {
        DoubleNode<T> temp = null;
        DoubleNode<T> current = head;
        tail = head;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            head = temp.prev;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<>(head);
    }

    DoubleNode<T> getHead() {
        return head;
    }
}
