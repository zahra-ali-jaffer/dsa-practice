# Trees, Binary Trees, and Binary Search Trees (BST)

This repository contains notes, explanations, and example implementations covering **tree data structures** in Java, including **Binary Trees**, **Binary Search Trees (BSTs)**, traversal algorithms, and performance analysis.

---

## Trees vs Graphs

**Main difference:**

* A **tree** has **no cycles**.
* A **graph** may contain cycles.

Trees are hierarchical, while graphs are more general network structures.

---

## Basic Tree Terminology

* **Node**: A location in the tree where an element is stored.
* **Empty Tree**: A tree with no nodes.
* **Root**: The base node of the tree; it has no parent.
* **Child**: A node directly below another node.

  * **Grandchild**: A child of a child.
* **Leaf**: A node with no children.
* **Internal Node**: A non-root node with at least one child.
* **Descendant**: Any node below a given node along a path to a leaf.
* **Ancestor**: Any node above a given node along the path from the root.
* **Siblings**: Nodes that share the same parent.
* **Depth / Level of a Node**: Number of nodes on the path from the root to the node.
* **Level of a Tree**: All nodes at a given depth.
* **Order of a Tree**: Maximum number of children per node.
* **Size of a Tree**: Total number of nodes in the tree.

### Height of a Tree

* An **empty tree** has size 0 and height 0.
* For non-empty trees, height can be defined as:

  1. The number of **nodes** on the longest path from root to leaf, or
  2. The number of **edges** on the longest path from root to leaf.

---

## Common Tree Operations

* Enumerating (traversing) elements
* Searching for an element
* Adding a new element
* Deleting an element
* **Pruning**: Removing a subtree
* **Grafting**: Attaching a subtree
* Finding the root of a node

> Example: In a language dictionary, lookup time can be considered **O(1)** in practice because words are short and trees are navigated quickly.

---

## Implementing Trees

Trees are typically implemented using **non-linear linked structures** arranged hierarchically.

### Why recursion?

* Tree structures are recursive by nature
* Recursive algorithms are cleaner, safer, and more natural
* Iterative versions are usually more complex and error-prone

---

## Binary Trees

A **binary tree** node consists of:

* A stored element
* A reference to a **left** child
* A reference to a **right** child

### BinaryTreeNode Implementation

```java
public static class BinaryTreeNode<E> {
    public E element;
    public BinaryTreeNode<E> left;
    public BinaryTreeNode<E> right;

    public BinaryTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public BinaryTreeNode(E element, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }
}
```

### Why this design?

* `static`: behaves like a top-level class, not tied to an instance
* `protected`: hidden from external clients but accessible to subclasses
* `public` fields: simplifies access within the tree class
* Maintains encapsulation without unnecessary getters/setters

**Minor drawback:**

* Generic type must be redeclared (`<E>` instead of `<T>`)

---

## Adding Elements to a Binary Tree

* Use methods like `addLeft(parent, child)` and `addRight(parent, child)`
* Steps:

  1. Find the parent node (using `find()`)
  2. Attach child to left or right
  3. Either replace existing child or throw an exception

---

## Removing Elements from a Binary Tree

* Define `removeLeftChild(parent)` and `removeRightChild(parent)`
* Steps:

  1. Find parent node
  2. Check child state:

     * **Leaf** → remove safely
     * **One child** → promote grandchild
     * **Multiple children** → prune subtree or throw exception

---

## Tree Traversal

Traversal means visiting every node in a systematic way.

### Pre-order (Depth-first)

1. Visit root
2. Traverse left subtree
3. Traverse right subtree

### In-order

1. Traverse left subtree
2. Visit root
3. Traverse right subtree

### Post-order

1. Traverse left subtree
2. Traverse right subtree
3. Visit root

### Level-order (Breadth-first)

1. Visit nodes level by level
2. Traverse left to right per level

---

## Level-Order Traversal (Breadth-First)

Implemented iteratively using a **FIFO queue**:

```java
Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
queue.add(root);

while (!queue.isEmpty()) {
    BinaryTreeNode<T> node = queue.remove();
    System.out.print(node.element + " ");

    if (node.left != null) queue.add(node.left);
    if (node.right != null) queue.add(node.right);
}
```

---

## Balanced Trees

### Perfect N-ary Trees

* All leaves at the same depth
* Every internal node has exactly N children
* Perfect binary tree of height `h` has:

```
2^h - 1 nodes
```

### Balanced Trees

* Leaf depths differ by at most 1
* Minimum possible height for given size
* Node count for height `h`:

```
2^(h-1) ≤ nodes ≤ 2^h - 1
```

### Degenerate Trees

* Each node has exactly one child
* Equivalent to a linked list
* Search time degrades to **O(n)**

---

## Binary Search Trees (BST)

A **BST** is a binary tree with ordering:

* Left subtree elements **precede** the node
* Right subtree elements **follow** the node

### Why BSTs?

* Efficient searching: **O(log₂ n)** when balanced
* Worst case (degenerate): **O(n)**

---

## Removing Elements from a BST

Steps:

1. Locate node using `find()`
2. Promote children correctly using `promote()`

### promote() rules

* No children → return `null`
* One child → return that child
* Two children:

  * Find **left-most node** of the right subtree
  * Promote it to the removed node’s position

---

## Time Complexity of BST Operations

| Operation | Balanced BST |
| --------- | ------------ |
| add()     | O(log n)     |
| find()    | O(log n)     |
| remove()  | O(log n)     |

* Node insertion/removal itself is **O(1)**
* Traversal/search dominates complexity

---

## Performance Analysis

* Balanced BST height < `log₂(n) + 1`
* Trees are used in Java collections like `TreeSet` and `TreeMap`
* Searches follow a single root-to-leaf path

---

## 🔗 BST vs Linked List

| Operation     | LinkedList | BST-based List |
| ------------- | ---------- | -------------- |
| removeFirst() | O(1)       | O(log n)       |
| removeLast()  | O(n)       | O(log n)       |
| remove()      | O(n)       | O(log n)*      |
| first()       | O(1)       | O(log n)       |
| last()        | O(n)       | O(log n)       |
| contains()    | O(n)       | O(log n)       |
| isEmpty()     | O(1)       | O(1)           |
| size()        | O(1)       | O(1)           |
| add()         | O(n)       | O(log n)*      |

`*` assumes balanced tree

---

##  Summary

* Trees are hierarchical, acyclic structures
* Binary trees limit nodes to two children
* BSTs add ordering for efficient searching
* Balanced trees provide optimal performance
* Recursive algorithms are ideal for trees

---



