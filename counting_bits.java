class Solution {
    public int hammingNum(int n) {
        int res = 0;
        int targetNum = n;
        int shiftNum = 30;

        while (shiftNum >= 0) {
            int lsb = 0;
            lsb = targetNum & 1;

            if (lsb == 1) {
                res += 1;
            } else {
                res = res;
            }
            shiftNum -= 1;
            targetNum = targetNum >> 1;
        }
        
        return res;
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int targetNum = n;
        for (int i = 0; i <= n; i++) {
            int tmp = 0;
            tmp = hammingNum(i);
            res[i] = tmp;
        }

        return res;
    }
}