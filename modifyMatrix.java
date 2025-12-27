/*Modify the matrix so that every -1 in the matrix is changed to
the max value of that column.

Intuitively, we traverse the matrix and find the max while we are doing it.
Then we contruct a same matrix and modify all the -1 to the max values we find earlier.
We can reduce the number of iteration by constructing more arrays but since the 
memory is so expensive right now, forget about it for now :)

Trick: Instead of traversing row by row, we traverse col by col to get the max value
for each col
*/

class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        //we need to know the dimension of the matrix
        //for now, assume the matrix is not jagged
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] newMatrix = new int[m][n];
        int[] maxVal = new int[n];

        //Traverse col by col instead of row by row
        for (int col = 0; col < n; col++) {
            int temp = Integer.MIN_VALUE;
            for (int row = 0; row < m; row++) {
                if (matrix[row][col] > temp) {
                    temp = matrix[row][col];
                }
                //copy the value to the new matrix
                newMatrix[row][col] = matrix[row][col];
            }
            maxVal[col] = temp;
        }

        //now maxVal contains the max for each row at the correct col position

        // we change all the -1 in the new matrix to the correct max value
        for (int row = 0; row < newMatrix.length; row++) {
            for (int col = 0; col < newMatrix[row].length; col++) {
                if (newMatrix[row][col] == -1) {
                    newMatrix[row][col] = maxVal[col];
                }
            }
        }

        return newMatrix;
    }
}