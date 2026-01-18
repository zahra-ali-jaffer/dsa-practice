# Contains Duplicate (Brute Force / Nested Loop Approach)

## Problem Statement

Given an integer array `nums`, return `true` if any value appears more than once in the array.
Otherwise, return `false`.

---

##  Solution

```java
public class Solution {
    public boolean hasDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

---

## Understanding the Approach

This solution uses a **brute-force comparison** technique.

The idea is simple:

* Compare every element with every other element.
* If any two elements are equal, a duplicate exists.

---

## Step-by-Step Explanation

### Outer Loop

```java
for (int i = 0; i < nums.length; i++)
```

* Selects one element at a time (`nums[i]`)
* Treats it as the current reference element

---

### Inner Loop

```java
for (int j = i + 1; j < nums.length; j++)
```

* Compares `nums[i]` with all elements that come **after** it
* Starts from `i + 1` to avoid:

  * Comparing the same element with itself
  * Repeating previous comparisons

---

### Duplicate Check

```java
if (nums[i] == nums[j]) {
    return true;
}
```

* If two elements are equal:

  * A duplicate is found
  * The function immediately returns `true`

---

### Final Return

```java
return false;
```

* Executed only if no duplicates are found
* Means all elements are unique

---

## Example Dry Run

### Input

```
nums = [1, 2, 3, 3]
```

### Comparisons

```
i=0 → compare 1 with 2, 3, 3
i=1 → compare 2 with 3, 3
i=2 → compare 3 with 3 → match found
```

### Output

```
true
```

---

## Time and Space Complexity

### Time Complexity

```
O(n²)
```

* Two nested loops
* Every pair of elements may be compared

---

### Space Complexity

```
O(1)
```

* No extra data structures used
* Constant extra space

---

## Why This Solution Is Correct but Not Optimal

### Pros

* Easy to understand
* No extra memory required
* Works for all inputs

### Cons

* Inefficient for large arrays
* Much slower than optimal solutions

---

## Comparison with Recommended Approach

| Approach            | Time       | Space |
| ------------------- | ---------- | ----- |
| Nested loops (this) | O(n²)      | O(1)  |
| HashSet             | O(n)       | O(n)  |
| Sorting             | O(n log n) | O(1)  |

---

## When This Approach Is Acceptable

* Small input sizes
* Memory-restricted environments
* Learning or demonstration purposes

---

## Key Takeaways

* This solution checks all possible pairs.
* Early return improves performance slightly.
* Not suitable for large datasets.
* HashSet approach is preferred in interviews.

---

