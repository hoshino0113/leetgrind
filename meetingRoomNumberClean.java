/* A more efficient solutionm than our original one
O(nlog(n))
Keep a min-heap of current room end times

For each meeting:

if earliest-ending room ends <= start, reuse it (pop then push new end)

else need a new room (push new end)

Heap size = rooms needed

*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        //base case: 
        if (intervals == null || intervals.length == 0) return 0;
        
        //no special case for intervals.length == 1!!!!!!!

        //sort the intervals based on their start time:
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //Initialze a priority queue that store the meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {
            int newStart = meeting[0];
            int newEnd = meeting[1];
            if (!minHeap.isEmpty() && minHeap.peek() <= newStart) {
                minHeap.poll();
            }
            minHeap.offer(newEnd);
        }

        return minHeap.size();
    }
}