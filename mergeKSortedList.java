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
 /*
 Thought 1:
 Use Priority Queue to solve this issue:
 - We iterate through all nodes in all the lists, add them to the PQ
 - We then reconstruct the list based on PQ
 Time complexity would be nlogn and space would be n.

 Thought 2:
 Still, use pq, but instead of adding all the nodes to the PQ:
 - We add all the heads of the list to the PQ, so pq.size() <= k
 - We repeatly keep polling the smallest element in the pq and re-add its next back to the pq

 Time complexity would be nlogk, n for n poll and offer operations and logk for each poll and offer.
 (since heap size max = k)
 space complexity is O(k)
 Lets do it!
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //base condition
        if (lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> line = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                line.offer(lists[i]);
            }
        }

        //dummy pointer will always points to the first node we inserted!
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        while (!line.isEmpty()) {
            ListNode head = line.poll();
            current.next = head;
            //don't forget to update the current ptr!!
            current = current.next;
            
            if (head.next != null) {
                line.offer(head.next);
            }
        }

        return dummy.next;
    }
}