# Implementation Guide

This guide tells you exactly **who does what**, **in what order**, and **where to put your code**.

Our project models the **University of Ghana (Legon) campus** — lecture halls, hostels, labs, libraries, the Balme Library, JQB, UGCS, N-Block, shuttle stops, maintenance requests, IT support tickets, and campus resource allocation.

Follow this guide carefully to avoid merge conflicts and wasted time.

---

## Our Campus Context

We are building a Smart Service Operations Optimizer for the **University of Ghana, Legon**. Here is what the data represents:

| Data Entity | What It Means on UG Campus | Examples |
|------------|---------------------------|----------|
| **Locations** | Buildings, halls, facilities on campus | Balme Library, JQB, UGCS, N-Block, Akuafo Hall, Legon Hall, Volta Hall, ISH, Pentagon, Night Market, Athletic Oval, Science Faculty, Engineering Faculty, Diaspora Dining Hall |
| **Roads/Edges** | Walkways, roads, shuttle routes connecting campus locations | JQB to Balme Library (0.3 km, 4 min walk), Pentagon to Night Market (0.5 km, 7 min walk), Shuttle: Pent to Legon Hall |
| **Service Requests** | Maintenance, IT, academic, or facility requests submitted by students/staff | "Fix broken projector in JQB Room 2", "Plumbing issue at Akuafo Hall Block C", "Wi-Fi down at UGCS Lab 3", "Room booking for Legon Hall seminar room" |
| **Resources** | Campus staff, vehicles, equipment that can be assigned to requests | Maintenance crew A, IT Technician 3, Shuttle Bus UG-05, Projector Unit, Generator Set |

---

## How to Read This Guide

1. **Find your name** in the role list below
2. Read **your role section** carefully — it tells you exactly what to build, what files to create, and what folder to work in
3. Check **who you depend on** — if that person is not done yet, you cannot start your main work (but you can prepare)
4. Check **who depends on you** — those people are waiting for your code, so do not delay
5. Follow the **"How to Start"** steps at the bottom of your role section

---

## The Execution Phases

The project runs in **5 phases**. Each phase must be substantially complete before the next one begins.

```
PHASE 1  ──────>  PHASE 2  ──────>  PHASE 3  ──────>  PHASE 4  ──────>  PHASE 5
 (Data &          (Custom Data      (Algorithms       (Testing &        (Menu &
  Database)        Structures)        & Logic)          Performance)      Report)
```

---

## Phase 1: Data & Database (Roles 1, 2, 3)

**These roles go FIRST.** Everyone else is waiting on you.

**Order within Phase 1 (sequential — one after the other):**

```
Role 1 (Matin)  ──>  Role 2 (Robert)  ──>  Role 3 (Prosper)
   FIRST                 SECOND                THIRD
```

Matin creates the UG campus CSV data. Robert cannot design the database schema until Matin's CSV files exist. Prosper cannot write the data loader until both the CSV files and the database schema are ready.

---

### Role 1: Local Context & Data Generator

**Assigned to:** Matin Seity (22372116)

**Your package:** `src/main/java/com/stackoverflow/smartservice/data/generator/`
**Your output folder:** `data/csv/`
**Your branch:** `feature/matin/data-generation`

**What you must build:**

You create the entire UG campus dataset as CSV files. This is the foundation of the whole project — everything depends on your data.

**File 1: `data/csv/locations.csv`** — 50 UG campus locations

```csv
locationId,name,area,type,latitude,longitude
1,Balme Library,Central Campus,Library,5.6505,-0.1862
2,JQB (J.Q.B. Building),Central Campus,Lecture Hall,5.6510,-0.1858
3,UGCS (Computer Science Dept),Science Area,Department,5.6498,-0.1870
4,N-Block,Central Campus,Lecture Hall,5.6512,-0.1855
5,Akuafo Hall,Residential,Hostel,5.6530,-0.1850
6,Legon Hall,Residential,Hostel,5.6520,-0.1865
7,Volta Hall,Residential,Hostel,5.6525,-0.1840
8,Commonwealth Hall,Residential,Hostel,5.6540,-0.1835
9,Pentagon Hall,Residential,Hostel,5.6515,-0.1880
10,ISH (International Students Hostel),Residential,Hostel,5.6508,-0.1890
...
```

Include locations like: Athletic Oval, Night Market, Diaspora Dining Hall, Science Faculty, Engineering Faculty, Business School, Law Faculty, Medical School, SRC Building, Main Gate, Okponglo Gate, University Hospital, Banking Square, Bookshop, Great Hall, Bush Canteen, etc.

**File 2: `data/csv/roads.csv`** — 100 roads/paths connecting locations

```csv
fromLocationId,toLocationId,distance,travelTime,roadConditionWeight
1,2,0.3,4,1.0
2,3,0.5,7,1.2
3,4,0.2,3,1.0
5,6,0.8,10,1.5
...
```

- `distance` = kilometers between locations
- `travelTime` = minutes to walk/drive
- `roadConditionWeight` = 1.0 (good), 1.5 (fair), 2.0 (poor) — affects routing algorithms

**File 3: `data/csv/service_requests.csv`** — 300 campus service requests

```csv
requestId,source,destination,category,urgency,timeSubmitted,deadline,status
1,5,3,IT_SUPPORT,HIGH,2024-09-01T08:30:00,2024-09-01T12:00:00,PENDING
2,6,1,MAINTENANCE,LOW,2024-09-01T09:00:00,2024-09-03T17:00:00,PENDING
3,8,2,ROOM_BOOKING,MEDIUM,2024-09-01T10:15:00,2024-09-02T08:00:00,PENDING
...
```

Categories: `IT_SUPPORT`, `MAINTENANCE`, `ROOM_BOOKING`, `SHUTTLE_REQUEST`, `SECURITY`, `CLEANING`, `EQUIPMENT_LOAN`
Urgency: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`
Status: `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

**File 4: `data/csv/resources.csv`** — 30 campus resources

```csv
resourceId,type,homeLocation,capacity,availabilityStatus
1,MAINTENANCE_CREW,12,3,AVAILABLE
2,IT_TECHNICIAN,3,1,AVAILABLE
3,SHUTTLE_BUS,15,45,AVAILABLE
...
```

Types: `MAINTENANCE_CREW`, `IT_TECHNICIAN`, `SHUTTLE_BUS`, `SECURITY_PATROL`, `CLEANING_CREW`, `EQUIPMENT_SET`

**File 5: `data/csv/evidence_note.md`** — Short explanation of how the dataset was constructed

Write 1-2 paragraphs explaining:
- That the data is based on real UG campus geography and operations
- That distances are approximate but realistic
- Which 3+ algorithm parameters are derived from team member index numbers

**Algorithm parameters from index numbers (REQUIRED — at least 3):**

