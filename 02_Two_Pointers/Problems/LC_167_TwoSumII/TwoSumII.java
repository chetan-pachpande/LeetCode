import java.util.*;

/**
 * Two Sum II - Input Array Is Sorted - LeetCode Problem #167
 * 
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * 
 * Return the indices of the two numbers, index1 and index2, as an integer array [index1, index2] of length 2.
 * 
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice.
 * 
 * Your solution must use only constant extra space.
 */
public class TwoSumII {

    public static void main(String[] args) {
        // Test cases
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;  // Expected: [1, 2]
        
        int[] numbers2 = {2, 3, 4};
        int target2 = 6;  // Expected: [1, 3]
        
        int[] numbers3 = {-1, 0};
        int target3 = -1;  // Expected: [1, 2]

        // Test all solutions
        System.out.println("Solution1 (Two Pointers) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TwoSumII1.twoSum(numbers1, target1))); 
        System.out.println("Test 2: " + Arrays.toString(TwoSumII1.twoSum(numbers2, target2))); 
        System.out.println("Test 3: " + Arrays.toString(TwoSumII1.twoSum(numbers3, target3))); 
        
        System.out.println("\nSolution2 (Binary Search) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TwoSumII2.twoSum(numbers1, target1))); 
        System.out.println("Test 2: " + Arrays.toString(TwoSumII2.twoSum(numbers2, target2))); 
        System.out.println("Test 3: " + Arrays.toString(TwoSumII2.twoSum(numbers3, target3))); 
        
        System.out.println("\nSolution3 (HashMap) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TwoSumII3.twoSum(numbers1, target1))); 
        System.out.println("Test 2: " + Arrays.toString(TwoSumII3.twoSum(numbers2, target2))); 
        System.out.println("Test 3: " + Arrays.toString(TwoSumII3.twoSum(numbers3, target3))); 
    }
}

/**
 * Solution 1: Two pointers approach (optimal)
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use two pointers
 */
class TwoSumII1 {
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                // Return 1-indexed array as required
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        // Per problem constraints, there is always exactly one solution
        return new int[] {-1, -1};
    }
}

/**
 * Solution 2: Binary search approach
 * Time Complexity: O(n log n) where n is the length of the array
 * Space Complexity: O(1) as we only use constant extra space
 */
class TwoSumII2 {
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            
            // Binary search for the complement
            int left = i + 1;
            int right = numbers.length - 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (numbers[mid] == complement) {
                    return new int[] {i + 1, mid + 1}; // 1-indexed
                } else if (numbers[mid] < complement) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        // Per problem constraints, there is always exactly one solution
        return new int[] {-1, -1};
    }
}

/**
 * Solution 3: HashMap approach (not optimal for space constraint)
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for storing elements in the HashMap
 * 
 * Note: This solution doesn't meet the constant extra space requirement
 * but is included for comparison with other approaches.
 */
class TwoSumII3 {
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            
            if (map.containsKey(complement)) {
                // Return 1-indexed array as required
                return new int[] {map.get(complement) + 1, i + 1};
            }
            
            map.put(numbers[i], i);
        }
        
        // Per problem constraints, there is always exactly one solution
        return new int[] {-1, -1};
    }
}
