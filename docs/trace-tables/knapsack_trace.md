# Dynamic Programming Knapsack Trace

Select service requests within an 8-hour response budget. Each request has `(hours, priority value)`:

`R1=(3,4), R2=(4,5), R3=(2,3), R4=(5,7)`.

`dp[i][b]` is the best value using the first `i` requests and at most `b` hours.

| Item considered | DP values for budgets 0..8 |
|---|---|
| None | `[0, 0, 0, 0, 0, 0, 0, 0, 0]` |
| R1 (3,4) | `[0, 0, 0, 4, 4, 4, 4, 4, 4]` |
| R2 (4,5) | `[0, 0, 0, 4, 5, 5, 5, 9, 9]` |
| R3 (2,3) | `[0, 0, 3, 4, 5, 7, 8, 9, 9]` |
| R4 (5,7) | `[0, 0, 3, 4, 5, 7, 8, 9, 10]` |

The optimal value is `10` at budget 8, achieved by selecting `R1 + R4` (3 + 5 hours, value 4 + 7).