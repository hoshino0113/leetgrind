//there might be another way to do it which requires less space
// In this question, we are asked to count the number of unique paths from the top-left corner to the bottom-right corner of an m x n grid.
//Core idea: Use DP
//- Let dp[i][j] represent the number of unique paths to reach cell (i, j).
//- To reach cell (i, j), we can come from either the cell directly above it (i-1, j) or the cell directly to the left of it (i, j-1).
//- Therefore, the number of unique paths to reach cell (i, j) is the sum of the number of unique paths to reach the cell above it and the cell to the left of it.
//- The base case is the starting cell (1, 1), which has exactly 1 unique path to itself.
// 

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == 1 && c == 1) {
                    dp[r][c] = 1;
                    continue;
                }

                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }

        return dp[m][n];
    }
}