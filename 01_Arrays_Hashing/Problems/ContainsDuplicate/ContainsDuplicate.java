import java.util.*;

public class ContainsDuplicate {
    public static void solution() {
        // Test cases
        int[] nums1 = {1, 2, 3, 1};  // true
        int[] nums2 = {1, 2, 3, 4};  // false
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};  // true

        // Testing all solution methods
        System.out.println("Solution1 (Brute Force) Test Results:");
        System.out.println("Test 1: " + ContainsDuplicate1.containsDup(nums1)); // Should return true
        System.out.println("Test 2: " + ContainsDuplicate1.containsDup(nums2)); // Should return false
        System.out.println("Test 3: " + ContainsDuplicate1.containsDup(nums3)); // Should return true
        
        System.out.println("\nSolution2 (HashSet) Test Results:");
        System.out.println("Test 1: " + ContainsDuplicate2.containsDup(nums1)); // Should return true
        System.out.println("Test 2: " + ContainsDuplicate2.containsDup(nums2)); // Should return false
        System.out.println("Test 3: " + ContainsDuplicate2.containsDup(nums3)); // Should return true
        
        System.out.println("\nSolution3 (Sorting) Test Results:");
        System.out.println("Test 1: " + ContainsDuplicate3.containsDup(nums1)); // Should return true
        System.out.println("Test 2: " + ContainsDuplicate3.containsDup(nums2)); // Should return false
        System.out.println("Test 3: " + ContainsDuplicate3.containsDup(nums3)); // Should return true
        
        System.out.println("\nSolution4 (HashMap) Test Results:");
        System.out.println("Test 1: " + ContainsDuplicate4.containsDup(nums1)); // Should return true
        System.out.println("Test 2: " + ContainsDuplicate4.containsDup(nums2)); // Should return false
        System.out.println("Test 3: " + ContainsDuplicate4.containsDup(nums3)); // Should return true
        
        System.out.println("\nSolution5 (Stream API) Test Results:");
        System.out.println("Test 1: " + ContainsDuplicate5.containsDup(nums1)); // Should return true
        System.out.println("Test 2: " + ContainsDuplicate5.containsDup(nums2)); // Should return false
        System.out.println("Test 3: " + ContainsDuplicate5.containsDup(nums3)); // Should return true
    }
    
    public static void main(String[] args) {
        solution();
    }
}

/**
 * Solution 1: Brute Force approach - comparing each element with all others
 * Time Complexity: O(n²) where n is the length of the array
 * Space Complexity: O(1) - constant extra space used
 */
class ContainsDuplicate1 {
    public static boolean containsDup(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Solution 2: Using HashSet to check for duplicates (most efficient)
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for storing elements in the HashSet
 */
class ContainsDuplicate2 {
    public static boolean containsDup(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        
        return false;
    }
}

/**
 * Solution 3: Using sorting technique
 * Time Complexity: O(n log n) due to sorting operation
 * Space Complexity: O(1) if we modify the original array, or O(n) if we create a copy
 */
class ContainsDuplicate3 {
    public static boolean containsDup(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        
        // Create a copy of the array to avoid modifying the original
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        
        // Check adjacent elements for duplicates
        for (int i = 0; i < sortedNums.length - 1; i++) {
            if (sortedNums[i] == sortedNums[i + 1]) {
                return true;
            }
        }
        
        return false;
    }
}

/**
 * Solution 4: Using HashMap to count occurrences
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for storing elements in the HashMap
 */
class ContainsDuplicate4 {
    public static boolean containsDup(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                return true;
            } else {
                countMap.put(num, 1);
            }
        }
        
        return false;
    }
}

/**
 * Solution 5: Using Java Stream API (most concise but potentially less efficient)
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for storing the distinct elements collection
 */
class ContainsDuplicate5 {
    public static boolean containsDup(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }
}
