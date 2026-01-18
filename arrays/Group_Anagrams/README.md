# Group Anagrams (HashMap + Sorting Approach)

## Problem Statement

Given an array of strings `strs`, group the anagrams together.

You can return the answer in any order.

Two strings are anagrams if they contain the **same characters in the same frequency**, but possibly in a different order.

---

## Understanding the Problem

We are asked to:

1. Identify which strings are anagrams of each other.
2. Group all anagrams together into lists.
3. Return a list of these groups.

Example:

```
Input: ["eat","tea","tan","ate","nat","bat"]

Output:
[
  ["eat","tea","ate"],
  ["tan","nat"],
  ["bat"]
]
```

---

## What Is an Anagram?

Two words are anagrams if:

* They have the same length
* They contain the same characters
* Each character appears the same number of times

Example:

* "eat" and "tea" → anagrams
* "eat" and "tan" → not anagrams

---

## Key Idea of the Solution

Anagrams become **identical** when their characters are sorted.

Example:

```
"eat" → "aet"
"tea" → "aet"
"ate" → "aet"
```

We use this sorted string as a **key** to group original strings together.

---

## Why Use a Map?

### Role of the Map

The map groups strings by their sorted character form.

* Key: sorted version of the string
* Value: list of original strings that match this key

Example:

```
Key     Value
"aet" → ["eat", "tea", "ate"]
"ant" → ["tan", "nat"]
"abt" → ["bat"]
```

The map allows us to:

* Quickly check if a group already exists
* Add strings to the correct anagram group

---

## Why Sorting Works

Sorting ensures:

* All anagrams produce the same key
* Non-anagrams produce different keys

Without sorting, we would need a more complex character-count comparison.

---

## Explanation of the Key Line

```java
char[] chars = s.toCharArray();
Arrays.sort(chars);
String key = new String(chars);
```

Explanation:

* Convert the string into a character array
* Sort the characters alphabetically
* Convert it back into a string
* Use this sorted string as a unique identifier for anagram groups

---

## Time and Space Complexity

* Time Complexity: `O(n * m log m)`

  * `n` = number of strings
  * `m` = maximum length of a string
  * Sorting each string dominates the cost

* Space Complexity: `O(n * m)`

  * For storing keys and grouped strings

---

## Alternative Approach (Character Count Key)

Instead of sorting, we can:

* Count frequency of characters (26 lowercase letters)
* Build a frequency-based key

This reduces sorting cost but adds implementation complexity.

---

## Key Takeaways

* Anagrams share the same sorted character sequence.
* HashMap is ideal for grouping.
* Sorting transforms the problem into a simple key-matching task.
* The order of output groups does not matter.

