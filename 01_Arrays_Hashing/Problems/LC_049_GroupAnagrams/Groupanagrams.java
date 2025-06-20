import java.util.*;

public class Groupanagrams {
    public static void solution() {

        // Test cases
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};

        // Testing all solutions methods
        System.out.println("Solution1 (Sorting Characters) Test Results:");
        System.out.println("Test 1: " + GroupAnagrams1.groupAnagrams(strs1));
        System.out.println("Test 2: " + GroupAnagrams1.groupAnagrams(strs2));
        System.out.println("Test 3: " + GroupAnagrams1.groupAnagrams(strs3));
        
        System.out.println("\nSolution2 (Character Count) Test Results:");
        System.out.println("Test 1: " + GroupAnagrams2.groupAnagrams(strs1));
        System.out.println("Test 2: " + GroupAnagrams2.groupAnagrams(strs2));
        System.out.println("Test 3: " + GroupAnagrams2.groupAnagrams(strs3));
        
        System.out.println("\nSolution3 (Prime Number Product) Test Results:");
        System.out.println("Test 1: " + GroupAnagrams3.groupAnagrams(strs1));
        System.out.println("Test 2: " + GroupAnagrams3.groupAnagrams(strs2));
        System.out.println("Test 3: " + GroupAnagrams3.groupAnagrams(strs3));

        // TODO: Implement your solution here
        System.out.println("Groupanagrams solution in Java");
    }
    
    public static void main(String[] args) {
        solution();
    }
}

/**
 * Solution 1: Using character sorting approach
 * Time Complexity: O(n * k log k) where n is the number of strings and k is the maximum length of a string
 * Space Complexity: O(n * k) for storing the result
 */
class GroupAnagrams1 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // TODO: Implement solution using character sorting
        return new ArrayList<>();
    }
}

/**
 * Solution 2: Using character count array as key
 * Time Complexity: O(n * k) where n is the number of strings and k is the maximum length of a string
 * Space Complexity: O(n * k) for storing the result
 */
class GroupAnagrams2 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // TODO: Implement solution using character count
        return new ArrayList<>();
    }
}

/**
 * Solution 3: Using prime number product as key
 * Time Complexity: O(n * k) where n is the number of strings and k is the maximum length of a string
 * Space Complexity: O(n * k) for storing the result
 */
class GroupAnagrams3 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // TODO: Implement solution using prime number product
        return new ArrayList<>();
    }
}
