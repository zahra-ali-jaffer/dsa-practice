# Java Maps and Dictionary Example

This file explores **Java Maps**, their operations, and practical uses like implementing a dictionary. It also highlights different `Map` implementations in the Java Collections Framework (JCF).

---

## Map Basics

A **map** is a collection of **key-value pairs**, where each key is unique and maps to a value.

### Typical Operations

- **Add / Put:** `put(key, value)` associates a new value with a key. Replaces the value if the key already exists.
- **Remove:** `remove(key)` deletes a key and its value.
- **Lookup:** `get(key)` retrieves the value associated with a key.
- **Check existence:** `containsKey(key)` returns `true` if the key exists.

---

## Where Maps Are Useful

Maps are ideal whenever you want to **quickly look up information by a unique identifier**. Examples:

1. **Phone book**  
   Key: Person’s **name**  
   Value: Phone number  

   ```java
   Map<String, String> phoneBook = new HashMap<>();
   phoneBook.put("Alice", "555-1234");
   phoneBook.put("Bob", "555-5678");


2. **Database indexing**
   Key: Unique identifier (name or ID)
   Value: Full object or record

   ```java
   Map<String, Supplier> suppliersByName = new HashMap<>();
   Map<String, Set<Supplier>> suppliersByCity = new TreeMap<>();
   ```

3. **Other uses**

   * Counting occurrences of words or items
   * Caching results
   * Configuration settings (`key = setting name`, `value = setting value`)

---

## Dictionary Example in Java

### DictionaryEntry Class

```java
public class DictionaryEntry {
    private String word;          // Word itself
    private String definition;    // Meaning
    private String example;       // Example sentence
    private User creator;         // Who added the entry
    private Date dateCreated;     // Date created
    private int likes;            // Upvotes
    private int dislikes;         // Downvotes

    // Constructors, getters, setters, and helper methods like upvote() or downvote()
}
```

* Note: The **word** is used as the key in a map.

### Creating the Map

```java
// Using 5000 entries as an example
Map<String, DictionaryEntry> dictionary = new HashMap<>(7500); // 5000 * 1.5
```

* Pre-sizing improves performance by reducing rehashing.

### Adding Entries

```java
DictionaryEntry entry = new DictionaryEntry(
    "apple",
    "A fruit that grows on trees",
    "I ate an apple for lunch",
    someUser,
    new Date(),
    0,
    0
);

dictionary.put(entry.getWord(), entry);
```

### Retrieving Entries

```java
DictionaryEntry result = dictionary.get("apple");
if (result != null) {
    System.out.println(result.getDefinition());
}
```

---

## Iterating Through a Map

Maps **cannot be iterated directly** like arrays. You have three main approaches:

### 1. Iterate over keys

```java
for (String key : students.keySet()) {
    Student student = students.get(key);
    System.out.println(key + " -> " + student.getName());
}
```

* `keySet()` returns all keys.
* Slightly less efficient because it requires a `get()` for each key.

### 2. Iterate over values

```java
for (Student student : students.values()) {
    System.out.println(student.getName());
}
```

* `values()` returns all values.
* Efficient if you don’t need keys.

### 3. Iterate over entries

```java
for (Map.Entry<String, Student> kvp : students.entrySet()) {
    String key = kvp.getKey();
    Student student = kvp.getValue();
    System.out.println(key + " -> " + student.getName());
}
```

* `entrySet()` gives both key and value.
* Most versatile and avoids extra lookups.

---

## Java Map Implementations (JCF)

### HashMap<K, V>

* Unordered hash table.
* Fast average O(1) lookups, insertions, and deletions.
* Can store `null` keys and values.
* **Use case:** Electronic dictionaries, caches.

### ConcurrentHashMap<K, V>

* Thread-safe version of HashMap.
* Supports concurrent reads and updates.
* Slightly slower due to internal locking.
* **Use case:** Multi-threaded shared maps.

### TreeMap<K, V>

* Ordered map implemented as a Red-Black tree.
* Keys are sorted naturally or via a `Comparator`.
* Lookup, insertion, deletion are O(log n).
* No `null` keys allowed.
* **Use case:** Paper dictionaries, any scenario where order matters.

---

## Key Takeaways

* Use **HashMap** for fast, unordered lookups.
* Use **ConcurrentHashMap** for safe concurrent operations.
* Use **TreeMap** when sorted order is required.
* Iteration is done via `keySet()`, `values()`, or `entrySet()`.
* Always pre-size maps if you know the approximate number of entries to improve performance.

---

## References / Examples

* [Official Java Map Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
* Practical dictionary example: `word -> DictionaryEntry` mapping



