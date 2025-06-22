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

### Solution 1: HashMap + Max Heap
1. Count the frequency of each element using a HashMap.
2. Use a max heap (priority queue) to sort elements by frequency.
3. Extract the top k elements.

**Time Complexity:** O(n log n) where n is the array's size
- O(n) to count frequencies using HashMap
- O(n log n) to add all elements to the heap
- O(k log n) to extract top k elements from the heap

**Space Complexity:** O(n) for the frequency map and the heap

**Advantages:**
- Straightforward implementation
- Good when k is close to n (the array size)

**Disadvantages:**
- Not optimal when k is much smaller than n

### Solution 2: HashMap + Min Heap Optimization
1. Count the frequency of each element using a HashMap.
2. Use a min-heap of size k to maintain the k most frequent elements.
3. If the heap exceeds size k, remove the least frequent element.

**Time Complexity:** O(n log k) for maintaining a heap of size k
- O(n) to count frequencies
- O(n log k) to maintain a heap of size k

**Space Complexity:** O(n) for the frequency map + O(k) for the heap

**Advantages:**
- More efficient than Solution 1 when k is much smaller than n
- Maintains only k elements in the heap at any time

**Disadvantages:**
- Still not as efficient as bucket sort for all cases

### Solution 3: Bucket Sort (Optimal Solution)
1. Count the frequency of each element.
2. Create buckets where each index represents a frequency and contains all numbers with that frequency.
3. Iterate through the buckets from highest frequency to lowest, adding elements to the result.

**Time Complexity:** O(n) - Linear time complexity
- O(n) to count frequencies
- O(n) to create and populate frequency buckets
- O(n) to collect the top k elements

**Space Complexity:** O(n) for the frequency map and buckets

**Advantages:**
- Optimal linear time complexity
- Works well for any value of k

**Disadvantages:**
- Requires more complex implementation than heap-based approaches

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
To run tests:
```bash
javac -cp .:path/to/junit TopKFrequentElementsTest.java
java -cp .:path/to/junit org.junit.runner.JUnitCore TopKFrequentElementsTest
```

## Related Problems
- LeetCode #215: Kth Largest Element in an Array
- LeetCode #692: Top K Frequent Words
- LeetCode #973: K Closest Points to Origin
