/*!!!!!!!!!!!!This solution is accepted but conceptually wrong.!!!!!!!!!!!!!!!!!!!

Idea: We call dfs on every node, and inside every dfs, we determine if the current node will increase the number of island count
It will increase the count iff: 
- Has not been visited
- None of its neighbour is 1 and visited

This is correct most of the time, but we should not rely on the timing of the visit to determine if the current
node should increase the island count.
Instead, we should rely on the structure of the map. Please refer to the numberOfIslandRobust.java for the correct
solution.
This is kept just for reference.
*/

class Solution {
    private int islandCount = 0;

    private void dfsCount(char[][] grid, int row, int col, boolean[][] visited) {
        //base condition: boundary check:
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;

        //if we are visiting a water area, return immediately
        if (grid[row][col] == '0') return;

        //if visited, return immediately
        if (visited[row][col]) return;

        //we are not out of bound AND we are not visiting water, and we have not visited it, so now:
        //we need to know if one of our neighbout: up, right, down, left are land AND if they have been visited before
        //if one of them is land and have been visited before:
        boolean isConnected = false;
        //up:
        if (row - 1 >= 0) {
            if (grid[row - 1][col] == '1' && visited[row - 1][col]) {
                isConnected = true;
            }
        }
        //right:
        if (col + 1 < grid[0].length) {
            if (grid[row][col + 1] == '1' && visited[row][col + 1]) {
                isConnected = true;
            }
        }
        //down:
        if (row + 1 < grid.length) {
            if (grid[row + 1][col] == '1' && visited[row + 1][col]) {
                isConnected = true;
            }
        }
        //left:
        if (col - 1 >= 0) {
            if (grid[row][col - 1] == '1' && visited[row][col - 1]) {
                isConnected = true;
            }
        }

        //if not connected
        if (!isConnected) islandCount++;
        //mark the grid point as visited
        visited[row][col] = true;
        //keep exploring its neigbours:
        //up
        dfsCount(grid, row - 1, col, visited);
        //right
        dfsCount(grid, row, col + 1, visited);
        //down
        dfsCount(grid, row + 1, col, visited);
        //left
        dfsCount(grid, row, col - 1, visited);
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //create a visited matrix that has the same dimension as the grid
        //if we have visited the node, mark that as true in the dfs
        boolean[][] visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                dfsCount(grid, row, col, visited);
            }
        }

        return islandCount;
    }
}