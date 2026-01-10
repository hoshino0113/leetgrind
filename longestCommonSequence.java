/* THIS QUESTION IS SO GOOD!
We need to use DP to solve this. The technique used to solve this question is classic and widely used!

First, we need to understand these concepts and incentives:
- For two strings text1 = abcde and text2 = ace, the longest common sequence is ace.
- Let i and j represent the ith char in text1, jth char in text2
- Therefore, text1.charAt(i - 1) = ith char in text1. If i = 2; then the char is text1.charAt(2 - 1) -> b
- When we are comparing these two words, we intuitively follow these steps:
    a. text1.charAt(0) vs text2.charAt(0) -> match, so lsc = a, length = 1
    b. text1.charAt(1) vs text2.charAt(1) -> b vs c, mismatch. Now, are we just stuck here and not making
    any progress? No. We need to skip either b or c but meanwhile, we do not want to forget about a, since
    a is 1 matching char that we have so far after all. So the question become, which char we skip? If we skip
    one char in that string (either text1 or text2), the number of char avaible for us to use in that string will
    be reduced by 1. For example, if we skip b in text1, we only have 1 char that we can use left for text1, which is a.
    If we skip c, we only have 1 char that we can use left for text2 which is a.
    You can also think in this way: Whenever there is a mismatch, we have to make progress because we don't want to
    get stuck. Therefore, we have to skip one of the char in one word to keep comparing. If we skip b, the lcs from the text1
    part would have one less char left that we can use to consider the + 1;
    To reflect this, we build a lcs matrix to record every lsc we have calculated so far.
    Therefore, we only skip the char that brings the minimal impact to the lcs. In the lcs matrix, we always pick the 
    number thats larger as the value for the current comparison with skip operation.
- We then simply need the lcs[m][n] to get the lcs between text1 and text2. (At mth char in text1 and nth char in text2,
what is the longest common sequence?)

LCS matrix for text1 = abcde and text2 = ace:
          Ø   a   c   e   ← text2 (columns)
        ----------------
Ø     |   0   0   0   0
a     |   0   1   1   1
b     |   0   1   1   1
c     |   0   1   2   2
d     |   0   1   2   2
e     |   0   1   2   3
↑
text1 (rows)

I am sorry for my poor explaintion, just learned this. I have attached a conversation history with our beloved friend
ChatGPT to clear the mist! THANK YOU GPT! I LOVE YOU!!❤️
The note file is called dpLongestCommonSequenceNote.pdf in the same directory as this code's.
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        //if (n > m) swap(text1, text2); to save space if we are using 1D array.
        
        //initialze the lcs matrix, and they should by default be filled with 0s
        int[][] lcs = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++){
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[m][n];
    }
}