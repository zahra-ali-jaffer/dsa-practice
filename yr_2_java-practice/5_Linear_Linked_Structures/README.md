# Linear Linked Structures (Stacks, Queues, and Sets)

## Overview

Linked structures are **dynamic data structures** built from nodes that reference one another. Unlike arrays, they do not require a fixed size at creation time and can grow or shrink as needed during execution.

Linked structures can be:

* **Linear** (Stacks, Queues, Sets, Linked Lists)
* **Non-linear** (Trees, Graphs)

> ⚠️ Note: **Doubly-linked lists** are still classified as *linear* structures because traversal is limited to forward and backward movement only.

---

## Self-Referential Structures

A linked structure is **self-referential**, meaning each node contains:

* **Data** (the element)
* **A reference to another node of the same type**

Example (`LinearNode<T>`):

```java
public class LinearNode<T> {
    private T element;
    private LinearNode<T> next;
}
```

This self-reference is what allows nodes to form chains.

---

## Advantages and Disadvantage of Linked Structures

### Advantages

* ✔ **Flexible size** – can grow and shrink dynamically
* ✔ **No wasted storage** from pre-allocated array cells
* ✔ **No need to check capacity** during insert operations (`push`, `enqueue`, `add`)

### Disadvantage

* ❌ **Higher memory overhead**:

  * Each node stores **two references** (data + link)
  * Array cells store only **one reference**
  * For equal-sized collections, arrays use less memory

---

## Linked Stack (LIFO)

A **stack** follows **Last-In, First-Out (LIFO)** order.

### Implementation

* Implemented using a **linked list**
* Only one pointer is required: `top`
* `LinkedStack<T>` implements `StackADT<T>`

### Operations

| Operation | Description      | Time Complexity |
| --------- | ---------------- | --------------- |
| `push()`  | Insert at top    | O(1)            |
| `pop()`   | Remove from top  | O(1)            |
| `peek()`  | View top element | O(1)            |

All operations occur at the **front of the list**, so no traversal is required.

---

## Linked Queue (FIFO)

A **queue** follows **First-In, First-Out (FIFO)** order.

### Key Characteristics

* Uses a linked list
* No shifting of elements required
* Maintains two pointers:

  * `front` → where elements are removed
  * `rear` → where elements are added

The final node’s `next` reference is always `null`.

### Do We Need `count`?

Technically, **no**.

But keeping `count` allows:

* `size()` to run in **O(1)** instead of **O(n)**
* Cleaner and more efficient design

 Recommended to include `count`.

### Operations

| Operation   | Description       | Time Complexity |
| ----------- | ----------------- | --------------- |
| `enqueue()` | Add at rear       | O(1)            |
| `dequeue()` | Remove from front | O(1)            |
| `peek()`    | View front        | O(1)            |

---

## Stack vs Queue Comparison

| Feature   | Stack        | Queue                 |
| --------- | ------------ | --------------------- |
| Order     | LIFO         | FIFO                  |
| Pointers  | One (`top`)  | Two (`front`, `rear`) |
| Insertion | Push at top  | Enqueue at rear       |
| Removal   | Pop from top | Dequeue from front    |

---

## Time Complexity Summary (Stacks & Queues)

All primary operations in **linked stacks and queues** are:

>  **O(1)** time complexity

This is one of the major advantages of linked linear structures.

---

## Linked Set

A **set** stores **unique elements only**, and **order does not matter**.

### Implementation Details

* Backed by a singly linked list
* No duplicate elements allowed
* Elements are often inserted at the **front** for efficiency

### Performance

| Operation    | Time Complexity               |
| ------------ | ----------------------------- |
| `contains()` | O(n)                          |
| `add()`      | O(n) (due to duplicate check) |
| `remove()`   | O(n)                          |

For the first time with linked structures:

> ⚠️ **LinkedSet operations are not O(1)**

### Set Operations

| Operation        | Time Complexity |
| ---------------- | --------------- |
| `union()`        | O(n²)           |
| `intersection()` | O(n²)           |
| `difference()`   | O(n²)           |

This is because each element comparison requires traversal.

---

## Iterators and Traversal

Linked structures typically provide **iterators** that:

* Traverse node-by-node
* Maintain a cursor reference
* Cannot safely implement `remove()` without access to the structure’s `count`

---

## Final Summary

* Linked structures are **dynamic and flexible**
* Built using **self-referential nodes**
* Excellent for stacks and queues with **O(1)** operations
* Linked sets trade performance for simplicity
* Arrays are more memory-efficient, but less flexible

Linked linear structures form the foundation for many advanced data structures and are essential for understanding dynamic memory-based design.
