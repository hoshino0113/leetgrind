/*
Idea: We need to first check the base case.
Then, we start from the first interval, mark it as the initial current interval
we are working on.
Then we start a loop, from the second interval, mark it as next interval we are comparing with
the current interval. If the current[1] >= next[0] we simply update the current[1] to be max(cur[1], next[1])
Then we keep looking with the next interval (i++) until the condition is no longer true; add cur to the result list
once the loop is over, do not forget to add the last cur!
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        //basic case
        if (intervals == null) return null;
        if (intervals.length == 0 || intervals.length == 1) return intervals;

        //sort the array
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();
        int[] cur = intervals[0];
        for (int i = 0; i < intervals.length; i++) {
            int[] next = intervals[i];
            
            if (cur[1] >= next[0]) {
                cur[1] = Math.max(cur[1], next[1]);
            } else {
                res.add(cur);
                cur = next;
            }
        }
        res.add(cur);
        return res.toArray(new int[res.size()][]);

    }
}

/*
Correct, efficient, but nasty solutions: Good job tho!
class Solution {
    public int[][] merge(int[][] intervals) {
        //basic case
        if (intervals == null) return null;
        if (intervals.length == 0 || intervals.length == 1) return intervals;

        //sort the array based on the start of the interval
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();

        int i = 0;
        while (i < intervals.length - 1) {
            while (i < intervals.length && intervals[i][1] < intervals[i + 1][0]) {
                res.add(intervals[i]);
                i++;
                if (i == intervals.length - 1) {
                    res.add(intervals[i]);
                    break;
                }
            }

            if (i == intervals.length - 1) {
                break;
            }

            int mergeStart = intervals[i][0];
            int mergeEnd = Math.max(intervals[i][1], intervals[i + 1][1]);
            i++;
            while (i < intervals.length - 1 && mergeEnd >= intervals[i + 1][0]) {
                mergeEnd = Math.max(intervals[i + 1][1], mergeEnd); 
                i++;
            }

            int[] merged = new int[2];
            merged[0] = mergeStart;
            merged[1] = mergeEnd;
            res.add(merged);
            i++;
            if (i == intervals.length - 1) {
                res.add(intervals[i]);
            }
        }
        
        return res.toArray(new int[res.size()][]);
    }
}
*/