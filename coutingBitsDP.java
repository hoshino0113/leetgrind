/*
This solution uses Dynamic Programming techniques.
If we divide a number by 2, it shift its binary to the right by 1.
Therefore, if we can know the hamming number of the number that we got after the division, 
plus the LSD (either 1 or 0, meaning either even or odd), then we get the total number of
1 in the binary of the original number.
To get the LSD: use n mod 2. If even then the result is 0, if odd then the result is 1;
*/

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            res[i] = res[i / 2] + i % 2;
        }
        return res;
    }
}