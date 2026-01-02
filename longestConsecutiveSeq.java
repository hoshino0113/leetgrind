/* We are required to find the longest consecutive sequence in an unsorted array of integers 

This approach uses a hash map to store all numbers 
and then checks for each number whether it's the start of a sequence. 
Mark every number as visited when it's part of a sequence. 
Skip all numbers that have already been visited. 

The marking part is uncessary and can cause the code to run slower, even though
it is correct and still runs in O(n) but with a higher constant factor 

We just keep this solution as a reference, because it is our work!
Please refer to a better solution name longestConsecutiveSeqClean.java

Good job!*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Map<Integer, Boolean> dic = new HashMap<>();

        //visit every node, hashmap them
        for (int i : nums) {
            if (dic.containsKey(i)) continue;
            dic.put(i, false);
        }

        int longestSeq = 0;

        for (Map.Entry<Integer, Boolean> num : dic.entrySet()) {
            int n = num.getKey();
            //if we have visited this num before, skip it
            if (dic.get(n)) continue;
            //a number only needs to be explored(a true start) iff n - 1 does not exist
            if (dic.containsKey(n - 1)) continue;
            //mark the value as visited
            dic.put(n, true);

            //look for the +1 value
            n++; 
            int tempLength = 1;
            while (dic.containsKey(n)) {
                dic.put(n, true);
                tempLength++;
                n++;
            }

            longestSeq = Math.max(longestSeq, tempLength);
        }

        return longestSeq;
    }
}