Pick at least 3 of these. Use the **last digits** of team member student IDs:

| Parameter | How to Derive | Example |
|-----------|--------------|---------|
| Hash table initial size | Sum of last 2 digits of 3 member IDs | 51 + 88 + 84 = 223 → use 227 (next prime) |
| Priority weight for CRITICAL requests | Last digit of Team Lead's ID × 2 | 1 × 2 = 2.0 |
| Random seed for data generation | Concatenate last 2 digits of 2 IDs | 16 + 88 = 1688 |
| Road penalty multiplier | Average of last digits of 4 IDs | (6+8+4+6)/4 = 6 → 1.6 |
| Budget constraint for knapsack | Sum of all 16 member IDs mod 1000 | ... |

Document which parameters you derived and from whose index numbers.

**How to start:**

```bash
# 1. Make sure you are on the latest dev
git checkout dev
git pull origin dev

# 2. Create your branch
git checkout -b feature/matin/data-generation

# 3. Create the CSV files in data/csv/
#    (Use any text editor, Excel, or write a Java generator)

# 4. If you write a Java generator, put it in:
#    src/main/java/com/stackoverflow/smartservice/data/generator/DataGenerator.java

# 5. When done, commit and push
git add data/csv/locations.csv data/csv/roads.csv data/csv/service_requests.csv data/csv/resources.csv data/csv/evidence_note.md
git commit -m "data: Create UG campus dataset with 50 locations, 100 roads, 300 requests, 30 resources"
git push -u origin feature/matin/data-generation

# 6. Go to GitHub and create a Pull Request to dev
```

**Depends on:** Nothing — you go first.

**Who is waiting for you:** Robert (Role 2) and Prosper (Role 3) cannot start without your CSV files.

---

### Role 2: Database Architect

**Assigned to:** Robert Ganyoh Goodness (22405188)

**Your package:** `src/main/java/com/stackoverflow/smartservice/data/database/`
**Your output folder:** `database/`
**Your branch:** `feature/robert/database-schema`

**What you must build:**

**File 1: `database/schema.sql`** — The complete database schema

```sql
-- UG Campus Smart Service Optimizer Database Schema

CREATE TABLE IF NOT EXISTS locations (
    locationId INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    area TEXT NOT NULL,
    type TEXT NOT NULL,
    latitude REAL,
    longitude REAL
);

CREATE TABLE IF NOT EXISTS roads (
    roadId INTEGER PRIMARY KEY AUTOINCREMENT,
    fromLocationId INTEGER NOT NULL,
    toLocationId INTEGER NOT NULL,
    distance REAL NOT NULL,
    travelTime REAL NOT NULL,
    roadConditionWeight REAL DEFAULT 1.0,
    FOREIGN KEY (fromLocationId) REFERENCES locations(locationId),
    FOREIGN KEY (toLocationId) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS service_requests (
    requestId INTEGER PRIMARY KEY,
    source INTEGER NOT NULL,
    destination INTEGER NOT NULL,
    category TEXT NOT NULL,
    urgency TEXT NOT NULL,
    timeSubmitted TEXT NOT NULL,
    deadline TEXT NOT NULL,
    status TEXT DEFAULT 'PENDING',
    FOREIGN KEY (source) REFERENCES locations(locationId),
    FOREIGN KEY (destination) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS resources (
    resourceId INTEGER PRIMARY KEY,
    type TEXT NOT NULL,
    homeLocation INTEGER NOT NULL,
    capacity INTEGER NOT NULL,
    availabilityStatus TEXT DEFAULT 'AVAILABLE',
    FOREIGN KEY (homeLocation) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS algorithm_runs (
    runId INTEGER PRIMARY KEY AUTOINCREMENT,
    algorithmName TEXT NOT NULL,
    inputSize INTEGER NOT NULL,
    timeNs INTEGER NOT NULL,
    memoryKb INTEGER,
    dateRun TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS audit_events (
    eventId INTEGER PRIMARY KEY AUTOINCREMENT,
    eventType TEXT NOT NULL,
    description TEXT NOT NULL,
    timestamp TEXT NOT NULL,
    userId TEXT,
    undoable INTEGER DEFAULT 0
);
```

**File 2: `src/.../data/database/DatabaseConnection.java`** — Manages SQLite connections

```java
package com.stackoverflow.smartservice.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:database/ug_campus.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
```

**File 3: `src/.../data/database/LocationDAO.java`** — CRUD operations for locations table
**File 4: `src/.../data/database/RoadDAO.java`** — CRUD operations for roads table
**File 5: `src/.../data/database/ServiceRequestDAO.java`** — CRUD operations for service_requests table
**File 6: `src/.../data/database/ResourceDAO.java`** — CRUD operations for resources table
**File 7: `src/.../data/database/AlgorithmRunDAO.java`** — CRUD for algorithm_runs table
**File 8: `src/.../data/database/AuditEventDAO.java`** — CRUD for audit_events table

Each DAO (Data Access Object) class should have at minimum:
- `insert(...)` — add a new record
- `getById(int id)` — retrieve one record
- `getAll()` — retrieve all records
- `update(...)` — modify a record
- `delete(int id)` — remove a record

**Also add the SQLite JDBC driver:**
- Download `sqlite-jdbc-3.46.x.jar` from https://github.com/xerial/sqlite-jdbc/releases
- Place it in `lib/sqlite-jdbc.jar`

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/robert/database-schema

# Create database/schema.sql
# Create the Java DAO classes in src/main/java/com/stackoverflow/smartservice/data/database/
# Download and add the SQLite JDBC driver to lib/

# Test your schema works:
sqlite3 database/ug_campus.db < database/schema.sql
sqlite3 database/ug_campus.db ".tables"
# You should see: algorithm_runs  audit_events  locations  resources  roads  service_requests

git add database/schema.sql lib/sqlite-jdbc.jar src/main/java/com/stackoverflow/smartservice/data/database/
git commit -m "feat: Create database schema and DAO classes for UG campus service optimizer"
git push -u origin feature/robert/database-schema
```

**Depends on:** Matin (Role 1) — you need his CSV files to know the exact column names and data types.

**Who is waiting for you:** Prosper (Role 3) needs your schema and `DatabaseConnection.java` to write the loader.

---

### Role 3: Data Loader (Java I/O)

**Assigned to:** Prosper Sokari (22244884) and Samuel Kofi Ntem Amankwah (22017217)

**Your package:** `src/main/java/com/stackoverflow/smartservice/data/loader/`
**Your branch:** `feature/prosper/data-loader`

**What you must build:**

**File 1: `CSVParser.java`** — Reads and parses CSV files

```java
package com.stackoverflow.smartservice.data.loader;

