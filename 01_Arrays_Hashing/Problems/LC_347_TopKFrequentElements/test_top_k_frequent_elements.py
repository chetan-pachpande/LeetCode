import unittest
from collections import Counter
from top_k_frequent_elements import Solution1, Solution2, Solution3

class TestTopKFrequentElements(unittest.TestCase):
    
    def test_solution1(self):
        # Test case 1: Normal case with duplicates
        nums1 = [1, 1, 1, 2, 2, 3]
        k1 = 2
        expected1 = [1, 2]
        result1 = Solution1.topKFrequent(nums1, k1)
        self.assertCountEqual(expected1, result1)
        
        # Test case 2: Single element
        nums2 = [1]
        k2 = 1
        expected2 = [1]
        result2 = Solution1.topKFrequent(nums2, k2)
        self.assertCountEqual(expected2, result2)
        
        # Test case 3: All elements have same frequency
        nums3 = [1, 2]
        k3 = 2
        expected3 = [1, 2]
        result3 = Solution1.topKFrequent(nums3, k3)
        self.assertCountEqual(expected3, result3)
        
        # Test case 4: Negative numbers
        nums4 = [4, 1, -1, 2, -1, 2, 3]
        k4 = 2
        expected4 = [-1, 2]
        result4 = Solution1.topKFrequent(nums4, k4)
        self.assertCountEqual(expected4, result4)
    
    def test_solution2(self):
        # Test case 1: Normal case with duplicates
        nums1 = [1, 1, 1, 2, 2, 3]
        k1 = 2
        expected1 = [1, 2]
        result1 = Solution2.topKFrequent(nums1, k1)
        self.assertCountEqual(expected1, result1)
        
        # Test case 2: Single element
        nums2 = [1]
        k2 = 1
        expected2 = [1]
        result2 = Solution2.topKFrequent(nums2, k2)
        self.assertCountEqual(expected2, result2)
        
        # Test case 3: All elements have same frequency
        nums3 = [1, 2]
        k3 = 2
        expected3 = [1, 2]
        result3 = Solution2.topKFrequent(nums3, k3)
        self.assertCountEqual(expected3, result3)
        
        # Test case 4: Negative numbers
        nums4 = [4, 1, -1, 2, -1, 2, 3]
        k4 = 2
        expected4 = [-1, 2]
        result4 = Solution2.topKFrequent(nums4, k4)
        self.assertCountEqual(expected4, result4)
    
    def test_solution3(self):
        # Test case 1: Normal case with duplicates
        nums1 = [1, 1, 1, 2, 2, 3]
        k1 = 2
        expected1 = [1, 2]
        result1 = Solution3.topKFrequent(nums1, k1)
        self.assertCountEqual(expected1, result1)
        
        # Test case 2: Single element
        nums2 = [1]
        k2 = 1
        expected2 = [1]
        result2 = Solution3.topKFrequent(nums2, k2)
        self.assertCountEqual(expected2, result2)
        
        # Test case 3: All elements have same frequency
        nums3 = [1, 2]
        k3 = 2
        expected3 = [1, 2]
        result3 = Solution3.topKFrequent(nums3, k3)
        self.assertCountEqual(expected3, result3)
        
        # Test case 4: Negative numbers
        nums4 = [4, 1, -1, 2, -1, 2, 3]
        k4 = 2
        expected4 = [-1, 2]
        result4 = Solution3.topKFrequent(nums4, k4)
        self.assertCountEqual(expected4, result4)

if __name__ == '__main__':
    unittest.main()
