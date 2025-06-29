import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

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

class Solution_Iterative {
    /**
     * The Master of Ceremonies' 🤵 methodical Royal Procession.
     * @param root The King where the procession begins.
     * @param k The number of the heir we are looking for.
     * @return The value of the Kth heir.
     */
    public int kthSmallest(TreeNode root, int k) {
        // The Royal Waiting Chamber, where nobles wait their turn.
        Stack<TreeNode> waitingChamber = new Stack<>();
        TreeNode currentNode = root;

        // The procession continues as long as there are nobles to process.
        while (currentNode != null || !waitingChamber.isEmpty()) {
            
            // First, send all the youngest nobles (the entire left lineage) to the waiting chamber.
            while (currentNode != null) {
                waitingChamber.push(currentNode);
                currentNode = currentNode.left;
            }

            // Now, call the next noble from the chamber. This will be the next smallest heir.
            currentNode = waitingChamber.pop();
            
            // "That's one heir processed." The MC decrements his counter.
            k--;

            // "Have we found our Kth heir?"
            if (k == 0) {
                // The procession is over! We have found the rightful heir.
                return currentNode.val;
            }

            // "The procession continues. Does this noble have an elder (right) lineage?"
            // If so, we will process that branch next.
            currentNode = currentNode.right;
        }
        
        // This should not be reached if k is valid.
        return -1;
    }
}

class Solution_Recursive {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        
        dfs(root, arr);
        return arr.get(k - 1);
    }

    private void dfs(TreeNode node, List<Integer> arr) {
        if (node == null) {
            return;
        }

        dfs(node.left, arr);
        arr.add(node.val);
        dfs(node.right, arr);
    }
}

public class TestSolution {
    public static void main(String[] args) {
        // Create test BST: [3,1,4,null,2]
        //     3
        //    / \
        //   1   4
        //    \
        //     2
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);

        // Create test BST: [5,3,6,2,4,null,null,1]
        //       5
        //      / \
        //     3   6
        //    / \
        //   2   4
        //  /
        // 1
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        Solution_Iterative iterativeSolution = new Solution_Iterative();
        Solution_Recursive recursiveSolution = new Solution_Recursive();

        System.out.println("=== Testing Kth Smallest Element in BST ===");
        System.out.println();

        // Test Case 1: root = [3,1,4,null,2], k = 1
        System.out.println("Test Case 1: BST [3,1,4,null,2], k = 1");
        System.out.println("Expected: 1");
        System.out.println("Iterative Result: " + iterativeSolution.kthSmallest(root1, 1));
        System.out.println("Recursive Result: " + recursiveSolution.kthSmallest(root1, 1));
        System.out.println();

        // Test Case 2: root = [5,3,6,2,4,null,null,1], k = 3
        System.out.println("Test Case 2: BST [5,3,6,2,4,null,null,1], k = 3");
        System.out.println("Expected: 3");
        System.out.println("Iterative Result: " + iterativeSolution.kthSmallest(root2, 3));
        System.out.println("Recursive Result: " + recursiveSolution.kthSmallest(root2, 3));
        System.out.println();

        // Test Case 3: root = [3,1,4,null,2], k = 2
        System.out.println("Test Case 3: BST [3,1,4,null,2], k = 2");
        System.out.println("Expected: 2");
        System.out.println("Iterative Result: " + iterativeSolution.kthSmallest(root1, 2));
        System.out.println("Recursive Result: " + recursiveSolution.kthSmallest(root1, 2));
        System.out.println();

        // Test Case 4: root = [5,3,6,2,4,null,null,1], k = 4
        System.out.println("Test Case 4: BST [5,3,6,2,4,null,null,1], k = 4");
        System.out.println("Expected: 4");
        System.out.println("Iterative Result: " + iterativeSolution.kthSmallest(root2, 4));
        System.out.println("Recursive Result: " + recursiveSolution.kthSmallest(root2, 4));
        System.out.println();

        System.out.println("=== All tests completed! ===");
    }
}
