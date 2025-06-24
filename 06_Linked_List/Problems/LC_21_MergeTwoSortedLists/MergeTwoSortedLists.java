/**
 * LeetCode #21: Merge Two Sorted Lists
 * 
 * Problem: Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * Input: l1 = [1,2,4], l2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * 
 * Time Complexity: O(n + m) where n and m are the lengths of the two lists
 * Space Complexity: O(1) as we're reusing the existing nodes
 */

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

class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 != null) return list2;
        if(list2 == null && list1 != null) return list1;

        ListNode dummy = new ListNode(0);
        ListNode node = dummy;

        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }

        if(list1 != null) {
            node.next= list1;
        } else if(list2 != null) {
            node.next = list2;
        }
        
        return dummy.next;
    }
}

public class MergeTwoSortedLists {
    // Main method to test the implementation
    public static void main(String[] args) {
        // Create two sorted linked lists
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        
        Solution solution = new Solution();
        ListNode merged = solution.mergeTwoLists(list1, list2);
        
        System.out.println("Merged List: " + merged);
    }
}
