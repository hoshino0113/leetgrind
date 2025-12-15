//gpt is complaining a lot about this question for skipping i = 0 and 1 case. But for this question,
// we are guarenteed that the n is positive and even, which means the LSB and MSB must both be 0.

// We use bit shifting to keep only the 1 bit we are interested. and or it with the result
// the result is initialized to 0, in java, 0 is 0000 0000  in bits
class Solution {
    public int reverseBits(int n) {
        int res = 0;
        int num = n;
        for (int i = 0; i < 32; i++) {
            num = n;
            if (i == 0 || i == 31) { continue; }
            if (i < 16) {
                num = num << i;
                num = num >>> 31;
                num = num << i;
                res = res | num;
            }
            if (i >= 16) {
                num = num >>> 31 - i;
                num = num << 31;
                num = num >>> 31 - i;
                res = res | num;
            }
        }
        return res;
    }
}
