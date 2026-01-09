class Solution {
    public int coinChange(int[] coins, int amount) {

        // Using an array instead of HashMap for DP:
        // - Faster access (O(1))
        // - No boxing/unboxing overhead
        // dp[a] will store the minimum number of coins needed to make amount a
        int[] dp = new int[amount + 1];

        // Initialize all entries with a sentinel value (amount + 1),
        // which represents "unreachable".
        // This works because the maximum number of coins needed
        // can never exceed 'amount' (using coin 1 repeatedly).
        Arrays.fill(dp, amount + 1);

        // Base case:
        // To make amount 0, we need 0 coins.
        dp[0] = 0;

        // Build the solution bottom-up:
        // For each amount from 1 to 'amount',
        // compute the minimum coins needed.
        for (int a = 1; a <= amount; a++) {

            // Try every coin as the *last coin* used to form amount a
            for (int c : coins) {

                // If coin c can contribute to amount a,
                // then we check the best way to form (a - c)
                if (c <= a) {

                    // If dp[a - c] is reachable, then
                    // dp[a - c] + 1 is a candidate solution for dp[a]
                    //Then we update the best candidate solution for the best solution we have found so far
                    // for dp[a]
                    dp[a] = Math.min(dp[a], dp[a - c] + 1);
                }
            }
        }

        // If dp[amount] is still the sentinel value,
        // then the amount cannot be formed with given coins
        return dp[amount] > amount ? -1 : dp[amount];
    }
}


// Correct but the runtime is shit:
// class Solution {
//     Map<Integer, Integer> combos = new HashMap<>();

//     public int coinChange(int[] coins, int amount) {
//         if (amount == 0) return 0;
//         if (combos.containsKey(amount)) return combos.get(amount);

//         int minCoins = Integer.MAX_VALUE;
//         for (int c : coins) {
//             if (c > amount) continue;
//             int coinCombo = 0;
//             if (this.combos.containsKey(amount - c)) {
//                 if (combos.get(amount - c) == Integer.MAX_VALUE) continue;
//                 coinCombo = 1 + combos.get(amount - c);
//             } else {
//                 int requiredCoins = coinChange(coins, amount - c);
//                 if (requiredCoins == -1) continue;
//                 coinCombo = 1 + requiredCoins;
//             }
//             minCoins = Math.min(minCoins, coinCombo);
//         }
//         if (minCoins == Integer.MAX_VALUE) {
//             combos.put(amount, Integer.MAX_VALUE);
//             return -1;
//         }
//         combos.put(amount, minCoins);
//         return minCoins;
//     }
// }