# Chapter 14 – Sorting and Searching

This chapter covers fundamental searching and sorting algorithms in Java, including their implementations and efficiency analysis using Big-O notation.

Understanding these algorithms is essential for writing efficient programs and analysing performance.

---

# Searching Algorithms

## 1. Linear Search — O(n)

Linear search checks each element one by one until the target is found or the list ends.

### Characteristics

* Works on unsorted arrays
* Simple to implement
* Worst-case time complexity: O(n)
* Suitable for small datasets

### Implementation

```java
// Linear Search - O(n)
public static <T extends Comparable<T>> int linearSearch(T[] data, int min, int max, T target) {
    int index = min;

    while (index <= max) {
        int comparison = target.compareTo(data[index]);

        if (comparison == 0)
            return index;                 // Target found
        else if (comparison < 0)
            return -(index - 1);          // Target not found (early stop)
        else
            index++;
    }

    return -(index - 1);                  // Target not found (end reached)
}
```

---

## 2. Binary Search — O(log₂ n)

Binary search repeatedly divides the search interval in half.

Important: The array must be sorted before using binary search.

### Characteristics

* Requires sorted data
* Much faster than linear search for large datasets
* Worst-case time complexity: O(log₂ n)

### Implementation (Recursive)

```java
// Binary Search - O(log n)
public static <T extends Comparable<T>> int binarySearch(T[] data, int min, int max, T target) {

    if (max < min)
        return -min - 1;

    int mid = (min + max) / 2;
    int comparison = target.compareTo(data[mid]);

    if (comparison == 0)
        return mid;                         // Target found
    else if (comparison < 0)
        return binarySearch(data, min, mid - 1, target);   // Search left
    else
        return binarySearch(data, mid + 1, max, target);   // Search right
}
```

---

## Linear vs Binary Search

| Feature               | Linear Search          | Binary Search        |
| --------------------- | ---------------------- | -------------------- |
| Requires sorted data? | No                     | Yes                  |
| Worst-case complexity | O(n)                   | O(log n)             |
| Suitable for          | Small / unsorted lists | Large / sorted lists |
| Speed                 | Slower                 | Faster               |

---

# Sorting Algorithms

Sorting arranges elements into a specific order (usually ascending).

---

## Sorting Complexity Types

### Sequential (Quadratic) Sorting

* Requires approximately n² comparisons
* Examples:

  * Straight Insertion Sort
  * Selection Sort
  * Bubble Sort

### Logarithmic (Efficient) Sorting

* Requires approximately n log₂ n comparisons
* Example:

  * Quick Sort

---

## In-Place Sorting

To maximise space efficiency, sorting can be done in-place, meaning:

* The same array is used
* No extra array is created
* Elements are swapped within the structure

This reduces memory usage but may increase assignments.

---

# Straight Insertion Sort

Builds a sorted portion of the array one element at a time by inserting elements into their correct position.

### Complexity

* Comparisons: ¼ n²
* Assignments: ¼ n²
* Time: O(n²)

---

# Binary Insertion Sort

Improves insertion sort by using binary search to locate the insertion point.

### Complexity

* Comparisons: log₂(n)
* Assignments: ¼ n²
* Time: O(n²) (shifting still costs n²)

This is the most efficient among the quadratic sorts listed.

---

# Selection Sort

Repeatedly selects the smallest element and places it in its final position.

### Complexity

* Comparisons: ½ n²
* Assignments: 3n
* Time: O(n²)

---

# Bubble Sort

Repeatedly swaps adjacent elements if they are in the wrong order.

### Complexity

* Comparisons: ½ n²
* Assignments: 3/2 n²
* Time: O(n²)

Generally the least efficient of these basic sorting algorithms.

---

# Comparing Quadratic Sorts

| Algorithm          | Comparisons | Assignments | Overall Rank |
| ------------------ | ----------- | ----------- | ------------ |
| Binary Insertion   | log₂(n)     | ¼ n²        | Best         |
| Straight Insertion | ¼ n²        | ¼ n²        | Second       |
| Selection Sort     | ½ n²        | 3n          | Third        |
| Bubble Sort        | ½ n²        | 3/2 n²      | Worst        |

---

# Quick Sort

Quick sort is a divide-and-conquer algorithm.

## How It Works

1. Choose a pivot (splitting value)
2. Partition array into:

   * Left: elements smaller than pivot
   * Right: elements greater than pivot
3. Recursively sort both partitions

---

## Implementation

```java
public static <T extends Comparable<T>> void quickSort(T[] data, int min, int max) {
    if (max > min) {
        int partitionIndex = findPartition(data, min, max);
        quickSort(data, min, partitionIndex - 1);   // Sort left
        quickSort(data, partitionIndex + 1, max);   // Sort right
    }
}

private static <T extends Comparable<T>> int findPartition(T[] data, int min, int max) {

    T split = data[min];       // First element as pivot
    int left = min;
    int right = max;

    while (left < right) {

        while (data[left].compareTo(split) <= 0 && left < right)
            left++;

        while (data[right].compareTo(split) > 0)
            right--;

        if (left < right) {
            T temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }
    }

    data[min] = data[right];
    data[right] = split;

    return right;
}
```

Note: Arrays are passed by reference, so modifications affect the original array from the first call.

---

## Efficiency of Quick Sort

### Average Case

* Partitioning takes n
* Recursion depth is log₂(n)
* Overall: O(n log₂ n)

### Worst Case

* Occurs when data is already sorted, reverse sorted, or nearly sorted
* Performance degrades to O(n²)

---

# In the Java Collections Framework

The `java.util.Arrays` class provides built-in search and sort methods:

* `Arrays.sort()`

  * Uses an optimized quicksort (primitive types)
  * Typically O(n log n)

* `Arrays.sort(Object[])`

  * Uses a modified merge sort (TimSort)
  * O(n log n)
  * Can be O(n) when nearly sorted

* `Arrays.binarySearch()`

  * Implements binary search
  * O(log n)

---

# Final Summary

| Algorithm            | Time Complexity | Requires Sorted? |
| -------------------- | --------------- | ---------------- |
| Linear Search        | O(n)            | No               |
| Binary Search        | O(log n)        | Yes              |
| Insertion Sort       | O(n²)           | No               |
| Selection Sort       | O(n²)           | No               |
| Bubble Sort          | O(n²)           | No               |
| Quick Sort (average) | O(n log n)      | No               |
| Quick Sort (worst)   | O(n²)           | No               |

---

# Key Takeaways

* Use Linear Search for small or unsorted data.
* Use Binary Search for large sorted data.
* Avoid Bubble Sort in practice.
* Prefer Quick Sort for efficient general-purpose sorting.
* Built-in Java sorting methods are usually the best choice in real-world applications.

