//THIS SOLUTION IS WRONG!
//We should not force some special case to be true in dfs! It might make some path unreachable

class Solution {
    private void dfsDual(int row, int col, int[][]heights, boolean[][] ocean, int previousHeight) {
        //base condition, check bound:
        if (row < 0 || row >= heights.length || col < 0) return;
        if (col >= heights[row].length) return;

        //now we are within bounds, check if ocean[row][col] is true already
        //if so, it means we already visited the node, return
        if(ocean[row][col]) return;

        ////////////////////////////////////////////////////////////////////
        /// PROBLEM ZONE: This is forcing the flow from pacific:
        /// It fails the case of heights = [[2, 1],[1,2]]
        /// visually: 2 1
        ///           1 2
        /// when first reach top left corner 2, it will call the later dfs on top, right, bottom, left with no issue
        /// BUT, when it reaches the top right 1, it forces mark that true by the line below, it then reach the 
        /// altitude check, which 1 < 2 return false. Then, the following dfs check got blocked FOREVER!
        /// It will make the bottom right 2 never able to reach the pacific!!
        /// removing the hack will make the solution correct!
        //if we are visiting top right, or bottom left, boolean is true
        if (row == 0 && col == heights[0].length - 1) {
            ocean[0][col] = true;
        }
        if (row == heights.length - 1 && col == 0) {
            ocean[row][0] = true;
        }
        ////////////////////////////////////////////////////////////////////
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