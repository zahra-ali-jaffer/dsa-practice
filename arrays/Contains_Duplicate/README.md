# Contains Duplicate (HashSet Approach)

## Problem Statement

Given an integer array `nums`, return `true` if any value appears **more than once** in the array.
Return `false` if every element is unique.

---

## Example

### Input

```
nums = [1, 2, 3, 3]
```

### Output

```
true
```

Explanation:
The number `3` appears more than once.

---

## Understanding the Problem

We are asked to:

1. Check whether **any duplicate value** exists in the array.
2. Return a boolean result.
3. Do this efficiently.

The problem does **not** require:

* Counting how many times a number appears
* Finding which number is duplicated

Only the **existence** of a duplicate matters.

---

## Recommended Time & Space Complexity

* Time Complexity: `O(n)`
* Space Complexity: `O(n)`

Where `n` is the number of elements in the array.

---

## Key Idea of the Solution

As we traverse the array:

* Keep track of elements we have already seen.
* If we encounter an element that we have seen before, return `true`.
* If we finish traversing the array without finding duplicates, return `false`.

---

## Why Use a HashSet?

### Role of HashSet

A `HashSet`:

* Stores only **unique values**
* Provides `O(1)` average time complexity for:

  * `add()`
  * `contains()`

This makes it ideal for detecting duplicates.

---

## Step-by-Step Logic

1. Create an empty `HashSet`.
2. Traverse the array.
3. For each number:

   * If it already exists in the set → duplicate found → return `true`.
   * Otherwise, add it to the set.
4. If the loop completes → no duplicates → return `false`.

---

## Explanation of the Key Line

```java
if (set.contains(num)) return true;
```

Explanation:

* `contains(num)` checks whether the number has already appeared.
* If it has, we immediately return `true` because a duplicate exists.
* This avoids unnecessary further checks.
---

## Time and Space Complexity Analysis

* Time Complexity: `O(n)`

  * Each element is checked and added once.

* Space Complexity: `O(n)`

  * In the worst case, all elements are unique and stored in the set.

---

## Alternative Approach (Sorting)

Another approach is:

1. Sort the array.
2. Check adjacent elements for equality.

However:

* Time Complexity becomes `O(n log n)`
* Less optimal than the HashSet approach

---

## Key Takeaways

* HashSet is the best data structure for detecting duplicates.
* Early return improves efficiency.
* No frequency counting is required.
* Meets the recommended time and space complexity.

---

