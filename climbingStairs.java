/*
Use Fibonacci Sequence F(n) = F(n - 1) + F(n - 2) to solve this issue
FS starts from 0
We use bottom-up iterations version of FS.
We start from 1 stairs, 2 stairs all the way til we reach n stairs.

Why we can use FS to solve this issue:
- Because we leared FS in undergrad, so we know there is a thing called FS :)
OK let's be serious:
- We human can only safely(relatively) climb up by 1 or 2 stairs at a time. (Good for you if you can do more than that)
This means, if we climb from the bottom to top, each time we make a climb action, we either decrease the number of 
remaining stairs that need to be climbed by 1 or 2: n - 1, n - 2; where n is the total # stairs needed to be climbed, and
its fixed in that scenario.
- Therefore, the question has become: 
    a. If I climbed up by 1, how many ways I can climb up for the rest of n - 1 stairs?
    b. If I climbed up by 2, how many ways I can climb up for the rest of n - 2 stairs?
- Combine the result from these two problems, we get the total number we can climb up n stairs.
This is exactly the idea of the Fibonacci sequence: F(n) = F(n - 1) + F(n - 2), where n can be the total number of
stairs we need to climb up.

There are many ways to solve the FS, but some of the most efficient ways are:
- Bottom-Up (Recommended): We start from i = 1, F(0) = 0, F(1) = 1; graduatly perform additions until we reach i = n (inclusive); 
- Bottom-Up with tabulation: Same as above, but keep the intermediate values in an array.
- Top-Down: Use Recursion and Memorization to keep track of the intermediate value. And finally return the result
from the recursion
*/

class Solution {
    public int climbStairs(int n) {
        if (n < 2) return n;

        int a = 0;
        int b = 1;

        for (int i = 1; i <= n; i++){
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}