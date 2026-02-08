import java.util.*;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal - LeetCode Problem #105
 * NeetCode Solutions
 * 
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal 
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and 
 * return the binary tree.
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ConstructBinaryTreeFromPreorderAndInorderTraversal_neetcode {
    
    public static void main(String[] args) {
        // Test Case 1: Normal tree
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        // Expected tree:    3
        //                  / \
        //                 9  20
        //                   /  \
        //                  15   7
        
        // Test Case 2: Single node
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        
        // Test Case 3: Left skewed tree
        int[] preorder3 = {1, 2, 3};
        int[] inorder3 = {3, 2, 1};
        
        System.out.println("=== NeetCode Solution 1: Array Slicing Approach ===");
        Solution1 sol1 = new Solution1();
        TreeNode result1_1 = sol1.buildTree(preorder1.clone(), inorder1.clone());
        TreeNode result1_2 = sol1.buildTree(preorder2.clone(), inorder2.clone());
        TreeNode result1_3 = sol1.buildTree(preorder3.clone(), inorder3.clone());
        System.out.println("Test 1 Root: " + (result1_1 != null ? result1_1.val : "null"));
        System.out.println("Test 2 Root: " + (result1_2 != null ? result1_2.val : "null"));
        System.out.println("Test 3 Root: " + (result1_3 != null ? result1_3.val : "null"));
        
        System.out.println("\n=== NeetCode Solution 2: HashMap Optimization ===");
        Solution2 sol2 = new Solution2();
        TreeNode result2_1 = sol2.buildTree(preorder1.clone(), inorder1.clone());
        TreeNode result2_2 = sol2.buildTree(preorder2.clone(), inorder2.clone());
        TreeNode result2_3 = sol2.buildTree(preorder3.clone(), inorder3.clone());
        System.out.println("Test 1 Root: " + (result2_1 != null ? result2_1.val : "null"));
        System.out.println("Test 2 Root: " + (result2_2 != null ? result2_2.val : "null"));
        System.out.println("Test 3 Root: " + (result2_3 != null ? result2_3.val : "null"));
        
        // Verify tree structure
        System.out.println("\n=== Verification ===");
        System.out.print("Original inorder: ");
        printArray(inorder1);
        System.out.print("Result inorder: ");
        printInorder(result2_1);
        System.out.println();
    }
    
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    private static void printInorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        System.out.print(result);
    }
    
    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }
}

/**
 * NeetCode Solution 1: Array Slicing Approach
 * Simple and intuitive but less efficient due to array copying
 * Time Complexity: O(n²) - array copying overhead
 * Space Complexity: O(n²) - multiple array copies
 */
class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // Root is always first element in preorder
        TreeNode root = new TreeNode(preorder[0]);
        
        // Find root position in inorder array
        int mid = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                mid = i;
                break;
            }
        }

        // Create subarrays for left subtree
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);
        root.left = buildTree(leftPreorder, leftInorder);

        // Create subarrays for right subtree
        int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
        int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }
}

/**
 * NeetCode Solution 2: HashMap Optimization
 * Optimized version using HashMap for O(1) lookups and index tracking
 * Time Complexity: O(n) - each node visited once
 * Space Complexity: O(n) - HashMap + recursion stack
 */
class Solution2 {
    int pre_idx = 0;
    HashMap<Integer, Integer> indices = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Reset for multiple test cases
        pre_idx = 0;
        indices.clear();
        
        // Build HashMap for O(1) inorder index lookup
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }
        
        return dfs(preorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int l, int r) {
        // Base case: invalid range
        if (l > r) return null;
        
        // Get root value from preorder and increment index
        int root_val = preorder[pre_idx++];
        TreeNode root = new TreeNode(root_val);
        
        // Find root position in inorder using HashMap
        int mid = indices.get(root_val);
        
        // Build left subtree first (preorder: root -> left -> right)
        root.left = dfs(preorder, l, mid - 1);
        // Build right subtree
        root.right = dfs(preorder, mid + 1, r);
        
        return root;
    }
}


    1
   / \
  2   3
     / \
    4   5
