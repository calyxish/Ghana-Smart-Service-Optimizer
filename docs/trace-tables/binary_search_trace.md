# Binary Search Trace

Search for location ID `23` in the sorted location list:

`[1, 3, 5, 8, 12, 23, 31, 42, 50]`

| Step | low | high | mid | `array[mid]` | Comparison | Action |
|---|---:|---:|---:|---:|---|---|
| 1 | 0 | 8 | 4 | 12 | 23 > 12 | `low = 5` |
| 2 | 5 | 8 | 6 | 31 | 23 < 31 | `high = 5` |
| 3 | 5 | 5 | 5 | 23 | 23 == 23 | Found at index 5 |

The algorithm returns index `5` after three comparisons. The sorted-input precondition is essential.
