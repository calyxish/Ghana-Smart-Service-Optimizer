# Binary Search Loop Invariant

**Claim:** At the start of every iteration, if the target exists, it is within `array[low..high]`.

**Initialization:** Initially `low = 0` and `high = n - 1`, so the interval contains the entire sorted array.

**Maintenance:** Let `mid` be the midpoint. If the target is greater than `array[mid]`, sortedness proves it cannot be at or left of `mid`; setting `low = mid + 1` preserves the claim. The less-than case is symmetric. Equality returns the target immediately.

**Termination:** If the target is found, the result is correct. If `low > high`, the interval is empty; by the invariant the target cannot exist in the array, so returning not-found is correct.