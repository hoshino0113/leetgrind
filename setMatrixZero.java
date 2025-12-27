/*The following solution use space O(m + n) to solve this issue,
which is the standard but not he most efficient
A better solution is shown in a separate file which uses O(1) space by
implementing storing 0 in first row & col technique

For this solution, the idea is use two separate hashsets (one for row and one
for col), to store the rows and cols we want to zero out*/

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        /*zeros will store the element's locations, with key = row, value = col
        we simply perform zero out later based on the key and value*/
        HashSet<Integer> rowZeros = new HashSet<>();
        HashSet<Integer> colZeros = new HashSet<>();

        //traverse row by row to get the locations of all the zeros in the matrix

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 0) {
                    if (!rowZeros.contains(row)) {
                        rowZeros.add(row);
                    }
                    if (!colZeros.contains(col)) {
                        colZeros.add(col);
                    }
                }
            }
        }

        //now rowZeros and colZeros should contain the rows and cols that need to be zero
        //we perform zero out now
        //row zero first:
        if (!rowZeros.isEmpty()) {
            for (int row : rowZeros) {
                for (int col = 0; col < n; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        //col zero:
        if (!colZeros.isEmpty()) {
            for (int col : colZeros) {
                for (int row = 0; row < m; row++){
                    matrix[row][col] = 0;
                }
            }
        }

    }
}