import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopKFrequentElementsTest {

    @Test
    public void testSolution1() {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements1.topKFrequent(nums1, k1);
        assertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements1.topKFrequent(nums2, k2);
        assertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements1.topKFrequent(nums3, k3);
        assertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements1.topKFrequent(nums4, k4);
        assertArrayContainsSameElements(expected4, result4);
    }
    
    @Test
    public void testSolution2() {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements2.topKFrequent(nums1, k1);
        assertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements2.topKFrequent(nums2, k2);
        assertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements2.topKFrequent(nums3, k3);
        assertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements2.topKFrequent(nums4, k4);
        assertArrayContainsSameElements(expected4, result4);
    }
    
    @Test
    public void testSolution3() {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements3.topKFrequent(nums1, k1);
        assertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements3.topKFrequent(nums2, k2);
        assertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements3.topKFrequent(nums3, k3);
        assertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements3.topKFrequent(nums4, k4);
        assertArrayContainsSameElements(expected4, result4);
    }
    
    // Helper method to check if two arrays contain the same elements (regardless of order)
    private void assertArrayContainsSameElements(int[] expected, int[] actual) {
        assertEquals(expected.length, actual.length);
        List<Integer> expectedList = Arrays.stream(expected).boxed().toList();
        List<Integer> actualList = Arrays.stream(actual).boxed().toList();
        for (Integer i : expectedList) {
            assertTrue(actualList.contains(i));
        }
    }
}
