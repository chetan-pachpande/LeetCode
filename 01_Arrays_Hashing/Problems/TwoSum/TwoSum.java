import java.util.HashMap;
import java.util.Map;

/**
 * TwoSum - Find indices of two numbers that add up to a target value
 * LeetCode Problem #1
 */
public class TwoSum {
    /**
     * Finds two numbers in the array that add up to the target sum
     * @param nums Array of integers
     * @param target Target sum to find
     * @return Array of two indices whose values sum to target, or [-1, -1] if no solution
     * Time Complexity: O(n) where n is the length of nums array
     * Space Complexity: O(n) for storing elements in the hashmap
     */
    public static int[] solution(int[] nums, int target) {
        // HashMap to store values we've seen and their indices
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement (value needed to reach target)
            int complement = target - nums[i];
            
            // If we've seen the complement before, we found our solution
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Store the current number and its index for future lookups
            map.put(nums[i], i);
        }
        
        // Return [-1, -1] if no solution exists (problem states there will always be one solution)
        return new int[] { -1, -1 };
    }
    
    /**
     * Main method to test the TwoSum solution
     */
    public static void main(String[] args) {
        // Test case: [2,7,11,15] with target 9 should return [0,1]
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = solution(nums, target);
        
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
    }
}
