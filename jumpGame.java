class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        boolean[] bool = new boolean[len];
        int max = 0;
        bool[0] = true;

        for (int i = 0; i < len; i++) {
            if (!bool[i]) continue;
            int value = nums[i];
            if (value + i >= len - 1) return true;
            int temp = max;
            max = Math.max(max, i + value);
            for (int j = temp; j <= max; j++) {
                bool[j] = true;
            }
        }

        return bool[len - 1];
    }
}