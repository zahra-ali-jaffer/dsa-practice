# Two Sum (Brute Force / Nested Loop Approach)

## Problem Statement

Given an integer array `nums` and an integer `target`, return the **indices** of the two numbers such that they add up to `target`.

You may assume:

* Exactly one solution exists.
* You may not use the same element twice.
* The answer can be returned in any order.

---

## Solution

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target ){
                    return new int[]{i, j};
                }
            }
        }
        return nums;
    }
}
```

---

## Understanding the Approach

This solution uses a **brute-force nested loop** approach.

The idea is:

* Try every possible pair of numbers.
* Check whether their sum equals the target.
* If a valid pair is found, return their indices immediately.

---

## Step-by-Step Explanation

### Outer Loop

```java
for (int i = 0; i < nums.length; i++)
```

* Selects the first number (`nums[i]`)
* Iterates through the array one element at a time

---

### Inner Loop

```java
for (int j = i + 1; j < nums.length; j++)
```

* Selects the second number (`nums[j]`)
* Starts from `i + 1` to:

  * Avoid using the same element twice
  * Avoid checking the same pair again

---

### Sum Check

```java
if (nums[i] + nums[j] == target)
```

* Checks whether the selected pair adds up to the target
* If true:

  * The indices `i` and `j` are returned immediately

---

### Return Statement

```java
return new int[]{i, j};
```

* Returns the indices of the two numbers that sum to the target
* Stops further execution

---

### Final Return

```java
return nums;
```

* Executed only if no valid pair is found
* This line is **not ideal**, because:

  * The problem guarantees a solution
  * Returning `nums` does not match the method’s purpose
* In practice, this line is rarely reached

---

## Example Dry Run

### Input

```
nums = [2, 7, 11, 15]
target = 9
```

### Comparisons

```
i=0, j=1 → 2 + 7 = 9 → match found
```

### Output

```
[0, 1]
```

---

## Time and Space Complexity

### Time Complexity

```
O(n²)
```

* All possible pairs are checked in the worst case

---

### Space Complexity

```
O(1)
```

* No additional data structures are used

---

## Why This Solution Works but Is Not Optimal

### Pros

* Simple and easy to understand
* No extra memory required
* Good for small inputs

### Cons

* Inefficient for large arrays
* Slower than optimal approaches

---

## Comparison with Optimal Approach

| Approach           | Time  | Space |
| ------------------ | ----- | ----- |
| Brute force (this) | O(n²) | O(1)  |
| HashMap            | O(n)  | O(n)  |

---

## Key Takeaways

* This solution checks all pairs of elements.
* Early return improves performance slightly.
* The final `return nums` is logically unnecessary.
* HashMap approach is preferred in interviews.

