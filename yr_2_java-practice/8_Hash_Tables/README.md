# Hash Tables and Java Collections Framework (JCF)

This repository contains a comprehensive overview of **hash tables, hash functions, collisions, chaining, Java specifics, load factor, resizing, String hashing, and Java Collections Framework classes**.  
It also includes explanations of common **doubts and pitfalls** encountered when learning hash tables in Java.

---

## 1. Hash Functions

A **hash function** converts data (like strings) into an integer index for a hash table.

**Example:**

```java
public int hashFunction(String name) {
    char initial = name.charAt(0);  // Get first character
    int asciiCode = (int) initial;  // ASCII value
    return asciiCode - 65;           // A=0, B=1, C=2, ...
}
```

**How it works:**

*   `"Alice"` → `'A'` → 65 → 65-65 = **0**
*   `"Bob"` → `'B'` → 66 → 66-65 = **1**
*   `"Charlie"` → `'C'` → 67 → 67-65 = **2**

**Limitations:** Only works for uppercase letters, empty strings will throw an error.

---

## 2. Perfect Hash Functions

A **perfect hash function** maps each key uniquely to a hash value.

**Minimal Perfect Hash Function (MPHF):** Perfect hash function that uses exactly N slots for N keys.

**Example:**

```java
h("A") = 0
h("B") = 1
h("C") = 2
```
No collisions occur.  
Usually feasible only for a fixed dataset.

---

## 3. Hash Tables in Practice

When the key domain is huge (like ISBNs):

**Library example:**
*   Possible ISBNs: >10 trillion
*   Books in library: ~50,000

Direct mapping wastes space.

**Solution:** Use a smaller hash table (~75,000–100,000 slots) + hash function.  
**Lookup:** O(1) average time.

---

## 4. Collisions & Chaining

**Collisions:**
When two keys map to the same bucket:
```
hash("A") = 2
hash("B") = 2   ← collision
```

**Chaining:**
Each bucket stores a list (chain) of entries. Objects are distinguished using `equals()`.

**Example:**
```
Bucket 2 → [A → B → C]
```

**Lookup process:**
1.  Compute hash → bucket index
2.  Traverse chain → use `equals()` to find the exact object

---

## 5. Table Size Guidelines

Let `n` = number of elements:

*   Perfect hash known: table size = `n`
*   Imperfect hash, known n: table size ≈ `1.5 × n`
*   Unknown n: use dynamic resizing

**Dynamic Resizing Steps:**
1.  Create a new, larger table
2.  Rehash all old entries into the new table
3.  Update reference to the new table

**Load factor (α):**  
`α = n / m` (where `m` = table size)  
Typical threshold: **0.75** → resize when table is 75% full.

---

## 6. Java Specifics: hashCode() & equals()

**The Rule:**  
If `a.equals(b)` is true → `a.hashCode() == b.hashCode()`

**Default:**
*   `Object.hashCode()` → based on memory address
*   `Object.equals()` → compares references

**Overriding Example:**
```java
public class Student {
    private String id;
    private String surname;
    private String firstname;

    public int hashCode() {
        return id.hashCode(); // Use content
    }

    public boolean equals(Object another) {
        if (another instanceof Student)
            return this.id.equals(((Student) another).id);
        return false;
    }
}
```
*   `hashCode()` decides bucket
*   `equals()` distinguishes objects in the same bucket
*   Important: collisions are handled using chaining, even if `equals()` is overridden.

---

## 7. String Hashing in Java

Java overrides `String.hashCode()`:

`hash = s[0]⋅31^(n-1) + s[1]⋅31^(n-2) + ⋯ + s[n-1]⋅31^0`

*   `s[i]` = i-th character as int
*   `n` = string length
*   **31** = prime multiplier (reduces collisions)

**Example:**
```java
String s = "ABC"; // A=65, B=66, C=67
hash = 65*31^2 + 66*31 + 67 = 64578
```
*   Order matters: `"ABC"` ≠ `"ACB"`
*   Case matters: `"abc"` ≠ `"ABC"`

---

## 8. Java Collections Framework (Hash-based)

| Class | Type | Ordered? | Null allowed | Thread-safe |
| :--- | :--- | :--- | :--- | :--- |
| `Hashtable<K,V>` | Map | No | No | **Yes** |
| `HashMap<K,V>` | Map | No | Yes (1 key) | No |
| `HashSet<V>` | Set | No | Yes | No |
| `LinkedHashMap<K,V>` | Map | Insertion order | Yes (1 key) | No |
| `LinkedHashSet<V>` | Set | Insertion order | Yes | No |

**Notes:**
*   All rely on `hashCode()` and `equals()`
*   Linked variants maintain insertion order
*   `HashMap` is faster than `Hashtable` (not synchronized)

---

## 9. Collision & Chaining Diagram

**Example:**  
Suppose a hash table with 5 buckets and these objects:

| Object | ID | Hash code (id % 5) |
| :--- | :--- | :--- |
| A | 7 | 2 |
| B | 12 | 2 |
| C | 17 | 2 |
| D | 3 | 3 |

```
Index 0 → empty
Index 1 → empty
Index 2 → A → B → C
Index 3 → D
Index 4 → empty
```
Bucket 2 contains a chain of collided objects: A → B → C

**Lookup steps for Object B:**
1.  Compute hash: hash(B) = 12 % 5 = 2 → go to bucket 2
2.  Traverse chain:
    *   Check `A.equals(B)` → false
    *   Check `B.equals(B)` → true → **found**
3.  Lookup successful

---

## 10. Common Doubts & Explanations

### 1. Can two objects have the same hash code?
 **Yes**, collisions are normal and expected.
*   Hash codes are **not unique identifiers**.
*   Hash tables handle collisions using chaining (or open addressing).

### 2. Does overriding `equals()` prevent collisions?
 **No**.
*   Collisions are based on **hash code**, not equality.
*   `equals()` only distinguishes objects in the **same bucket**.

### 3. How lookup works if two objects share a hash code?
Example: Objects A and B both map to bucket 2.

**Steps:**
1.  Compute hash → bucket 2
2.  Traverse chain: check each object with `equals()`
3.  Return the object when `equals()` returns true

Chaining + `equals()` together ensure correct retrieval.

### 4. Load Factor
**Definition:** percentage of table occupancy at which resizing occurs.

`load factor = (number of elements) / (table size)`

Java standard: **0.75** → resize when table is 75% full.  
Balances speed and memory usage.

---

## 11. Summary

*   **Hash function** → maps key to bucket
*   **Collisions** → multiple keys map to same bucket
*   **Chaining** → stores collided items in a list
*   **Load factor** → controls resizing (~75% in Java)
*   **`hashCode() + equals()`** → required for correct object lookup
*   **String hashing** → weighted sum using 31 as multiplier
*   **JCF hash-based classes** → `HashMap`, `HashSet`, `LinkedHashMap`, etc.
```
