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


 // use two pointers again, when fast reach NULL, ht means the slow is poiting at the middle
 // then, reverse the second half of the lists,\
 // then we perform insertion to the first half
class Solution {
    private ListNode reverseList(ListNode head) {
        ListNode prevNode = null;
        ListNode nextNode = null;
        while (head != null) {
            nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }
        return prevNode;
    }
    
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        while (fast.next != null) {
            if (fast.next != null) {
                fast = fast.next;
            }
            if (fast.next != null) {
                fast = fast.next;
            }
            slow = slow.next;
        } // after this while loop, we will have fast that points to the null, while slow points to the mid
        ListNode slowHead = slow.next;
        slow.next = null;
        
        //reverse the second half, which start with slow.next:
        ListNode reversedSecondHalf = null;
        reversedSecondHalf = this.reverseList(slowHead);

        //now we perform insertion:
        ListNode nextNode = null;
        ListNode saveHead = head;
        while (reversedSecondHalf != null) {
            nextNode = head.next;
            head.next = reversedSecondHalf;
            reversedSecondHalf = reversedSecondHalf.next;
            head.next.next = nextNode;
            head = nextNode;
        }
        head = saveHead;
    }
    
}