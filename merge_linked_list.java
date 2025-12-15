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


//just be mindful for the poitners. Ya we are basically have a new head pointer that points to the result linkedlist and return it in the end
// go through both lists depending on which node has higher result
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pt1 = list1;
        ListNode pt2 = list2;
        ListNode head = null;
        ListNode newPt = null;

        if (pt1 == null) {
            return list2;
        }

        if (pt2 == null) {
            return list1;
        }

        while (pt1 != null || pt2 != null){
            if (pt1 == null) {
                newPt.next = pt2;
                break;
            }

            if (pt2 == null) {
                newPt.next = pt1;
                break;
            }

            if (pt1.val < pt2.val) {
                if (head == null) {
                    head = pt1;
                    newPt = head;
                } else {
                    newPt.next = pt1;
                    newPt = pt1;
                }

                pt1 = pt1.next;
            } else {
                if (head == null) {
                    head = pt2;
                    newPt = head;
                } else {
                    newPt.next = pt2;
                    newPt = pt2;
                }

                pt2 = pt2.next;
            }
        }
        return head;
    }
}