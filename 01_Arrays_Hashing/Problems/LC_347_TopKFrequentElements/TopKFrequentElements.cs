using System;
using System.Collections.Generic;
using System.Linq;

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
public class TopKFrequentElements
{
    public static void Main(string[] args)
    {
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
        Console.WriteLine("Solution1 (Dictionary + LINQ) Test Results:");
        Console.WriteLine($"Test 1: [{string.Join(", ", TopKFrequentElements1.TopKFrequent(nums1, k1))}]");
        Console.WriteLine($"Test 2: [{string.Join(", ", TopKFrequentElements1.TopKFrequent(nums2, k2))}]");
        Console.WriteLine($"Test 3: [{string.Join(", ", TopKFrequentElements1.TopKFrequent(nums3, k3))}]");
        Console.WriteLine($"Test 4: [{string.Join(", ", TopKFrequentElements1.TopKFrequent(nums4, k4))}]");
        
        Console.WriteLine("\nSolution2 (Dictionary + PriorityQueue) Test Results:");
        Console.WriteLine($"Test 1: [{string.Join(", ", TopKFrequentElements2.TopKFrequent(nums1, k1))}]");
        Console.WriteLine($"Test 2: [{string.Join(", ", TopKFrequentElements2.TopKFrequent(nums2, k2))}]");
        Console.WriteLine($"Test 3: [{string.Join(", ", TopKFrequentElements2.TopKFrequent(nums3, k3))}]");
        Console.WriteLine($"Test 4: [{string.Join(", ", TopKFrequentElements2.TopKFrequent(nums4, k4))}]");
        
        Console.WriteLine("\nSolution3 (Bucket Sort) Test Results:");
        Console.WriteLine($"Test 1: [{string.Join(", ", TopKFrequentElements3.TopKFrequent(nums1, k1))}]");
        Console.WriteLine($"Test 2: [{string.Join(", ", TopKFrequentElements3.TopKFrequent(nums2, k2))}]");
        Console.WriteLine($"Test 3: [{string.Join(", ", TopKFrequentElements3.TopKFrequent(nums3, k3))}]");
        Console.WriteLine($"Test 4: [{string.Join(", ", TopKFrequentElements3.TopKFrequent(nums4, k4))}]");
    }
}

/**
 * Solution 1: Using Dictionary and LINQ
 * 
 * Time Complexity: O(n log n) - where n is the number of elements in the array
 *   - O(n) to count frequencies using Dictionary
 *   - O(n log n) for sorting by frequency
 * 
 * Space Complexity: O(n) for storing elements in the Dictionary
 */
public class TopKFrequentElements1
{
    public static int[] TopKFrequent(int[] nums, int k)
    {
        // Count the frequency of each element
        Dictionary<int, int> frequencyMap = new Dictionary<int, int>();
        foreach (int num in nums)
        {
            if (frequencyMap.ContainsKey(num))
            {
                frequencyMap[num]++;
            }
            else
            {
                frequencyMap[num] = 1;
            }
        }
        
        // Use LINQ to sort by frequency and take top k
        return frequencyMap
            .OrderByDescending(pair => pair.Value)
            .Take(k)
            .Select(pair => pair.Key)
            .ToArray();
    }
}

/**
 * Solution 2: Using Dictionary and Manual Priority Queue implementation
 * 
 * Time Complexity: O(n log k) - where n is the number of elements in the array
 *   - O(n) to count frequencies
 *   - O(n log k) to maintain a heap of size k
 * 
 * Space Complexity: O(n) for the Dictionary + O(k) for the heap = O(n)
 * 
 * Note: Using a simple sorted list to simulate a priority queue since C# only
 * got an official PriorityQueue in .NET 6
 */
public class TopKFrequentElements2
{
    public static int[] TopKFrequent(int[] nums, int k)
    {
        // Count the frequency of each element
        Dictionary<int, int> frequencyMap = new Dictionary<int, int>();
        foreach (int num in nums)
        {
            if (frequencyMap.ContainsKey(num))
            {
                frequencyMap[num]++;
            }
            else
            {
                frequencyMap[num] = 1;
            }
        }
        
        // Create a list to simulate a min heap of size k
        List<KeyValuePair<int, int>> minHeap = new List<KeyValuePair<int, int>>();
        
        // Maintain k most frequent elements
        foreach (var entry in frequencyMap)
        {
            minHeap.Add(new KeyValuePair<int, int>(entry.Key, entry.Value));
            // Sort to keep the least frequent at the beginning
            minHeap.Sort((a, b) => a.Value.CompareTo(b.Value));
            
            if (minHeap.Count > k)
            {
                minHeap.RemoveAt(0); // Remove least frequent
            }
        }
        
        // Extract the elements (keys)
        return minHeap.Select(pair => pair.Key).ToArray();
    }
}

/**
 * Solution 3: Using Bucket Sort
 * 
 * Time Complexity: O(n) - where n is the number of elements in the array
 *   - O(n) to count frequencies
 *   - O(n) to create and populate buckets
 *   - O(n) to collect the results
 * 
 * Space Complexity: O(n) for the Dictionary and buckets
 */
public class TopKFrequentElements3
{
    public static int[] TopKFrequent(int[] nums, int k)
    {
        // Count the frequency of each element
        Dictionary<int, int> frequencyMap = new Dictionary<int, int>();
        foreach (int num in nums)
        {
            if (frequencyMap.ContainsKey(num))
            {
                frequencyMap[num]++;
            }
            else
            {
                frequencyMap[num] = 1;
            }
        }
        
        // Create buckets for each frequency (1 to n)
        List<int>[] buckets = new List<int>[nums.Length + 1];
        for (int i = 0; i < buckets.Length; i++)
        {
            buckets[i] = new List<int>();
        }
        
        // Add elements to buckets based on frequency
        foreach (var entry in frequencyMap)
        {
            int num = entry.Key;
            int freq = entry.Value;
            buckets[freq].Add(num);
        }
        
        // Collect the top k frequent elements
        List<int> result = new List<int>();
        
        // Start from the highest frequency bucket
        for (int i = buckets.Length - 1; i >= 0 && result.Count < k; i--)
        {
            result.AddRange(buckets[i]);
            if (result.Count > k)
            {
                result = result.Take(k).ToList();
            }
        }
        
        return result.ToArray();
    }
}
