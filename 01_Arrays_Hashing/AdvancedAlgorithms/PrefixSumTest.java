/**
 * Test class for the PrefixSum implementation
 */
public class PrefixSumTest {
    public static void main(String[] args) {
        // Create an array
        int[] nums = {1, 2, 3, 4, 5};
        
        System.out.println("Original array: [1, 2, 3, 4, 5]");
        System.out.println("------------------------");
        
        // Initialize PrefixSum
        PrefixSum prefixSum = new PrefixSum(nums);
        
        // Test some range queries
        System.out.println("Sum of range [0,2]: " + prefixSum.rangeSum(0, 2));  // Should be 1+2+3 = 6
        System.out.println("Sum of range [1,3]: " + prefixSum.rangeSum(1, 3));  // Should be 2+3+4 = 9
        System.out.println("Sum of range [2,4]: " + prefixSum.rangeSum(2, 4));  // Should be 3+4+5 = 12
        System.out.println("Sum of range [0,4]: " + prefixSum.rangeSum(0, 4));  // Should be 1+2+3+4+5 = 15
    }
}
