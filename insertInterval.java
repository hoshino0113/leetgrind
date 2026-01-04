/*We got mislead by the leetcode hint to use binary search... :( but nice review on the BS!

Instead, we can do this in O(n) time without using the BS.
We initialze a list of array, since we know we want to keep adding interval, so 2D array won't work well.
- 1.Keep adding the interval from the original intervals as long as interval[i][1] < newInterval[0], because
we are sure the newInterval will apprear after them!
- 2.Once we stop, we know the new Interval has hit a point where its start might be located within an existing 
interval OR it has come to the end and its the biggest and will be added to the end of the intervals;
 a.If the start is within an interval, then we know the start will be that interval (let's call it newStart) 
 but we don't know if the end of the new interval will overlap with other following invervals. 
 So we increment i to find the interval that has an end >= new interval's end.
 Meanwhile, we do not want to lose the starting point so we keep using min() to keep the newStart.
 b. If the start is not within an interval, meaning it bigger than the largest interval in the original intervals,
 we proceed to the next step.
- 3.We then add the new interval with the appropriate start and end.
- 4.We might have some leftover intervals from the original intervals, add them
- Convert the List<int[]> to int[][] using: res.toArray(new int[res.size()][]). Since res is List<int[]> 
- Return
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        //base case:
        if (intervals.length == 0) {
            int[][] res = {newInterval};
            return res;
        }

        List<int[]> res = new ArrayList<>();
        int i = 0;
        int startNew = newInterval[0];
        int endNew = newInterval[1];

        //1. add the interval to the list while interval[i][1] < startNew, because they will come before the newInterval
        while (i < intervals.length && intervals[i][1] < startNew) {
            res.add(intervals[i]);
            i++;
        }

        //2. now we have encounter a scenario where intervals[i][1] >= startNew, we perform inserting and merging while:
        // startEnd >= intervals[i][0] is true
        while (i < intervals.length && intervals[i][0] <= endNew) {
            startNew = Math.min(startNew, intervals[i][0]);
            endNew = Math.max(endNew, intervals[i][1]);
            i++;
        }
        //3. add the merged interval to the list
        int[] mergedInterval = new int[2];
        mergedInterval[0] = startNew;
        mergedInterval[1] = endNew;
        res.add(mergedInterval);

        //4. after insertion and merging, we might have some leftover in the original intervals, add them in:
        while (i < intervals.length) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}