# ADTs- Stacks, Queues, and Sets

#### AbstractSet<T> – Behaviour vs Storage

##### Implement in abstract class → behaviour-based methods

- `addAll()`, `removeAll()`, `equals()`
- These rely on abstract methods like `add()` or `remove()` but define behaviour once for all sets

##### Leave abstract → storage-dependent methods

- `add()`, `remove()`, `contains()`, `iterator()`
- Concrete subclasses must implement these

##### Important note on `remove()`:
- Using `remove()` in behaviour-based methods ≠ implementing it

#### Iterator in AbstractSet

- AbstractSet does not know how elements are stored →` iterator()` is left abstract

- Calling A.iterator() (e.g., in `addAll()` or `removeAll()`) assumes A is concrete and has implemented `iterator()`

- Key idea: abstract class can use methods declared in interfaces/abstract classes, but actual implementation exists only in concrete subclasses

#### Arrays – Allocation & Initialisation

| Style                     | Example                       | Default values                          |
|----------------------------|-------------------------------|----------------------------------------|
| Allocate, init later       | `int[] a; a = new int[10];`   | int → 0, boolean → false, objects → null |
| Allocate and init now      | `boolean[] b = new boolean[5];` | same defaults, all 5 elements exist   |
| Allocate and init with values | `int[] c = {1,3,2,4};`     | values set explicitly                   |

##### Generic arrays in Java:

- new T[] → not allowed (type erasure)

- @SuppressWarnings("unchecked") → compiler warning, indicates unsafe cast

- Common hacks:

contents = `(T[]) new Object[maxSize];           // works but unsafe`
contents = `(T[]) Array.newInstance(Object.class, maxSize); // slightly safer`


-** Best practice: **pass Class<T> to constructor and use Array.newInstance(clazz, maxSize) → safe, no warnings

#### ArraySet<T> & ArrayIterator<T>
#### What it is

- ArraySet<T> → bounded set using an array
- Implements Bounded → supports isFull(), capacity()
- Extends AbstractSet<T> → inherits behaviour-based methods
- Tracks:
 - contents[] → array storing elements
 - count → number of valid elements
 - maxSize → maximum elements

#### Construction
`ArraySet()                // default capacity
`
`ArraySet(int maxSize)     // custom capacity`

- Arrays of generic types require casting Object[] → T[]
- @SuppressWarnings("unchecked") used due to generic array limitation

**Doubt clarified:**
*Why `(T[]) new Object[maxSize]`?*

Java cannot create generic arrays → cast needed

**Alternative:** `Array.newInstance(Object.class, maxSize)`

#### Accessor Methods

`size()` → returns `count`

`isEmpty() `→ true if `count == 0`

`capacity() `→ returns `maxSize`

`isFull()` → true if `count == maxSize`

`count` ≠ `contents.length` → count tracks valid elements

#### Core Set Operations

- **contains(T element):** loop through `contents[0..count-1]`, use `equals()`

- **add(T element):** add if not present, increment `count`, throw exception if full

- **remove(T element):** swap with last element, decrement `count` → O(1), order not preserved

- **intersection(SetADT<T> A):** create new set with elements present in both sets, direct array assignment avoids unnecessary checks

#### Iterator 

- iterator() → returns ArrayIterator<T>
- ArrayIterator<T> fields:
- contents[] → array reference
- cursor → current index
- limit → number of valid elements (count)

**Methods:**
 - hasNext() → cursor < limit
 - next() → return element, cursor++
 - remove() → not supported

**Doubt clarified:**

*Why can AbstractSet call` A.iterator()`?
*

AbstractSet does not know storage, but assumes concrete subclass has implemented iterator

#### Best Practices / Key Points

1. Generic arrays require unsafe cast → unavoidable in Java

2. `count` ≠ `contents.length` → count = number of valid elements

3. `add()` prevents duplicates → enforces set behaviour

4. `remove()` swaps last element → avoids gaps, O(1)

5. `intersection()` can manipulate `contents[]` directly → more efficient

6. `retainAll()` vs `removeAll()`:
 - `removeAll(A)` → remove elements that exist in A
 - `retainAll(A)` → keep only elements in A, remove the rest

7. Iterators are safe for traversing but cannot remove unless designed

### Bounded → Unbounded

| Aspect      | Old (bounded)           | New (unbounded)                       |
|------------|------------------------|---------------------------------------|
| Array full | Throw exception        | Expand array automatically            |
| maxSize    | Fixed                  | Not needed                             |
| add() return | true/false           | Same, but never fails due to capacity |


- `expandCapacity()` → double array size, copy elements

-` add()` still checks duplicates, increments count

### ArrayQueue<T>
#### Purpose

- Array-based FIFO queue implementing QueueADT<T>
- Stores elements in contiguous array
- Tracks next available spot using rear

#### Fields
```java
    private T[] queue;         // array for elements
    private int rear;          // index of next free spot
    private static final int DEFAULT_CAPACITY = 100;
```

#### Constructors
```java
@SuppressWarnings("unchecked")
public ArrayQueue(int initCapacity) {
    rear = 0;
    queue = (T[]) new Object[initCapacity]; // unsafe generic array
}

public ArrayQueue() {
    this(DEFAULT_CAPACITY); // Proper constructor chaining
}
```

#### Enqueue – Add element
```java
public void enqueue(T element) {
    if (size() == queue.length) throw new IllegalStateException("queue full!");
    queue[rear] = element;
    rear++;
}
```

- Adds element at rear
- O(1) insertion
- Throws exception if queue is full

#### Dequeue – Remove element
```java
public T dequeue() {
    if (isEmpty()) throw new IllegalStateException("queue empty!");
    T result = queue[0];        // front element
    rear--;                      // decrease count
    for (int i = 0; i < rear; i++) queue[i] = queue[i+1]; // shift left
    queue[rear] = null;          // clear last slot
    return result;
}
```
- Removes element from front
- Shifts all elements → O(n)
- Returns removed element
- Throws exception if empty

**Note:** inefficient for large queues; circular buffer can make O(1)

### Key Points / Limitations

- Bounded queue → throws exception when full
- `rear` = next free index, not front element
- `enqueue()` O(1), `dequeue() `O(n) due to shifting
- Can be made unbounded by dynamic resizing (like ArraySet)
- Can be made efficient with **circular array** using `front` and `rear`

#### Takeaways

- **AbstractSet** handles behaviour-based methods; storage left abstract
- **Iterator** in abstract class allows looping over unknown storage
- **Generic arrays** in Java are tricky → unsafe casts are common `Array.newInstance()`
- **ArraySet**→ bounded/unbounded set with `count`, `maxSize`, `isFull()`
- **ArrayQueue**→ bounded FIFO queue; shifting on dequeue is slow
- **Best practice:** dynamic arrays or circular buffers improve efficiency
