using System;
using System.Collections.Generic;
using System.Linq;

public class ContainsDuplicate
{
    public static void Solution()
    {
        // Test cases
        int[] nums1 = {1, 2, 3, 1};  // true
        int[] nums2 = {1, 2, 3, 4};  // false
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};  // true

        // Testing all solution methods
        Console.WriteLine("Solution1 (Brute Force) Test Results:");
        Console.WriteLine($"Test 1: {ContainsDuplicate1.ContainsDup(nums1)}"); // Should return true
        Console.WriteLine($"Test 2: {ContainsDuplicate1.ContainsDup(nums2)}"); // Should return false
        Console.WriteLine($"Test 3: {ContainsDuplicate1.ContainsDup(nums3)}"); // Should return true
        
        Console.WriteLine("\nSolution2 (HashSet) Test Results:");
        Console.WriteLine($"Test 1: {ContainsDuplicate2.ContainsDup(nums1)}"); // Should return true
        Console.WriteLine($"Test 2: {ContainsDuplicate2.ContainsDup(nums2)}"); // Should return false
        Console.WriteLine($"Test 3: {ContainsDuplicate2.ContainsDup(nums3)}"); // Should return true
        
        Console.WriteLine("\nSolution3 (Sorting) Test Results:");
        Console.WriteLine($"Test 1: {ContainsDuplicate3.ContainsDup(nums1)}"); // Should return true
        Console.WriteLine($"Test 2: {ContainsDuplicate3.ContainsDup(nums2)}"); // Should return false
        Console.WriteLine($"Test 3: {ContainsDuplicate3.ContainsDup(nums3)}"); // Should return true
        
        Console.WriteLine("\nSolution4 (Dictionary) Test Results:");
        Console.WriteLine($"Test 1: {ContainsDuplicate4.ContainsDup(nums1)}"); // Should return true
        Console.WriteLine($"Test 2: {ContainsDuplicate4.ContainsDup(nums2)}"); // Should return false
        Console.WriteLine($"Test 3: {ContainsDuplicate4.ContainsDup(nums3)}"); // Should return true
        
        Console.WriteLine("\nSolution5 (LINQ) Test Results:");
        Console.WriteLine($"Test 1: {ContainsDuplicate5.ContainsDup(nums1)}"); // Should return true
        Console.WriteLine($"Test 2: {ContainsDuplicate5.ContainsDup(nums2)}"); // Should return false
        Console.WriteLine($"Test 3: {ContainsDuplicate5.ContainsDup(nums3)}"); // Should return true
    }
    
    public static void Main(string[] args)
    {
        Solution();
    }
}

// Solution 1: Brute Force approach - comparing each element with all others
// Time Complexity: O(n²) where n is the length of the array
// Space Complexity: O(1) - constant extra space used
public class ContainsDuplicate1
{
    public static bool ContainsDup(int[] nums)
    {
        for (int i = 0; i < nums.Length; i++)
        {
            for (int j = i + 1; j < nums.Length; j++)
            {
                if (nums[i] == nums[j])
                {
                    return true;
                }
            }
        }
        return false;
    }
}

// Solution 2: Using HashSet to check for duplicates (most efficient)
// Time Complexity: O(n) where n is the length of the array
// Space Complexity: O(n) for storing elements in the HashSet
public class ContainsDuplicate2
{
    public static bool ContainsDup(int[] nums)
    {
        HashSet<int> set = new HashSet<int>();
        
        foreach (int num in nums)
        {
            if (set.Contains(num))
            {
                return true;
            }
            set.Add(num);
        }
        
        return false;
    }
}

// Solution 3: Using sorting technique
// Time Complexity: O(n log n) due to sorting operation
// Space Complexity: O(n) for the sorted copy of the array
public class ContainsDuplicate3
{
    public static bool ContainsDup(int[] nums)
    {
        if (nums == null || nums.Length <= 1) return false;
        
        // Create a copy of the array to avoid modifying the original
        int[] sortedNums = (int[])nums.Clone();
        Array.Sort(sortedNums);
        
        // Check adjacent elements for duplicates
        for (int i = 0; i < sortedNums.Length - 1; i++)
        {
            if (sortedNums[i] == sortedNums[i + 1])
            {
                return true;
            }
        }
        
        return false;
    }
}

// Solution 4: Using Dictionary to count occurrences
// Time Complexity: O(n) where n is the length of the array
// Space Complexity: O(n) for storing elements in the Dictionary
public class ContainsDuplicate4
{
    public static bool ContainsDup(int[] nums)
    {
        Dictionary<int, int> countMap = new Dictionary<int, int>();
        
        foreach (int num in nums)
        {
            if (countMap.ContainsKey(num))
            {
                return true;
            }
            else
            {
                countMap[num] = 1;
            }
        }
        
        return false;
    }
}

// Solution 5: Using LINQ (most concise but potentially less efficient)
// Time Complexity: O(n) where n is the length of the array
// Space Complexity: O(n) for storing the distinct elements collection
public class ContainsDuplicate5
{
    public static bool ContainsDup(int[] nums)
    {
        return nums.Length != nums.Distinct().Count();
    }
}
