/*
Use DP:
To reach a node, the min cost would be the cost of the current node + min(Cost(n - 1), Cost(n - 2))
Store the previous 2 node costs, update then just like what we did to the Fibonacci Sequence

Feeling so defeated
*/
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int prevCost1 = cost[0];
        int prevCost2 = cost[1];

        for (int i = 2; i < n; i++) {
            int newCost = cost[i] + Math.min(prevCost1, prevCost2);
            prevCost1 = prevCost2;
            prevCost2 = newCost;
        }
        return Math.min(prevCost1, prevCost2);
    }
}

// Defeated:
//class Solution {
//     public int minCostClimbingStairs(int[] cost) {
//         int numStairs = cost.length;
//         if (numStairs == 0) return 0;
//         if (numStairs == 1) return cost[0];

//         //if we start from step 0
//         int startZero = 0;
//         int costStartZero = 0;
//         while (startZero < numStairs) {
//             if (cost[startZero] == 0) {
//                 startZero++;
//                 if (startZero == numStairs) break;
//                 costStartZero -= cost[startZero];
//             }
//             costStartZero += cost[startZero];
//             int oneStepCost = Integer.MAX_VALUE;
//             int twoStepCost = Integer.MAX_VALUE;
//             if (startZero + 1 < numStairs) {
//                 oneStepCost = Math.min(cost[startZero + 1], oneStepCost);
//                 if (startZero + 2 < numStairs) {
//                     twoStepCost = Math.min(cost[startZero + 2], twoStepCost);
//                 } else {
//                     break;
//                 }
//             }
//             // if (startZero + 2 == numStairs - 1) {
//             //     startZero += 2;
//             //     break;
//             // }
//             if (oneStepCost < twoStepCost) {
//                 startZero++;
//             } else {
//                 startZero += 2;
//             }
//         }
//         //if we start from step 1
//         int startOne = 1;
//         int costStartOne = 0;
//         while (startOne < numStairs) {
//             if (cost[startOne] == 0) {
//                 startOne++;
//                 if (startOne == numStairs) break;
//                 costStartOne -= cost[startOne];
//             }
//             costStartOne += cost[startOne];
//             int oneStepCost = Integer.MAX_VALUE;
//             int twoStepCost = Integer.MAX_VALUE;
//             if (startOne + 1 < numStairs) {
//                 oneStepCost = Math.min(cost[startOne + 1], oneStepCost);
//                 if (startOne + 2 < numStairs) {
//                     twoStepCost = Math.min(cost[startOne + 2], twoStepCost);
//                 } else {
//                     break;
//                 }
//             }
//             // if (startOne + 2 == numStairs - 1) {
//             //     startOne += 2;
//             //     break;
//             // }
//             if (oneStepCost < twoStepCost) {
//                 startOne++;
//             } else {
//                 startOne += 2;
//             }
//         }

//         return Math.min(costStartZero, costStartOne);
//     }
// }