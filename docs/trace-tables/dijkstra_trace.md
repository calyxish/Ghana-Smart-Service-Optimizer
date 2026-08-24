# Dijkstra Trace

Find the shortest route from Akuafo Hall (`A`) to UGCS (`G`) using this weighted campus graph:

`A-B=4, A-C=2, B-C=1, B-D=5, C-D=8, C-E=10, D-E=2, D-G=6, E-G=3`.

| Step | Chosen vertex | Distance A | B | C | D | E | G | Relaxation |
|---|---|---:|---:|---:|---:|---:|---:|---|
| 0 | A | 0 | inf | inf | inf | inf | inf | Set B=4, C=2 |
| 1 | C | 0 | 3 | 2 | 10 | 12 | inf | B via C=3; D=10; E=12 |
| 2 | B | 0 | 3 | 2 | 8 | 12 | inf | D via B=8 |
| 3 | D | 0 | 3 | 2 | 8 | 10 | 14 | E via D=10; G=14 |
| 4 | E | 0 | 3 | 2 | 8 | 10 | 13 | G via E=13 |
| 5 | G | 0 | 3 | 2 | 8 | 10 | 13 | Destination finalized |

Predecessors give the route `A -> C -> B -> D -> E -> G`, with total distance `13`.