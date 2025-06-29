import java.util.Stack;

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
