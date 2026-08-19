package com.stackoverflow.smartservice.structures.tree;

import java.util.Map;

/**
 * Manual verification harness for BinarySearchTree, RedBlackTree, and
 * BTree, in the same spirit as Role 4's LinearDemo -- prints expected vs.
 * actual so a reviewer can eyeball correctness without a test framework.
 *
 * Uses the same UG campus locations as the assignment's example tree
 * (Balme Library, JQB, UGCS, N-Block, Pentagon Hall, Athletic Oval), with
 * their real IDs from data/csv/locations.csv (the assignment's diagram
 * used illustrative IDs, e.g. Athletic Oval as 20; the real generated
 * dataset gives it ID 11).
 */
public class TreeDemo {

    public static void main(String[] args) {
        System.out.println(" Starting Custom Tree Structures Verification");

        bstDemo();
        redBlackTreeDemo();
        bTreeDemo();

        System.out.println("\n  All Tree Structures Tests Completed ");
    }

    private static void bstDemo() {
        System.out.println("\n Testing BinarySearchTree ");
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.insert(1, "Balme Library");
        bst.insert(3, "UGCS");
        bst.insert(2, "JQB");
        bst.insert(9, "Pentagon Hall");
        bst.insert(11, "Athletic Oval");
        bst.insert(4, "N-Block");

        System.out.println("Size: " + bst.size()); // Expect 6
        System.out.println("Height: " + bst.height());

        printEntries("Inorder", bst.inorderTraversal());
        printEntries("Preorder", bst.preorderTraversal());
        printEntries("Postorder", bst.postorderTraversal());

        System.out.println("search(3): " + bst.search(3)); // Expect UGCS
        System.out.println("search(99): " + bst.search(99)); // Expect null

        bst.delete(2); // remove JQB
        System.out.println("After delete(2), size: " + bst.size()); // Expect 5
        System.out.println("search(2) after delete: " + bst.search(2)); // Expect null
        printEntries("Inorder after delete", bst.inorderTraversal());
    }

    private static void redBlackTreeDemo() {
        System.out.println("\n Testing RedBlackTree ");
        RedBlackTree<Integer, String> rbt = new RedBlackTree<>();
        rbt.insert(1, "Balme Library");
        rbt.insert(2, "JQB");
        rbt.insert(3, "UGCS");
        rbt.insert(4, "N-Block");
        rbt.insert(9, "Pentagon Hall");
        rbt.insert(11, "Athletic Oval");

        System.out.println("Size: " + rbt.size()); // Expect 6
        System.out.println("search(9): " + rbt.search(9)); // Expect Pentagon Hall
        System.out.println("Red/black invariants hold: " + rbt.isValid()); // Expect true
        printEntries("Inorder", rbt.inorderTraversal());

        // Worst case for a plain BST: keys inserted in already-sorted order
        // degrade it into a linked list. RedBlackTree is exactly the fix.
        BinarySearchTree<Integer, Integer> degenerateBst = new BinarySearchTree<>();
        RedBlackTree<Integer, Integer> balanced = new RedBlackTree<>();
        for (int id = 1; id <= 50; id++) {
            degenerateBst.insert(id, id);
            balanced.insert(id, id);
        }
        System.out.println("\nInserting IDs 1..50 in sorted order:");
        System.out.println("  Plain BST height:   " + degenerateBst.height() + " (degenerate, ~n)");
        System.out.println("  RedBlackTree height: " + balanced.height() + " (bounded by ~2*log2(n+1))");
        System.out.println("  RedBlackTree still valid: " + balanced.isValid()); // Expect true
    }

    private static void bTreeDemo() {
        System.out.println("\n Testing BTree ");
        BTree<Integer, String> btree = new BTree<>(); // default minDegree=2 (2-3-4 tree)
        int[] ids = {1, 3, 2, 9, 11, 4, 20, 15, 30, 7, 45, 22, 5, 18, 33};
        String[] names = {
                "Balme Library", "UGCS", "JQB", "Pentagon Hall", "Athletic Oval", "N-Block",
                "Main Gate", "Engineering Faculty", "UGCS Laboratory", "Volta Hall",
                "School of Education", "University Hospital", "Akuafo Hall",
                "Medical School", "Legon Hall Dining Hall"
        };
        for (int i = 0; i < ids.length; i++) {
            btree.insert(ids[i], names[i]);
        }

        System.out.println("Size: " + btree.size()); // Expect 15
        System.out.println("Height: " + btree.height()); // Expect small (node holds multiple keys)
        System.out.println("search(9): " + btree.search(9)); // Expect Pentagon Hall
        System.out.println("search(99): " + btree.search(99)); // Expect null

        var entries = btree.inorderTraversal();
        boolean sorted = true;
        for (int i = 1; i < entries.size(); i++) {
            if (entries.get(i - 1).getKey().compareTo(entries.get(i).getKey()) >= 0) {
                sorted = false;
                break;
            }
        }
        System.out.println("Inorder is strictly ascending (proves split kept order): " + sorted);
        printEntries("Inorder", entries);
    }

    private static void printEntries(String label, Iterable<? extends Map.Entry<?, ?>> entries) {
        StringBuilder sb = new StringBuilder(label + ": ");
        boolean first = true;
        for (Map.Entry<?, ?> e : entries) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(e.getValue()).append("(").append(e.getKey()).append(")");
            first = false;
        }
        System.out.println(sb);
    }
}
