# Ghana Smart Service Operations Optimizer

A Java-based service operations platform implementing custom data structures and algorithms for the **University of Ghana campus**.

**Course:** DCIT 204/308 - Data Structures and Algorithms I & II
**Team:** Stack Overflow
**Team Lead:** Kwakye Ishmael Affum (22032451)

---

## What Is This Project?

We are building a **Smart Service Operations Optimizer** for the **University of Ghana campus**. The system models campus operations — lecture halls, hostels, labs, walkways, maintenance requests, IT support, shuttle routing, and resource allocation — using custom data structures and algorithms.

The system:

- Stores UG campus operational data (locations, roads, service requests, resources) in a database
- Loads data into **custom-built data structures** (no built-in Java collections for core logic)
- Applies algorithms to solve operational problems (routing, scheduling, optimization)
- Verifies correctness with tests, trace tables, and proofs
- Measures performance as input sizes grow
- Presents results with tables and graphs

---

## Quick Links for Team Members

| Guide | What It Covers |
|-------|---------------|
| [Guide_Contribution.md](Guide_Contribution.md) | How to use Git, create branches, commit, and submit your work |
| [Guide_Installation.md](Guide_Installation.md) | Setting up Java, your IDE, the database, and cloning the repo |
| [Guide_Implementation.md](Guide_Implementation.md) | Who does what, in what order, and where to put your code |
| [Guide_Performance.md](Guide_Performance.md) | Track your progress and check off completed work |

---

## Role Assignments

### Group 1: Data & Database Engineers (Phase 1 — Do First)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 1** | Local Context & Data Generator | Matin Seity | 22372116 |
| **Role 2** | Database Architect | Robert Ganyoh Goodness | 22405188 |
| **Role 3** | Data Loader (Java I/O) | Prosper Sokari, Samuel Kofi Ntem Amankwah | 22244884, 22017217 |

### Group 2: Core Data Structure Architects (Phase 2 — After Phase 1)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 4** | Linear Structures Lead | Samuel Kofi Ntem Amankwah, Ablorh-Adjei Caleb | 22017217, 22186668 |
| **Role 5** | Tree Structures Lead | Samuel Kofi Ntem Amankwah, Prince Boateng | 22017217, 22017084 |
| **Role 6** | Priority & Hash Lead | Kwakye Ishmael Affum | 22032451 |
| **Role 7** | Graph Foundations Lead | Kwakye Ishmael Affum | 22032451 |

### Group 3: Algorithm & Logic Engineers (Phase 3 — After Phase 2)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 8** | Search & Sort Specialist | Mensah Renny Abbey, Mohammed Hamidu Abilla | 22304259, 22398905 |
| **Role 9** | Graph Routing Expert | Kwakye Ishmael Affum | 22032451 |
| **Role 10** | Service Scheduling Logic | Kennedy Sarfo | 22064116 |
| **Role 11** | Optimization Expert | Amenyo Hator | 22040317 |

### Group 4: QA, Testing & Performance Lab (Phase 4 — After Phases 2+3)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 12** | Unit Tester & QA | Prince Tawiah Tettey | 22390334 |
| **Role 13** | Theoretical Proof Analyst | Emelia Quarshie | 22305966 |
| **Role 14** | Empirical Lab Tech | Opoku Owusu-Bonsu David | 22303106 |

### Group 5: Integration, Documentation & Presentation (Phase 5 — Final)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 15** | Repository & Menu Manager | Kwakye Ishmael Affum | 22032451 |
| **Role 16** | Technical Report Coordinator | Elijah Kporha | 22370828 |

### Bonus Roles (Optional — Pick Up If You Finish Early)

| Role | Responsibility | Assigned To | Student ID |
|------|---------------|-------------|------------|
| **Role 17** | Data Visualizer | TBD | — |
| **Role 18** | Audit Log Developer | TBD | — |
| **Role 19** | Video Demo Director | TBD | — |
| **Role 20** | AI-Resistance & Compliance Checker | Samuel Amanful Dadzie | 22298847 |

---

## Project Structure

```
Ghana-Smart-Service-Optimizer/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── stackoverflow/
│                   └── smartservice/
│                       ├── data/              # Group 1: Data & Database
│                       ├── structures/        # Group 2: Custom Data Structures
│                       ├── algorithms/        # Group 3: Algorithms & Logic
│                       ├── testing/           # Group 4: QA & Performance
│                       └── app/               # Group 5: Integration & Menu
├── src/
│   └── test/
│       └── java/                              # Unit tests go here
├── data/
│   └── csv/                                   # CSV seed files
├── database/
│   └── schema.sql                             # Database schema
├── docs/
│   ├── trace-tables/                          # Trace table evidence
│   ├── diagrams/                              # Architecture and data structure diagrams
│   └── performance/                           # Performance graphs and CSV results
├── Guide_Contribution.md
├── Guide_Installation.md
├── Guide_Implementation.md
├── Guide_Performance.md
└── README.md
```

---

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | **JDK 17 (LTS)** | Main programming language |
| SQLite | 3.x | Database (lightweight, no server needed) |
| JUnit 5 | 5.10+ | Unit testing |
| Git | Latest | Version control |
| IDE | IntelliJ IDEA / VS Code | Code editor |

---

## Team Members

| Student ID | Full Name | Role(s) |
|-----------|-----------|---------|
| 22032451 | **Kwakye Ishmael Affum** (Team Lead) | Roles 6, 7, 9, 15 |
| 22186668 | Ablorh-Adjei Caleb | Role 4 |
| 22244884 | Prosper Sokari | Roles 3 |
| 22064116 | Kennedy Sarfo | Role 10 |
| 22017217 | Samuel Kofi Ntem Amankwah | Roles 3, 4, 5 |
| 22017084 | Prince Boateng | Role 5 |
| 22304259 | Mensah, Renny Abbey | Role 8 |
| 22040317 | Amenyo Hator | Role 11 |
| 22305966 | Quarshie, Emelia Akua | Role 13 |
| 22370828 | Kporha, Elijah Mawuli | Role 16 |
| 22303106 | Opoku, David Owusu-Bonsu | Role 14 |
| 22390334 | Tawiah, Prince Tettey | Role 12 |
| 22405188 | Ganyoh, Robert Goodness | Role 2 |
| 22298847 | Dadzie, Samuel Amanful | Role 20 |
| 22398905 | Mohammed, Hamidu Abilla | Role 8 |
| 22372116 | Seity, Matin | Role 1 |

---

## Rules

1. **Never commit directly to `main` or `dev`.** Always use a feature branch.
2. **No built-in Java collections** (HashMap, TreeMap, PriorityQueue, Stack, ArrayDeque) for assessed core logic.
3. **Everyone uses Java 17.** No exceptions.
4. **Every branch needs a pull request** to merge into `dev`.
5. **Only the Team Lead merges `dev` into `main`.**

---

## License

This project is for academic purposes under the University of Ghana, Department of Computer Science.
