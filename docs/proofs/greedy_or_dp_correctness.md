# Dynamic Programming Correctness

For knapsack, let `OPT(i, b)` be the greatest value obtainable from the first `i` requests using at most `b` hours. For request `i` with duration `h` and value `v`:

`OPT(i, b) = OPT(i-1, b)` when `h > b`.

Otherwise, every optimal solution either excludes the request or includes it, so:

`OPT(i, b) = max(OPT(i-1, b), v + OPT(i-1, b-h))`.

The base case `OPT(0, b) = OPT(i, 0) = 0` is correct. The recurrence considers every possible inclusion decision, and each subproblem is optimal by induction on `i`; therefore the final table entry is optimal.