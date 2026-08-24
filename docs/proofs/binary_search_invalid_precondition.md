# Binary Search on Unsorted Data

Binary search requires sorted input. Take the unsorted list `[1, 7, 3, 5, 9]` and search for `3`.

| Step | low | high | mid | Value | Action |
|---|---:|---:|---:|---:|---|
| 1 | 0 | 4 | 2 | 3 | Found in this case |

Now search the same list for `7`:

| Step | low | high | mid | Value | Action |
|---|---:|---:|---:|---:|---|
| 1 | 0 | 4 | 2 | 3 | 7 > 3, set `low = 3` |
| 2 | 3 | 4 | 3 | 5 | 7 > 5, set `low = 4` |
| 3 | 4 | 4 | 4 | 9 | 7 < 9, set `high = 3` |

The algorithm reports not found even though `7` is at index 1. The discarded halves were eliminated using comparisons that are only valid when the array is sorted.