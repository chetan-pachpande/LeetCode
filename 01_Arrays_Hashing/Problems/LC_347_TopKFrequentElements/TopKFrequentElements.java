import java.util.*;

/**
 * Top K Frequent Elements - LeetCode Problem #347
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements. 
 * You may return the answer in any order.
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - k is in the range [1, the number of unique elements in the array]
 * - It is guaranteed that the answer is unique.
 * 
 * Follow up: Your algorithm's time complexity must be better than O(n log n), 
 * where n is the array's size.
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;  // Expected: [1, 2]
        
        int[] nums2 = {1};
        int k2 = 1;  // Expected: [1]
        
        int[] nums3 = {1, 2};
        int k3 = 2;  // Expected: [1, 2]
        
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;  // Expected: [-1, 2]
        
        // Test all solutions
        System.out.println("Solution1 (HashMap + Max Heap) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TopKFrequentElements1.topKFrequent(nums1, k1)));
        System.out.println("Test 2: " + Arrays.toString(TopKFrequentElements1.topKFrequent(nums2, k2)));
        System.out.println("Test 3: " + Arrays.toString(TopKFrequentElements1.topKFrequent(nums3, k3)));
        System.out.println("Test 4: " + Arrays.toString(TopKFrequentElements1.topKFrequent(nums4, k4)));
        
        System.out.println("\nSolution2 (HashMap + Min Heap) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TopKFrequentElements2.topKFrequent(nums1, k1)));
        System.out.println("Test 2: " + Arrays.toString(TopKFrequentElements2.topKFrequent(nums2, k2)));
        System.out.println("Test 3: " + Arrays.toString(TopKFrequentElements2.topKFrequent(nums3, k3)));
        System.out.println("Test 4: " + Arrays.toString(TopKFrequentElements2.topKFrequent(nums4, k4)));
        
        System.out.println("\nSolution3 (Bucket Sort) Test Results:");
        System.out.println("Test 1: " + Arrays.toString(TopKFrequentElements3.topKFrequent(nums1, k1)));
        System.out.println("Test 2: " + Arrays.toString(TopKFrequentElements3.topKFrequent(nums2, k2)));
        System.out.println("Test 3: " + Arrays.toString(TopKFrequentElements3.topKFrequent(nums3, k3)));
        System.out.println("Test 4: " + Arrays.toString(TopKFrequentElements3.topKFrequent(nums4, k4)));
    }
}

/**
 * Solution 1: Using HashMap and Max Heap (PriorityQueue)
 * 
 * This approach uses a max heap to find the k most frequent elements.
 * We first count the frequency of each element using a HashMap,
 * then add all entries to a max heap ordered by frequency.
 * Finally, we extract the top k elements from the heap.
 * 
 * Time Complexity: O(n log n) - where n is the number of elements in the array
 *   - O(n) to count frequencies using HashMap
 *   - O(n log n) to add all elements to the heap
 *   - O(k log n) to extract top k elements from the heap
 * 
 * Space Complexity: O(n) for storing elements in the HashMap and heap
 * 
 * Advantages:
 * - Straightforward implementation
 * - Good when k is close to n (the array size)
 * 
 * Disadvantages:
 * - Not optimal when k is much smaller than n
 */
class TopKFrequentElements1 {
    public static int[] topKFrequent(int[] nums, int k) {
        // Handle edge cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k >= nums.length) {
            // If k is greater than or equal to the array size,
            // we can just return unique elements from the array
            return Arrays.stream(nums).distinct().toArray();
        }
        
        // Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create a max heap ordered by frequency
        // The comparator (a, b) -> b.getValue() - a.getValue() sorts in descending order
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        
        // Add all elements to the heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            heap.add(entry);
        }
        
        // Extract the top k elements from the heap
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll().getKey();
        }
        
        return result;
    }
}

/**
 * Solution 2: Using HashMap and Min Heap (PriorityQueue)
 * 
 * This approach uses a min heap of size k to find the k most frequent elements.
 * We maintain a heap with only k elements by removing the least frequent element
 * whenever the heap size exceeds k. This is more efficient than Solution 1 when
 * k is much smaller than n.
 * 
 * Time Complexity: O(n log k) - where n is the number of elements in the array
 *   - O(n) to count frequencies using HashMap
 *   - O(n log k) to maintain a heap of size k
 * 
 * Space Complexity: O(n) for the HashMap + O(k) for the heap = O(n)
 * 
 * Advantages:
 * - More efficient than Solution 1 when k is much smaller than n
 * - Maintains only k elements in the heap at any time
 * 
 * Disadvantages:
 * - Still not as efficient as bucket sort for all cases
 */
class TopKFrequentElements2 {
    public static int[] topKFrequent(int[] nums, int k) {
        // Handle edge cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k >= nums.length) {
            // If k is greater than or equal to the array size,
            // we can just return unique elements from the array
            return Arrays.stream(nums).distinct().toArray();
        }
        
        // Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create a min heap of size k
        // The comparator (a, b) -> a.getValue() - b.getValue() sorts in ascending order
        // This means the least frequent element is at the top of the heap
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        
        // Add elements to the heap, keeping only the k most frequent
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            heap.add(entry);
            if (heap.size() > k) {
                heap.poll(); // Remove the least frequent element
            }
        }
        
        // Extract all elements from the heap
        // We extract in reverse order to get the most frequent first
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll().getKey();
        }
        
        return result;
    }
}

/**
 * Solution 3: Using Bucket Sort (Optimal Solution)
 * 
 * This approach uses bucket sort to achieve linear time complexity.
 * We create buckets where the index represents the frequency, and each bucket
 * contains all elements with that frequency. By iterating through the buckets
 * from highest frequency to lowest, we get the top k frequent elements.
 * 
 * Time Complexity: O(n) - where n is the number of elements in the array
 *   - O(n) to count frequencies
 *   - O(n) to create and populate frequency buckets
 *   - O(n) to collect the top k elements
 * 
 * Space Complexity: O(n) for the HashMap and buckets
 * 
 * Advantages:
 * - Optimal linear time complexity
 * - Works well for any value of k
 * 
 * Disadvantages:
 * - Requires more complex implementation than heap-based approaches
 */
class TopKFrequentElements3 {
    public static int[] topKFrequent(int[] nums, int k) {
        // Handle edge cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k >= nums.length) {
            // If k is greater than or equal to the array size,
            // we can just return unique elements from the array
            return Arrays.stream(nums).distinct().toArray();
        }
        
        // Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create buckets for each frequency (1 to n)
        // This array of lists stores elements by their frequency
        // Type safety handled with unchecked warning suppression
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Add elements to buckets based on frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            buckets[freq].add(num);
        }
        
        // Collect the top k frequent elements
        int[] result = new int[k];
        int index = 0;
        
        // Start from the highest frequency bucket
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            for (int num : buckets[i]) {
                result[index++] = num;
                if (index == k) {
                    break;
                }
            }
        }
        
        return result;
    }
}
