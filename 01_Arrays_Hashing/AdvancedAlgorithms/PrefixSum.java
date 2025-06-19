import java.util.List;
import java.util.ArrayList;

/**
 * PrefixSum - An implementation of the prefix sum algorithm
 * Used to efficiently calculate the sum of elements in a range of an array
 */
public class PrefixSum {
    
    List<Integer> prefix;

    /**
     * Constructs the prefix sum array from the given input array
     * Time Complexity: O(n)
     * 
     * @param nums The input array
     */
    public PrefixSum(int[] nums) {
        prefix = new ArrayList<>();
        int total = 0;
        for (int n : nums) {
            total += n;
            prefix.add(total);
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
        int preRight = prefix.get(right);
        int preLeft = left > 0 ? prefix.get(left - 1) : 0;
        return (preRight - preLeft);      
    }
}
