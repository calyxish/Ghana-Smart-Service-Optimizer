# Trace Table Audit — Role 20

Auditing: `docs/trace-tables/*` (authored by Emelia, Role 13)

**Purpose:** Confirm each required trace table uses real UG campus data and shows genuine step-by-step understanding, not boilerplate.

**Required minimum (per brief, Section 10):** 6 trace tables — binary search, insertion sort, merge sort or quicksort, Dijkstra, Kruskal or Prim, dynamic programming.

---

## Per-Table Audit

### 1. Binary Search
- [ ] File exists: `docs/trace-tables/___________`
- [ ] Uses real location/request IDs (not `[1,2,3,4,5]`)
- [ ] Shows low/mid/high at each step
- [ ] States precondition (array must be sorted) and confirms it's met
- [ ] Verdict: ☐ Pass ☐ Needs revision

### 2. Insertion Sort
- [ ] File exists
- [ ] Uses real dataset (e.g., service requests by urgency/time)
- [ ] Shows array state after each pass
- [ ] Verdict: ☐ Pass ☐ Needs revision

### 3. Merge Sort / Quicksort
- [ ] File exists
- [ ] Uses real dataset
- [ ] Shows split/merge or partition steps, not just final sorted output
- [ ] Verdict: ☐ Pass ☐ Needs revision

### 4. Dijkstra
- [ ] File exists
- [ ] Uses actual UG campus locations and road weights
- [ ] Shows distance table and predecessor updates at each step
- [ ] Final shortest path is stated
- [ ] Verdict: ☐ Pass ☐ Needs revision

### 5. Kruskal / Prim
- [ ] File exists
- [ ] Uses actual UG campus roads/edges
- [ ] Shows edge selection order and running total cost
- [ ] Disjoint-set union or visited-set state shown (whichever algorithm)
- [ ] Verdict: ☐ Pass ☐ Needs revision

### 6. Dynamic Programming
- [ ] File exists
- [ ] Uses real request/budget data (e.g., knapsack-style selection)
- [ ] Shows memoisation/tabulation table
- [ ] Shows reconstruction of the final selected solution
- [ ] Verdict: ☐ Pass ☐ Needs revision

---

## Supporting Evidence Also Required

- [ ] At least 2 counterexamples present (1 greedy failure case, 1 invalid-precondition case e.g. unsorted binary search input)
- [ ] At least 3 proof sketches present (loop invariant, induction/recursion, greedy/DP correctness)
- [ ] At least 40 unit tests exist across data structures/algorithms (check `src/test/java/`)

---

## Overall Verdict

- [ ] ✅ All 6 trace tables pass
- [ ] 🔶 Some need revision (listed above)
- [ ] ❌ Insufficient — escalate to Team Lead

**Reviewed by:** Samuel Dadzie Amanful (22298847)
**Date:**
