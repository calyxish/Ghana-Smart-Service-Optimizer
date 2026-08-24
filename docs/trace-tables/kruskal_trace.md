# Kruskal Trace

Build an MST for six campus locations. Edges are processed by increasing weight:

`A-C=1, D-E=2, B-C=3, E-F=3, A-B=4, C-D=5, D-F=6, B-E=7`.

| Step | Edge | Components before | Decision | Weight total |
|---|---|---|---|---:|
| 1 | A-C (1) | A, B, C, D, E, F | Accept; union A and C | 1 |
| 2 | D-E (2) | AC, B, D, E, F | Accept; union D and E | 3 |
| 3 | B-C (3) | AC, B, DE, F | Accept; union B with AC | 6 |
| 4 | E-F (3) | ABC, DE, F | Accept; union F with DE | 9 |
| 5 | A-B (4) | ABC, DEF | Reject; creates a cycle | 9 |
| 6 | C-D (5) | ABC, DEF | Accept; all vertices connected | 14 |

The MST edges are `{A-C, D-E, B-C, E-F, C-D}`. It contains `6 - 1 = 5` edges and has total weight `14`.