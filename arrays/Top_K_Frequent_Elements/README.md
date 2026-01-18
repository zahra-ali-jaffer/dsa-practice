# Top K Frequent Elements (Bucket Sort Approach)

## Problem Statement

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements in the array.

* The answer is guaranteed to be unique.
* The order of the output does not matter.

---

## Understanding the Problem

We are asked to:

1. Count how many times each number appears in the array.
2. Identify the `k` numbers that appear the most frequently.
3. Return those numbers efficiently.

A direct approach would involve sorting elements by frequency, but that would take `O(n log n)` time.
To optimise this, we use a **HashMap + Bucket Sort** approach that runs in `O(n)` time.

---

## Why Use Both Map and Bucket?

### Role of the Map

The map is used to **count frequencies**.

* Key: number from the array
* Value: how many times it appears

Example:

```
nums = [1,1,1,2,2,3]
freqMap = {1=3, 2=2, 3=1}
```

The map answers the question:
“How many times does each number occur?”

However, the map alone does not help us efficiently find the top `k` frequent elements.

---

### Role of the Bucket Array

The bucket array is used to **group numbers by frequency**.

* Index of the bucket = frequency
* Value at that index = list of numbers with that frequency

This allows us to:

* Avoid sorting
* Directly access elements with highest frequency first

---

## Why Bucket Array Size Is `nums.length + 1`

```java
List<Integer>[] buckets = new List[nums.length + 1];
```

Explanation:

* The maximum possible frequency of any number is `nums.length`
* We want to use frequency directly as an index
* Array indices start from 0, so we add `+1`

Structure:

```
buckets[1] → numbers appearing once
buckets[2] → numbers appearing twice
buckets[3] → numbers appearing three times
...
```

Each bucket stores a list because multiple numbers can have the same frequency.

---

## Explanation of Frequency Count Line

```java
freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
```

This line means:

* If `num` already exists in the map, get its current count
* If it does not exist, start with 0
* Add 1 because the number has been seen again
* Store the updated value back in the map

This is a concise way of counting frequencies.

---

## Why We Traverse Buckets Backwards

```java
for (int i = buckets.length - 1; i >= 0 && index < k; i--)
```

Explanation:

* Higher index = higher frequency
* We want the most frequent elements first
* Start from the largest possible frequency
* Move downwards
* Stop once we have collected `k` elements (`index < k`)

This ensures efficiency and correctness.

---

## Complete Java Solution

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Step 1: Count frequency using Map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            // Get the current count of num (or 0 if it doesn’t exist),
            // add 1, and store it back in the map.
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create Bucket Array
        // Index represents frequency
        // Each index holds a list of numbers with that frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        // Step 3: Collect top k frequent elements
        int[] result = new int[k];
        int index = 0;

        // Start from the highest frequency bucket and move down
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[index++] = num;
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
```

---

## Time and Space Complexity

* Time Complexity: `O(n)`
* Space Complexity: `O(n)`

This is optimal for this problem.

---

## Key Takeaways

* Use a HashMap to count frequencies.
* Use Bucket Sort to avoid sorting.
* Bucket index represents frequency.
* Traverse buckets from highest to lowest to get top `k`.

---

