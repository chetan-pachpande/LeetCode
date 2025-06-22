from collections import Counter
import heapq

"""
Top K Frequent Elements - LeetCode Problem #347

Given an integer array nums and an integer k, return the k most frequent elements.
You may return the answer in any order.

Constraints:
- 1 <= nums.length <= 10^5
- k is in the range [1, the number of unique elements in the array]
- It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n),
where n is the array's size.
"""

# ======================================================================
# Solution 1: Using Counter and heapq (Min Heap)
# Time Complexity: O(n log k) where n is the length of the array
# Space Complexity: O(n) for storing the counter
#
# This approach uses Python's Counter class to count frequencies and heapq
# to efficiently find the top k elements without fully sorting.
# ======================================================================
class Solution1:
    @staticmethod
    def topKFrequent(nums, k):
        # Handle edge cases
        if not nums:
            return []
        if k <= 0:
            return []
        if len(nums) == k:
            return nums
            
        # Count frequencies using Counter (optimized dictionary for counting)
        count = Counter(nums)
        
        # Use heapq.nlargest which maintains a min heap internally
        # and efficiently returns the k largest elements
        return heapq.nlargest(k, count.keys(), key=count.get)


# ======================================================================
# Solution 2: Using Counter's most_common method
# Time Complexity: O(n log n) where n is the length of the array
# Space Complexity: O(n) for storing the counter
#
# Counter.most_common() internally sorts all elements by their frequencies,
# which is simple to implement but less efficient than a heap-based approach
# when k is much smaller than n.
# ======================================================================
class Solution2:
    @staticmethod
    def topKFrequent(nums, k):
        # Handle edge cases
        if not nums:
            return []
        if k <= 0:
            return []
        if len(nums) == k:
            return nums
            
        # Count frequencies using Counter
        count = Counter(nums)
        
        # Use Counter's built-in most_common method to get top k elements
        # most_common returns a list of (element, count) tuples
        return [item[0] for item in count.most_common(k)]


# ======================================================================
# Solution 3: Using bucket sort (Optimal solution)
# Time Complexity: O(n) where n is the length of the array
# Space Complexity: O(n) for storing the counter and buckets
#
# This is the optimal solution with linear time complexity. We use buckets
# where the index represents the frequency, and each bucket contains all
# elements with that frequency. By iterating through the buckets from highest
# to lowest frequency, we get the elements in order of frequency.
# ======================================================================
class Solution3:
    @staticmethod
    def topKFrequent(nums, k):
        # Handle edge cases
        if not nums:
            return []
        if k <= 0:
            return []
        if len(nums) == k:
            return nums
            
        # Count frequencies
        count = Counter(nums)
        
        # Create buckets for each frequency (1 to n)
        # The index of the bucket represents the frequency
        n = len(nums)
        buckets = [[] for _ in range(n + 1)]
        
        # Put elements in their frequency buckets
        for num, freq in count.items():
            buckets[freq].append(num)
        
        # Flatten the buckets from highest frequency to lowest
        # and collect the top k elements
        result = []
        for i in range(n, -1, -1):
            result.extend(buckets[i])
            if len(result) >= k:
                return result[:k]
        
        # This should never be reached with the given constraints
        # but return the result as a safety measure
        return result


if __name__ == "__main__":
    # Test cases
    test_cases = [
        ([1, 1, 1, 2, 2, 3], 2),  # Expected: [1, 2]
        ([1], 1),                 # Expected: [1]
        ([1, 2], 2),              # Expected: [1, 2]
        ([4, 1, -1, 2, -1, 2, 3], 2)  # Expected: [-1, 2]
    ]
    
    # Test all solutions
    for i, (nums, k) in enumerate(test_cases, 1):
        print(f"Test {i}: nums = {nums}, k = {k}")
        print(f"Solution 1: {Solution1.topKFrequent(nums, k)}")
        print(f"Solution 2: {Solution2.topKFrequent(nums, k)}")
        print(f"Solution 3: {Solution3.topKFrequent(nums, k)}")
        print()
