## Analysis of Algorithms and Time Complexity

Algorithm analysis focuses on evaluating how efficiently a program executes as the **input size grows**. Rather than measuring actual execution time (which depends on hardware and implementation details), analysis concentrates on the **rate of growth** of resource usage, most commonly **time complexity**.

Analysis of algorithms focuses on evaluating how efficiently a program runs as input size grows. Time complexity describes this growth using Big-O notation, which measures how loops, recursion, and operations scale with data. Nested loops increase complexity, where one loop typically leads to O(n), two nested loops to O(n²), and three nested loops to O(n³). By identifying how code structures expand with input size, we can compare solutions, predict performance, and choose more efficient approaches.

### Time Complexity and Big-O Notation

Time complexity is expressed using **Big-O notation**, which describes the *asymptotic behaviour* of an algorithm. Big-O captures the dominant term of a growth function and shows how the number of operations scales with input size `n`, ignoring constants and lower-order terms.

This allows algorithms to be compared independently of machine speed or programming language.

---

### Loops and Their Impact on Complexity

A common way to estimate time complexity is by analysing loops in the code:

* **Single loop** → `O(n)`
  Executes once for each input element (linear growth).

* **Two nested loops** → `O(n²)`
  For each input element, the entire input is processed again (quadratic growth).

* **Three nested loops** → `O(n³)`
  Each additional level of nesting multiplies the growth rate (cubic growth).

For example:

* Iterating through an `ArrayList` once is `O(n)`
* Comparing every element with every other element is `O(n²)`

---

### Why Algorithm Analysis Matters

By identifying how code structures scale with increasing input size, algorithm analysis enables developers to:

* Compare alternative solutions objectively
* Predict performance for large datasets
* Avoid inefficient designs that may become impractical at scale
* Choose appropriate data structures and algorithms for real-world problems

As input sizes grow large, algorithms with lower-order time complexity (such as `O(n)` or `O(n log n)`) significantly outperform higher-order alternatives (such as `O(n²)` or `O(n³)`), even if the latter appear simpler to implement.

---

### Summary

Algorithm analysis, using Big-O notation, provides a formal and practical method for reasoning about performance. By examining loops, recursion, and dominant operations, developers can design software that remains efficient, scalable, and reliable as problem sizes increase.

---

