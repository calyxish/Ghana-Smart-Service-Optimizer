# Greedy Assignment Counterexample

Consider five requests and five available crews. Each crew can handle one request in this simplified assignment. Travel distances are:

| Request | C1 | C2 | C3 | C4 | C5 |
|---|---:|---:|---:|---:|---:|
| R1 | 1 | 2 | 50 | 50 | 50 |
| R2 | 2 | 100 | 50 | 50 | 50 |
| R3 | 50 | 50 | 1 | 2 | 50 |
| R4 | 50 | 50 | 2 | 100 | 50 |
| R5 | 50 | 50 | 50 | 50 | 1 |

A nearest-available-crew strategy processes requests in order. It chooses C1 for R1, C2 for R2, C3 for R3, C4 for R4, and C5 for R5, producing a total of `1 + 100 + 1 + 100 + 1 = 203`.

An optimal coordinated assignment uses C2 for R1, C1 for R2, C4 for R3, C3 for R4, and C5 for R5, for a total of `2 + 2 + 2 + 2 + 1 = 9`. Thus the greedy result is suboptimal because an early locally cheapest choice consumes a crew needed by a later request.