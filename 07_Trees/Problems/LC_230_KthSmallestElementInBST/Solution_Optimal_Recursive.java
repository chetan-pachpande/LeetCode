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

class Solution {

    // A helper class to pass state through recursive calls by reference.
    // This avoids using class-level variables and makes the function self-contained.
    private static class StateTracker {
        int k_counter; // The remaining number of elements to visit.
        int result;    // Stores the final answer once found.

        StateTracker(int k) {
            this.k_counter = k;
            this.result = 0;
        }
    }

    /**
     * The main function to find the Kth smallest element.
     * @param root The root of the BST.
     * @param k The target rank (e.g., k=1 for smallest, k=2 for 2nd smallest).
     * @return The value of the Kth smallest element.
     */
    public int kthSmallest(TreeNode root, int k) {
        // Create an object to track state through the recursion.
        StateTracker tracker = new StateTracker(k);
        
        // Start the in-order traversal.
        dfs(root, tracker);
        
        // The tracker object will have been updated with the result.
        return tracker.result;
    }

    /**
     * A recursive Depth-First Search that performs an in-order traversal.
     * It stops early as soon as the Kth element is found.
     * @param node The current node being visited.
     * @param tracker The shared state object.
     */
    private void dfs(TreeNode node, StateTracker tracker) {
        // Base case: If the node is null, end this path of recursion.
        if (node == null) {
            return;
        }

        // 1. Traverse the left subtree first.
        dfs(node.left, tracker);
        
        // After returning from the left subtree, check if the result has already been found.
        if (tracker.k_counter == 0) {
            return;
        }

        // 2. "Visit" the current node: decrement the counter.
        tracker.k_counter--;
        
        // Check if the current node is the Kth smallest element.
        if (tracker.k_counter == 0) {
            // If it is, store its value and terminate this path.
            tracker.result = node.val;
            return;
        }

        // 3. If the Kth element has not been found yet, traverse the right subtree.
        dfs(node.right, tracker);
    }

    /**
     * Main method to test the optimal recursive solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Create test BST: [3,1,4,null,2]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);

        // Create test BST: [5,3,6,2,4,null,null,1]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        System.out.println("=== Testing Optimal Recursive Solution ===");
        System.out.println();

        // Test Case 1
        System.out.println("Test Case 1: BST [3,1,4,null,2], k = 1");
        System.out.println("Expected: 1, Result: " + solution.kthSmallest(root1, 1));

        // Test Case 2
        System.out.println("Test Case 2: BST [5,3,6,2,4,null,null,1], k = 3");
        System.out.println("Expected: 3, Result: " + solution.kthSmallest(root2, 3));

        System.out.println();
        System.out.println("✅ Optimal approach benefits:");
        System.out.println("- Early termination when kth element found");
        System.out.println("- O(H + k) time complexity");
        System.out.println("- O(H) space complexity");
    }
}
