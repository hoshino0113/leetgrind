class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            curSum += nums[i];
            if (curSum < nums[i]) {
                maxSum = Math.max(nums[i], maxSum);
                curSum = nums[i];
            } else {
                maxSum = Math.max(curSum, maxSum);
            }
        }
        return maxSum;
    }
}