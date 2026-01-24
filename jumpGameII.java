class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] numJump = new int[len];
        Arrays.fill(numJump, Integer.MAX_VALUE);
        numJump[0] = 0;
        int far = 0;

        for (int i = 0; i < len; i++) {
            int start = far;
            far = Math.max(far, i + nums[i]);
            while (start <= far && start < len) {
                numJump[start] = Math.min(numJump[start], numJump[i] + 1);
                start++;
            }
        }
        return numJump[len - 1];
    }
}