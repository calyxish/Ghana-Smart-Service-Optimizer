# Insertion Sort Trace

Sort service-request urgency values in arrival order: `[3, 1, 5, 2, 4, 1]`.

The sorted prefix is shown after each insertion.

| Step | Value inserted | Sorted prefix before | Shifts | Array after step |
|---|---:|---|---|---|
| 0 | - | - | - | `[3, 1, 5, 2, 4, 1]` |
| 1 | 1 | `[3]` | 3 moves right | `[1, 3, 5, 2, 4, 1]` |
| 2 | 5 | `[1, 3]` | None | `[1, 3, 5, 2, 4, 1]` |
| 3 | 2 | `[1, 3, 5]` | 5 and 3 move right | `[1, 2, 3, 5, 4, 1]` |
| 4 | 4 | `[1, 2, 3, 5]` | 5 moves right | `[1, 2, 3, 4, 5, 1]` |
| 5 | 1 | `[1, 2, 3, 4, 5]` | 5, 4, 3, 2 move right | `[1, 1, 2, 3, 4, 5]` |

Final urgency order: `[1, 1, 2, 3, 4, 5]`.