/**
 * PrefixSum - An implementation of the prefix sum algorithm
 * Used to efficiently calculate the sum of elements in a range of an array
 */
public class PrefixSum {
    private final int[] prefix;
    private final int length;

    /**
     * Constructs the prefix sum array from the given input array
     * Time Complexity: O(n)
     * 
     * @param nums The input array
     */
    public PrefixSum(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }

        length = nums.length;
        prefix = new int[length + 1];
        for (int i = 0; i < length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    /**
     * Returns the sum of elements in the range [left, right] (inclusive)
     * Time Complexity: O(1)
     * 
     * @param left Starting index (inclusive)
     * @param right Ending index (inclusive)
     * @return Sum of elements in the specified range
     */
    public int rangeSum(int left, int right) {
        if (left < 0 || right < 0 || left > right || right >= length) {
            throw new IllegalArgumentException(
                "Invalid range: left=" + left + ", right=" + right + ", length=" + length
            );
        }

        return prefix[right + 1] - prefix[left];
    }
}
