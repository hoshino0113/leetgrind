/*
Use two pointer 

If the subsequence is within the string, then we must be able to find it in one go
Simply go through the t with s to match. Once we get a match, return immediately
*/
//DP solution is possible, but overkill
class Solution {
    public boolean isSubsequence(String s, String t) {
        //base case, if s is empty return true
        if (s.isEmpty()) return true;
        //if t is empty return false
        if (t.isEmpty()) return false;

        int j = 0;

        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }

        return j == s.length();
    }
}