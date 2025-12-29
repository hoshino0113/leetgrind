/*Idea: Linear algebra time
To rotate a matrix in 90 degree clockwise, we need to transpose it first, then reverse all the rows:
- Transpose: r, c -> c, r
- Reverse a row: r, c -> r, n - c - 1

For other rotation:
90 = transpose + reverse row
180 = reverse row + reverse column
270 = transpose + reverse col
*/

class Solution {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //transpose the matrix;
        //Attention: We shouldn't iterate through all the element, as some element will
        //already be modified to the desired value before iterations!
        for (int col = 0; col < n; col++) {
            int row = col;
            while (row < m) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
                row++;
            }
        }

        //reverse every row:
        //do not iterate through all the elements for the same reason for trasnposing
        for (int row = 0; row < m; row++) {
            for (int col = 0; col <= (n - 1) / 2; col++){
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][n - col - 1];
                matrix[row][n - col - 1] = temp;
            }
        }
    }
}