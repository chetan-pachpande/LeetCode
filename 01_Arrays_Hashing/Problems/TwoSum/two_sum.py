def two_sum(nums, target):
    # Create a dictionary to store numbers and their indices
    hash_map = {}
    
    for i, num in enumerate(nums):
        complement = target - num
        
        # If the complement is in the dictionary, return the indices
        if complement in hash_map:
            return [hash_map[complement], i]
        
        # Add the current number and its index to the dictionary
        hash_map[num] = i
    
    return [-1, -1]

if __name__ == "__main__":
    nums = [2, 7, 11, 15]
    target = 9
    result = two_sum(nums, target)
    print(f"Indices: {result}")
