## Abstraction, Inheritance, and Collections – Implementation Details

Java Abstraction, Inheritance, and Collections

---

## Abstract Class: `Ownable` (UML → Java)

The UML diagram defines **Ownable** as an *abstract concept* representing anything that can be owned by a `Person`.
In Java, this is implemented using an **abstract class**, which cannot be instantiated directly and exists to provide shared state and behaviour.

```java
public abstract class Ownable {
    private Person owner;   // Association to Person (owner)

    public Ownable() { /* ... */ }

    // Delegates behaviour to the associated Person object
    public String getOwnerTel() {
        return owner.getTel();
    }

    // Allows ownership to be updated
    public void changeOwner(Person newOwner) {
        /* ... */
    }
}
```

### Why this is abstraction

* `Ownable` defines *what* all ownable items can do (have an owner, change owner)
* It hides *how* ownership is managed internally
* No object of type `Ownable` can exist on its own

This matches the UML notation where the class name is italicised to indicate **abstract**.

---

## Inheritance: `Car` and `Pet`

Both `Car` and `Pet` are **specialisations** of `Ownable`.

```java
public class Car extends Ownable {
    /* ... */
}

public class Pet extends Ownable {
    /* ... */
}
```

### Key points

* `Car` **is-a** `Ownable`
* `Pet` **is-a** `Ownable`
* Both inherit:

  * The `owner` association
  * `getOwnerTel()`
  * `changeOwner(...)`

This avoids duplicated code and enforces consistent ownership behaviour.

---

## Association: `Person` owns `Ownable` objects

The UML diagram shows that a `Person` can own **up to two** `Ownable` objects.
This is implemented using an array whose element type is the **abstract superclass**.

```java
public class Person {
    private String name;
    private String phone;
    private int income;
    private Ownable[] possessions;

    public Person(String name) {
        this.name = name;
        this.phone = null;
        this.income = 0;
        this.possessions = new Ownable[2];
    }
}
```

### Why this design is important

* The array can store **any subclass of `Ownable`**
* A `Person` does **not** need to know whether it owns a `Car` or a `Pet`
* This demonstrates **polymorphism via abstraction**

Example:

```java
possessions[0] = new Car();
possessions[1] = new Pet();
```

---

## Interfaces as Abstraction (Bank Account Example)

Java interfaces define **pure abstraction**: behaviour without implementation.

In this example, a client class works with `BankAccount` references without caring about the concrete subtype.

```java
ArrayList<BankAccount> myAccounts = new ArrayList<>(5);

myAccounts.add(new CurrentAccount());
myAccounts.add(new MortgageAccount());

showAllAccounts(myAccounts);
```

### Why this demonstrates abstraction and polymorphism

* `CurrentAccount` and `MortgageAccount` share a common parent (`BankAccount`)
* The collection stores them **as their abstract type**
* `showAllAccounts(...)` works for *all account types* via dynamic binding

The client code depends on **what the object can do**, not **what it is**.

---

## Working with Collections (Post Java 5.0)

The project uses the **Java Collections Framework** with generics to ensure type safety.

```java
import java.util.ArrayList;

public class Auction {
    private ArrayList<Lot> lots = new ArrayList<>();

    public void showLots() {
        for (Lot lot : lots) {
            System.out.println(lot);
        }
    }
}
```

### Key concepts demonstrated

* `ArrayList<Lot>` ensures only `Lot` objects can be stored
* Enhanced `for-each` loop uses the `Iterable` interface
* No explicit casting is required
* `toString()` is called polymorphically

---

## Generic Types: `Box<T>`

The `Box<T>` class demonstrates **generic programming**, where the type is not known until instantiation.

```java
import java.util.ArrayList;

public class Box<T> {
    private ArrayList<T> contents;
    /* ... */
}
```

### Why generics are used

* Allows reuse of the same class for different types
* Provides compile-time type checking
* Eliminates runtime `ClassCastException`

Example usage:

```java
Box<Lot> lotBox = new Box<>();
Box<Prize> prizeBox = new Box<>();
```

---

## Summary of OOP Concepts Used

| Concept       | Where Applied                                |
| ------------- | -------------------------------------------- |
| Abstraction   | `Ownable`, `BankAccount`, Interfaces         |
| Inheritance   | `Car extends Ownable`, `Pet extends Ownable` |
| Polymorphism  | `Ownable[]`, `ArrayList<BankAccount>`        |
| Encapsulation | Private fields with controlled access        |
| Collections   | `ArrayList`, enhanced for-loop               |
| Generics      | `ArrayList<T>`, `Box<T>`                     |

---
