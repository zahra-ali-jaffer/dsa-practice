# Recursion vs. Iteration

This README summarises key concepts, examples, and code patterns related to **recursion**, **iteration**, and their use in Java, based on linked structures, classic algorithms, and performance analysis.

---

## Core Principle

> **Every recursive solution has a corresponding iterative solution.**

* Recursion relies on the **call stack** to manage state.
* Iteration relies on **loops and variables** to manage state.
* Both are computationally equivalent, but differ in clarity, performance, and memory usage.

---

## Recursion

### Characteristics

* A method calls itself
* Each call creates a **new execution environment (stack frame)**
* Requires:

  * A **base case** (stopping condition)
  * A **recursive case** (problem reduction)

### Advantages

* Often more **elegant and readable**
* Naturally fits recursive structures:

  * Linked lists
  * Trees
  * Divide-and-conquer algorithms

### Disadvantages

* Additional overhead from method calls
* Higher memory usage (call stack)
* Risk of **stack overflow**
* Can be inefficient with overlapping subproblems

---

## Iteration

### Characteristics

* Uses loops (`for`, `while`)
* Explicitly manages state with variables
* No extra stack frames

### Advantages

* Usually **faster**
* Uses **constant or minimal memory**
* Better suited for linear problems

### Disadvantages

* Can be more verbose
* Less intuitive for recursive data structures

---

## Linear Linked Structures with Recursion

### Recursive Node Design

```java
public class LinearNode<T> {
  private LinearNode<T> next;
  private T element;

  public int size() {
    if (next == null) return 1;
    else return 1 + next.size();
  }

  public String toString() {
    if (next == null) return element.toString();
    else return element.toString() + ", " + next.toString();
  }

  public LinearNode<T> copy() {
    LinearNode<T> current = new LinearNode<>(element);
    if (next != null) current.next = next.copy();
    return current;
  }
}
```

* Each node handles **itself** and delegates the rest to `next`
* Clean, elegant recursion

---

## LinkedList Delegating to Nodes

```java
public class LinkedList<T> {
  private LinearNode<T> front;

  public String toString() {
    String result = "{";
    if (front != null) result += front.toString();
    result += "}";
    return result;
  }

  public LinkedList<T> copy() {
    LinkedList<T> result = new LinkedList<>();
    if (front != null) result.front = front.copy();
    return result;
  }
}
```

* `LinkedList` handles empty cases and formatting
* Nodes handle recursion

---

## Recursive Helper Method Pattern (No Node Modification)

```java
public class LinkedList<T> {
  private LinearNode<T> front;

  public LinkedList<T> copy() {
    LinkedList<T> result = new LinkedList<>();
    if (front != null) result.front = copy(front);
    return result;
  }

  private LinearNode<T> copy(LinearNode<T> node) {
    LinearNode<T> current = new LinearNode<>(node.getElement());
    if (node.getNext() != null)
      current.setNext(copy(node.getNext()));
    return current;
  }
}
```


* Recursion stays inside `LinkedList`
* Useful when node classes cannot be modified

---
## Comparison of Approaches

When working with linked structures and recursion, there are multiple valid design approaches. The main difference is **where the recursion lives** and **who controls traversal**.

| Approach                    | Where recursion lives         | Who controls traversal | Pros                                                               | Cons                                                     |
| --------------------------- | ----------------------------- | ---------------------- | ------------------------------------------------------------------ | -------------------------------------------------------- |
| **Node-based recursion**    | `LinearNode`                  | Each node              | Very elegant, mirrors data structure, minimal code in `LinkedList` | Requires modifying node class, logic spread across nodes |
| **Helper-method recursion** | `LinkedList` (private helper) | The list               | Keeps all logic in `LinkedList`, works when nodes can’t be changed | Slightly more code, less object-oriented                 |
| **Iterative**               | `LinkedList`                  | The list               | Fastest, no recursion overhead, constant stack space               | More verbose, less natural for recursive structures      |

---

## Tower of Hanoi (Classic Recursive Algorithm)

```java
private void moveTower(int numDisks, int start, int end, int temp) {
  if (numDisks == 1)
    moveOneDisk(start, end);
  else {
    moveTower(numDisks - 1, start, temp, end);
    moveOneDisk(start, end);
    moveTower(numDisks - 1, temp, end, start);
  }
}
```

### Key Points

* Each call makes **two recursive calls**
* Total moves: `2^n - 1`
* Time complexity: **O(2^n)**
* Example of **double recursion**

---

## Recursive Fibonacci (Inefficient)

```java
public int fib(int n) {
  if (n == 0 || n == 1) return 1;
  return fib(n - 1) + fib(n - 2);
}
```

### Characteristics

* Matches the mathematical definition
* Recomputes the same values repeatedly
* Time complexity: **O(2^n)**
* Space complexity: **O(n)**

---

## Iterative Fibonacci (Efficient)

```java
public int fib(int n) {
  if (n == 0 || n == 1) return 1;

  int f0 = 1, f1 = 1, result = 1;
  for (int i = 2; i <= n; i++) {
    result = f0 + f1;
    f0 = f1;
    f1 = result;
  }
  return result;
}
```

### Characteristics

* Linear time: **O(n)**
* Constant space: **O(1)**
* No recursion overhead

---

## Recursion vs. Iteration Summary

| Aspect      | Recursion                | Iteration       |
| ----------- | ------------------------ | --------------- |
| Readability | Often higher             | Often lower     |
| Performance | Slower                   | Faster          |
| Memory      | More (stack)             | Less            |
| Best for    | Trees, linked structures | Linear problems |

---

## Key Takeaways

* Every recursive algorithm can be rewritten iteratively
* Recursion trades performance for clarity
* Iteration trades simplicity for efficiency
* Choose based on:

  * Problem structure
  * Input size
  * Performance requirements


