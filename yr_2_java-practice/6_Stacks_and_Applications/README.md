# 2D Arrays & Stack Concepts in Java  

This file contains examples and explanations of **2D arrays**, **stack implementations**, and their use in **maze traversal** in Java. The goal is to understand **memory, array operations, and stack structures**.

---

##  2D Arrays 

###  Declaration & Memory
```java
int[][] a;                 // Reference stored on stack
a = new int[3][4];         // 3 rows, 4 columns, 12 integer cells on heap
double[][] b = new double[10][10]; // 10x10 grid, floating-point numbers
```
 
**Notes:**
- `a` and `b` are references on the stack
- Actual arrays are allocated on the heap
- `int[][]` stores integers, `double[][]` stores decimals

###  Assigning Values
```java
a[0][0] = 1; 
a[0][1] = 5;
```
Assigns values row by row, column by column.

###  Initialisation Shortcut
```java
int[][] c = {
    {1, 5, 1, 4}, 
    {6, 0, 3, 2}
}; // 2 rows, 4 columns
```
Directly initialises array with values.

###  Getting Dimensions
```java
c.length      // Number of rows → 2
c[0].length   // Number of columns → 4
```

###  Looping Through a 2D Array

**Traditional nested loops:**
```java
for (int row = 0; row < c.length; row++) {
    for(int col = 0; col < c[row].length; col++) {
        c[row][col] *= 2; // multiply each element by 2
    }
}
```

**Enhanced for loops:**
```java
for (int[] row : c) {
    for (int element : row) {
        element++; // increment each element by 1 (note: modifies copy)
    }
}
```

---

##  Maze Traversal Concept

A 2D array can represent a maze:

| Cell Value | Meaning  |
|------------|----------|
| 0          | Path     |
| 1          | Wall     |
| 2          | Visited  |

Traversal algorithms like DFS/BFS iterate through `array[row][col]`.

- **`int[][]`** → for walls/paths
- **`double[][]`** → for weighted paths (costs or probabilities)

**TL;DR:** `array = maze map`, `indices = coordinates`, `algorithm = pathfinder`.

---

##  Stacks in Java

### 📌 `java.util.Stack`
Part of JDK, not JCF. Extends `Vector`, which has extra features making it less ideal for pure stack modeling.

### 📌 Wrapper for a Pure Stack
```java
import java.util.Stack;

public class PureStack<T> {
    private Stack<T> stack = new Stack<>();

    public void push(T item) { stack.push(item); }
    public T pop() { return stack.pop(); }
    public T peek() { return stack.peek(); }
    public boolean isEmpty() { return stack.isEmpty(); }
}
```
Wraps `Stack` to expose only LIFO operations, hiding extra `Vector` methods.

### 📌 `ArrayDeque` as Stack Alternative
```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);
stack.pop();
stack.peek();
```
- `Deque` supports LIFO (stack) and FIFO (queue) operations
- More modern and consistent than `java.util.Stack`
- Stack methods in `Deque` are directly analogous to `push()`, `pop()`, `peek()`

---

## 📝 References & Notes

- Stack references live on the stack, actual elements on the heap
- 2D arrays map naturally to grid-based problems like mazes, game boards, or matrices
- `ArrayDeque` is preferred over `Stack` for a pure stack implementation
```
