# LeetCode Problem 347: Top K Frequent Elements

## Problem Description

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

### Examples

**Example 1:**
```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

**Example 2:**
```
Input: nums = [1], k = 1
Output: [1]
```

### Constraints
- 1 <= nums.length <= 10^5
- k is in the range [1, the number of unique elements in the array]
- It is guaranteed that the answer is unique.

### Follow up
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

## Solutions

### Solution 1: HashMap/Dictionary + Heap
1. Count the frequency of each element using a HashMap/Dictionary.
2. Use a heap (priority queue) to find the k most frequent elements.

**Time Complexity:** 
- O(n log k) when using a min-heap of size k
- O(n log n) when using a max-heap with all elements

**Space Complexity:** O(n) for the frequency map and the heap

**Implementation Details:**
- **Java**: Uses `PriorityQueue` with a custom comparator to create a max-heap.
- **C#**: Uses LINQ's `OrderByDescending` and `Take` methods in the first solution variant.
- **Python**: Uses `heapq.nlargest` with a key function to get the top k elements.

### Solution 2: HashMap/Dictionary + Min Heap Optimization
1. Count the frequency of each element using a HashMap/Dictionary.
2. Use a min-heap of size k to maintain the k most frequent elements.
3. If the heap exceeds size k, remove the least frequent element.

**Time Complexity:** O(n log k) for maintaining a heap of size k
**Space Complexity:** O(n) for the frequency map + O(k) for the heap

**Implementation Details:**
- **Java**: Uses `PriorityQueue` with a min-heap of size k, polling the smallest element when the size exceeds k.
- **C#**: Uses a manually maintained list to simulate a priority queue (min-heap).
- **Python**: Uses `Counter` and `most_common` method which internally uses a heap-based approach.

### Solution 3: Bucket Sort (Optimal Solution)
1. Count the frequency of each element.
2. Create buckets where each index represents a frequency and contains all numbers with that frequency.
3. Iterate through the buckets from highest frequency to lowest, adding elements to the result until we have k elements.

**Time Complexity:** O(n) - Linear time complexity
**Space Complexity:** O(n) for the frequency map and buckets

**Implementation Details:**
- **Java**: Creates an array of ArrayList where the index is the frequency.
- **C#**: Similar to Java implementation but uses List<int>[] for buckets.
- **Python**: Uses a list of lists to represent buckets and flattens the result.

### Performance Comparison

| Solution | Time Complexity | Space Complexity | Best For | Implementation Complexity |
|----------|----------------|------------------|----------|---------------------------|
| HashMap + Max Heap | O(n log n) | O(n) | Simplicity, when k is close to n | Low |
| HashMap + Min Heap | O(n log k) | O(n) | When k is small compared to n | Medium |
| Bucket Sort | O(n) | O(n) | Optimal solution for all cases | Medium-High |

### Example Performance (Theoretical)

For an array with n = 1,000,000 elements and k = 10:

| Solution | Approximate Operations |
|----------|------------------------|
| HashMap + Max Heap | 1,000,000 * log(1,000,000) ≈ 20,000,000 |
| HashMap + Min Heap | 1,000,000 * log(10) ≈ 3,300,000 |
| Bucket Sort | 1,000,000 (linear) |

This demonstrates why bucket sort is significantly faster for large arrays when we need only a few top frequent elements.

## How to Run

### Java
```bash
javac TopKFrequentElements.java
java TopKFrequentElements
```

### C#
```bash
dotnet run --project TopKFrequentElements.csproj
```

### Python
```bash
python3 top_k_frequent_elements.py
```

## Related Problems
- LeetCode #215: Kth Largest Element in an Array
- LeetCode #692: Top K Frequent Words
- LeetCode #973: K Closest Points to Origin
