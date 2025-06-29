# LC_230: Kth Smallest Element in a BST

## Problem Description
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

## Examples
**Example 1:**
```
Input: root = [3,1,4,null,2], k = 1
Output: 1
```

**Example 2:**
```
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
```

## Constraints
- The number of nodes in the tree is n.
- 1 <= k <= n <= 10^4
- 0 <= Node.val <= 10^4

## Solutions
1. **Solution_Iterative.java** - Iterative approach using stack for in-order traversal with creative royal procession comments
2. **Solution_Recursive.java** - Basic recursive approach collecting all elements in ArrayList
3. **Solution_Optimal_Recursive.java** - Optimal recursive approach with early termination using StateTracker

## Time Complexity
- **Iterative**: O(H + k) where H is the height of the tree
- **Basic Recursive**: O(n) where n is the number of nodes (visits all nodes)
- **Optimal Recursive**: O(H + k) where H is the height of the tree (early termination)

## Space Complexity
- **Iterative**: O(H) for the stack
- **Basic Recursive**: O(n) for the ArrayList + O(H) for recursion stack
- **Optimal Recursive**: O(H) for the recursion stack only

## Approach
The key insight is that in a BST, an in-order traversal visits nodes in ascending order. Therefore, the kth node visited during in-order traversal will be the kth smallest element.
