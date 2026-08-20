package com.stackoverflow.smartservice.structures.hashing;

public class CustomHashSet<T> {

    private static final Object PRESENT = new Object();
    private final HashTable<T, Object> table;

    public CustomHashSet() {
        this.table = new HashTable<>();
    }

    public CustomHashSet(int capacity) {
        this.table = new HashTable<>(capacity);
    }

    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public boolean add(T element) {
        if (table.containsKey(element)) {
            return false;
        }
        table.put(element, PRESENT);
        return true;
    }

    public boolean contains(T element) {
        return table.containsKey(element);
    }

    public boolean remove(T element) {
        return table.remove(element) != null;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) table.keys();
    }
}
