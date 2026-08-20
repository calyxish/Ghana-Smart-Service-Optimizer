# Performance & Progress Tracker

Use this guide to track your progress and know when you are done with your part.

---

## How To Use This Guide

1. Find your **Role number** in the table below
2. Check off each task as you complete it
3. When all your tasks are checked, your role is **DONE**
4. Notify the group chat so the next person knows they can start

**When you complete your role, edit this file on your branch and check off your boxes before creating your PR.** The Team Lead will see your progress when reviewing.

---

## Phase 1: Data & Database Engineers

### Role 1: Local Context & Data Generator

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Create `locations.csv` with 50 Ghanaian locations | `data/csv/locations.csv` |
| [ ] | Create `roads.csv` with 100 road connections | `data/csv/roads.csv` |
| [ ] | Create `service_requests.csv` with 300 requests | `data/csv/service_requests.csv` |
| [ ] | Create `resources.csv` with 30 resources | `data/csv/resources.csv` |
| [ ] | Use real Ghanaian place names and local context | All CSV files |
| [ ] | Derive 3+ algorithm parameters from team index numbers | Document in `docs/index-number-params.md` |
| [ ] | Write evidence note on how dataset was constructed | `docs/data-evidence-note.md` |
| [ ] | All CSV files parse correctly (no encoding issues) | Tested manually |

**Done signal:** Message the group: "Phase 1 - Role 1 COMPLETE. CSV files are ready on dev."

---

### Role 2: Database Architect

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Create `schema.sql` with all 6 tables | `database/schema.sql` |
| [ ] | `locations` table matches CSV structure | Schema file |
| [ ] | `roads` table matches CSV structure | Schema file |
| [ ] | `service_requests` table matches CSV structure | Schema file |
| [ ] | `resources` table matches CSV structure | Schema file |
| [ ] | `algorithm_runs` table for storing benchmark results | Schema file |
| [ ] | `audit_events` table for undo/audit trail | Schema file |
| [ ] | Write `DatabaseConnection.java` for SQLite connection | Java file |
| [ ] | Write CRUD helper methods (insert, select, update, delete) | Java file |
| [ ] | Schema creates successfully with `sqlite3 database.db < schema.sql` | Tested |

**Done signal:** Message the group: "Phase 1 - Role 2 COMPLETE. Database schema and connection ready on dev."

---

### Role 3: Data Loader (Java I/O)

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Write CSV parser for `locations.csv` | Java file |
| [ ] | Write CSV parser for `roads.csv` | Java file |
| [ ] | Write CSV parser for `service_requests.csv` | Java file |
| [ ] | Write CSV parser for `resources.csv` | Java file |
| [ ] | Validate records (check for missing/invalid fields) | Validation logic |
| [ ] | Load validated data into SQLite database | Uses Role 2's connection |
| [ ] | Handle errors without crashing (log bad records) | Error handling |
| [ ] | Data loads successfully end-to-end (CSV -> DB) | Tested manually |

**Done signal:** Message the group: "Phase 1 COMPLETE. Data pipeline is working. Phase 2 can start."

---

## Phase 2: Core Data Structure Architects

**Reminder: NO built-in Java collections allowed. Build everything from scratch.**

### Role 4: Linear Structures Lead

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `DynamicArray.java` — insert, get, set, remove, resize | Java file |
| [x] | `SinglyLinkedList.java` — addFirst, addLast, insertAfter, remove | Java file |
| [x] | `DoublyLinkedList.java` — addFirst, addLast, remove, reverse | Java file |
| [x] | `CustomIterator.java` — iterator for linked lists | Java file |
| [x] | `CustomStack.java` — push, pop, peek, isEmpty | Java file |
| [x] | `CustomQueue.java` — enqueue, dequeue (+ circular variant) | Java file |
| [x] | `CustomDeque.java` — addFront, addRear, removeFront, removeRear | Java file |
| [x] | All structures compile without errors | `javac` passes |
| [x] | Basic manual tests pass (add, remove, edge cases) | Tested |

**Done signal:** Message the group: "Role 4 COMPLETE. Linear structures ready for Roles 8, 10, 11."

---

### Role 5: Tree Structures Lead

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `BinarySearchTree.java` — insert, search, delete, traversals | Java file |
| [x] | `RedBlackTree.java` OR `BalancedTree.java` — with rotations | Java file |
| [x] | `BTree.java` — node split and search | Java file |
| [x] | Inorder traversal produces sorted output | Tested |
| [x] | All structures compile without errors | `javac` passes |

**Done signal:** Message the group: "Role 5 COMPLETE. Tree structures ready."

---

### Role 6: Priority & Hash Lead

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `MinHeap.java` / `MaxHeap.java` — insert, extract, heapify | Java file |
| [x] | `PriorityQueue.java` — backed by heap | Java file |
| [x] | `HashTable.java` — put, get, remove, collision handling | Java file |
| [x] | `CustomHashSet.java` — set on top of hash table | Java file |
| [x] | `CustomHashMap.java` — map on top of hash table | Java file |
| [x] | Collision handling works correctly | Tested |
| [x] | All structures compile without errors | `javac` passes |

