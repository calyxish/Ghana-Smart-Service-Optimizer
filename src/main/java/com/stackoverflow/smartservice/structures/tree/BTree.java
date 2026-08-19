package com.stackoverflow.smartservice.structures.tree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simplified multi-way search tree (a proper B-tree of configurable minimum
 * degree t; the default t=2 makes every node hold 1-3 keys and 2-4
 * children, i.e. a 2-3-4 tree, per the spec's suggested simplification).
 * Every node holds multiple keys, and a full node is split on the way down
 * during insert -- exactly how a real database index page splits when it
 * fills up.
 *
 * UG campus usage: simulates how a database index works, e.g. indexing
 * every service_requests row by requestId across a handful of wide nodes
 * instead of one node per key.
 *
 * Search and insert are the only operations the spec calls for; inorder()
 * is included anyway because it's the cheapest way to prove insert/split
 * actually keeps the tree sorted (see TreeDemo).
 */
public class BTree<K extends Comparable<K>, V> {

    private static final int DEFAULT_MIN_DEGREE = 2;

    private final int minDegree; // t: min keys per non-root node = t-1, max keys = 2t-1
    private Node<K, V> root;
    private int size;

    private static class Node<K, V> {
        boolean leaf;
        final List<K> keys = new ArrayList<>();
        final List<V> values = new ArrayList<>();
        final List<Node<K, V>> children = new ArrayList<>();

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    public BTree() {
        this(DEFAULT_MIN_DEGREE);
    }

    public BTree(int minDegree) {
        if (minDegree < 2) {
            throw new IllegalArgumentException("minDegree must be at least 2");
        }
        this.minDegree = minDegree;
        this.root = new Node<>(true);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Height of the tree in edges (every leaf sits at the same depth in a B-tree). */
    public int height() {
        int h = 0;
        Node<K, V> node = root;
        while (!node.leaf) {
            h++;
            node = node.children.get(0);
        }
        return h;
    }

    public V search(K key) {
        return search(root, key);
    }

    private V search(Node<K, V> node, K key) {
        int i = firstIndexAtLeast(node, key);
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            return node.values.get(i);
        }
        if (node.leaf) {
            return null;
        }
        return search(node.children.get(i), key);
    }

    public boolean contains(K key) {
        return search(key) != null;
    }

    /** Insert key/value (updating the value in place if the key already exists). */
    public void insert(K key, V value) {
        int maxKeys = 2 * minDegree - 1;
        if (root.keys.size() == maxKeys) {
            Node<K, V> newRoot = new Node<>(false);
            newRoot.children.add(root);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key, value);
    }

    private void insertNonFull(Node<K, V> node, K key, V value) {
        int i = firstIndexAtLeast(node, key);
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            node.values.set(i, value);
            return;
        }

        if (node.leaf) {
            node.keys.add(i, key);
            node.values.add(i, value);
            size++;
            return;
        }

        Node<K, V> child = node.children.get(i);
        if (child.keys.size() == 2 * minDegree - 1) {
            splitChild(node, i);
            if (key.compareTo(node.keys.get(i)) > 0) {
                i++;
            } else if (key.compareTo(node.keys.get(i)) == 0) {
                node.values.set(i, value);
                return;
            }
        }
        insertNonFull(node.children.get(i), key, value);
    }

    /**
     * Splits the full child at parent.children[index] around its median key,
     * pushing that median up into parent. Classic proactive split: called
     * on the way down before ever descending into a full node, so a single
     * top-down insert pass never has to split more than once per level.
     */
    private void splitChild(Node<K, V> parent, int index) {
        int t = minDegree;
        Node<K, V> full = parent.children.get(index);
        Node<K, V> sibling = new Node<>(full.leaf);

        K medianKey = full.keys.get(t - 1);
        V medianValue = full.values.get(t - 1);

        sibling.keys.addAll(full.keys.subList(t, full.keys.size()));
        sibling.values.addAll(full.values.subList(t, full.values.size()));
        if (!full.leaf) {
            sibling.children.addAll(full.children.subList(t, full.children.size()));
            trimTail(full.children, t);
        }
        trimTail(full.keys, t - 1);
        trimTail(full.values, t - 1);

        parent.children.add(index + 1, sibling);
        parent.keys.add(index, medianKey);
        parent.values.add(index, medianValue);
    }

    private <T> void trimTail(List<T> list, int keepCount) {
        for (int i = list.size() - 1; i >= keepCount; i--) {
            list.remove(i);
        }
    }

    /** First index i in node.keys such that key <= node.keys[i] (or node.keys.size() if none). */
    private int firstIndexAtLeast(Node<K, V> node, K key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        return i;
    }

    public List<Map.Entry<K, V>> inorderTraversal() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node<K, V> node, List<Map.Entry<K, V>> result) {
        for (int i = 0; i < node.keys.size(); i++) {
            if (!node.leaf) {
                inorder(node.children.get(i), result);
            }
            result.add(new AbstractMap.SimpleEntry<>(node.keys.get(i), node.values.get(i)));
        }
        if (!node.leaf) {
            inorder(node.children.get(node.keys.size()), result);
        }
    }
}
