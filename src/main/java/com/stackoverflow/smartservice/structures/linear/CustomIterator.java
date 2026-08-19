package com.stackoverflow.smartservice.structures.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator<T> implements Iterator<T> {
    private SinglyLinkedList.Node<T> singleNode;
    private DoublyLinkedList.DoubleNode<T> doubleNode;
    private final boolean isDoubly;

    public CustomIterator(SinglyLinkedList.Node<T> head) {
        this.singleNode = head;
        this.doubleNode = null;
        this.isDoubly = false;
    }

    public CustomIterator(DoublyLinkedList.DoubleNode<T> head) {
        this.singleNode = null;
        this.doubleNode = head;
        this.isDoubly = true;
    }

    @Override
    public boolean hasNext() {
        if (isDoubly) {
            return doubleNode != null;
        } else {
            return singleNode != null;
        }
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in iterator");
        }
        T data;
        if (isDoubly) {
            data = doubleNode.data;
            doubleNode = doubleNode.next;
        } else {
            data = singleNode.data;
            singleNode = singleNode.next;
        }
        return data;
    }
}
