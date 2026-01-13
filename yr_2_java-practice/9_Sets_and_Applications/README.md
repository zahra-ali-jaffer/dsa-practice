# Java Sets and Bingo Simulation

This file contains explanations, code examples, and discussions on **sets in Java**, including custom implementations and their usage in a Bingo simulation. It also covers the **Java Collections Framework (JCF) Set interfaces and classes**.

---

## Overview of Sets

* **Unbounded or bounded:** Depends on implementation (arrays → bounded, linked structures → potentially unbounded).
* **Unorganised:** Unlike stacks or queues, sets do not follow a specific order.
* **Unique elements:** Sets do not allow duplicates.
* **Access:** No "first-in" or "last-in" access; query or iterate freely.

**Typical time complexity:** `O(n)` for most array/linked-list implementations.

---

## Set Operations

Common actions on sets include:

* `add()`
* `addAll()`
* `remove()`
* `removeRandom()` *(custom method, not in JCF)*
* `removeAll()`
* `retainAll()`
* `union()` *(custom method, not in JCF)*
* `intersection()` *(custom method, not in JCF)*
* `difference()` *(custom method, not in JCF)*
* `contains()`
* `equals()`
* `isEmpty()`
* `size()`
* `iterator()`
* `toString()`
* `isSubset()` *(custom method, not in JCF)*
* `copy()`

> Sets are especially useful for **membership testing** and **removing duplicates**.

---

## Random Selection from a Set

Example of a custom `pickRandom()` method:

```java
public T pickRandom() {
    if (isEmpty()) throw new IllegalStateException("pickRandom: set empty!");
    java.util.Random rdm = new java.util.Random();
    int choice = rdm.nextInt(count); // random index between 0 and count-1
    return contents[choice];
}
```

**Explanation:**

1. **Check empty set:** Prevents out-of-bounds errors.
2. **Random index generation:** Uses `Random.nextInt(count)`.
3. **Return element:** Access array at that index for constant-time selection.

**Time complexity:** `O(1)` when using an array-backed set.

---

## Bingo Example

### `BingoBall` Class

```java
public class BingoBall { 
    private char letter;
    private int number;
    
    public BingoBall(int num) {
        this.number = num;
        if (num <= 15) letter = 'B';
        else if (num <= 30) letter = 'I';
        else if (num <= 45) letter = 'N';
        else if (num <= 60) letter = 'G';
        else letter = 'O';
    }

    public String toString() { return (letter + " " + number); }
}
```

**Notes:**

* Each ball has a **number and letter** (B-I-N-G-O) based on range.
* `toString()` allows convenient printing like `"B 7"`.

**Doubt Clarification:**

> **Question:** If `num <= 30`, isn’t 15 and under also qualified?
>
> **Answer:** No, because of the `if … else if` chain:
>
> ```java
> if (num <= 15) letter = 'B';
> else if (num <= 30) letter = 'I';
> ```
>
> Numbers ≤ 15 satisfy the first condition, so the `else if` never runs. Only numbers 16–30 get `'I'`. The ordering ensures **no overlap between ranges**.

---

### `Bingo` Class

```java
public class Bingo {
    public static void main(String[] args) {
        final int NUM_BALLS = 75;
        final int NUM_PULLS = 10;

        ArraySet<BingoBall> bingoSet = new ArraySet<>();
        BingoBall ball;

        // Create all balls
        for (int ballNum = 1; ballNum <= NUM_BALLS; ballNum++) {
            ball = new BingoBall(ballNum);
            bingoSet.add(ball);
        }

        System.out.println("We have " + bingoSet.size() + " balls in play!\n");

        // Draw balls randomly
        for (int pull = 1; pull <= NUM_PULLS; pull++) {
            ball = bingoSet.removeRandom(); 
            System.out.println(ball); // calls toString()
        }
    }
}
```

**Explanation:**

* `ArraySet` ensures **uniqueness** of balls.
* `removeRandom()` simulates **drawing a Bingo ball**.
* Repeated draws reduce the size of the set, just like a real Bingo game.

---

## Java Collections Framework (JCF) Sets

### Interfaces and Classes

* `Set` → basic unordered set, minimal methods like `add()`, `remove()`, `contains()`.
* `SortedSet` → elements are sorted.
* `NavigableSet` → extends `SortedSet` with navigation methods like `floor()` and `ceiling()`.
* `AbstractSet` → skeleton implementation for creating new sets.
* `HashSet` → hash table-based; fast operations, unordered.
* `TreeSet` → tree-based; elements sorted, O(log n) operations.
* `LinkedHashSet` → hash table + linked list; preserves insertion order, faster iteration than HashSet.

**Important Note:**

The official `Set` interface **does not have**:

* `isSubset()`
* `removeRandom()`
* `union()`, `intersection()`, `difference()`

> You can implement union/intersection/difference manually using `addAll()`, `retainAll()`, `removeAll()`, but these are **not built-in methods**.

---

## Key Takeaways

1. **Custom sets** like `ArraySet` are useful for teaching or simulations because they can include **extra convenience methods**.
2. **JCF sets** are minimal, standardized, and fast but only include **core set operations**.
3. Sets are perfect for **unique collections, random selection, and membership testing**.
4. Conditional chains like `if … else if … else` ensure **proper range assignment** without overlap.
5. Understanding **differences between custom sets and JCF sets** is essential when writing Java applications.


