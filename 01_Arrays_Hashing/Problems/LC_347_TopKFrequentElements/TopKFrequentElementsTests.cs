using System;
using System.Collections.Generic;
using System.Linq;
using NUnit.Framework;

[TestFixture]
public class TopKFrequentElementsTests
{
    [Test]
    public void TestSolution1()
    {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements1.TopKFrequent(nums1, k1);
        AssertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements1.TopKFrequent(nums2, k2);
        AssertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements1.TopKFrequent(nums3, k3);
        AssertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements1.TopKFrequent(nums4, k4);
        AssertArrayContainsSameElements(expected4, result4);
    }
    
    [Test]
    public void TestSolution2()
    {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements2.TopKFrequent(nums1, k1);
        AssertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements2.TopKFrequent(nums2, k2);
        AssertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements2.TopKFrequent(nums3, k3);
        AssertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements2.TopKFrequent(nums4, k4);
        AssertArrayContainsSameElements(expected4, result4);
    }
    
    [Test]
    public void TestSolution3()
    {
        // Test case 1: Normal case with duplicates
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = TopKFrequentElements3.TopKFrequent(nums1, k1);
        AssertArrayContainsSameElements(expected1, result1);
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        int[] result2 = TopKFrequentElements3.TopKFrequent(nums2, k2);
        AssertArrayContainsSameElements(expected2, result2);
        
        // Test case 3: All elements have same frequency
        int[] nums3 = {1, 2};
        int k3 = 2;
        int[] expected3 = {1, 2};
        int[] result3 = TopKFrequentElements3.TopKFrequent(nums3, k3);
        AssertArrayContainsSameElements(expected3, result3);
        
        // Test case 4: Negative numbers
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3};
        int k4 = 2;
        int[] expected4 = {-1, 2};
        int[] result4 = TopKFrequentElements3.TopKFrequent(nums4, k4);
        AssertArrayContainsSameElements(expected4, result4);
    }
    
    // Helper method to check if two arrays contain the same elements (regardless of order)
    private void AssertArrayContainsSameElements(int[] expected, int[] actual)
    {
        Assert.That(actual.Length, Is.EqualTo(expected.Length));
        foreach (int item in expected)
        {
            Assert.That(actual.Contains(item), Is.True);
        }
    }
}