// Use java.io.BufferedReader, java.io.FileReader — NOT any external library
// Read each line, split by comma, handle quoted fields if needed
// Return parsed records as arrays of Strings
```

**File 2: `DataValidator.java`** — Validates parsed records

```java
// Check for:
// - Missing required fields (locationId, name, etc.)
// - Invalid data types (text where number expected)
// - Out-of-range values (negative distance, invalid urgency level)
// - Duplicate IDs
// Log invalid records to console with the line number and reason
// Do NOT crash on bad data — skip the bad record and continue
```

**File 3: `DataLoader.java`** — Main loader that ties everything together

```java
// 1. Use CSVParser to read each CSV file from data/csv/
// 2. Use DataValidator to validate each record
// 3. Use Robert's DAO classes to insert valid records into the database
// 4. Print a summary: "Loaded 48/50 locations (2 invalid records skipped)"
```

**File 4: `StructureLoader.java`** — Loads data from database into custom data structures

```java
// This file will be completed AFTER Phase 2 (when the data structures exist)
// It will:
// 1. Read records from the database using DAO classes
// 2. Insert them into the custom DynamicArray, LinkedList, HashTable, Graph, etc.
// 3. This connects the database layer to the algorithm layer
```

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/prosper/data-loader

# Create your Java files in src/main/java/com/stackoverflow/smartservice/data/loader/
# You will import Robert's DatabaseConnection and DAO classes

# Test by running your loader on Matin's CSV files:
# - It should read all 4 CSV files
# - Validate every record
# - Insert valid records into the SQLite database
# - Print a summary

git add src/main/java/com/stackoverflow/smartservice/data/loader/
git commit -m "feat: Implement CSV parser, validator, and database loader for UG campus data"
git push -u origin feature/prosper/data-loader
```

**Depends on:** Matin (Role 1) for CSV files AND Robert (Role 2) for database schema and DAO classes.

**Who is waiting for you:** Everyone in Phase 2 will use your loader to test their structures with real UG campus data.

---

## Phase 2: Core Data Structures (Roles 4, 5, 6, 7)

**These four roles can work IN PARALLEL (at the same time).** Each person works in their own package, so there will be no merge conflicts.

**Wait until Phase 1 is done** so you can test your structures with real UG campus data. However, you CAN start coding and testing with dummy data while waiting.

**CRITICAL RULE:** No built-in Java collections allowed. You cannot use `ArrayList`, `LinkedList`, `HashMap`, `TreeMap`, `PriorityQueue`, `Stack`, `ArrayDeque`, or any class from `java.util` for these implementations. Build everything from scratch using only arrays (`[]`), `String`, and primitives.

---

### Role 4: Linear Structures Lead

**Assigned to:** Samuel Kofi Ntem Amankwah (22017217) and Ablorh-Adjei Caleb (22186668)

**Your package:** `src/main/java/com/stackoverflow/smartservice/structures/linear/`
**Your branch:** `feature/samuel-caleb/linear-structures`

**What you must build (split between the two of you):**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `DynamicArray.java` | Array that grows automatically when full. Methods: insert, get, set, remove, resize, size | Store the list of all 50 campus locations. When new locations are added, the array grows. |
| `SinglyLinkedList.java` | Linked list with one direction. Methods: addFirst, addLast, insertAfter, remove, contains, size | Chain of maintenance requests waiting to be processed. New requests added to the end. |
| `DoublyLinkedList.java` | Linked list with forward and backward traversal. Methods: addFirst, addLast, remove, reverse, size | Browse service request history forwards and backwards. |
| `CustomIterator.java` | Allows looping through your linked lists with `hasNext()` and `next()` | Loop through all requests: `while (iterator.hasNext()) { Request r = iterator.next(); }` |
| `CustomStack.java` | Last-in-first-out. Methods: push, pop, peek, isEmpty, size | Undo system: last action performed is the first to be undone. Also used for DFS. |
| `CustomQueue.java` | First-in-first-out (regular and circular). Methods: enqueue, dequeue, peek, isEmpty, size | FIFO request queue: first request submitted is first to be handled. Shuttle dispatch queue. |
| `CustomDeque.java` | Double-ended queue. Methods: addFront, addRear, removeFront, removeRear, peekFront, peekRear | Urgent requests jump to the front. Normal requests go to the back. |

**How to split the work between Samuel and Caleb:**

- **Samuel:** DynamicArray, CustomStack, CustomQueue, CustomDeque
- **Caleb:** SinglyLinkedList, DoublyLinkedList, CustomIterator

Both of you work on the same branch. Coordinate who commits which file to avoid conflicts.

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/samuel-caleb/linear-structures

# Each file should:
# 1. Use generics: public class DynamicArray<T> { ... }
# 2. Use a plain array internally: private T[] data;
# 3. Have a main method or test class that demonstrates it works

# Example skeleton for DynamicArray:
# - Start with capacity 10
# - When full, create a new array with double the capacity
# - Copy all elements to the new array

git add src/main/java/com/stackoverflow/smartservice/structures/linear/
git commit -m "feat: Implement DynamicArray with auto-resize and generic type support"
git push -u origin feature/samuel-caleb/linear-structures
```

**Depends on:** Phase 1 should be done for real data. You can start with dummy data.

**Who is waiting for you:** Renny & Hamidu (Role 8 — sorting/searching your arrays and lists), Kennedy (Role 10 — uses your Queue and Deque), Hator (Role 11 — uses your DynamicArray).

---

### Role 5: Tree Structures Lead

**Assigned to:** Samuel Kofi Ntem Amankwah (22017217) and Prince Boateng (22017084)

**Your package:** `src/main/java/com/stackoverflow/smartservice/structures/tree/`
**Your branch:** `feature/samuel/tree-structures`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `BinarySearchTree.java` | Tree where left child < parent < right child. Methods: insert, search, delete, inorder, preorder, postorder | Index campus locations by ID for fast lookup. Search for "location 23" in O(log n). |
| `RedBlackTree.java` | Self-balancing BST with color rules. Methods: insert (with rotations/recoloring), search, inorder | Balanced index of service requests. Guarantees O(log n) even in worst case. |
| `BTree.java` | Multi-way search tree (simplified). Methods: insert (with node split), search | Simulates how a database index works. Each node holds multiple keys. |

**How to split:** Samuel handles BST, Prince handles RedBlackTree and BTree.

**For the BST, support these traversals:**

```
        Balme(1)
       /        \
   UGCS(3)     JQB(2)
   /    \         \
 Pent(9) AO(20)  NBlock(4)

