# Implementation Guide

This guide tells you exactly **who does what**, **in what order**, and **where to put your code**. Follow it carefully to avoid merge conflicts and wasted time.

---

## How This Project Works

The project is split into **5 groups** and **16 roles**. Each role has a specific job, a specific folder to work in, and specific roles it depends on.

**You cannot all work at the same time on everything.** Some roles must finish first before others can begin. This guide tells you the exact order.

---

## The Execution Phases

The project runs in **5 phases**. Each phase must be substantially complete before the next one begins.

```
PHASE 1  ──>  PHASE 2  ──>  PHASE 3  ──>  PHASE 4  ──>  PHASE 5
 (Data)     (Structures)  (Algorithms)   (Testing)   (Integration)
```

### Phase 1: Data & Database (Roles 1, 2, 3)

These roles go **first** because everyone else needs the data and database to exist.

**Order within Phase 1:**

```
Role 1 (Data Generator)  ──>  Role 2 (Database Architect)  ──>  Role 3 (Data Loader)
        FIRST                       SECOND                          THIRD
```

Role 1 creates the CSV data files. Role 2 cannot design the schema until they see the data. Role 3 cannot write the loader until both the data and the database exist.

---

### Phase 2: Core Data Structures (Roles 4, 5, 6, 7)

These four roles can work **in parallel** (at the same time) because they each work in their own package and do not depend on each other.

```
Role 4 (Linear Structures)  ──┐
Role 5 (Tree Structures)     ──┤  ALL WORK AT THE SAME TIME
Role 6 (Priority & Hash)     ──┤
Role 7 (Graph Foundations)   ──┘
```

**However**, all four must wait until **Phase 1 is complete** so they can test their structures with real data.

---

### Phase 3: Algorithms & Logic (Roles 8, 9, 10, 11)

These roles **use the data structures from Phase 2**. They cannot begin until those structures are built and working.

```
Role 8  (Search & Sort)         depends on ──>  Role 4 (arrays, lists)
Role 9  (Graph Routing)         depends on ──>  Role 7 (graph structures)
Role 10 (Service Scheduling)    depends on ──>  Roles 4 + 6 (queues, heaps)
Role 11 (Optimization)          depends on ──>  Roles 4 + 7 (arrays, graphs)
```

Roles 8, 9, 10, and 11 can work **in parallel** with each other, as long as their required Phase 2 structures are done.

---

### Phase 4: QA, Testing & Performance (Roles 12, 13, 14)

These roles **test and measure** everything built in Phases 2 and 3. They must wait until there is code to test.

```
Role 12 (Unit Tester)           depends on ──>  Phases 2 + 3 (structures & algorithms)
Role 13 (Proof Analyst)         depends on ──>  Phase 3 (algorithms to trace)
Role 14 (Empirical Lab Tech)    depends on ──>  Phases 2 + 3 + Role 2 (database for storing results)
```

These three roles can work **in parallel** with each other.

---

### Phase 5: Integration & Documentation (Roles 15, 16)

These roles tie everything together. They depend on **all previous phases**.

```
Role 15 (Repository & Menu Manager)    depends on ──>  ALL phases
Role 16 (Technical Report Coordinator) depends on ──>  ALL phases
```

---

## Role Details

### Group 1: Data & Database Engineers

---

#### Role 1: Local Context & Data Generator

**Package:** `src/main/java/com/stackoverflow/smartservice/data/generator/`
**Output Folder:** `data/csv/`

**What to build:**
- Create CSV files with realistic Ghanaian data:
  - `locations.csv` — 50 locations (locationId, name, area, type, latitude, longitude)
  - `roads.csv` — 100 roads (fromLocationId, toLocationId, distance, travelTime, roadConditionWeight)
  - `service_requests.csv` — 300 requests (requestId, source, destination, category, urgency, timeSubmitted, deadline, status)
  - `resources.csv` — 30 resources (resourceId, type, homeLocation, capacity, availabilityStatus)
- Use real Ghanaian place names, realistic distances, and local context
- Derive at least 3 algorithm parameters from team member index numbers (e.g., hash table size, priority weights, random seeds)
- Write a short evidence note explaining how the dataset was constructed