**Done signal:** Message the group: "Role 6 COMPLETE. Priority queue and hash structures ready for Role 10."

---

### Role 7: Graph Foundations Lead

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `AdjacencyListGraph.java` — graph using adjacency lists | Java file |
| [x] | `AdjacencyMatrixGraph.java` — graph using adjacency matrix | Java file |
| [x] | `DisjointSet.java` — makeSet, find, union by rank, path compression | Java file |
| [x] | Can build graph from locations and roads data | Tested |
| [x] | All structures compile without errors | `javac` passes |

**Done signal:** Message the group: "Role 7 COMPLETE. Graph structures ready for Roles 9, 11."

---

## Phase 3: Algorithm & Logic Engineers

### Role 8: Search & Sort Specialist

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | `LinearSearch.java` — search through arrays/lists | Java file |
| [ ] | `BinarySearch.java` — search in sorted arrays | Java file |
| [ ] | `SelectionSort.java` — implemented from scratch | Java file |
| [ ] | `InsertionSort.java` — implemented from scratch | Java file |
| [ ] | `MergeSort.java` — divide-and-conquer from scratch | Java file |
| [ ] | `QuickSort.java` — with partition logic from scratch | Java file |
| [ ] | Binary search states sorted precondition | Documented |
| [ ] | All algorithms produce correct output | Tested manually |

**Done signal:** Message the group: "Role 8 COMPLETE. Search and sort algorithms ready."

---

### Role 9: Graph Routing Expert

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `BFS.java` — breadth-first search | Java file |
| [x] | `DFS.java` — depth-first search | Java file |
| [x] | `Dijkstra.java` — shortest path | Java file |
| [x] | `Prim.java` — minimum spanning tree | Java file |
| [x] | `Kruskal.java` — MST using DisjointSet | Java file |
| [x] | Dijkstra returns correct shortest path and distance | Tested |
| [x] | MST returns correct total cost | Tested |

**Done signal:** Message the group: "Role 9 COMPLETE. Graph algorithms ready."

---

### Role 10: Service Scheduling Logic

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | `FIFODispatch.java` — first-in-first-out using Queue | Java file |
| [ ] | `PriorityDispatch.java` — urgency-based using Heap | Java file |
| [ ] | `CircularDispatch.java` — round-robin using circular queue | Java file |
| [ ] | `DequeDispatch.java` — urgent insertion using Deque | Java file |
| [ ] | Comparison of strategies on same request set | Documented |

**Done signal:** Message the group: "Role 10 COMPLETE. Scheduling strategies ready."

---

### Role 11: Optimization Expert

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | `GreedyAssignment.java` — greedy resource assignment | Java file |
| [ ] | `GreedyCounterexample.java` — case where greedy fails | Java file + explanation |
| [ ] | `DPKnapsack.java` — dynamic programming knapsack | Java file |
| [ ] | Memoization/tabulation table shown | Documented |
| [ ] | Solution reconstruction works | Tested |

**Done signal:** Message the group: "Role 11 COMPLETE. Optimization algorithms ready."

---

## Phase 4: QA, Testing & Performance Lab

### Role 12: Unit Tester & QA

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Tests for DynamicArray (normal, boundary, invalid) | Test file |
| [ ] | Tests for LinkedList (normal, boundary, invalid) | Test file |
| [ ] | Tests for Stack (normal, boundary, invalid) | Test file |
| [ ] | Tests for Queue/Deque (normal, boundary, invalid) | Test file |
| [ ] | Tests for HashTable (normal, collision, load factor) | Test file |
| [ ] | Tests for BST (normal, boundary, duplicate keys) | Test file |
| [ ] | Tests for Heap/PriorityQueue | Test file |
| [ ] | Tests for Graph (disconnected, unreachable path) | Test file |
| [ ] | Tests for Search algorithms | Test file |
| [ ] | Tests for Sort algorithms | Test file |
| [ ] | Tests for Dijkstra/BFS/DFS | Test file |
| [ ] | Tests for Greedy and DP | Test file |
| [ ] | Total test count is at least **40** | All test files |
| [ ] | All tests pass | `mvn test` or JUnit runner |

**Done signal:** Message the group: "Role 12 COMPLETE. All 40+ tests passing."

---

### Role 13: Theoretical Proof Analyst

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Trace table: Binary Search | `docs/trace-tables/` |
| [ ] | Trace table: Insertion Sort | `docs/trace-tables/` |
| [ ] | Trace table: Merge Sort or QuickSort | `docs/trace-tables/` |
| [ ] | Trace table: Dijkstra's Algorithm | `docs/trace-tables/` |
| [ ] | Trace table: Kruskal's or Prim's Algorithm | `docs/trace-tables/` |
| [ ] | Trace table: Dynamic Programming (Knapsack) | `docs/trace-tables/` |
| [ ] | Proof sketch: Loop invariant (search or sort) | `docs/proofs/` |
| [ ] | Proof sketch: Induction or recursion proof | `docs/proofs/` |
| [ ] | Proof sketch: Greedy or DP correctness | `docs/proofs/` |
| [ ] | Counterexample: Greedy failure | `docs/proofs/` |
| [ ] | Counterexample: Invalid precondition | `docs/proofs/` |

