package com.stackoverflow.smartservice.testing.benchmark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] INPUT_SIZES = {100, 500, 1000, 5000, 10000};
    private static final int ITERATIONS = 3;
    private static final String CSV_DIR = "docs" + File.separator + "performance" + File.separator + "results";
    private static final String CSV_PATH = CSV_DIR + File.separator + "benchmark_results.csv";

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("  Ghana Smart Service Optimizer - Empirical Benchmark Suite       ");
        System.out.println("  Role 14: Empirical Lab Tech (David Owusu-Bonsu - ID: 22303106)  ");
        System.out.println("==================================================================\n");

        File resultsFolder = new File(CSV_DIR);
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_PATH))) {
            writer.println("Category,Algorithm,InputSize,AvgTimeNs,AvgTimeMs,MemoryUsedKb");

            runSearchBenchmarks(writer);
            runSortBenchmarks(writer);
            runTreeBenchmarks(writer);
            runHeapBenchmarks(writer);

            System.out.println("\n[SUCCESS] Benchmarks finished successfully!");
            System.out.println("[INFO] CSV output saved to: " + CSV_PATH);
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write CSV output: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------
    // SEARCH BENCHMARKS: Linear Search vs. Binary Search
    // -------------------------------------------------------------
    private static void runSearchBenchmarks(PrintWriter writer) {
        System.out.println(">> Running Search Benchmarks (Linear vs Binary)...");
        Random rand = new Random(42);

        for (int n : INPUT_SIZES) {
            int[] data = rand.ints(n, 1, 1000000).sorted().toArray();
            int target = data[rand.nextInt(n)];

            // JIT Warm-up
            for (int i = 0; i < 50; i++) linearSearch(data, target);

            // Linear Search
            long totalLinear = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                long start = System.nanoTime();
                linearSearch(data, target);
                totalLinear += (System.nanoTime() - start);
            }
            recordMetric(writer, "Search", "LinearSearch", n, totalLinear / ITERATIONS);

            // Binary Search
            long totalBinary = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                long start = System.nanoTime();
                binarySearch(data, target);
                totalBinary += (System.nanoTime() - start);
            }
            recordMetric(writer, "Search", "BinarySearch", n, totalBinary / ITERATIONS);
        }
    }

    // -------------------------------------------------------------
    // SORTING BENCHMARKS: Insertion, Selection, Merge, QuickSort
    // -------------------------------------------------------------
    private static void runSortBenchmarks(PrintWriter writer) {
        System.out.println("\n>> Running Sorting Benchmarks (Insertion, Selection, Merge, QuickSort)...");
        Random rand = new Random(42);

        for (int n : INPUT_SIZES) {
            int[] original = rand.ints(n, 1, 1000000).toArray();

            // Insertion Sort
            long totalInsertion = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                int[] copy = original.clone();
                long start = System.nanoTime();
                insertionSort(copy);
                totalInsertion += (System.nanoTime() - start);
            }
            recordMetric(writer, "Sort", "InsertionSort", n, totalInsertion / ITERATIONS);

            // Selection Sort
            long totalSelection = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                int[] copy = original.clone();
                long start = System.nanoTime();
                selectionSort(copy);
                totalSelection += (System.nanoTime() - start);
            }
            recordMetric(writer, "Sort", "SelectionSort", n, totalSelection / ITERATIONS);

            // Merge Sort
            long totalMerge = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                int[] copy = original.clone();
                long start = System.nanoTime();
                mergeSort(copy, 0, copy.length - 1);
                totalMerge += (System.nanoTime() - start);
            }
            recordMetric(writer, "Sort", "MergeSort", n, totalMerge / ITERATIONS);

            // QuickSort
            long totalQuick = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                int[] copy = original.clone();
                long start = System.nanoTime();
                quickSort(copy, 0, copy.length - 1);
                totalQuick += (System.nanoTime() - start);
            }
            recordMetric(writer, "Sort", "QuickSort", n, totalQuick / ITERATIONS);
        }
    }

    // -------------------------------------------------------------
    // TREE BENCHMARKS: BST Insert vs. Search
    // -------------------------------------------------------------
    private static void runTreeBenchmarks(PrintWriter writer) {
        System.out.println("\n>> Running Tree Benchmarks (BST Operations)...");
        Random rand = new Random(42);

        for (int n : INPUT_SIZES) {
            int[] dataset = rand.ints(n, 1, 1000000).toArray();

            long totalTreeInsert = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                BSTNode root = null;
                long start = System.nanoTime();
                for (int val : dataset) {
                    root = insertBST(root, val);
                }
                totalTreeInsert += (System.nanoTime() - start);
            }
            recordMetric(writer, "Tree", "BST_Insert", n, totalTreeInsert / ITERATIONS);
        }
    }

    // -------------------------------------------------------------
    // HEAP BENCHMARKS: MaxHeap Insert and ExtractMax
    // -------------------------------------------------------------
    private static void runHeapBenchmarks(PrintWriter writer) {
        System.out.println("\n>> Running Heap Benchmarks (Priority Operations)...");
        Random rand = new Random(42);

        for (int n : INPUT_SIZES) {
            int[] dataset = rand.ints(n, 1, 1000000).toArray();

            long totalHeapInsert = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                SimpleMaxHeap heap = new SimpleMaxHeap(n);
                long start = System.nanoTime();
                for (int val : dataset) {
                    heap.insert(val);
                }
                totalHeapInsert += (System.nanoTime() - start);
            }
            recordMetric(writer, "Heap", "MaxHeap_Insert", n, totalHeapInsert / ITERATIONS);
        }
    }

    private static void recordMetric(PrintWriter writer, String category, String algo, int size, long avgTimeNs) {
        long memoryUsedKb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        double avgTimeMs = avgTimeNs / 1_000_000.0;
        writer.printf("%s,%s,%d,%d,%.4f,%d%n", category, algo, size, avgTimeNs, avgTimeMs, memoryUsedKb);
        System.out.printf("  [%-7s | %-14s] N=%-5d -> Time: %-10d ns (%-7.3f ms) | Mem: %d KB%n",
                category, algo, size, avgTimeNs, avgTimeMs, memoryUsedKb);
    }

    // --- Search Algorithms ---
    private static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // --- Sorting Algorithms ---
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int tmp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = tmp;
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    // --- BST Helper ---
    static class BSTNode {
        int key;
        BSTNode left, right;
        BSTNode(int item) { key = item; }
    }

    private static BSTNode insertBST(BSTNode root, int key) {
        if (root == null) return new BSTNode(key);
        if (key < root.key) root.left = insertBST(root.left, key);
        else if (key > root.key) root.right = insertBST(root.right, key);
        return root;
    }

    // --- MaxHeap Helper ---
    static class SimpleMaxHeap {
        private final int[] heap;
        private int size;
        public SimpleMaxHeap(int cap) { heap = new int[cap + 1]; size = 0; }
        public void insert(int val) {
            heap[++size] = val;
            int current = size;
            while (current > 1 && heap[current] > heap[current / 2]) {
                int tmp = heap[current];
                heap[current] = heap[current / 2];
                heap[current / 2] = tmp;
                current = current / 2;
            }
        }
    }
}