# Ghana Smart Service Operations Optimizer

A Java-based service operations platform implementing custom data structures and algorithms for a Ghanaian context.

**Course:** DCIT 204/308 - Data Structures and Algorithms I & II
**Team:** Stack Overflow
**Team Lead:** Kwakye Ishmael Affum (22032451)

---

## What Is This Project?

We are building a **Smart Service Operations Optimizer** for a Ghanaian local context. The system:

- Stores local operational data (locations, roads, service requests, resources) in a database
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

| Student ID | Full Name |
|-----------|-----------|
| 22032451 | **Kwakye Ishmael Affum** (Team Lead) |
| 22186668 | Ablorh-Adjei Caleb |
| 22244884 | Prosper Sokari |
| 22064116 | Kennedy Sarfo |
| 22017217 | Samuel Kofi Ntem Amankwah |
| 22017084 | Prince Boateng |
| 22304259 | Mensah, Renny Abbey |
| 22040317 | Amenyo Hator |
| 22305966 | Quarshie, Emelia Akua |
| 22370828 | Kporha, Elijah Mawuli |
| 22303106 | Opoku, David Owusu-Bonsu |
| 22390334 | Tawiah, Prince Tettey |
| 22405188 | Ganyoh, Robert Goodness |
| 22298847 | Dadzie, Samuel Amanful |
| 22398905 | Mohammed, Hamidu Abilla |
| 22372116 | Seity, Matin |

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