Inorder:  UGCS, Pent, AO, Balme, JQB, NBlock  (sorted by ID)
Preorder: Balme, UGCS, Pent, AO, JQB, NBlock  (root first)
Postorder: Pent, AO, UGCS, NBlock, JQB, Balme (leaves first)
```

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/samuel/tree-structures

# Start with BST — it is the simplest
# Then build RedBlackTree on top of BST concepts
# BTree can be simplified (2-3 tree or 2-3-4 tree)

git add src/main/java/com/stackoverflow/smartservice/structures/tree/
git commit -m "feat: Implement BST with insert, search, delete, and three traversal orders"
git push -u origin feature/samuel-ishmael/tree-structures
```

**Depends on:** Phase 1 for real data to test with.

**Who is waiting for you:** Renny & Hamidu (Role 8) may use BST for indexed searching. David (Role 14) needs this for BST vs balanced tree benchmarks.

---

### Role 6: Priority & Hash Lead

**Assigned to:** Kwakye Ishmael Affum (22032451)

**Your package:** `src/main/java/com/stackoverflow/smartservice/structures/hashing/`
**Your branch:** `feature/ishmael/priority-hash`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `MinHeap.java` | Complete binary tree where parent <= children. Methods: insert, extractMin, peek, heapify, size | Find the least urgent request. Process lowest-priority items first. |
| `MaxHeap.java` | Complete binary tree where parent >= children. Methods: insert, extractMax, peek, heapify, size | Find the most urgent request. CRITICAL requests extracted first. |
| `CustomPriorityQueue.java` | Queue backed by your Heap. Methods: enqueue(item, priority), dequeue, peek, isEmpty | Dispatch system: "Fix broken pipe at Akuafo Hall (CRITICAL)" goes before "Replace light bulb at N-Block (LOW)". |
| `HashTable.java` | Key-value storage with O(1) average lookup. Methods: put, get, remove, containsKey, size, keys | Fast lookup: "Is location ID 23 in the system?" → instant answer. Collision handling with chaining or open addressing. |
| `CustomHashSet.java` | Set of unique values built on your HashTable. Methods: add, remove, contains, size | Track which locations have been visited during graph traversal. |
| `CustomHashMap.java` | Key-value map built on your HashTable. Methods: put, get, remove, containsKey, size, entrySet | Map location IDs to location names: `map.put(1, "Balme Library")` → `map.get(1)` returns "Balme Library". |

**Hash table sizing using team index numbers:**

Use the sum of last 2 digits of 3 member IDs to compute initial table size, then pick the next prime number. Document this in a comment at the top of `HashTable.java`.

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/ishmael/priority-hash

# Start with HashTable — it's the most complex
# Then build CustomHashSet and CustomHashMap on top of it
# Then build MinHeap/MaxHeap
# Finally build CustomPriorityQueue using MaxHeap

git add src/main/java/com/stackoverflow/smartservice/structures/hashing/
git commit -m "feat: Implement HashTable with chaining collision resolution"
git push -u origin feature/ishmael/priority-hash
```

**Depends on:** Phase 1 for real data.

**Who is waiting for you:** Kennedy (Role 10 — uses your PriorityQueue), Samuel (Role 12 — needs collision statistics for testing).

---

### Role 7: Graph Foundations Lead

**Assigned to:** Kwakye Ishmael Affum (22032451)

**Your package:** `src/main/java/com/stackoverflow/smartservice/structures/graph/`
**Your branch:** `feature/ishmael/graph-foundations`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `AdjacencyListGraph.java` | Graph where each node stores a list of its neighbors. Methods: addVertex, addEdge, getNeighbors, hasEdge, getVertices, getEdgeWeight | The UG campus road network. Balme Library connects to JQB (0.3 km), UGCS (0.5 km). Memory-efficient for sparse campus maps. |
| `AdjacencyMatrixGraph.java` | Graph using a 2D array. Methods: addVertex, addEdge, getNeighbors, hasEdge, getWeight | Same campus network but using a 50x50 matrix. `matrix[1][2] = 0.3` means Balme to JQB is 0.3 km. Fast edge lookup. |
| `DisjointSet.java` | Tracks which locations are connected. Methods: makeSet, find (with path compression), union (by rank/size) | "Are Akuafo Hall and the Engineering Faculty in the same connected zone?" Used by Kruskal's algorithm. |

**The UG campus as a graph (example):**

```
                 Main Gate
                /        \
         Okponglo          Banking Sq
            |                  |
          NBlock ── JQB ── Balme Library
            |        |         |
         Science    UGCS    Great Hall
            |                  |
       Engineering         Athletic Oval
            |
        Pentagon ── Night Market
```

Each edge has a weight (distance in km and travel time in minutes).

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/ishmael/graph-foundations

# Start with AdjacencyListGraph — most commonly used
# Then AdjacencyMatrixGraph — simpler but uses more memory
# Then DisjointSet — needed for Kruskal's MST

# Test: build the campus graph from roads.csv and verify:
# - Balme Library has neighbors: JQB, UGCS, Great Hall
# - You can traverse from any hostel to any lecture hall

git add src/main/java/com/stackoverflow/smartservice/structures/graph/
git commit -m "feat: Implement adjacency list graph with weighted edges for UG campus network"
git push -u origin feature/ishmael/graph-foundations
```

