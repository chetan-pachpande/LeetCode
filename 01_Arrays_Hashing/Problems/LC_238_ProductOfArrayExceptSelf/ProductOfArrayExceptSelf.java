import java.util.*;

/**
 * Product of Array Except Self - LeetCode Problem #238
 * 
 * Given an integer array nums, return an array answer such that answer[i] 
 * is equal to the product of all the elements of nums except nums[i].
 * 
 * The algorithm should run in O(n) time without using the division operation.
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 2, 3, 4};  // Expected output: [24, 12, 8, 6]
        int[] nums2 = {-1, 1, 0, -3, 3};  // Expected output: [0, 0, 9, 0, 0]

        // Test all solutions
        System.out.println("Solution1 (Prefix and Suffix Products) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(ProductOfArrayExceptSelf1.productExceptSelf(nums1)));
        System.out.println("Test 2: " + Arrays.toString(ProductOfArrayExceptSelf1.productExceptSelf(nums2)));
        
        System.out.println("\nSolution2 (Space Optimized) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(ProductOfArrayExceptSelf2.productExceptSelf(nums1)));
        System.out.println("Test 2: " + Arrays.toString(ProductOfArrayExceptSelf2.productExceptSelf(nums2)));
        
        System.out.println("\nSolution3 (Alternative Approach) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(ProductOfArrayExceptSelf3.productExceptSelf(nums1)));
        System.out.println("Test 2: " + Arrays.toString(ProductOfArrayExceptSelf3.productExceptSelf(nums2)));
    }
}

/**
 * Solution 1: Using prefix and suffix product arrays
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for storing the prefix and suffix arrays
 */
class ProductOfArrayExceptSelf1 {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate prefix products
        int[] prefix = new int[n];
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] * nums[i-1];
        }
        
        // Calculate suffix products
        int[] suffix = new int[n];
        suffix[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            suffix[i] = suffix[i+1] * nums[i+1];
        }
        
        // Combine prefix and suffix products
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        
        return result;
    }
}

/**
 * Solution 2: Space optimized solution using only the output array
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) excluding the output array
 */
class ProductOfArrayExceptSelf2 {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate prefix products and store in result array
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        
        // Calculate suffix products and multiply with prefix products in result
        int suffixProduct = 1;
        for (int i = n-1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return result;
    }
}

/**
 * Solution 3: Using a different approach without division
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) excluding the output array
 * 
 * Note: This solution avoids using division as required by the problem statement.
 */
class ProductOfArrayExceptSelf3 {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: Calculate running product from left to right
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }
        
        // Second pass: Multiply by running product from right to left
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
}
