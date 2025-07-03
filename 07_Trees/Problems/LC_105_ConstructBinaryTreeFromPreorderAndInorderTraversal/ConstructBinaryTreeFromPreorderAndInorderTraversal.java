import java.util.*;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal - LeetCode Problem #105
 * 
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal 
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and 
 * return the binary tree.
 * 
 * Constraints:
 * - 1 <= preorder.length <= 3000
 * - inorder.length == preorder.length
 * - -3000 <= preorder[i], inorder[i] <= 3000
 * - preorder and inorder consist of unique values
 * - Each value of inorder also appears in preorder
 * - preorder is guaranteed to be the preorder traversal of the tree
 * - inorder is guaranteed to be the inorder traversal of the tree
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

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    
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
        // Expected tree: -1
        
        // Test Case 3: Left skewed tree
        int[] preorder3 = {1, 2, 3};
        int[] inorder3 = {3, 2, 1};
        // Expected tree:  1
        //                /
        //               2
        //              /
        //             3
        
        System.out.println("=== Testing Solution 1: HashMap with Recursion ===");
        TreeNode result1_1 = Solution1.buildTree(preorder1, inorder1);
        TreeNode result1_2 = Solution1.buildTree(preorder2, inorder2);
        TreeNode result1_3 = Solution1.buildTree(preorder3, inorder3);
        System.out.println("Test 1 Root: " + (result1_1 != null ? result1_1.val : "null"));
        System.out.println("Test 2 Root: " + (result1_2 != null ? result1_2.val : "null"));
        System.out.println("Test 3 Root: " + (result1_3 != null ? result1_3.val : "null"));
        
        System.out.println("\n=== Testing Solution 2: Array Index Approach ===");
        TreeNode result2_1 = Solution2.buildTree(preorder1.clone(), inorder1.clone());
        TreeNode result2_2 = Solution2.buildTree(preorder2.clone(), inorder2.clone());
        TreeNode result2_3 = Solution2.buildTree(preorder3.clone(), inorder3.clone());
        System.out.println("Test 1 Root: " + (result2_1 != null ? result2_1.val : "null"));
        System.out.println("Test 2 Root: " + (result2_2 != null ? result2_2.val : "null"));
        System.out.println("Test 3 Root: " + (result2_3 != null ? result2_3.val : "null"));
        
        System.out.println("\n=== Testing Solution 3: Iterative with Stack ===");
        TreeNode result3_1 = Solution3.buildTree(preorder1.clone(), inorder1.clone());
        TreeNode result3_2 = Solution3.buildTree(preorder2.clone(), inorder2.clone());
        TreeNode result3_3 = Solution3.buildTree(preorder3.clone(), inorder3.clone());
        System.out.println("Test 1 Root: " + (result3_1 != null ? result3_1.val : "null"));
        System.out.println("Test 2 Root: " + (result3_2 != null ? result3_2.val : "null"));
        System.out.println("Test 3 Root: " + (result3_3 != null ? result3_3.val : "null"));
        
        // Verify tree structure with inorder traversal
        System.out.println("\n=== Verification: Inorder Traversal ===");
        System.out.print("Original inorder1: ");
        printArray(inorder1);
        System.out.print("Result1 inorder: ");
        printInorder(result1_1);
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
 * Solution 1: HashMap for O(1) lookup + Recursion (Optimal)
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for HashMap and recursion stack
 */
class Solution1 {
    private static Map<Integer, Integer> inorderMap;
    private static int preorderIndex;
    
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Build HashMap for O(1) lookup of inorder indices
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        preorderIndex = 0;
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, int inorderStart, int inorderEnd) {
        // Base case: no elements to construct tree
        if (inorderStart > inorderEnd) {
            return null;
        }
        
        // Pick current root from preorder
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        // Root splits inorder list into left and right subtrees
        int inorderRootIndex = inorderMap.get(rootVal);
        
        // Build left subtree first (preorder: root -> left -> right)
        root.left = buildTreeHelper(preorder, inorderStart, inorderRootIndex - 1);
        // Build right subtree
        root.right = buildTreeHelper(preorder, inorderRootIndex + 1, inorderEnd);
        
        return root;
    }
}

/**
 * Solution 2: Array Search Approach (Less Optimal)
 * Time Complexity: O(n²) in worst case due to linear search for root index
 * Space Complexity: O(n) for recursion stack
 */
class Solution2 {
    private static int preorderIndex;
    
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        return buildTreeHelper(preorder, inorder, 0, inorder.length - 1);
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, int[] inorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd || preorderIndex >= preorder.length) {
            return null;
        }
        
        // Get root value from preorder
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        // Find root index in inorder array (O(n) operation)
        int inorderRootIndex = -1;
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == rootVal) {
                inorderRootIndex = i;
                break;
            }
        }
        
        // Build left and right subtrees
        root.left = buildTreeHelper(preorder, inorder, inorderStart, inorderRootIndex - 1);
        root.right = buildTreeHelper(preorder, inorder, inorderRootIndex + 1, inorderEnd);
        
        return root;
    }
}

/**
 * Solution 3: Iterative Approach using Stack (Advanced)
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for stack and HashMap
 */
class Solution3 {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        
        // Create root
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        int inorderIndex = 0;
        
        // Process remaining preorder elements
        for (int preorderIndex = 1; preorderIndex < preorder.length; preorderIndex++) {
            int preorderVal = preorder[preorderIndex];
            TreeNode node = stack.peek();
            
            // If stack top doesn't match inorder element, go left
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                // Pop stack until we find the correct parent for right child
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                // Add right child
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        
        return root;
    }
}

/**
 * Solution 4: Divide and Conquer without Global Variables (Clean Approach)
 * Time Complexity: O(n) with HashMap optimization
 * Space Complexity: O(n) for HashMap and recursion stack
 */
class Solution4 {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Build index map for O(1) lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, preorder.length - 1, 
                              inorder, 0, inorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                          int[] inorder, int inStart, int inEnd,
                                          Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        // Root is first element in preorder range
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // Find root position in inorder
        int rootIndex = inorderMap.get(preorder[preStart]);
        int leftTreeSize = rootIndex - inStart;
        
        // Recursively build left and right subtrees
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftTreeSize,
                                   inorder, inStart, rootIndex - 1, inorderMap);
        root.right = buildTreeHelper(preorder, preStart + leftTreeSize + 1, preEnd,
                                    inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
}
