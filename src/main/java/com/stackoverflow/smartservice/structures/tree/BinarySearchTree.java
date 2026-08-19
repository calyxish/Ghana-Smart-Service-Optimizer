package com.stackoverflow.smartservice.structures.tree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classic unbalanced binary search tree, keyed by a Comparable key with an
 * associated value (e.g. key = locationId, value = the Location itself),
 * so it doubles as an ordered index: left subtree keys < node key <
 * right subtree keys.
 *
 * UG campus usage: index campus locations by ID for fast lookup, e.g.
 * search(23) to find "location 23" in O(log n) on a balanced-ish tree
 * (worst case O(n) if keys are inserted in sorted order -- that's exactly
 * the failure mode RedBlackTree exists to fix).
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /** Height of the tree: -1 if empty, 0 for a single node. */
    public int height() {
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Inserts key/value. If the key already exists, its value is replaced. */
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /** Returns the value stored under key, or null if the key isn't present. */
    public V search(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                return node.value;
            }
            node = cmp < 0 ? node.left : node.right;
        }
        return null;
    }

    public boolean contains(K key) {
        return search(key) != null;
    }

    /** Removes key if present; no-op otherwise. */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            // found it: 0 or 1 child unlinks directly; 2 children swaps in
            // the inorder successor's key/value, then removes that
            // successor node from the right subtree (where it has at most
            // one child, so that recursive call always hits a base case).
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }
            Node<K, V> successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = delete(node.right, successor.key);
        }
        return node;
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Left, node, right -- keys in ascending sorted order. */
    public List<Map.Entry<K, V>> inorderTraversal() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node<K, V> node, List<Map.Entry<K, V>> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
        inorder(node.right, result);
    }

    /** Node, left, right -- root visited first (useful for rebuilding the tree). */
    public List<Map.Entry<K, V>> preorderTraversal() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node<K, V> node, List<Map.Entry<K, V>> result) {
        if (node == null) {
            return;
        }
        result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
        preorder(node.left, result);
        preorder(node.right, result);
    }

    /** Left, right, node -- leaves visited first (useful for safe deletion order). */
    public List<Map.Entry<K, V>> postorderTraversal() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node<K, V> node, List<Map.Entry<K, V>> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
    }
}
