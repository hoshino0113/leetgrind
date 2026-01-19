/* In this question, we are asked to count the number of combinations that sum up to a target value. 
The order matters.

Core idea: Let the target number be t, and the given numbers be nums[].
- To get target number t, we iterate through all the numbers in nums[].
- If the target number t is reachable by using number n in nums[], 
then the remaining number to reach t is (t - n). 
The number of ways to reach t is the sum of all possible ways to reach (t - n). For all ns in nums[].
- We can use DP to store the number of ways to reach each number from 0 to t.

We start from 0 to t, for each number i, we iterate through all numbers in nums[].
*/

class Solution {
    public int combinationSum4(int[] nums, int target) {
        //dp[i] represent the possible ways to achive number i
        int[] dp = new int[target + 1];

        //there are exactly 1 way to achive 0, which is pick nothing
        dp[0] = 1;

        for (int i = 0; i <= target; i++) {
            for (int n : nums) {
                if (i - n < 0) continue;

                dp[i] += dp[i - n];
            }
        }

        return dp[target];
    }
}