**Your branch name:** `feature/your-name/data-generation`

**Depends on:** Nothing. You go first.

**Who is waiting for you:** Role 2 and Role 3 cannot start without your CSV files.

---

#### Role 2: Database Architect

**Package:** `src/main/java/com/stackoverflow/smartservice/data/database/`
**Output Folder:** `database/`

**What to build:**
- Design and create `database/schema.sql` with tables for:
  - `locations` — all nodes in the service network
  - `roads` — weighted edges between locations
  - `service_requests` — jobs to be queued, prioritized, searched
  - `resources` — vehicles, staff, assets
  - `algorithm_runs` — empirical runtime measurements
  - `audit_events` — stack-based undo/audit operations
- Write Java classes for database connection and CRUD operations
- Use SQLite (no server setup needed)

**Your branch name:** `feature/your-name/database-schema`

**Depends on:** Role 1 (you need to see the CSV structure to design matching tables).

**Who is waiting for you:** Role 3 needs your schema and connection classes.

---

#### Role 3: Data Loader (Java I/O)

**Package:** `src/main/java/com/stackoverflow/smartservice/data/loader/`

**What to build:**
- Java code to parse CSV files (using `java.io` / `java.nio`)
- Validate records (check for missing fields, invalid values)
- Load validated data into the database (using Role 2's connection classes)
- Load data from the database into the custom Java data structures (when Phase 2 is done)
- Handle errors gracefully (log bad records, don't crash)

**Your branch name:** `feature/your-name/data-loader`

**Depends on:** Role 1 (CSV files) and Role 2 (database schema and connection).

**Who is waiting for you:** Everyone in Phase 2 can start building structures, but they will need your loader to test with real data.

---

### Group 2: Core Data Structure Architects

**CRITICAL RULE: No built-in Java collections allowed.** You cannot use `ArrayList`, `LinkedList`, `HashMap`, `TreeMap`, `PriorityQueue`, `Stack`, `ArrayDeque`, or any class from `java.util` for these implementations. You must build them from scratch.

You MAY use: arrays (`[]`), `java.io`, `java.sql`, `String`, and primitive types.

---

#### Role 4: Linear Structures Lead

**Package:** `src/main/java/com/stackoverflow/smartservice/structures/linear/`

**What to build:**
- `DynamicArray.java` — insert, get, set, remove, auto-resize
- `SinglyLinkedList.java` — addFirst, addLast, insertAfter, remove
- `DoublyLinkedList.java` — addFirst, addLast, remove, reverse traversal
- `CustomIterator.java` — iterator for your linked lists
- `CustomStack.java` — push, pop, peek, isEmpty
- `CustomQueue.java` — enqueue, dequeue (regular and circular)
- `CustomDeque.java` — addFront, addRear, removeFront, removeRear

**Your branch name:** `feature/your-name/linear-structures`

**Depends on:** Phase 1 should be done so you can test with real data. But you can start coding the structures while waiting.

**Who is waiting for you:** Role 8 (Search & Sort), Role 10 (Scheduling), Role 11 (Optimization).

---

#### Role 5: Tree Structures Lead

**Package:** `src/main/java/com/stackoverflow/smartservice/structures/tree/`

**What to build:**
- `BinarySearchTree.java` — insert, search, delete, inorder/preorder/postorder traversal
- `RedBlackTree.java` OR `BalancedTree.java` — insertion with rotations and recoloring (or a simplified balanced tree)
- `BTree.java` — node split, search (can be simplified to demonstrate the concept)

**Your branch name:** `feature/your-name/tree-structures`

**Depends on:** Phase 1 for real data to test with.

**Who is waiting for you:** Role 8 (Search & Sort) may use BST for indexed searching.

---

#### Role 6: Priority & Hash Lead

**Package:** `src/main/java/com/stackoverflow/smartservice/structures/hashing/`

**What to build:**
- `MinHeap.java` / `MaxHeap.java` — insert, extractMin/Max, heapify
- `PriorityQueue.java` — backed by your heap, for dispatch ordering
- `HashTable.java` — put, get, remove, collision handling (chaining or open addressing)
- `CustomHashSet.java` — set built on top of your hash table
- `CustomHashMap.java` — map built on top of your hash table

**Your branch name:** `feature/your-name/priority-hash`

**Depends on:** Phase 1 for real data.

**Who is waiting for you:** Role 10 (Scheduling), Role 12 (Testing needs collision statistics).

---

#### Role 7: Graph Foundations Lead

**Package:** `src/main/java/com/stackoverflow/smartservice/structures/graph/`

**What to build:**
- `AdjacencyListGraph.java` — graph using adjacency lists
- `AdjacencyMatrixGraph.java` — graph using adjacency matrix
- `DisjointSet.java` — makeSet, find (with path compression), union (by rank/size)

**Your branch name:** `feature/your-name/graph-foundations`

**Depends on:** Phase 1 (need locations and roads data to build the graph).

**Who is waiting for you:** Role 9 (Graph Routing), Role 11 (Optimization).

---

### Group 3: Algorithm & Logic Engineers

---

#### Role 8: Search & Sort Specialist

**Package:** `src/main/java/com/stackoverflow/smartservice/algorithms/searchsort/`

**What to build:**
- `LinearSearch.java` — search through arrays/lists
- `BinarySearch.java` — search in sorted arrays (state the sorted precondition clearly)
- `SelectionSort.java` — implement from scratch
- `InsertionSort.java` — implement from scratch
- `MergeSort.java` — implement from scratch with clear divide-and-conquer
- `QuickSort.java` — implement from scratch with partition logic

**Your branch name:** `feature/your-name/search-sort`

**Depends on:** Role 4 (needs DynamicArray and LinkedList to sort/search).

**Who is waiting for you:** Role 12 (testing), Role 13 (trace tables), Role 14 (performance benchmarks).

---

#### Role 9: Graph Routing Expert

**Package:** `src/main/java/com/stackoverflow/smartservice/algorithms/graph/`

**What to build:**
- `BFS.java` — breadth-first search for reachable locations
- `DFS.java` — depth-first search for traversal order
- `Dijkstra.java` — shortest path between two locations
- `Prim.java` — minimum spanning tree
- `Kruskal.java` — minimum spanning tree (uses DisjointSet from Role 7)

**Your branch name:** `feature/your-name/graph-routing`

**Depends on:** Role 7 (needs Graph and DisjointSet structures).

**Who is waiting for you:** Role 12 (testing), Role 13 (trace tables), Role 14 (performance benchmarks).

---

#### Role 10: Service Scheduling Logic

**Package:** `src/main/java/com/stackoverflow/smartservice/algorithms/scheduling/`

**What to build:**
- `FIFODispatch.java` — first-in-first-out scheduling using Queue
- `PriorityDispatch.java` — urgency-based scheduling using PriorityQueue/Heap
- `CircularDispatch.java` — round-robin scheduling using circular queue
- `DequeDispatch.java` — front/rear insertion for urgent requests using Deque
- Compare different dispatch strategies on the same request set

**Your branch name:** `feature/your-name/scheduling`

**Depends on:** Role 4 (Queue, Deque) and Role 6 (PriorityQueue, Heap).

**Who is waiting for you:** Role 12 (testing), Role 14 (performance benchmarks).

---

#### Role 11: Optimization Expert

**Package:** `src/main/java/com/stackoverflow/smartservice/algorithms/optimization/`

**What to build:**
- `GreedyAssignment.java` — greedy algorithm for resource assignment or route choice
- `GreedyCounterexample.java` — demonstrate a case where greedy fails
- `DPKnapsack.java` — dynamic programming for request selection under budget/capacity/time constraint
- Show memoization or tabulation table and reconstruction of solution

**Your branch name:** `feature/your-name/optimization`

**Depends on:** Role 4 (arrays) and Role 7 (graph for route optimization).

**Who is waiting for you:** Role 12 (testing), Role 13 (proof sketches), Role 14 (benchmarks).

---

### Group 4: QA, Testing & Performance Lab

---

#### Role 12: Unit Tester & QA

**Package:** `src/test/java/com/stackoverflow/smartservice/`

**What to build:**
- At least **40 unit tests** using JUnit 5
- Test every data structure from Group 2:
  - Normal case (typical usage)
  - Boundary case (empty structure, single element, full capacity)
  - Invalid input case (null, negative index, duplicate keys)
- Test key algorithms from Group 3
- Specific tests for:
  - Hash collision behavior
  - Disconnected graph / unreachable path
  - Queue full/empty states
  - BST with duplicate keys
  - Sort stability

**Your branch name:** `feature/your-name/unit-tests`

**Depends on:** Phases 2 and 3 (you need the structures and algorithms to exist before you can test them). You can start writing test stubs and planning test cases early.

**Who is waiting for you:** Role 16 (needs test results for the report).

---

#### Role 13: Theoretical Proof Analyst

**Package:** `docs/trace-tables/` and `docs/proofs/`

**What to build:**
- At least **6 trace tables** (step-by-step execution tables):
  1. Binary Search
  2. Insertion Sort
  3. Merge Sort or QuickSort
  4. Dijkstra's Algorithm
  5. Kruskal's or Prim's Algorithm
  6. Dynamic Programming (Knapsack)
- At least **3 proof sketches**:
  1. Loop invariant for a search or sort algorithm
  2. Induction or recursion proof
  3. Greedy or DP correctness argument
- At least **2 counterexamples**:
  1. Greedy failure case
  2. Invalid precondition (e.g., binary search on unsorted input)

**Your branch name:** `feature/your-name/proofs-traces`

**Depends on:** Phase 3 (you need the algorithms to be implemented to trace them).

**Who is waiting for you:** Role 16 (needs trace tables and proofs for the report).

---

#### Role 14: Empirical Lab Tech

**Package:** `src/main/java/com/stackoverflow/smartservice/testing/benchmark/`
**Output Folder:** `docs/performance/`

**What to build:**
- Benchmark runner that measures time (in nanoseconds) and memory for:
  - Search comparison: linear vs binary at 100, 500, 1000, 5000, 10000 records
  - Sort comparison: selection, insertion, merge, quicksort at same sizes
  - Hash table: load factor vs collision count at 100 to 20000 keys
  - BST vs balanced tree: insert/search time at multiple sizes
  - Heap: insert/extract at 100 to 20000 requests
  - Graph algorithms: BFS/DFS/Dijkstra/MST at 50, 100, 200, 500 locations
- Run each experiment **at least 3 times** and report the average
- Log results to the database (`algorithm_runs` table)
- Export results to CSV files in `docs/performance/`
- Create line graphs (using Excel, Python matplotlib, or any tool)
- State the machine specification used for testing

**Your branch name:** `feature/your-name/benchmarks`

**Depends on:** Phases 2 and 3 (structures and algorithms) and Role 2 (database for storing results).

**Who is waiting for you:** Role 16 (needs graphs and data for the report), Data Visualizer (bonus role).

---

### Group 5: Integration, Documentation & Presentation

---

#### Role 15: Repository & Menu Manager

**Package:** `src/main/java/com/stackoverflow/smartservice/app/`

**What to build:**
- `Main.java` — entry point for the application
- `ConsoleMenu.java` — interactive console menu that lets an examiner:
  - Load data from the database
  - Run any search/sort algorithm
  - Find shortest path between locations
  - View scheduling comparisons
  - Run performance benchmarks
  - View audit log
- All menu options must work without the examiner editing source code
- Help resolve any remaining merge conflicts across the codebase

**Your branch name:** `feature/your-name/console-menu`

**Depends on:** ALL previous phases must be substantially complete.

**Who is waiting for you:** Role 16 (needs screenshots of the working menu for the report).

---

#### Role 16: Technical Report Coordinator

**Output Folder:** `docs/report/`

**What to build:**
- Final PDF/DOCX report with all required sections:
  1. Cover page (title, team members, local context)
  2. Problem statement and assumptions
  3. Dataset description and data dictionary
  4. Database schema
  5. System architecture and module design
  6. Data structure implementations with diagrams
  7. Algorithm implementations with pseudocode
  8. Correctness evidence (trace tables, proofs, tests)
  9. Performance analysis (graphs, interpretation)
  10. Database integration evidence
  11. Responsible algorithm selection discussion
  12. Individual contribution statements
  13. References
- Collect screenshots, diagrams, and outputs from all team members

**Your branch name:** `feature/your-name/report`

**Depends on:** ALL previous phases.

---

## Folder Summary

Use this table to find where your code goes:

| Role | Package / Folder |
|------|-----------------|
| Role 1 | `data/csv/` and `src/.../data/generator/` |
| Role 2 | `database/` and `src/.../data/database/` |
| Role 3 | `src/.../data/loader/` |
| Role 4 | `src/.../structures/linear/` |
| Role 5 | `src/.../structures/tree/` |
| Role 6 | `src/.../structures/hashing/` |
| Role 7 | `src/.../structures/graph/` |
| Role 8 | `src/.../algorithms/searchsort/` |
| Role 9 | `src/.../algorithms/graph/` |
| Role 10 | `src/.../algorithms/scheduling/` |
| Role 11 | `src/.../algorithms/optimization/` |
| Role 12 | `src/test/java/.../` |
| Role 13 | `docs/trace-tables/` and `docs/proofs/` |
| Role 14 | `src/.../testing/benchmark/` and `docs/performance/` |
| Role 15 | `src/.../app/` |
| Role 16 | `docs/report/` |

**`src/...` means `src/main/java/com/stackoverflow/smartservice/`**

---

## Dependency Map (Visual)

```
PHASE 1 (Do First - Sequential)
┌──────────────────────────────────────────────────┐
│  Role 1 ──> Role 2 ──> Role 3                   │
│  (CSV Data)  (Schema)   (Loader)                 │
└──────────────────────────────────────────────────┘
                    │
                    ▼
PHASE 2 (All Start Together - Parallel)
┌──────────────────────────────────────────────────┐
│  Role 4          Role 5        Role 6    Role 7  │
│  (Linear)        (Trees)       (Hash)    (Graph) │
└──────────────────────────────────────────────────┘
                    │
                    ▼
PHASE 3 (Start When Your Dependencies Are Done)
┌──────────────────────────────────────────────────┐
│  Role 8          Role 9       Role 10    Role 11 │
│  (Sort)          (Routing)    (Schedule) (Optim) │
│  needs R4        needs R7     needs R4+6 needs R4+7
└──────────────────────────────────────────────────┘
                    │
                    ▼
PHASE 4 (Start When Phases 2+3 Are Done)
┌──────────────────────────────────────────────────┐
│  Role 12          Role 13          Role 14       │
│  (Unit Tests)     (Proofs)         (Benchmarks)  │
└──────────────────────────────────────────────────┘
                    │
                    ▼
PHASE 5 (Final Assembly)
┌──────────────────────────────────────────────────┐
│  Role 15                    Role 16              │
│  (Console Menu)             (Report)             │
└──────────────────────────────────────────────────┘
```

---

## What To Do If You Finish Early

If your phase is done and the next phase has not started yet:

1. **Write tests for your own code** (helps Role 12)
2. **Document your implementation** with comments explaining key logic (helps Role 16)
3. **Pick up a bonus role:**
   - Data Visualizer — plot performance graphs
   - Audit Log Developer — implement undo/audit trail using Stack
   - Video Demo Director — plan and record the 5-8 minute demo video
   - AI-Resistance Checker — verify algorithm parameters use index numbers

---

## Important Reminders

1. **Stay in your folder.** Do not edit files in another role's package.
2. **Do not use built-in Java collections** for core implementations (Group 2 especially).
3. **Everyone must use Java 17.** Check with `java -version`.
4. **Communicate.** If you are blocked because a previous role has not finished, message the group chat.
5. **Commit often.** Small, working commits are better than one massive commit at the end.
6. **Test your code** before creating a PR. At minimum, make sure it compiles.
