import java.util.Arrays; //Imports the Arrays class, which provides utility methods like sort() and equals().

class ValidAnagram {
    static boolean isAnagram(String s, String t) { //A static method that takes two strings s and t. Returns true if they are anagrams; Returns false otherwise
        if (s.length() != t.length()) return false; //Length check, cannot be anagrams if not same
        char[] a = s.toCharArray(); //Convert strings to character arrays
        char[] b = t.toCharArray();
        Arrays.sort(a); //Sort
        Arrays.sort(b); //If two strings are anagrams, their sorted character arrays will be identical
        return Arrays.equals(a, b); 
    }
}
