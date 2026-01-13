# Queues & Priority Queues in Java

This file contains notes, explanations, and implementations of **queue data structures** in Java, based on array-based, linked, circular, and priority queue designs. It also connects these implementations to the **Java Collections Framework (JCF)** and discusses **time complexity trade-offs**.

---

## Queue Fundamentals

A **queue** follows **FIFO (First-In, First-Out)** ordering:

* Elements are added at the **rear** (`enqueue`)
* Elements are removed from the **front** (`dequeue`)

Queues are commonly used in:

* Scheduling systems
* Simulations (e.g. cashier/customer models)
* Producer–consumer problems

---

## Circular Array Queue

### Key Idea

A **circular array queue** treats the array as a ring using modulo arithmetic. This avoids shifting elements when dequeuing.

```java
rear = (rear + 1) % queue.length;
front = (front + 1) % queue.length;
```

### Why Circular?

* Prevents wasted space at the front of the array
* Maintains O(1) dequeue operations
* Requires resizing when full (unbounded version)

### Time Complexity (Unbounded)

| Operation | Best Case | Worst Case    |
| --------- | --------- | ------------- |
| enqueue   | O(1)      | O(n) (resize) |
| dequeue   | O(1)      | O(1)          |

---

## Linked Structure Queue

### Key Idea

A **linked queue** uses nodes instead of arrays:

* Front pointer → first node
* Rear pointer → last node

No resizing or shifting is required.

### Time Complexity

| Operation | Best Case | Worst Case |
| --------- | --------- | ---------- |
| enqueue   | O(1)      | O(1)       |
| dequeue   | O(1)      | O(1)       |

This is the most stable and predictable queue implementation.

---

## Linear Array Queue (Unbounded)

### Key Idea

A **linear array queue** stores elements contiguously. When elements are dequeued, the remaining elements must be shifted left.

### Time Complexity

| Operation | Best Case | Worst Case    |
| --------- | --------- | ------------- |
| enqueue   | O(1)      | O(n) (resize) |
| dequeue   | O(n)      | O(n)          |

This implementation is inefficient for frequent dequeues.

---

## Summary of Queue Implementations

| Implementation             | enqueue     | dequeue |
| -------------------------- | ----------- | ------- |
| Linked Queue               | O(1)        | O(1)    |
| Linear Array (Unbounded)   | O(1) → O(n) | O(n)    |
| Circular Array (Unbounded) | O(1) → O(n) | O(1)    |

---

## Priority Queues

### Custom Priority Queue (Linked)

In a linked priority queue, elements are inserted in **priority order**.

* Higher priority elements appear closer to the front
* FIFO order is preserved among equal priorities

#### Time Complexity

* `enqueue()` → **O(n)** (must find correct position)
* `dequeue()` → **O(1)**

> With arrays, this becomes even worse because elements must be shifted.

---

## Bucket-Based Priority Queue

Another approach uses **multiple queues**, one per priority level:

```text
Priority 5 → Queue
Priority 4 → Queue
Priority 3 → Queue
...
```

* `enqueue()` → O(1)
* `dequeue()` → O(p), where `p` is the number of priority levels

FIFO order is preserved **within each priority**.

---

## Java Collections Framework (JCF)

### Queue Interface

`java.util.Queue<E>` defines five core methods:

* `add(e)`
* `offer(e)`
* `remove()`
* `poll()`
* `element()`
* `peek()`

### Common Implementations

* `LinkedList<E>`
* `ArrayDeque<E>`
* `PriorityQueue<E>`
* `LinkedBlockingQueue<E>`
* `ArrayBlockingQueue<E>`

---

## BlockingQueue Interface

`java.util.concurrent.BlockingQueue` extends Queue with **thread coordination**:

* Consumers wait if the queue is empty
* Producers wait if the queue is full

### Implementations

* `ArrayBlockingQueue<E>` → bounded, array-based
* `LinkedBlockingQueue<E>` → optionally bounded, linked
* `DelayQueue<E>` → elements removed only after delay expires

Used heavily in **producer–consumer systems**.

---

## TransferQueue Interface

A more specialized version of `BlockingQueue`:

* Producers can wait until consumers receive elements
* Supports message-passing systems

### Implementation

* `LinkedTransferQueue<E>` (unbounded, linked)

> Thankfully, this is mostly for advanced concurrency use cases.

---

## Deque (Double-Ended Queue)

A `Deque` supports insertion and removal at **both ends**.

### Queue Equivalents

| Queue Method | Deque Method  |
| ------------ | ------------- |
| add(e)       | addLast(e)    |
| offer(e)     | offerLast(e)  |
| remove()     | removeFirst() |
| poll()       | pollFirst()   |
| element()    | getFirst()    |
| peek()       | peekFirst()   |

### Implementations

* `ArrayDeque`
* `LinkedBlockingDeque`
* `LinkedList`

🚧 **Important Rule of Thumb**

> `ArrayDeque` is faster than:
>
> * `Stack` when used as a stack
> * `LinkedList` when used as a queue

---

## JCF PriorityQueue

Java’s built-in `PriorityQueue`:

* Orders elements using **natural ordering** (`compareTo()`)
* Not FIFO for equal elements
* Does **not** use explicit priorities

Example:

* Numbers → numerical order
* Strings → alphabetical order

> Elements with equal priority may be returned **arbitrarily**.

So yes… it’s convenient, but **not equivalent to our priority queues**.

---

## Final Takeaways

* Circular arrays fix dequeue inefficiencies
* Linked queues provide consistent O(1) performance
* Priority queues trade insertion cost for fast removal
* JCF offers powerful, optimized queue implementations
* `ArrayDeque` is the go-to general-purpose queue

---



