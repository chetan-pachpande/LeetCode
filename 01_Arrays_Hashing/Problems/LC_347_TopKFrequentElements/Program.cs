// Entry point for running the TopKFrequentElements implementations

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
