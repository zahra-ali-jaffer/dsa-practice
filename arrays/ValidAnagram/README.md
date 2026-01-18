# Valid Anagram (Sorting Approach)

## Problem Statement

Given two strings `s` and `t`, return `true` if the two strings are **anagrams** of each other.
Otherwise, return `false`.

An anagram is a string that contains the **exact same characters** as another string, but the **order of the characters can be different**.

---

## Example

### Input

```
s = "racecar"
t = "carrace"
```

### Output

```
true
```

Explanation:
Both strings contain the same characters with the same frequencies.

---

## Understanding the Problem

We are asked to:

1. Compare two strings.
2. Check whether they contain the same characters.
3. Ensure each character appears the same number of times in both strings.

Order does not matter, but **frequency does**.

---

## Key Idea of the Solution

If two strings are anagrams:

* They must have the same length.
* When their characters are sorted, the resulting strings must be identical.

---

## Why Length Check Is Important

```java
if (s.length() != t.length()) return false;
```

Explanation:

* Anagrams must contain the same number of characters.
* If lengths differ, they cannot be anagrams.
* This allows an early return and improves efficiency.

---

## Why Sorting Works

Sorting rearranges characters into a standard order.

Example:

```
"racecar" → [a, a, c, e, r, r]
"carrace" → [a, a, c, e, r, r]
```

If two sorted character arrays are identical, the strings are anagrams.

---

## Explanation of Key Lines

```java
char[] a = s.toCharArray();
char[] b = t.toCharArray();
```

* Converts both strings into character arrays.
* Allows sorting and direct comparison.

---

```java
Arrays.sort(a);
Arrays.sort(b);
```

* Sorts both character arrays alphabetically.
* Ensures anagrams produce identical arrays.

---

```java
return Arrays.equals(a, b);
```

* Compares both sorted arrays.
* Returns `true` if they are exactly the same.

---

## Complete Java Solution

```java
import java.util.Arrays;

class ValidAnagram {
    static boolean isAnagram(String s, String t) {

        // Step 1: Check length
        if (s.length() != t.length()) return false;

        // Step 2: Convert strings to char arrays
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        // Step 3: Sort both arrays
        Arrays.sort(a);
        Arrays.sort(b);

        // Step 4: Compare sorted arrays
        return Arrays.equals(a, b);
    }
}
```

---

## Time and Space Complexity

### Time Complexity

```
O(n log n)
```

* Sorting dominates the runtime
* `n` is the length of the strings

---

### Space Complexity

```
O(n)
```

* Character arrays are used for sorting

---

## Alternative Approach (Frequency Count)

Instead of sorting:

* Count occurrences of each character
* Compare frequency arrays

This approach runs in `O(n)` time and is more optimal, but slightly more complex.

---

## Key Takeaways

* Length check prevents unnecessary work.
* Sorting simplifies anagram comparison.
* Arrays.equals ensures exact character match.
* Suitable and readable solution for interviews and exams.

---

