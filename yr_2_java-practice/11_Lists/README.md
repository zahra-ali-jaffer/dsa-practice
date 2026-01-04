# Doubly Linked Lists in Java

This file demonstrates the design, implementation, and usage of a **Doubly Linked List** in Java, including iterator support and its role in implementing different **List Abstract Data Types (ADTs)**.

---

## Why Doubly Linked Lists?

A doubly linked list stores references to both the **next** and **previous** nodes. This structure provides several advantages over singly linked lists.

### Advantages

* Easier support for the `Iterator.remove()` operation
* Forward and backward traversal
* Lists can be processed in reverse order
* Efficient `removeFirst()` and `removeLast()` operations (O(1))

Example pointer update during removal:

```java
cursor.getPrevious().setNext(cursor.getNext());
cursor.getNext().setPrevious(cursor.getPrevious());
```

Failing to update both links may leave nodes incorrectly connected, resulting in invalid backward references.

---

### Disadvantages

* Additional memory overhead due to extra node references
* Increased time overhead when updating both next and previous links
* Three elements must be tracked per node (data, next, previous)

---

## Modelling Doubly Linked Lists

A doubly linked list implementation requires the following components:

### DoubleNode

Represents a node within a doubly linked list.

* Stores the element
* Reference to the next node
* Reference to the previous node

---

### DoubleList (Abstract Class)

Models the list structure.

* Abstract and cannot be instantiated
* All methods are concrete to reduce the burden on implementers
* Intended to be extended by implementations of ordered, indexed, or unordered lists

Most methods are `protected` and intended for internal use by subclasses.
Utility methods such as `isEmpty()` and `size()` are public for client access.

---

### DoubleIterator (Inner Class)

Provides iterator support for the list.

* Implements `Iterator<T>`
* Has direct access to `DoubleList` fields
* Fully supports the `remove()` operation

---

## Rules for Iterator.remove()

The `remove()` method in `DoubleIterator` follows these rules:

* Removes the element returned by the most recent call to `next()`
* `remove()` cannot be called before `next()`
* `remove()` cannot be called twice without an intervening `next()` call

These rules ensure correct and safe modification of the list during iteration.

---

## The List Abstract Data Type (ADT)

Lists are linear collections that provide greater flexibility than stacks and queues, allowing insertion and removal at any position.

### Types of Lists

#### Ordered (Sorted) Lists

* Elements are arranged according to an inherent order
* Examples include alphabetical names, numerical scores, and chronological events
* Custom ordering can be defined using `compareTo()` for user-defined objects

---

#### Indexed Lists

* Elements accessed by numerical index
* No inherent ordering
* Often implemented using arrays

A helper method such as `findNode(int index)` is commonly used.

---

#### Unordered Lists

* A special case of indexed lists
* Positions are defined relative to other elements
* Examples include adding before or after a specific element or at the front or rear

---

## Implementing Lists with Arrays

Although linked structures are commonly used, lists can also be implemented using arrays.

### Important Limitation

Circular array techniques cannot be used for lists because lists allow removal from the middle, unlike queues.

---

### Indexed Lists Using Arrays

* Elements stored contiguously
* A size field tracks the next available index
* Capacity expanded as needed

---

### Ordered Lists Using Arrays

Directly extending `ArrayList` exposes methods that may violate ordering constraints.

A wrapper class can be used to expose only permitted operations while hiding the underlying structure.

---

## Performance Comparison

| Operation     | Doubly Linked List | Array-based |
| ------------- | ------------------ | ----------- |
| get()         | O(n)               | O(1)        |
| set()         | O(n)               | O(1)        |
| removeFirst() | O(1)               | O(n)        |
| removeLast()  | O(1)               | O(1)        |
| addFirst()    | O(1)               | O(n)        |
| addLast()     | O(1) / O(n)        | O(1) / O(n) |
| remove()      | O(n)               | O(n)        |
| add()         | O(n)               | O(n)        |

---

## Key Takeaways

* Doubly linked lists trade memory usage for flexibility
* Iterator removal is simpler than in singly linked lists
* Abstract classes help enforce structure while reducing duplication
* Choice of list implementation depends on use case




