/*DO NOT USE GRAPH TO SOLVE THIS ISSUE!

Easy: Instead of starting from the inland grid, we start from the ocean! It will only flows from low to high altitude
from the ocean!! (sounds weird eh?)
For every grid that borders the oceans, we use dfs on all of its top, right, bottom, left neighbours. Immediately mark
them as true(reachable) once performed the altitude check.

Therefore, we need two boolean matrix, one for atlantic and one for pacific.
At the end, we compare the elements, if the same are marked true in both matrix, add it to the res list!
*/

class Solution {
    private void dfsDual(int row, int col, int[][]heights, boolean[][] ocean, int previousHeight) {
        //base condition, check bound:
        if (row < 0 || row >= heights.length || col < 0) return;
        if (col >= heights[row].length) return;

        //now we are within bounds, check if ocean[row][col] is true already
        //this prevents re-visiting the same grid(potential endless loop)
        //if so, it means we already visited the node, return
        if(ocean[row][col]) return;

        //now we are at normal grid, and we have not visited yet, or the grid is not reachable yet
        //if the new grid is not higher than the previous visited height, return
        int altitude = heights[row][col];
        if (altitude < previousHeight) return;

        //higher, so mark that grid as true
        ocean[row][col] = true;
        
        //call dfs on all of its neightbour: top, right, bottom, left
        dfsDual(row - 1, col, heights, ocean, altitude);
        dfsDual(row, col + 1, heights, ocean, altitude);
        dfsDual(row + 1, col, heights, ocean, altitude);
        dfsDual(row, col - 1, heights, ocean, altitude);

    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = 0;
        //get the max col num in the matrix:
        for (int row = 0; row < m; row++) {
            if (heights[row].length > n) {
                n = heights[row].length;
            }
        }

        //define two boolean matrix so that if the grid can reach the corresponding ocean,
        //atlantic[r][c] = true; or/and pacific[r][c] = true
        boolean[][] atlantic = new boolean[m][n];
        boolean[][] pacific = new boolean[m][n];

        //for pacific: We only check the first row, and first column only
        for (int row = 0; row < m; row++) {
            if (row == 0) {
                for (int col = 0; col < heights[0].length; col++) {
                    dfsDual(row, col, heights, pacific, 0);
                    continue;
                }
            }
            dfsDual(row, 0, heights, pacific, 0);
        }

        //for atlantic, we only check the last column and the last row
        for (int row = 0; row < m; row++) {
            if (row == m - 1) {
                for (int col = 0; col < heights[row].length; col++){
                    dfsDual(row, col, heights, atlantic, 0);
                    continue;
                }
            }
            dfsDual(row, heights[row].length - 1, heights, atlantic, 0);
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < heights[row].length; col++) {
                if (atlantic[row][col] && pacific[row][col]) {
                    res.add(Arrays.asList(row, col));
                }
            }
        }
        
        return res;
        
    }
}