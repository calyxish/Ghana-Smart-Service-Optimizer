# Empirical Performance Analysis & Benchmark Report

**Investigator:** Opoku Owusu-Bonsu David  
**Role:** Role 14 – Empirical Lab Tech  
**Student ID:** 22303106  
**Course:** DCIT 204/308 – Data Structures and Algorithms  
**Project:** Ghana Smart Service Operations Optimizer  

---

## 1. Benchmarking Environment & Hardware Specifications

* **Operating System:** Windows 11 (64-bit)
* **Runtime Environment:** OpenJDK 17 (Java 17 LTS)
* **JVM Options:** `-Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8`
* **Timing Mechanism:** `System.nanoTime()` with 3 statistical iterations per input size and 50 warm-up iterations to mitigate JIT compilation skew.

---

## 2. Experimental Data Summary

The raw empirical data collected across input sizes $N \in \{100, 500, 1000, 5000, 10000\}$:

| Category | Algorithm | $N=100$ ($\text{ms}$) | $N=500$ ($\text{ms}$) | $N=1,000$ ($\text{ms}$) | $N=5,000$ ($\text{ms}$) | $N=10,000$ ($\text{ms}$) | Theoretical Big-O |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Search** | Linear Search | 0.0015 | 0.0079 | 0.0010 | 0.0311 | 0.0324 | $O(n)$ |
| **Search** | Binary Search | 0.0095 | 0.0024 | 0.0013 | 0.0056 | 0.0038 | $O(\log n)$ |
| **Sort** | Insertion Sort | 0.1193 | 3.1568 | 0.8356 | 17.0156 | 19.4751 | $O(n^2)$ |
| **Sort** | Selection Sort | 0.7015 | 3.7042 | 3.4255 | 16.3419 | 34.2166 | $O(n^2)$ |
| **Sort** | Merge Sort | 0.2114 | 0.4361 | 0.3952 | 1.6202 | 2.7031 | $O(n \log n)$ |
| **Sort** | QuickSort | 0.1628 | 0.2869 | 0.1327 | 0.6583 | 0.8670 | $O(n \log n)$ |
| **Tree** | BST Insert | 8.2415 | 0.1575 | 1.7077 | 1.5238 | 3.6479 | $O(h)$ |
| **Heap** | MaxHeap Insert | 0.0281 | 0.0391 | 0.1448 | 0.3965 | 0.9506 | $O(\log n)$ |

---

## 3. Empirical Observations & Analysis

1. **Search Scaling ($O(n)$ vs $O(\log n)$):**
   * As $N$ scaled from $100$ to $10,000$, Linear Search exhibited a steep runtime increase (reaching $\approx 0.032\,\text{ms}$).
   * Binary Search maintained consistent execution times ($\approx 0.0038\,\text{ms}$) regardless of array size, validating logarithmic time complexity.

2. **Sorting Scaling ($O(n^2)$ vs $O(n \log n)$):**
   * At $N = 10,000$, Quadratic algorithms like Selection Sort (**34.22 ms**) and Insertion Sort (**19.48 ms**) degraded severely.
   * Divide-and-conquer algorithms proved vastly superior: QuickSort completed in **0.87 ms** (over **39x faster** than Selection Sort), and MergeSort completed in **2.70 ms**.

3. **Heap Priority Operations:**
   * MaxHeap insertion scaled smoothly from $0.028\,\text{ms}$ at $N=100$ to $0.951\,\text{ms}$ at $N=10,000$, validating $O(\log n)$ logarithmic per-item insertion.