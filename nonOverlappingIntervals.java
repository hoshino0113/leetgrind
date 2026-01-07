/*Idea:
First we sort the array
An interval overlaps with another one iff one of the following is true:
- The start of the interval is smaller than the previous end
- The end of the interval is bigger than the next start
Therefore, whenever there is an overlap while we traverse the array,
we only remove the intervals that has a higher ending
since it will fuck up more intervals later
*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 1) return 0;

        //sort the intervals
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int res = 0;

        int[] good = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];

            if (next[0] < good[1]) {
                if (next[1] >= good[1]) {
                    res++;
                    continue;
                } else {
                    res++;
                    good = next;
                }
            } else {
                good = next;
            }
        }

        return res;
    }
}