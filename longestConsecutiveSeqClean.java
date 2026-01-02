import java.util.HashSet;
import java.util.Set;
//This is the clean version of longest consecutive sequence problem
class Solution {
    public int longestConsecutive(int[] nums) {
        // Edge case: empty array has no consecutive sequence
        if (nums == null || nums.length == 0) return 0;

        /*
         * Core idea:
         * - We only care whether a number exists or not.
         * - Order does NOT matter.
         * - Duplicates do NOT help extend a consecutive sequence.
         *
         * Therefore, a HashSet is sufficient:
         * - O(1) average-time existence checks
         * - Automatically removes duplicates
         *
         * A HashMap<Integer, Boolean> (visited) is NOT needed because:
         * - Any number that is "already visited" will fail the
         *   (x - 1 exists) check below, so it will never start a sequence again.
         */
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int longest = 0;

        /*
         * Key invariant (this is the heart of the problem):
         * A number x is the START of a consecutive sequence
         * IF AND ONLY IF (x - 1) does NOT exist in the set.
         *
         * - If (x - 1) exists, then x is NOT a start (it belongs to an earlier sequence).
         * - If (x - 1) does NOT exist, x is a true starting point.
         *
         * This invariant makes explicit "visited" tracking unnecessary.
         */
        for (int x : set) {

            // Only attempt to build a sequence from true starts
            if (!set.contains(x - 1)) {

                int current = x;
                int length = 1;

                /*
                 * Expand forward (+1) to count the full sequence.
                 * Each number is visited at most once across the entire algorithm
                 * because only 'true starts' trigger this loop.
                 */
                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                // Update the maximum sequence length found so far
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}
