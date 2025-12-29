/*
Core idea:
- We write 4 functions that help print the result: 
a.left to right
b.top to bottom
c.right to left
d.bottom to top
- They will be called recursively in the order indicated above
- They will be guided by 4 bounds: top, right, bottom, right:
a. top: Starting from the top (0), will be ++ once the row has been printed
b. right: Starting from the right (n - 1), will be -- once the column has been printed
c. bottom: Starting from the bottom (m - 1), will be -- once the row has been printed
d. left: Starting from the left (0), will be ++ once the column has been printed
- Once we print a row/column, we need to do the above modification for one of the value
- Becareful for the boundary issue, when two values collide, (eg left == right), it means
there is one last row/column (eg left==right means one last columnn left) to be printed
*/

class Solution {
    //Four helpers, each perform different col/row operations.
    //Guided by different starting and ending values
    private void printLeftToRight(int top, int left, int right, List<Integer> res, int[][] matrix) {
        int i = left;
        while (i <= right) {
            res.add(matrix[top][i]);
            i++;
        }
    }
    private void printTopToBottom(int right, int top, int bottom, List<Integer> res, int[][] matrix) {
        int i = top;
        while (i <= bottom) {
            res.add(matrix[i][right]);
            i++;
        }
    }
    private void printRightToLeft(int bottom, int right, int left, List<Integer> res, int[][] matrix) {
        int i = right;
        while (i >= left) {
            res.add(matrix[bottom][i]);
            i--;
        }
    }
    private void printBottomToTop(int left, int bottom, int top, List<Integer> res, int[][] matrix) {
        int i = bottom;
        while (i >= top) {
            res.add(matrix[i][left]);
            i--;
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();

        int top = 0;
        int right = n - 1;
        int left = 0;
        int bottom = m - 1;
        //perform printing in strict order recursively, until condition is met
        while (left <= right && top <= bottom) {
            if (top <= bottom) {
                printLeftToRight(top, left, right, res, matrix);
            }
            top++;

            if (right >= left) {
                printTopToBottom(right, top, bottom, res, matrix);
            }
            right--;

            if (top <= bottom) {
                printRightToLeft(bottom, right, left, res, matrix);
            }
            bottom--;

            if (left <= right) {
                printBottomToTop(left, bottom, top, res, matrix);
            }
            left++;
        }

        return res;
    }
}