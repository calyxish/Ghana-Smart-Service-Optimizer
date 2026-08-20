package com.stackoverflow.smartservice.structures.hashing;

/**
 * Hash table with separate chaining collision resolution.
 * Initial size 227 — derived from team member index numbers:
 * last 2 digits of 22372116 (16) + 22405188 (88) + 22244884 (84) = 188,
 * next prime after 188 is 191; bumped to 227 (next prime) for headroom.
 */
public class HashTable<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;
    private int collisionCount;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.buckets = new Entry[227];
        this.size = 0;
        this.collisionCount = 0;
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.buckets = new Entry[Math.max(capacity, 1)];
        this.size = 0;
        this.collisionCount = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public double getLoadFactor() {
        return (double) size / buckets.length;
    }

    public int getCapacity() {
        return buckets.length;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if ((double)(size + 1) / buckets.length > LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        if (buckets[index] != null) {
            collisionCount++;
        }
        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        int idx = 0;
        for (Entry<K, V> bucket : buckets) {
            Entry<K, V> current = bucket;
            while (current != null) {
                result[idx++] = current.key;
                current = current.next;
            }
        }
        return result;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[nextPrime(oldBuckets.length * 2)];
        size = 0;
        collisionCount = 0;

        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> current = bucket;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    private static int nextPrime(int n) {
        if (n <= 2) return 2;
        if (n % 2 == 0) n++;
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
