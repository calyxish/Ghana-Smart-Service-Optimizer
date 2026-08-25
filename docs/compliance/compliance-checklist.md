# Compliance Checklist — Role 20 (AI-Resistance & Compliance Checker)

**Checker:** Samuel Dadzie Amanful (22298847)
**Last updated:** _(fill in date of each review pass)_

Status legend: ☐ Not checked | 🔶 In progress | ✅ Verified | ❌ Failed — needs fix

---

## A. Index-Number-Derived Parameters (need ≥ 3)

| # | Parameter | Derived From (whose index #s) | Calculation Shown? | Status | Notes |
|---|-----------|-------------------------------|---------------------|--------|-------|
| 1 | | | ☐ | ☐ | |
| 2 | | | ☐ | ☐ | |
| 3 | | | ☐ | ☐ | |

**Check against `data/csv/evidence_note.md`:**
- [ ] At least 3 parameters documented
- [ ] Each shows the actual arithmetic (not just a final number)
- [ ] Parameters are actually used in code (`structures/` or `algorithms/`), not just written in the note
- [ ] Cross-check the code value matches the documented derivation

---

## B. Local UG Campus Context Authenticity

- [ ] Locations are real UG Legon buildings (Balme Library, JQB, UGCS, N-Block, halls, etc.)
- [ ] Distances/travel times are plausible for actual campus geography
- [ ] Service request categories/examples reflect real campus scenarios (IT, maintenance, room booking, shuttle, security)
- [ ] Not a reskinned generic logistics/hospital/courier dataset
- [ ] `data/csv/evidence_note.md` explains how data was constructed without exposing personal data

---

## C. Trace Tables (need ≥ 6, from `docs/trace-tables/`)

| Algorithm | File Present? | Uses Real UG Data? | Shows Step-by-Step State? | Status |
|-----------|---------------|---------------------|----------------------------|--------|
| Binary search | ☐ | ☐ | ☐ | ☐ |
| Insertion sort | ☐ | ☐ | ☐ | ☐ |
| Merge sort / Quicksort | ☐ | ☐ | ☐ | ☐ |
| Dijkstra | ☐ | ☐ | ☐ | ☐ |
| Kruskal / Prim | ☐ | ☐ | ☐ | ☐ |
| Dynamic Programming | ☐ | ☐ | ☐ | ☐ |

- [ ] No generic/toy examples (e.g., array of `[1,2,3]`) — must use actual location/request IDs
- [ ] At least 2 counterexamples present (1 greedy failure, 1 invalid precondition case)
- [ ] At least 3 proof sketches present (loop invariant, induction/recursion, greedy/DP correctness)

---

## D. AI-Assistance Disclosure

- [ ] `docs/compliance/ai-disclosure-log.md` filled in by each member who used AI tools
- [ ] Tool name, purpose, and (where relevant) prompts logged
- [ ] Spot-check: pick 2–3 members, ask them to explain/modify a piece of their own code live
- [ ] No unattributed AI-generated boilerplate in report or code comments

---

## E. Development Log

- [ ] Weekly entries exist (check team's shared doc or repo log)
- [ ] Entries show real decisions/challenges, not backfilled after the fact
- [ ] Log ties back to actual commits/PRs where possible

---

## F. Repo Rules Compliance

| Rule | Check | Status | Notes |
|---|---|---|---|
| No direct commits to `main`/`dev` | Review commit history on `main`/`dev` for non-merge commits | ☐ | |
| No built-in Java collections (`HashMap`, `TreeMap`, `PriorityQueue`, `Stack`, `ArrayDeque`) in assessed core logic | `grep -rn "import java.util.\(HashMap\|TreeMap\|PriorityQueue\|Stack\|ArrayDeque\)"` in `structures/` and `algorithms/` | ☐ | |
| Everyone uses Java 17 | Spot-check `java -version` output from a few members / CI config | ☐ | |
| Every branch has a PR into `dev` | Check merged PR list vs. branch list on GitHub | ☐ | |
| Only Team Lead merges `dev` → `main` | Check `main` merge commits' author | ☐ | |

---

## G. Oral Defense Readiness

- [ ] Every member can name one data structure and one algorithm they can defend live
- [ ] Team has rehearsed a "live change" scenario (e.g., add a location, change hash table size, rerun an algorithm)

---

## G. Repo Rules Compliance

| # | Rule | How to Check | Status | Notes |
|---|------|---------------|--------|-------|
| 1 | Never commit directly to `main` or `dev` — always use a feature branch | `git log main --first-parent` / `git log dev --first-parent`: every commit should be a merge commit from a feature branch, not a direct commit | ☐ | |
| 2 | No built-in Java collections (`HashMap`, `TreeMap`, `PriorityQueue`, `Stack`, `ArrayDeque`) for assessed core logic | grep `structures/` and `algorithms/` for `import java.util.HashMap`, `TreeMap`, `PriorityQueue`, `Stack`, `ArrayDeque` — flag any hits outside allowed uses (file I/O, JDBC, plotting, test scaffolding) | ☐ | |
| 3 | Everyone uses Java 17, no exceptions | Ask 2–3 members for `java -version` output; check `pom.xml`/`build.gradle` source/target version | ☐ | |
| 4 | Every branch needs a pull request to merge into `dev` | Check GitHub PR history — no branch should be merged without an associated PR | ☐ | |
| 5 | Only the Team Lead merges `dev` into `main` | Check `main` branch merge commits — merger should be the Team Lead's account | ☐ | |

**Verdict:** ☐ All 5 rules followed ☐ Violations found (list below)

**Violations found:**

-

---

## Sign-off

- [ ] All sections above ✅
- [ ] Summary of any outstanding issues sent to Team Lead
- [ ] Final compliance note added to report Section 15 (Academic Integrity)
