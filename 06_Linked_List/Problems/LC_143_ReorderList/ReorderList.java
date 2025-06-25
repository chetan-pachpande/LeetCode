/**
 * LeetCode #143: Reorder List
 *
 * Problem: Given the head of a singly linked list L: L0 -> L1 -> ... -> Ln,
 * reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * Do not modify the values in the list's nodes; only the nodes themselves may be changed.
 *
 * Example:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
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

public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find the middle of the list
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        ListNode second = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (second != null) {
            ListNode nextTemp = second.next;
            second.next = prev;
            prev = second;
            second = nextTemp;
        }

        // Step 3: Merge two halves
        ListNode first = head;
        while (prev != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = prev.next;
            first.next = prev;
            prev.next = temp1;
            first = temp1;
            prev = temp2;
        }
    }
}
