# Merge Sort Induction Proof

**Claim:** Merge sort returns the input elements in nondecreasing order.

**Base case:** An array of length zero or one is already sorted.

**Inductive step:** Assume recursive calls correctly sort arrays shorter than length `n`. For an array of length `n`, the two halves are shorter, so the assumption makes both halves sorted. The merge operation repeatedly chooses the smaller first remaining element. Therefore no chosen element is greater than a later remaining element, and every input element appears exactly once. The merged array is sorted.

By induction, merge sort correctly sorts arrays of every finite length.