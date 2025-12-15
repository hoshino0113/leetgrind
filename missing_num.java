// we can sort the array and go through it one by one, in O(log(n)) time
// however, we iterate through the entire array, and while we do that, we:
// - Add up i to a variable a, and add up nums[i] to another variable b.
// - once we hit the last iteration, we simply substract b from a to get the result

class Solution {
    public int missingNumber(int[] nums) {
        int totalSum = 0;
        int expectedSum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            expectedSum += i;
        }
        return expectedSum - totalSum;
    }
}