## Problem Recap

Given an array `nums`, return an array `res` where:

[
res[i] = \text{product of all elements in nums except nums[i]}
]

Example:
`[1, 2, 4, 6]` → `[48, 24, 12, 8]`

---

## Core Difficulty

Division normally makes this easy:

[
res[i] = \frac{\text{total product}}{nums[i]}
]

But zeros break this logic. Your solution is built entirely around handling zeros safely.

---

## High Level Strategy

1. Compute the product of all **non-zero** elements.
2. Count how many zeros are present.
3. Use different formulas depending on the number of zeros.

---

## Step 1: Variable Initialization

```java
int prod = 1, zeroCount = 0;
```

* `prod` stores the product of all non-zero elements.
* `zeroCount` counts how many zeros appear in the array.

---

## Step 2: First Pass Through the Array

```java
for (int num : nums) {
    if (num != 0){
        prod *= num;
    } else {
        zeroCount++;
    }
}
```

Two things happen in one pass:

### When `num != 0`

We multiply it into `prod`.

### When `num == 0`

We do not multiply, because that would make the product permanently zero. Instead, we just count it.

After this loop:

* `prod` = product of all non-zero numbers
* `zeroCount` = number of zeros in the array

---

## Step 3: Case of More Than One Zero

```java
if (zeroCount > 1) {
    return new int[nums.length];
}
```

If there are 2 or more zeros:

For any index you remove, at least one zero remains in the multiplication. So every result is 0.

A new `int` array is automatically filled with zeros, which is correct.

---

## Step 4: Create Result Array

```java
int[] res = new int[nums.length];
```

This will store the final answer.

---

## Step 5: Second Pass to Fill Results

```java
for (int i = 0; i < nums.length; i++) {
    if (zeroCount > 0) {
        res[i] = (nums[i] == 0) ? prod : 0;
    } else {
        res[i] = prod / nums[i];
    }
}
```

Now we handle two main scenarios.

---

## Scenario A: Exactly One Zero

`zeroCount > 0` and we already handled the case where it is greater than 1.

So there is exactly one zero.

### If current element is the zero

```java
res[i] = prod;
```

Since `prod` contains all non-zero elements, this is the correct product except self.

### If current element is not zero

```java
res[i] = 0;
```

Because the product except self includes the single zero in the array.

---

## Scenario B: No Zeros

```java
res[i] = prod / nums[i];
```

Now division is safe.

`prod` is the product of all elements in the array.

Dividing by `nums[i]` removes the contribution of the current element, leaving the product of all others.

---

## Step 6: Return

```java
return res;
```

---

## Complexity

Time complexity: O(n)
Two linear passes.

Space complexity: O(1) extra space
Only a few variables are used besides the output array.

---

## Why This Is Not the Follow-Up Solution

The follow-up explicitly forbids division. This solution relies on division when there are no zeros, so it does not satisfy that constraint.

The division-free approach uses prefix and suffix products instead.

---

## Final Understanding

This solution works because it separates the problem into three mathematical cases:

1. More than one zero → all outputs are zero
2. Exactly one zero → only that position gets the product of non-zero elements
3. No zeros → use total product divided by current element

That is the entire logic of the algorithm.
