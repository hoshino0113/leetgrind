/*The following solution use space O(1) to solve this issue.

Ideas:
- Determine if the first row and col have zeros, set two boolean variables
- Iterate throught the matrix row by row, if we encounter a 0, we set the
values at [0] for that rows and cols to be 0, indicating that row and vol
need to be zero out.
- Since we already have the two boolean values and that row and col need to
be zero out anyway, we can safely store the 0 at that location. (we know if
the first row and col need to be zero out by the two booleans derived earlier)
- We then perform zero out for the matrix
- Zero out first row and col if needed*/

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;

        int m = matrix.length;
        int n = matrix[0].length;

        for (int row = 0; row < m; row++) {
            if (matrix[row][0] == 0) {
                firstColZero = true;
            }
        }

        for (int col = 0; col < n; col++) {
            if (matrix[0][col] == 0){
                firstRowZero = true;
            }
        }

        //traverse the matrix row by row, starting from [1] if possible
        for (int row = 1; row < m; row++) {
            for(int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        //now we should be good to go
        //performing zero out for the matrix
        //ATTENTION: Must start from 1, otherwise we will have all zero matrix!(THINK WHY)
        for (int row = 1; row < m; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 0; col < n; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int col = 1; col < n; col++){
            if (matrix[0][col] == 0) {
                for (int row = 0; row < m; row++){
                    matrix[row][col] = 0;
                }
            }
        }

        //check if we need to zero out the first row and col:
        if (firstRowZero) {
            for (int col = 0; col < n; col++) {
                matrix[0][col] = 0;
            }
        }

        if (firstColZero) {
            for (int row = 0; row < m; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}