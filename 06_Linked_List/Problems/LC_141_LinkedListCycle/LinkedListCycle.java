/**
 * LeetCode #141: Linked List Cycle
 * 
 * Problem: Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by 
 * continuously following the next pointer. Return true if there is a cycle in the linked list. 
 * Otherwise, return false.
 * 
 * Example:
 * Input: head = [3,2,0,-4], where -4 points back to 2
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * 
 * Time Complexity: O(n) where n is the number of nodes in the linked list
 * Space Complexity: O(n) as we're using a HashSet to store visited nodes
 */

import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Implementation of ListNode class for testing
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    @Override
    public String toString() {
        return "ListNode(" + val + ")";
    }
}

/**
 * Hash Set approach - Using extra space
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class HashSetSolution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> seen = new HashSet<>();
        ListNode cur = head;

        while(cur != null) {
            if(!seen.contains(cur)) {
                seen.add(cur);
            } else {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
}

/**
 * Floyd's Cycle-Finding Algorithm (Tortoise and Hare) - Alternative Implementation
 * Time Complexity: O(n)
 * Space Complexity: O(1) - constant space
 */
class OptimalSolution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Fast pointer moves twice as fast as slow pointer
        // If there's a cycle, they will eventually meet
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move slow pointer one step
            fast = fast.next.next;     // Move fast pointer two steps
            
            if (slow == fast) {        // If they meet, there's a cycle
                return true;
            }
        }
        
        // If fast pointer reaches the end, there's no cycle
        return false;
    }
}

/**
 * Floyd's Cycle-Finding Algorithm (Tortoise and Hare) - Main Solution
 * Time Complexity: O(n)
 * Space Complexity: O(1) - constant space
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) return true;
        }

        return false;
    }
}

public class LinkedListCycle {
    
    // Method to create a test linked list with a cycle
    private static ListNode createCyclicList(int[] values, int cyclePos) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleNode = cyclePos == 0 ? head : null;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            
            if (i == cyclePos) {
                cycleNode = current;
            }
        }
        
        // Create cycle if cyclePos is valid
        if (cyclePos >= 0 && cyclePos < values.length) {
            current.next = cycleNode;
        }
        
        return head;
    }
    
    // Method to print a linked list (with cycle detection)
    private static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        
        ListNode current = head;
        int count = 0;
        final int MAX_NODES = 10; // Limit to avoid infinite printing if there's a cycle
        
        System.out.print("List: ");
        while (current != null && count < MAX_NODES) {
            System.out.print(current.val);
            current = current.next;
            if (current != null && count < MAX_NODES - 1) {
                System.out.print(" -> ");
            }
            count++;
        }
        
        if (count == MAX_NODES) {
            System.out.print(" -> ... (possibly cyclic)");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Cyclic List
        int[] values1 = {3, 2, 0, -4};
        ListNode list1 = createCyclicList(values1, 1); // Cycle at position 1 (points to node with val=2)
        
        HashSetSolution hashSetSolution = new HashSetSolution();
        Solution solution = new Solution(); // This is your implementation
        OptimalSolution optimalSolution = new OptimalSolution();
        
        System.out.println("Test Case 1: List with cycle at position 1");
        printLinkedList(list1);
        System.out.println("Has cycle (HashSet solution): " + hashSetSolution.hasCycle(list1));
        System.out.println("Has cycle (Floyd's algorithm - your version): " + solution.hasCycle(list1));
        System.out.println("Has cycle (Floyd's algorithm - alternative): " + optimalSolution.hasCycle(list1));
        
        // Test Case 2: Non-cyclic List
        int[] values2 = {1, 2, 3, 4, 5};
        ListNode list2 = createCyclicList(values2, -1); // No cycle
        
        System.out.println("\nTest Case 2: List with no cycle");
        printLinkedList(list2);
        System.out.println("Has cycle (HashSet solution): " + hashSetSolution.hasCycle(list2));
        System.out.println("Has cycle (Floyd's algorithm - your version): " + solution.hasCycle(list2));
        System.out.println("Has cycle (Floyd's algorithm - alternative): " + optimalSolution.hasCycle(list2));
        
        // Test Case 3: Single node with cycle to itself
        ListNode list3 = new ListNode(1);
        list3.next = list3; // Self cycle
        
        System.out.println("\nTest Case 3: Single node with cycle to itself");
        System.out.println("Has cycle (HashSet solution): " + hashSetSolution.hasCycle(list3));
        System.out.println("Has cycle (Floyd's algorithm - your version): " + solution.hasCycle(list3));
        System.out.println("Has cycle (Floyd's algorithm - alternative): " + optimalSolution.hasCycle(list3));
        
        // Test Case 4: Empty list
        ListNode list4 = null;
        
        System.out.println("\nTest Case 4: Empty list");
        System.out.println("Has cycle (HashSet solution): " + hashSetSolution.hasCycle(list4));
        System.out.println("Has cycle (Floyd's algorithm - your version): " + solution.hasCycle(list4));
        System.out.println("Has cycle (Floyd's algorithm - alternative): " + optimalSolution.hasCycle(list4));
    }
}
