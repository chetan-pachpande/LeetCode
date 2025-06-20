import java.util.*;

public class Validanagram {
    public static void solution() {
        // Test cases
        String s1 = "anagram", t1 = "nagaram";  // true
        String s2 = "rat", t2 = "car";          // false
        String s3 = "a", t3 = "a";              // true
        
        // Testing all solution methods
        System.out.println("Solution1 (Sorting) Test Results:");
        System.out.println("Test 1: " + ValidAnagram1.isAnagram(s1, t1)); // Should return true
        System.out.println("Test 2: " + ValidAnagram1.isAnagram(s2, t2)); // Should return false
        System.out.println("Test 3: " + ValidAnagram1.isAnagram(s3, t3)); // Should return true
        
        System.out.println("\nSolution2 (Character Count) Test Results:");
        System.out.println("Test 1: " + ValidAnagram2.isAnagram(s1, t1)); // Should return true
        System.out.println("Test 2: " + ValidAnagram2.isAnagram(s2, t2)); // Should return false
        System.out.println("Test 3: " + ValidAnagram2.isAnagram(s3, t3)); // Should return true
        
        System.out.println("\nSolution3 (HashMap) Test Results:");
        System.out.println("Test 1: " + ValidAnagram3.isAnagram(s1, t1)); // Should return true
        System.out.println("Test 2: " + ValidAnagram3.isAnagram(s2, t2)); // Should return false
        System.out.println("Test 3: " + ValidAnagram3.isAnagram(s3, t3)); // Should return true
    }
    
    public static void main(String[] args) {
        solution();
    }
}

/**
 * Solution 1: Using sorting approach
 * Time Complexity: O(n log n) where n is the length of the string (due to sorting)
 * Space Complexity: O(n) for storing the sorted character arrays
 */
class ValidAnagram1 {
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        char[] sSort = s.toCharArray();
        char[] tSort = t.toCharArray();
        Arrays.sort(sSort);
        Arrays.sort(tSort);
        return Arrays.equals(sSort, tSort);
    }
}

/**
 * Solution 2: Using character count array
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - constant space for a fixed-size array (26 lowercase letters)
 */
class ValidAnagram2 {
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
         
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }    
        
        for(int val : count){
            if(val != 0){
                return false;
            }
        }

        return true;
    }
}

/**
 * Solution 3: Using HashMap to count characters
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */
class ValidAnagram3 {
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> countS = new HashMap<>();
        HashMap<Character, Integer> countT = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            countS.put(s.charAt(i), countS.getOrDefault(s.charAt(i), 0) + 1);
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
        }

        return countS.equals(countT);
    }
}
