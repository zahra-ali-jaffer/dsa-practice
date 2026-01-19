# Encode and Decode Strings

This project implements an algorithm in Java to **encode** a list of strings into a single string and **decode** it back to the original list. This is a common interview problem (LeetCode: *Encode and Decode Strings*) and is useful in scenarios like network transmission or data serialization.

The idea is to safely convert a list of strings into one string so it can be sent over the network, then reconstructed without any data loss.

---

## Approach

Each string is encoded in the format:

```

<length>#<string>

````

For example:

```text
["lint", "code", "love", "you"]
````

Becomes:

```text
4#lint4#code4#love3#you
```

While decoding:

1. Read digits until `#` to get the length.
2. Read exactly that many characters to get the original string.
3. Repeat until the end of the encoded string.

Because the length is stored explicitly, this method works even if the strings contain special characters like `#`.

---

## Java Implementation

```java
import java.util.*;

public class Solution {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s.length()).append('#').append(s);
        }
        return res.toString();
    }

    // Decodes a single string back to a list of strings.
    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < str.length()) {
            int j = i;
            // Find the separator '#'
            while (str.charAt(j) != '#') {
                j++;
            }

            // Extract the length
            int length = Integer.parseInt(str.substring(i, j));

            // Move to the start of the actual string
            i = j + 1;
            j = i + length;

            // Extract the string and add it to result
            res.add(str.substring(i, j));

            // Move forward for the next encoded string
            i = j;
        }
        return res;
    }
}
```

---

## Example

```java
List<String> input = Arrays.asList("hello", "world", "#java");
Solution sol = new Solution();

String encoded = sol.encode(input);
// Output: "5#hello5#world5##java"

List<String> decoded = sol.decode(encoded);
// Output: ["hello", "world", "#java"]
```

---

## Time and Space Complexity

| Operation | Complexity |
| --------- | ---------- |
| Encode    | O(N)       |
| Decode    | O(N)       |
| Space     | O(N)       |

Where `N` is the total number of characters across all strings.

---

## About

* The length prefix removes all ambiguity.
* Works with any characters, including `#`, spaces, and digits.
* Simple, fast, and reliable.
* Common technique used in real systems for serialization.