**Done signal:** Message the group: "Role 13 COMPLETE. Trace tables and proofs ready for the report."

---

### Role 14: Empirical Lab Tech

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Benchmark: Linear vs Binary search (5 input sizes) | CSV + graph |
| [ ] | Benchmark: 4 sorting algorithms (5 input sizes) | CSV + graph |
| [ ] | Benchmark: Hash table load factor (multiple sizes) | CSV + graph |
| [ ] | Benchmark: BST vs balanced tree | CSV + graph |
| [ ] | Benchmark: Heap insert/extract (multiple sizes) | CSV + graph |
| [ ] | Benchmark: Graph algorithms (4 input sizes) | CSV + graph |
| [ ] | Each experiment run at least 3 times (averaged) | Raw data |
| [ ] | Results logged to `algorithm_runs` database table | Database |
| [ ] | Results exported to CSV in `docs/performance/` | CSV files |
| [ ] | Line graphs created | Image files |
| [ ] | Machine specification documented | In report |
| [ ] | Mismatch between theory and observed runtime explained | Written analysis |

**Done signal:** Message the group: "Role 14 COMPLETE. All benchmarks and graphs ready for the report."

---

## Phase 5: Integration & Presentation

### Role 15: Repository & Menu Manager

| Status | Task | Deliverable |
|--------|------|------------|
| [x] | `Main.java` entry point works | Java file |
| [x] | Console menu loads data from database | Working menu |
| [ ] | Menu option: Run search algorithms | Placeholder (Role 8 pending) |
| [ ] | Menu option: Run sort algorithms | Placeholder (Role 8 pending) |
| [x] | Menu option: Find shortest path (Dijkstra) | Working |
| [ ] | Menu option: View scheduling comparisons | Placeholder (Role 10 pending) |
| [ ] | Menu option: Run performance benchmarks | Placeholder (Role 14 pending) |
| [ ] | Menu option: View audit log | Placeholder (Role 18 pending) |
| [x] | All menu options work without editing source code | Tested |
| [x] | No merge conflicts remaining in the codebase | Verified |

**Done signal:** Message the group: "Role 15 COMPLETE. Application menu is working end-to-end."

---

### Role 16: Technical Report Coordinator

| Status | Task | Deliverable |
|--------|------|------------|
| [ ] | Cover page with team details and local context | Report |
| [ ] | Problem statement and assumptions | Report |
| [ ] | Dataset description and data dictionary | Report |
| [ ] | Database schema documentation | Report |
| [ ] | System architecture diagram | Report |
| [ ] | Data structure implementations with diagrams | Report |
| [ ] | Algorithm implementations with pseudocode | Report |
| [ ] | Trace tables and proof sketches included | Report |
| [ ] | Performance graphs and interpretation | Report |
| [ ] | Database integration evidence (screenshots) | Report |
| [ ] | Responsible algorithm selection discussion | Report |
| [ ] | Individual contribution statements | Report |
| [ ] | References | Report |
| [ ] | Final PDF exported | `docs/report/` |

**Done signal:** Message the group: "Role 16 COMPLETE. Final report ready for submission."

---

## Overall Project Completion Checklist

This is the final checklist before submission. The Team Lead will verify all items:

| Status | Item | Owner |
|--------|------|-------|
| [ ] | All CSV data files present and valid | Role 1 |
| [ ] | Database schema creates and loads successfully | Roles 2 + 3 |
| [x] | All 7 custom data structures implemented (no built-in collections) | Roles 4-7 |
| [ ] | All 6 search/sort algorithms implemented | Role 8 |
| [x] | All 5 graph algorithms implemented (BFS, DFS, Dijkstra, Prim, Kruskal) | Role 9 |
| [ ] | Scheduling engine with 4 dispatch strategies | Role 10 |
| [ ] | Greedy + DP algorithms with counterexample | Role 11 |
| [ ] | 40+ unit tests passing | Role 12 |
| [ ] | 6 trace tables completed | Role 13 |
| [ ] | 3 proof sketches + 2 counterexamples | Role 13 |
| [ ] | 6 benchmark experiments with graphs | Role 14 |
| [x] | Console menu works end-to-end | Role 15 |
| [ ] | Final report PDF complete | Role 16 |
| [ ] | 5-8 minute demo video recorded | Bonus |
| [ ] | Algorithm parameters derived from index numbers | Roles 1 + 11 |
| [ ] | Everyone prepared for oral defense | All |

---

## Oral Defense Preparation

Every team member must be ready to explain **one data structure** and **one algorithm** during the oral defense. Prepare to answer:

1. How does your data structure/algorithm work?
2. What is its time complexity (best, average, worst)?
3. Walk through a trace table or example
4. What edge cases did you handle?
5. What would happen if the input was very large?

Practice explaining your work out loud. The examiner will ask questions.
