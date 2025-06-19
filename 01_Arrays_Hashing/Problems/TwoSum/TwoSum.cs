using System;
using System.Collections.Generic;

public class TwoSum
{
    public static int[] Solution(int[] nums, int target)
    {
        Dictionary<int, int> map = new Dictionary<int, int>();
        
        for (int i = 0; i < nums.Length; i++)
        {
            int complement = target - nums[i];
            if (map.ContainsKey(complement))
            {
                return new int[] { map[complement], i };
            }
            
            if (!map.ContainsKey(nums[i]))
            {
                map.Add(nums[i], i);
            }
        }
        
        return new int[] { -1, -1 };
    }
    
    public static void Main(string[] args)
    {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = Solution(nums, target);
        
        Console.WriteLine($"Indices: [{result[0]}, {result[1]}]");
    }
}
