//Find the hammingWeight of an int


class Solution {
    public int hammingWeight(int n) {
        int targetNum = n;
        int shiftNum = 30;
        int hammingWeight = 0;
        while (shiftNum >= 0) {
            int lsb = 0;
            lsb = targetNum & 1;
            if (lsb == 1) {
                hammingWeight += 1;
            } else {
                hammingWeight = hammingWeight;
            }
            shiftNum -= 1;
            targetNum = targetNum >> 1;
        }
        return hammingWeight;
    }
}