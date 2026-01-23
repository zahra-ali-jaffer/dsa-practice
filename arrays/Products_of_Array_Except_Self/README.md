##  Problem Recap

You’re given an array `nums`.

You must return a new array `res` where:

[
res[i] = \text{product of all elements in nums except nums[i]}
]

Example:
`[1, 2, 4, 6]` →

* For index 0 → 2×4×6 = **48**
* For index 1 → 1×4×6 = **24**
* etc.

---

##  Key Challenge

You **cannot use division** in the optimal version, but **the solution *does* use division** (still valid logically, just not the follow-up constraint).

Main issue to handle: **zeros**
Division breaks when zero is involved, so we track them carefully.

---

## The Code — High-Level Idea

1. Compute the product of all **non-zero** numbers.
2. Count how many zeros exist.
3. Use different logic depending on zero count.

---

## 🔍 Step-by-Step Breakdown

### ###  Variable Setup

```java
int prod = 1, zeroCount = 0;
```

* `prod` → product of all **non-zero** elements
* `zeroCount` → how many zeros are in the array

---

### ###  First Loop — Calculate Product & Count Zeros

```java
for (int num : nums) {
    if (num != 0){
        prod *= num;
    } else {
        zeroCount++;
    }
}
```

We scan the array once.

#### Case A: `num != 0`

Multiply into `prod`.

#### Case B: `num == 0`

We **don’t multiply** (would make product 0 forever).
Instead, just increment `zeroCount`.

---

### ###  If More Than One Zero

```java
if (zeroCount > 1) {
    return new int[nums.length];
}
```

If there are **2 or more zeros**, every product except self will include at least one zero.

Example: `[1, 0, 3, 0]`

Any index you remove, at least one zero remains → product = **0**.

So we return an array of default `0`s.

---

### ###  Create Result Array

```java
int[] res = new int[nums.length];
```

This will store the final answers.

---

### ###  Second Loop — Fill Result

```java
for (int i = 0; i < nums.length; i++) {
    if (zeroCount > 0) {
        res[i] = (nums[i] == 0) ? prod : 0;
    } else {
        res[i] = prod / nums[i];
    }
}
```

We now handle **two big scenarios**:

---

###  Case 1: There is **one zero**

```java
if (zeroCount > 0)
```

Only one index can have a non-zero result — the index where the zero is.

#### If current element is zero:

```java
res[i] = prod;
```

Why?
Because for that index, we multiply **all non-zero elements**.

Example: `[1, 2, 0, 4]`
Product of non-zero = `1×2×4 = 8`
So output = `[0, 0, 8, 0]`

#### If current element is not zero:

```java
res[i] = 0;
```

Because the product except self includes that one zero.

---

###  Case 2: **No zeros**

```java
else {
    res[i] = prod / nums[i];
}
```

Now division works safely.

We already have:

[
prod = nums[0] \times nums[1] \times ... \times nums[n-1]
]

So:

[
res[i] = \frac{prod}{nums[i]}
]

Example: `[1, 2, 4, 6]`
Total product = 48

| i | nums[i] | res[i] = 48 / nums[i] |
| - | ------- | --------------------- |
| 0 | 1       | 48                    |
| 1 | 2       | 24                    |
| 2 | 4       | 12                    |
| 3 | 6       | 8                     |

---

### ###  Return Result

```java
return res;
```

---

##  Time & Space Complexity

| Metric | Value                                   |
| ------ | --------------------------------------- |
| Time   | **O(n)** (two passes through array)     |
| Space  | **O(1)** extra (excluding output array) |

---

##  Why This Doesn't Meet the Follow-Up

You used:

```java
res[i] = prod / nums[i];
```

The follow-up requires **no division**, which is solved using **prefix and suffix products** instead.

---

##  Summary

The solution:

* Handles zeros (the tricky part)
* Avoids division errors
* Works in O(n) time
* Uses constant extra space


