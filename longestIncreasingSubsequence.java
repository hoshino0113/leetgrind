/*Idea:
Use DP
We iterate through the array, and for each number n:

- We need to know what is the longest increasing subsequence such that: Its largest element >= n
- We then replace this element with n;
- Therefore, we need an array lls such that:
    a. lls[i] represent the longest increasing sequence with length i + 1, ends with element lls[i] (the largest
    in the sequence)
- We then perform lower bound search (a special binary search) to find the lls[i] we need to update:
    a. To find the lls[i] that is the first element that is >= n;

-If number n > the largest element in the array, it means it can expand the array, add it to the array
- We then finally return the size() of the lls
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        //We are guarenteed that nums.length >= 1;
        //lls stands for Longest Length's smallest element
        List<Integer> lls = new ArrayList<>();
        lls.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lls.get(lls.size() - 1)) {
                lls.add(nums[i]);
                continue;
            }

            int left = 0;
            int right = lls.size() - 1;
            int mid = left + (right - left) / 2; //prevent overflow

            //perform lower bound search, stop when left ptr is >= right.
            //it means that the lower bound should be 0, meaning that the number is smaller
            //than the smallest element in the array
            while (left < right) {
                if (lls.get(mid) < nums[i]) {
                    //meaning we have to search the right
                    left = mid + 1;
                } else {
                    //if mid >= nums[i], don't forget to preserve the mid! (no - 1)
                    right = mid;
                }
                mid = left + (right - left) / 2;
            }
            
            //we then update the lls:
            lls.set(mid, nums[i]);
            
        }
        return lls.size();
    }

}