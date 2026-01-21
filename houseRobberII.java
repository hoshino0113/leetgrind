class Solution {
    public int rob(int[] nums) {
        int len = nums.length;

        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);

        //now we know len >= 2;
        int[] robFirstDp = new int[len + 1];
        int[] robLastDp = new int[len + 1];
        robFirstDp[0] = 0;
        robFirstDp[1] = nums[0];
        robLastDp[0] = 0;
        robLastDp[2] = nums[1];

        //for rob first
        for (int i = 2; i < len; i++) {
            robFirstDp[i] = robFirstDp[i - 2] + nums[i - 1];
            robFirstDp[i] = Math.max(robFirstDp[i], robFirstDp[i - 1]);
        }

        //for rob last
        for (int i = 3; i <= len; i++) {
            robLastDp[i] = robLastDp[i - 2] + nums[i - 1];
            robLastDp[i] = Math.max(robLastDp[i], robLastDp[i - 1]);
        }

        // for (int i : robFirstDp) {
        //     System.out.println("Rob first returns: " + i);
        // }
        // for (int i : robLastDp) {
        //     System.out.println("Rob last returns: " + i);
        // }
        return Math.max(robFirstDp[len - 1], robLastDp[len]);
    }

}