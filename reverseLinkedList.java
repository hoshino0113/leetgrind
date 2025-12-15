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
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prevPointer = null;
        ListNode nextPointer = null;
        while (head != null) {
            nextPointer = head.next;
            head.next = prevPointer; //change the pointer to its predecessor
            prevPointer = head;
            head = nextPointer; // now head points to the next node
        }
        return prevPointer; // return the last case
    }
}