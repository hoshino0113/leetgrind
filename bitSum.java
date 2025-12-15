// Use bitwise algorithm to solve this issue.
// No overflow simulation is required, but in python, we need to do that manually, since python int is unbound

class Solution {
    public int getSum(int a, int b) {
        while (b != 0){
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}