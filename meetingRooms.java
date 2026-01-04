/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], 
determine if a person could attend all meetings.

Sort based on the starting time, then only check if the next starting time is earlier than the previous end time
O(nlog(n)) + O(n)
*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        //base condition:
        if (intervals == null || intervals.length <= 1) return true;

        //sort the array based on the starting time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //perform checking:
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }

        return true;
    }
}