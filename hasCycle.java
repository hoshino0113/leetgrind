/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 /* We can use an extra array to solve this issue, but lets not go down with that path */

 //use two pointers, a fast and slow pointer; where the fast pointer traverse the linked list twice as fast as the slowpointer
 // if there is a cycle, the fast pointer will catch up with the slow pointer very soon
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while (fastPointer != null && slowPointer != null) {
            slowPointer = slowPointer.next;
            if (fastPointer.next != null) {
                fastPointer = fastPointer.next.next;
            } else {
                fastPointer = null;
            }
            
            if (slowPointer == fastPointer && slowPointer != null) {
                return true;
            }
        }
        return false;
    }
}