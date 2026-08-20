package com.stackoverflow.smartservice.structures.hashing;

public class CustomHashMap<K, V> {

    private final HashTable<K, V> table;

    public CustomHashMap() {
        this.table = new HashTable<>();
    }

    public CustomHashMap(int capacity) {
        this.table = new HashTable<>(capacity);
    }

    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public void put(K key, V value) {
        table.put(key, value);
    }

    public V get(K key) {
        return table.get(key);
    }

    public boolean containsKey(K key) {
        return table.containsKey(key);
    }

    public V remove(K key) {
        return table.remove(key);
    }

    @SuppressWarnings("unchecked")
    public K[] keys() {
        return table.keys();
    }

    public int getCollisionCount() {
        return table.getCollisionCount();
    }

    public double getLoadFactor() {
        return table.getLoadFactor();
    }
}
