import java.util.*;

/**
 * Valid Palindrome - LeetCode Problem #125
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * 
 * Alphanumeric characters include letters and numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        // Test cases
        String s1 = "A man, a plan, a canal: Panama";  // Expected: true
        String s2 = "race a car";                      // Expected: false
        String s3 = " ";                               // Expected: true
        String s4 = "0P";                              // Expected: false

        // Test all solutions
        System.out.println("Solution1 (Two Pointers) Test Results:");
        System.out.println("Test 1: " + ValidPalindrome1.isPalindrome(s1)); // Should return true
        System.out.println("Test 2: " + ValidPalindrome1.isPalindrome(s2)); // Should return false
        System.out.println("Test 3: " + ValidPalindrome1.isPalindrome(s3)); // Should return true
        System.out.println("Test 4: " + ValidPalindrome1.isPalindrome(s4)); // Should return false
        
        System.out.println("\nSolution2 (Clean String First) Test Results:");
        System.out.println("Test 1: " + ValidPalindrome2.isPalindrome(s1)); // Should return true
        System.out.println("Test 2: " + ValidPalindrome2.isPalindrome(s2)); // Should return false
        System.out.println("Test 3: " + ValidPalindrome2.isPalindrome(s3)); // Should return true
        System.out.println("Test 4: " + ValidPalindrome2.isPalindrome(s4)); // Should return false
        
        System.out.println("\nSolution3 (Using Java 8 Stream API) Test Results:");
        System.out.println("Test 1: " + ValidPalindrome3.isPalindrome(s1)); // Should return true
        System.out.println("Test 2: " + ValidPalindrome3.isPalindrome(s2)); // Should return false
        System.out.println("Test 3: " + ValidPalindrome3.isPalindrome(s3)); // Should return true
        System.out.println("Test 4: " + ValidPalindrome3.isPalindrome(s4)); // Should return false
    }
}

/**
 * Solution 1: Two pointers approach without creating a new string
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use two pointers
 */
class ValidPalindrome1 {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case insensitive)
            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}

/**
 * Solution 2: Clean the string first, then check if it's a palindrome
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for storing the cleaned string
 */
class ValidPalindrome2 {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        // Convert to lowercase and remove non-alphanumeric characters
        String cleanString = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        // Check if the cleaned string is a palindrome
        int left = 0;
        int right = cleanString.length() - 1;
        
        while (left < right) {
            if (cleanString.charAt(left) != cleanString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}

/**
 * Solution 3: Using Java 8 Stream API
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for storing the filtered characters
 */
class ValidPalindrome3 {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        // Filter alphanumeric characters and convert to lowercase
        String filtered = s.chars()
                           .filter(Character::isLetterOrDigit)
                           .map(Character::toLowerCase)
                           .collect(StringBuilder::new, 
                                   StringBuilder::appendCodePoint, 
                                   StringBuilder::append)
                           .toString();
        
        // Compare with its reverse
        String reversed = new StringBuilder(filtered).reverse().toString();
        
        return filtered.equals(reversed);
    }
}
