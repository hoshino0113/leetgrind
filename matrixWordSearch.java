/*
Idea: We definetely want to start from each element in the matrix to search for the word. Unless
we find the word, we need to continue searching.
For each element in the matrix, we will do a DFS search to find the word with backtracking.
 
Which means, we need a dfs helper.
The dfs helper will take the current position in the matrix (row, col), the current index in the word (wordIndex),
the board itself, the word itself, and a visitorRecord matrix to record which element has been visited in the current path.

Inside the dfs, we need to check for the base cases, and then we mark the current element as visited, 
then we do dfs for all 4 directions (top, right, bottom, left).
If any of the dfs returns true, we return true.
If none of them returns true, we unmark the current element as visited (backtracking),it is important as
it allows other paths to use this element (rememeber even though the current path does not work out, 
it doesnt mean this element is disqualified for all paths)
*/

class Solution {
    //DFS helper function
    private boolean dfs(int row, int col, int wordIndex, char[][] board, String word, boolean[][] visitorRecord) {
        /* Base case checks:
        We only iterate the 4 directions for the current element if:
        - The current element is not out of bound (imporant, has to be checked first)
        - The current element has not been visited in the current path (has to be check the second)
        - The current element has the correct matching value to the word[i] */
        if (row >= board.length || col >= board[0].length ||
            row < 0 || col < 0) return false;
        if (visitorRecord[row][col]) return false;
        if (board[row][col] != word.charAt(wordIndex)) return false;
        

        if (wordIndex == word.length() - 1) return true;// only return true when everything matches and no more char to be matched

        //mark the element in the matrix to be visited for the current path
        visitorRecord[row][col] = true;
        //we now need to visit all the direction: top, right, bottom, left
        if (dfs(row - 1, col, wordIndex + 1, board, word, visitorRecord) ||
            dfs(row, col + 1, wordIndex + 1, board, word, visitorRecord) ||
            dfs(row + 1, col, wordIndex + 1, board, word, visitorRecord) ||
            dfs(row, col - 1, wordIndex + 1, board, word, visitorRecord)) {
                return true;
            }
        
        //unmark the visitorRecord for that element to prevent path corroptions
        //Because not able to match the word for the current path does not mean
        //this element disqualify for all the paths
        visitorRecord[row][col] = false;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visitorRecord = new boolean[m][n];//default initialzation to false
        boolean found = false;

        //We do DFS for all the element, until we find the word
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                found = dfs(row, col, 0, board, word, visitorRecord);
                if (found == true) break;
            }
            if (found == true) break;
        }
        return found;
    }
}