**Depends on:** Phase 1 (Matin's locations and roads data to build the graph).

**Who is waiting for you:** Ishmael himself (Role 9 — routing algorithms), Hator (Role 11 — optimization on the graph).

---

## Phase 3: Algorithms & Logic (Roles 8, 9, 10, 11)

**These four roles can work IN PARALLEL** with each other, as long as their required Phase 2 structures are done.

Each role uses the custom data structures built in Phase 2. **Do NOT use built-in Java collections** — use the custom implementations from Group 2.

---

### Role 8: Search & Sort Specialist

**Assigned to:** Mensah Renny Abbey (22304259) and Mohammed Hamidu Abilla (22398905)

**Your package:** `src/main/java/com/stackoverflow/smartservice/algorithms/searchsort/`
**Your branch:** `feature/renny-hamidu/search-sort`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `LinearSearch.java` | Check every element one by one. O(n). | Search through all 300 service requests to find requests from "Akuafo Hall". |
| `BinarySearch.java` | Divide sorted array in half each step. O(log n). **Precondition: data must be sorted first.** | Quickly find location ID 23 in a sorted list of 50 locations. |
| `SelectionSort.java` | Find minimum, swap to front, repeat. O(n²). | Sort 300 requests by urgency — find the most urgent, put it first, repeat. |
| `InsertionSort.java` | Insert each element into its sorted position. O(n²). | Sort a small batch of new requests into an already-sorted list. |
| `MergeSort.java` | Divide in half, sort halves, merge. O(n log n). | Sort all 300 requests by submission time efficiently. |
| `QuickSort.java` | Pick pivot, partition around it. O(n log n) average. | Sort campus locations alphabetically. |

**How to split:** Renny handles LinearSearch, BinarySearch, SelectionSort. Hamidu handles InsertionSort, MergeSort, QuickSort.

**Important:** Your algorithms must work with the `DynamicArray` and `SinglyLinkedList` from Role 4 (Samuel & Caleb). Import their classes:

```java
import com.stackoverflow.smartservice.structures.linear.DynamicArray;
```

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/renny-hamidu/search-sort

# Each algorithm class should have:
# 1. A static method that takes a DynamicArray (or regular array) and sorts/searches it
# 2. A main method that demonstrates it works with sample campus data

# Example: sort campus locations by name
# Before: [Balme Library, Akuafo Hall, JQB, UGCS]
# After:  [Akuafo Hall, Balme Library, JQB, UGCS]

git add src/main/java/com/stackoverflow/smartservice/algorithms/searchsort/
git commit -m "feat: Implement merge sort and quicksort for campus service request ordering"
git push -u origin feature/renny-hamidu/search-sort
```

**Depends on:** Samuel & Caleb (Role 4) — you need their DynamicArray and LinkedList.

**Who is waiting for you:** Samuel (Role 12 — testing), Emelia (Role 13 — trace tables for your sorts), David (Role 14 — benchmarks comparing your sorting algorithms).

---

### Role 9: Graph Routing Expert

**Assigned to:** Kwakye Ishmael Affum (22032451)

**Your package:** `src/main/java/com/stackoverflow/smartservice/algorithms/graph/`
**Your branch:** `feature/ishmael/graph-routing`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `BFS.java` | Visit all neighbors level by level. Finds all reachable locations. | "Starting from Balme Library, which buildings can I reach?" Shows all connected campus locations. |
| `DFS.java` | Go as deep as possible before backtracking. | "Explore all paths from Pentagon" — goes deep into one direction before trying another. |
| `Dijkstra.java` | Find the shortest weighted path between two locations. | "What is the fastest route from Akuafo Hall to UGCS?" → Akuafo → JQB → UGCS (0.8 km, 11 min). |
| `Prim.java` | Build a minimum spanning tree starting from one node. | "What is the minimum total road length to connect all 50 campus buildings?" |
| `Kruskal.java` | Build MST by adding cheapest edges (uses DisjointSet). | Same as Prim but different approach — sort all roads by distance, add cheapest first. |

**Dijkstra example on UG campus:**

```
Find shortest path: Akuafo Hall → UGCS

Step 1: Start at Akuafo Hall (distance = 0)
Step 2: Check neighbors: Legon Hall (0.8 km), JQB (0.6 km)
Step 3: Visit JQB (closest unvisited, 0.6 km)
Step 4: From JQB, check: UGCS (0.5 km from JQB, total = 1.1 km)
Step 5: Visit UGCS (total distance = 1.1 km)

Result: Akuafo Hall → JQB → UGCS = 1.1 km
```

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/ishmael/graph-routing

# Use your own Graph structures from Role 7
import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;
import com.stackoverflow.smartservice.structures.graph.DisjointSet;

# Start with BFS and DFS (simpler), then Dijkstra, then Prim/Kruskal

git add src/main/java/com/stackoverflow/smartservice/algorithms/graph/
git commit -m "feat: Implement Dijkstra shortest path for UG campus routing"
git push -u origin feature/ishmael/graph-routing
```

**Depends on:** Role 7 (your own Graph and DisjointSet structures).

**Who is waiting for you:** Samuel (Role 12 — testing), Emelia (Role 13 — trace tables for Dijkstra and Kruskal), David (Role 14 — graph algorithm benchmarks).

---

### Role 10: Service Scheduling Logic

**Assigned to:** Kennedy Sarfo (22064116)

**Your package:** `src/main/java/com/stackoverflow/smartservice/algorithms/scheduling/`
**Your branch:** `feature/kennedy/scheduling`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `FIFODispatch.java` | First request in = first request handled. Uses CustomQueue. | Requests processed in order received: 8:30am projector fix → 9:00am plumbing → 10:15am room booking. |
| `PriorityDispatch.java` | Most urgent request handled first. Uses CustomPriorityQueue. | CRITICAL "Water main burst at Volta Hall" jumps ahead of LOW "Replace light bulb at N-Block". |
| `CircularDispatch.java` | Round-robin across resource teams. Uses circular CustomQueue. | Crew A gets request 1, Crew B gets request 2, Crew C gets request 3, back to Crew A for request 4. |
| `DequeDispatch.java` | Normal requests go to back, urgent to front. Uses CustomDeque. | CRITICAL requests inserted at the front of the queue; regular requests go to the back. |
| `SchedulingComparator.java` | Run all 4 strategies on the same set of requests and compare results. | "Given these 50 requests, FIFO finishes in 48 hours, Priority finishes in 32 hours." |

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/kennedy/scheduling

# Import the structures from Role 4 and Role 6:
import com.stackoverflow.smartservice.structures.linear.CustomQueue;
import com.stackoverflow.smartservice.structures.linear.CustomDeque;
import com.stackoverflow.smartservice.structures.hashing.CustomPriorityQueue;

# Load service requests from the database
# Run each dispatch strategy
# Compare: which strategy handles CRITICAL requests fastest?

git add src/main/java/com/stackoverflow/smartservice/algorithms/scheduling/
git commit -m "feat: Implement FIFO and priority-based dispatch for UG campus service requests"
git push -u origin feature/kennedy/scheduling
```

**Depends on:** Samuel & Caleb (Role 4 — Queue, Deque) and Ishmael (Role 6 — PriorityQueue).

**Who is waiting for you:** Samuel (Role 12 — testing), David (Role 14 — benchmarks).

---

### Role 11: Optimization Expert

**Assigned to:** Amenyo Hator (22040317)

**Your package:** `src/main/java/com/stackoverflow/smartservice/algorithms/optimization/`
**Your branch:** `feature/hator/optimization`

**What you must build:**

| File | What It Does | UG Campus Usage Example |
|------|-------------|------------------------|
| `GreedyAssignment.java` | Always pick the locally best option. | Assign maintenance crews to requests: always pick the nearest crew. Fast but not always optimal. |
| `GreedyCounterexample.java` | Show a case where greedy gives a wrong/suboptimal answer. | Nearest crew to Balme is busy in 10 min, but greedy picks the far crew. A smarter algorithm would wait. |
| `DPKnapsack.java` | Dynamic programming — consider all combinations optimally. | "We have 8 hours and 20 pending requests with different urgencies and time costs. Which subset maximizes total urgency handled within the time budget?" |

**Knapsack example for UG campus:**

```
Budget: 8 hours of maintenance crew time

Request A: Fix projector at JQB      (2 hours, urgency value 5)
Request B: Plumbing at Akuafo Hall   (3 hours, urgency value 7)
Request C: Wi-Fi fix at UGCS         (4 hours, urgency value 8)
Request D: Clean Great Hall          (1 hour,  urgency value 2)
Request E: Generator repair at ISH   (5 hours, urgency value 9)

DP finds: Pick B + C + D = 8 hours, total value = 17 (optimal)
Greedy (by value/hour) might pick differently and get less.
```

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/hator/optimization

# For GreedyAssignment: use the graph from Role 7 to find nearest resources
# For DPKnapsack: use DynamicArray from Role 4 to store the DP table
# For Counterexample: construct a specific set of requests where greedy fails

git add src/main/java/com/stackoverflow/smartservice/algorithms/optimization/
git commit -m "feat: Implement greedy resource assignment and DP knapsack for UG campus requests"
git push -u origin feature/hator/optimization
```

**Depends on:** Samuel & Caleb (Role 4 — DynamicArray) and Ishmael (Role 7 — Graph).

**Who is waiting for you:** Samuel (Role 12 — testing), Emelia (Role 13 — proof sketches and counterexample documentation), David (Role 14 — benchmarks).

---

## Phase 4: QA, Testing & Performance (Roles 12, 13, 14)

**These three roles can work IN PARALLEL.** They all test and analyze the code from Phases 2 and 3.

**You must wait until Phases 2 and 3 are done** (or at least mostly done). However, you can start writing test stubs, planning trace tables, and setting up benchmark frameworks early.

---

### Role 12: Unit Tester & QA

**Assigned to:** Prince Tawiah Tettey (22390334)

**Your package:** `src/test/java/com/stackoverflow/smartservice/`
**Your branch:** `feature/prince-tettey/unit-tests`

**What you must build:**

At least **40 unit tests** using JUnit 5. Organize your test files by what they test:

```
src/test/java/com/stackoverflow/smartservice/
├── structures/
│   ├── DynamicArrayTest.java          (5+ tests)
│   ├── LinkedListTest.java            (5+ tests)
│   ├── StackQueueDequeTest.java       (5+ tests)
│   ├── BSTTest.java                   (3+ tests)
│   ├── HeapPriorityQueueTest.java     (4+ tests)
│   ├── HashTableTest.java            (5+ tests)
│   └── GraphTest.java                 (3+ tests)
├── algorithms/
│   ├── SearchTest.java                (3+ tests)
│   ├── SortTest.java                  (4+ tests)
│   └── GraphAlgorithmTest.java        (3+ tests)
```

**Every test must cover 3 cases:**

| Case Type | What to Test | Example |
|-----------|-------------|---------|
| **Normal** | Typical usage | Insert 10 campus locations into BST, search for "JQB" → found |
| **Boundary** | Edge cases | Search in empty BST → returns null. Queue with 1 element → dequeue works. |
| **Invalid** | Bad input | Insert null into DynamicArray → throws exception. Negative index → throws exception. |

**Specific edge cases to hunt for:**

- Hash table with many collisions (e.g., keys that all hash to the same bucket)
- Graph with disconnected components (e.g., "can you reach Athletic Oval from Pentagon if no road connects them?")
- Binary search on unsorted input (should fail or give wrong answer — document this)
- Stack overflow on deep recursion (very deep BST)
- Queue wrap-around in circular queue

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/samuel/unit-tests

# Create test files in src/test/java/com/stackoverflow/smartservice/
# Each test class imports JUnit 5:
# import org.junit.jupiter.api.Test;
# import static org.junit.jupiter.api.Assertions.*;

# Run tests: javac and run with JUnit, or use your IDE's test runner

git add src/test/java/com/stackoverflow/smartservice/
git commit -m "test: Add 40+ unit tests across all data structures and algorithms"
git push -u origin feature/samuel/unit-tests
```

**Depends on:** Phases 2 and 3 — you need the code to exist before you can test it. Start writing test stubs early.

**Who is waiting for you:** Elijah (Role 16 — needs test results and screenshots for the report).

---

### Role 13: Theoretical Proof Analyst

**Assigned to:** Emelia Quarshie (22305966)

**Your output folder:** `docs/trace-tables/` and `docs/proofs/`
**Your branch:** `feature/emelia/proofs-traces`

**What you must build:**

**At least 6 trace tables** — step-by-step execution showing how the algorithm processes UG campus data:

| # | Algorithm | UG Campus Example to Trace |
|---|-----------|---------------------------|
| 1 | Binary Search | Search for location ID 23 in sorted location list [1, 3, 5, 8, 12, 23, 31, 42, 50] |
| 2 | Insertion Sort | Sort 6 service requests by urgency value: [3, 1, 5, 2, 4, 1] |
| 3 | Merge Sort or QuickSort | Sort 8 campus locations alphabetically |
| 4 | Dijkstra | Find shortest path from Akuafo Hall to UGCS (use 6-8 locations) |
| 5 | Kruskal or Prim | Build MST for a subset of 6-8 campus locations |
| 6 | DP Knapsack | Select optimal requests within 8-hour budget |

**Trace table format example (Binary Search):**

```
Searching for locationId = 23 in sorted array:
[1, 3, 5, 8, 12, 23, 31, 42, 50]

| Step | low | high | mid | array[mid] | Comparison        | Action       |
|------|-----|------|-----|------------|-------------------|--------------|
| 1    | 0   | 8    | 4   | 12         | 23 > 12           | low = mid+1  |
| 2    | 5   | 8    | 6   | 31         | 23 < 31           | high = mid-1 |
| 3    | 5   | 5    | 5   | 23         | 23 == 23          | FOUND at index 5 |
```

**At least 3 proof sketches:**

1. **Loop invariant for Binary Search:** "At every iteration, if the target exists, it is within array[low..high]."
2. **Induction proof for Merge Sort:** "If merge correctly combines two sorted halves, then recursively sorting all halves produces a fully sorted array."
3. **Greedy correctness (or failure):** Show why greedy nearest-crew assignment doesn't always minimize total travel distance.

**At least 2 counterexamples:**

1. **Greedy failure:** Construct a set of 5 UG campus requests where greedy assignment gives a suboptimal total distance.
2. **Binary search on unsorted data:** Show that binary search returns wrong answer when the input is not sorted.

You can write these in Markdown or as PDF. Put them in `docs/trace-tables/` and `docs/proofs/`.

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/emelia/proofs-traces

# Create markdown files:
# docs/trace-tables/binary_search_trace.md
# docs/trace-tables/insertion_sort_trace.md
# docs/trace-tables/merge_sort_trace.md
# docs/trace-tables/dijkstra_trace.md
# docs/trace-tables/kruskal_trace.md
# docs/trace-tables/dp_knapsack_trace.md
# docs/proofs/loop_invariant_binary_search.md
# docs/proofs/induction_merge_sort.md
# docs/proofs/greedy_correctness.md
# docs/proofs/counterexample_greedy.md
# docs/proofs/counterexample_unsorted_binary_search.md

git add docs/trace-tables/ docs/proofs/
git commit -m "docs: Add 6 trace tables and 3 proof sketches with UG campus examples"
git push -u origin feature/emelia/proofs-traces
```

**Depends on:** Phase 3 — you need the algorithms to be implemented so you can trace them with real behavior.

**Who is waiting for you:** Elijah (Role 16 — your trace tables and proofs go directly into the final report).

---

### Role 14: Empirical Lab Tech

**Assigned to:** Opoku Owusu-Bonsu David (22303106)

**Your package:** `src/main/java/com/stackoverflow/smartservice/testing/benchmark/`
**Your output folder:** `docs/performance/`
**Your branch:** `feature/david/benchmarks`

**What you must build:**

**File 1: `BenchmarkRunner.java`** — Runs all experiments and measures time in nanoseconds

**Required experiments:**

| Experiment | Input Sizes | What to Measure |
|-----------|-------------|-----------------|
| Linear vs Binary Search | 100, 500, 1000, 5000, 10000 records | Time (ns) to find a target |
| Sorting comparison | 100, 500, 1000, 5000, 10000 records | Time (ns) for selection, insertion, merge, quicksort |
| Hash table load factor | 100, 500, 1000, 5000, 10000, 20000 keys | Collision count and lookup time at different table sizes |
| BST vs Red-Black Tree | 100, 500, 1000, 5000, 10000 inserts | Insert time, search time, tree height |
| Heap operations | 100, 500, 1000, 5000, 10000, 20000 requests | Insert and extractMax time |
| Graph algorithms | 50, 100, 200, 500 locations/edges | BFS, DFS, Dijkstra, MST runtime |

**Rules:**
- Run each experiment **at least 3 times** and report the **average**
- Use `System.nanoTime()` for timing
- Log results to the database (`algorithm_runs` table via Robert's DAO)
- Export results to CSV files in `docs/performance/`
- State your machine specs (processor, RAM, OS) at the top of your results
- Create line graphs comparing algorithms (use Excel, Google Sheets, Python matplotlib, or any tool)

**File 2: `docs/performance/results/` folder** — CSV files with raw timing data

```csv
algorithmName,inputSize,run1_ns,run2_ns,run3_ns,average_ns
LinearSearch,100,4500,4200,4800,4500
LinearSearch,500,22000,21500,22800,22100
BinarySearch,100,800,750,820,790
BinarySearch,500,950,900,980,943
...
```

**File 3: `docs/performance/graphs/` folder** — Line graph images (PNG or PDF)

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/david/benchmarks

# Create BenchmarkRunner.java that:
# 1. Generates random data at each input size
# 2. Runs each algorithm 3 times
# 3. Records average time
# 4. Saves to database AND CSV

git add src/main/java/com/stackoverflow/smartservice/testing/benchmark/ docs/performance/
git commit -m "feat: Add benchmark runner and performance results for search/sort/hash/graph algorithms"
git push -u origin feature/david/benchmarks
```

**Depends on:** Phases 2 + 3 (all structures and algorithms) and Robert (Role 2 — database for logging results).

**Who is waiting for you:** Elijah (Role 16 — needs your graphs and CSV data for the final report). Data Visualizer (bonus role) will also use your CSV data.

---

## Phase 5: Integration & Documentation (Roles 15, 16)

**These roles tie everything together.** They depend on ALL previous phases being substantially complete.

---

### Role 15: Repository & Menu Manager

**Assigned to:** Kwakye Ishmael Affum (22032451)

**Your package:** `src/main/java/com/stackoverflow/smartservice/app/`
**Your branch:** `feature/ishmael/console-menu`

**What you must build:**

**File 1: `Main.java`** — Entry point

**File 2: `ConsoleMenu.java`** — Interactive menu the examiner uses to demo the system

```
========================================
  UG Campus Smart Service Optimizer
  Team: Stack Overflow
========================================

1. Load Data
   1.1 Load from CSV files into database
   1.2 Load from database into data structures
   1.3 View loaded data summary

2. Search & Sort
   2.1 Search for a location (Linear Search)
   2.2 Search for a location (Binary Search)
   2.3 Sort requests by urgency (Selection Sort)
   2.4 Sort requests by urgency (Insertion Sort)
   2.5 Sort requests by urgency (Merge Sort)
   2.6 Sort requests by urgency (QuickSort)

3. Campus Routing
   3.1 Find all reachable locations (BFS)
   3.2 Explore campus paths (DFS)
   3.3 Find shortest route (Dijkstra)
   3.4 Build minimum campus network (Prim)
   3.5 Build minimum campus network (Kruskal)

4. Service Scheduling
   4.1 FIFO dispatch
   4.2 Priority-based dispatch
   4.3 Round-robin dispatch
   4.4 Deque dispatch (urgent front-loading)
   4.5 Compare all strategies

5. Optimization
   5.1 Greedy resource assignment
   5.2 Greedy counterexample
   5.3 DP knapsack request selection

6. Data Structures Demo
   6.1 Dynamic Array demo
   6.2 Linked List demo
   6.3 Stack demo (with undo)
   6.4 BST demo
   6.5 Hash Table demo (show collisions)
   6.6 Graph demo

7. Performance Benchmarks
   7.1 Run all benchmarks
   7.2 View past benchmark results

8. Audit Log
   8.1 View recent events
   8.2 Undo last action

0. Exit

Enter your choice:
```

The examiner should be able to type a number and see the result. **No code editing required to run demos.**

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/ishmael/console-menu

# Wire up all the components from Roles 1-14
# Use java.util.Scanner for console input (this is allowed — it's not a data structure)
# Handle invalid input gracefully (don't crash if user types "abc")

git add src/main/java/com/stackoverflow/smartservice/app/
git commit -m "feat: Build interactive console menu for UG campus service optimizer demo"
git push -u origin feature/ishmael/console-menu
```

**Depends on:** ALL previous phases.

**Who is waiting for you:** Elijah (Role 16 — needs screenshots of the working menu for the report).

---

### Role 16: Technical Report Coordinator

**Assigned to:** Elijah Kporha (22370828)

**Your output folder:** `docs/report/`
**Your branch:** `feature/elijah/report`

**What you must build:**

Final PDF/DOCX report with these sections:

| Section | Content | Who Provides It |
|---------|---------|----------------|
| 1. Cover Page | Title, team members (all 16 names + IDs), "University of Ghana Campus" context | You write it |
| 2. Problem Statement | What the system does, assumptions, boundaries | You write it |
| 3. Dataset Description | Data dictionary, sample data, evidence note | Matin (Role 1) |
| 4. Database Schema | Schema diagram, table descriptions, sample queries | Robert (Role 2) |
| 5. System Architecture | Module diagram, package structure, how components connect | You draw it |
| 6. Data Structures | Implementation diagrams, code snippets, explanations | Samuel, Caleb, Ishmael (Roles 4-7) |
| 7. Algorithms | Pseudocode, Java snippets, step-by-step explanations | Renny, Hamidu, Ishmael, Kennedy, Hator (Roles 8-11) |
| 8. Correctness Evidence | Trace tables, proofs, test results | Emelia (Role 13), Samuel (Role 12) |
| 9. Performance Analysis | Graphs, CSV data, interpretation, theory vs practice | David (Role 14) |
| 10. Database Integration | Screenshots, query logs, load/save evidence | Robert (Role 2), Prosper (Role 3) |
| 11. Responsible Algorithm Selection | When each algorithm is appropriate and when it is not | You compile from all algorithm owners |
| 12. Individual Contributions | Each member states what they did | All 16 members (collect from each person) |
| 13. References | Textbooks, websites, lecture materials | You compile |

**How to start:**

```bash
git checkout dev
git pull origin dev
git checkout -b feature/elijah/report

# Start with the cover page and table of contents
# Create a shared document (Google Docs or Word) for collecting contributions
# Message each team member to send their section content

# Final report goes in docs/report/
# Export as PDF AND DOCX

git add docs/report/
git commit -m "docs: Add final technical report for UG campus smart service optimizer"
git push -u origin feature/elijah/report
```

**Depends on:** ALL previous phases.

---

## Bonus Roles (Roles 17-20)

These are optional. Pick one up if you finish your main role early.

| Role | What to Do | When to Start |
|------|-----------|--------------|
| **Role 17: Data Visualizer** | Take David's CSV data (Role 14) and create high-quality line graphs comparing theoretical O(n) complexity with actual runtime. Use Python matplotlib, Excel, or any graphing tool. | After Phase 4 |
| **Role 18: Audit Log Developer** | Implement a Stack-based undo/audit trail using Ishmael's CustomStack (Role 4). Track events like "Request #42 assigned to Crew A" and allow undoing the last action. | After Phase 2 |
| **Role 19: Video Demo Director** | Plan and record the 5-8 minute demonstration video. Show: database loading, algorithm demos, graph routing on campus, test results, performance graphs. | After Phase 5 |
| **Role 20: AI-Resistance Checker** (Samuel Amanful Dadzie, 22298847) | Verify the project meets AI-resistance requirements: algorithm parameters derived from student index numbers, local UG campus context is genuine and distinct, trace tables show real understanding. | After Phase 5 |

---

## Folder Summary

| Role | Person(s) | Package / Folder |
|------|----------|-----------------|
| Role 1 | Matin | `data/csv/` and `src/.../data/generator/` |
| Role 2 | Robert | `database/` and `src/.../data/database/` |
| Role 3 | Prosper, Samuel | `src/.../data/loader/` |
| Role 4 | Samuel, Caleb | `src/.../structures/linear/` |
| Role 5 | Samuel, Prince B. | `src/.../structures/tree/` |
| Role 6 | Ishmael | `src/.../structures/hashing/` |
| Role 7 | Ishmael | `src/.../structures/graph/` |
| Role 8 | Renny, Hamidu | `src/.../algorithms/searchsort/` |
| Role 9 | Ishmael | `src/.../algorithms/graph/` |
| Role 10 | Kennedy | `src/.../algorithms/scheduling/` |
| Role 11 | Hator | `src/.../algorithms/optimization/` |
| Role 12 | Prince T. | `src/test/java/.../` |
| Role 13 | Emelia | `docs/trace-tables/` and `docs/proofs/` |
| Role 14 | David | `src/.../testing/benchmark/` and `docs/performance/` |
| Role 15 | Ishmael | `src/.../app/` |
| Role 16 | Elijah | `docs/report/` |

**`src/...` means `src/main/java/com/stackoverflow/smartservice/`**

---

## Dependency Map (Visual)

```
PHASE 1 (Do First - Sequential)
┌──────────────────────────────────────────────────────────────┐
│  Role 1 (Matin) ──> Role 2 (Robert) ──> Role 3 (Prosper+Samuel) │
│  CSV Data             DB Schema            Data Loader       │
└──────────────────────────────────────────────────────────────┘
                         │
                         ▼
PHASE 2 (All Start Together - Parallel)
┌──────────────────────────────────────────────────────────────┐
│  Role 4              Role 5          Role 6       Role 7     │
│  Samuel+Caleb        Samuel+Prince B Ishmael      Ishmael    │
│  (Linear)            (Trees)         (Hash)       (Graph)    │
└──────────────────────────────────────────────────────────────┘
                         │
                         ▼
PHASE 3 (Start When Your Phase 2 Dependencies Are Done)
┌──────────────────────────────────────────────────────────────┐
│  Role 8            Role 9        Role 10       Role 11       │
│  Renny+Hamidu      Ishmael       Kennedy       Hator         │
│  (Search/Sort)     (Routing)     (Scheduling)  (Optimization)│
│  needs R4          needs R7      needs R4+6    needs R4+7    │
└──────────────────────────────────────────────────────────────┘
                         │
                         ▼
PHASE 4 (Start When Phases 2+3 Are Done)
┌──────────────────────────────────────────────────────────────┐
│  Role 12             Role 13            Role 14              │
│  Prince T.           Emelia             David                │
│  (Unit Tests)        (Proofs/Traces)    (Benchmarks)         │
└──────────────────────────────────────────────────────────────┘
                         │
                         ▼
PHASE 5 (Final Assembly)
┌──────────────────────────────────────────────────────────────┐
│  Role 15                          Role 16                    │
│  Ishmael                          Elijah                     │
│  (Console Menu)                   (Report)                   │
└──────────────────────────────────────────────────────────────┘
```

---

## Important Reminders

1. **Stay in your folder.** Do not edit files in another role's package. This prevents merge conflicts.
2. **Do not use built-in Java collections** for core implementations. No `ArrayList`, `HashMap`, `PriorityQueue`, etc.
3. **Everyone must use Java 17.** Check with `java -version`. If yours is different, fix it before coding.
4. **Communicate.** If you are blocked because a previous role has not finished, message the group chat immediately.
5. **Commit often.** Small, working commits are better than one massive commit at the end.
6. **Test your code** before creating a PR. At minimum, make sure it compiles with `javac`.
7. **Follow the branch naming convention.** Your branch name is listed in your role section above.
8. **Create a Pull Request to `dev`** when done. The Team Lead reviews and merges.
9. **Never commit directly to `main` or `dev`.** Always use your feature branch.
10. **Use UG campus data** in your examples and tests. Not generic data — our campus, our buildings, our routes.
