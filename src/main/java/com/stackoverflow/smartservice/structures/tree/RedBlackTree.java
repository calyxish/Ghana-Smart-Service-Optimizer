package com.stackoverflow.smartservice.structures.tree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Self-balancing binary search tree using the standard red/black coloring
 * rules (CLRS-style insert with rotations and recoloring). A missing child
 * is treated as an implicit black leaf, so there is no explicit sentinel
 * node to wire up.
 *
 * UG campus usage: a balanced index of service requests, keyed by
 * requestId. Unlike a plain BinarySearchTree, this guarantees O(log n)
 * search/insert even in the worst case (e.g. request IDs arriving in
 * already-sorted order), because the red/black invariants keep the tree
 * height at most 2*log2(n+1).
 *
 * Delete is intentionally not implemented -- the spec for this class only
 * calls for insert (with rotations/recoloring), search, and inorder.
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        boolean color;

        Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
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

    private boolean colorOf(Node<K, V> n) {
        return n == null ? BLACK : n.color;
    }

    private void setColor(Node<K, V> n, boolean color) {
        if (n != null) {
            n.color = color;
        }
    }

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

    /** Inserts key/value (updating the value in place if the key already exists). */
    public void insert(K key, V value) {
        Node<K, V> parent = null;
        Node<K, V> current = root;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                current.value = value;
                return;
            }
            current = cmp < 0 ? current.left : current.right;
        }

        Node<K, V> node = new Node<>(key, value, RED);
        node.parent = parent;
        if (parent == null) {
            root = node;
        } else if (key.compareTo(parent.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        size++;
        fixAfterInsert(node);
    }

    private void rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node<K, V> x) {
        Node<K, V> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Standard CLRS RB-insert-fixup: z always starts RED, so the only
    // invariant that can be broken is "no red node has a red parent";
    // walk up recoloring/rotating until that's restored, then force the
    // root black.
    private void fixAfterInsert(Node<K, V> z) {
        while (colorOf(z.parent) == RED) {
            Node<K, V> parent = z.parent;
            Node<K, V> grandparent = parent.parent;
            if (parent == grandparent.left) {
                Node<K, V> uncle = grandparent.right;
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    z = grandparent;
                } else {
                    if (z == parent.right) {
                        z = parent;
                        rotateLeft(z);
                        parent = z.parent;
                    }
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    rotateRight(grandparent);
                }
            } else {
                Node<K, V> uncle = grandparent.left;
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    z = grandparent;
                } else {
                    if (z == parent.left) {
                        z = parent;
                        rotateRight(z);
                        parent = z.parent;
                    }
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = BLACK;
    }

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

    /**
     * Checks every red/black invariant (BST ordering, root is black, no red
     * node has a red child, every root-to-null-leaf path has the same black
     * height). Not required by the spec, but cheap and useful for proving
     * the balancing logic is actually correct rather than just "looks
     * right" -- exercised by the demo/tests below.
     */
    public boolean isValid() {
        if (root != null && colorOf(root) != BLACK) {
            return false;
        }
        return checkNode(root, null, null) >= 0;
    }

    /** Returns the black-height of the subtree, or -1 if any invariant is violated. */
    private int checkNode(Node<K, V> node, K lowerBound, K upperBound) {
        if (node == null) {
            return 1; // implicit black leaf contributes one black node to every path
        }
        if (lowerBound != null && node.key.compareTo(lowerBound) <= 0) {
            return -1;
        }
        if (upperBound != null && node.key.compareTo(upperBound) >= 0) {
            return -1;
        }
        if (colorOf(node) == RED && (colorOf(node.left) == RED || colorOf(node.right) == RED)) {
            return -1;
        }

        int leftBlackHeight = checkNode(node.left, lowerBound, node.key);
        if (leftBlackHeight < 0) {
            return -1;
        }
        int rightBlackHeight = checkNode(node.right, node.key, upperBound);
        if (rightBlackHeight < 0 || leftBlackHeight != rightBlackHeight) {
            return -1;
        }
        return leftBlackHeight + (colorOf(node) == BLACK ? 1 : 0);
    }
}
