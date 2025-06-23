/*
 * Implementation of a singly linked list
 */

// Implementation for Singly Linked List
public class SinglyLinkedList {
    // Define ListNode class inside
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    
    ListNode head;
    ListNode tail;

    public SinglyLinkedList() {
        head = new ListNode(-1);
        tail = head;
    }

    public void insertEnd(int val) {
        tail.next = new ListNode(val);
        tail = tail.next; 
    }

    public void remove(int index) {
        int i = 0;
        ListNode curr = head;
        while (i < index && curr != null) {
            i++;
            curr = curr.next;
        }
        
        // Remove the node ahead of curr
        if (curr != null && curr.next != null) {
            if (curr.next == tail) {
                tail = curr;
            }
            curr.next = curr.next.next;
        }
    }

    public void print() {
        ListNode curr = head.next;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }

        System.out.println();
    }
    
    // Main method to verify the implementation
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        
        // Insert elements
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        
        System.out.println("Original list:");
        list.print();
        
        // Remove element at index 2 (the value 3)
        list.remove(2);
        System.out.println("After removing element at index 2:");
        list.print();
        
        // Remove element at index 0 (the value 1)
        list.remove(0);
        System.out.println("After removing element at index 0:");
        list.print();
        
        // Remove last element (the value 5)
        list.remove(2);
        System.out.println("After removing last element:");
        list.print();
        
        // Add more elements
        list.insertEnd(6);
        list.insertEnd(7);
        System.out.println("After adding elements 6 and 7:");
        list.print();
    }
}
