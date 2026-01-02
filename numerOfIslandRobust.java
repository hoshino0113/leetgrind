class Solution {

    /**
     * DFS is responsible ONLY for marking all land cells
     * that belong to the same connected component (island).
     *
     * It does NOT count islands.
     */
    private void dfs(char[][] grid, int row, int col) {
        // 1. Boundary check: stop if out of grid
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {
            return;
        }

        // 2. Stop if this cell is water or already visited
        //    (we "visit" land by changing '1' -> '0')
        if (grid[row][col] == '0') {
            return;
        }

        // 3. Mark current land cell as visited
        //    This prevents counting it again later
        grid[row][col] = '0';

        // 4. Explore all 4 connected directions
        dfs(grid, row - 1, col); // up
        dfs(grid, row + 1, col); // down
        dfs(grid, row, col - 1); // left
        dfs(grid, row, col + 1); // right
    }

    /**
     * The outer loop scans the entire grid.
     * A new island is found ONLY when we see an unvisited '1'.
     *
     * Each DFS call "consumes" (marks) one entire island.
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        // 1. Scan every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // 2. If we find unvisited land,
                //    this is the start of a NEW island
                if (grid[r][c] == '1') {
                    islandCount++;

                    // 3. Use DFS to mark all connected land
                    //    so it will not be counted again
                    dfs(grid, r, c);
                }
            }
        }

        return islandCount;
    }
}
