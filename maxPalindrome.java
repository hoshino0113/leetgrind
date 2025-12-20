//Use head and tail pointers, move simultaneously. NO, bad idea!
//We should use two pointers but expand from the centers.
//Centers: Odd and even centers
//For each center, we expand to right and left at the same time
//once there is a mismatch, we terminate the loop for that center
//record the longest palindromic string

//runtime is expected to be O(n**2)


class Solution {
    public String longestPalindrome(String s) {
        String maxPalindrome = "";
        int left = 0;
        int right = 0;

        for (int i = 0; i < s.length(); i++) {
            //odd center loop
            //left and right start from the same i
            left = i;
            right = i;
            String localLongest = "";
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String temp = s.substring(left, right + 1);
                if (temp.length() > localLongest.length()) {
                    localLongest = temp;
                }
                left--;
                right++;
            }
            //even center loop
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String temp = s.substring(left, right + 1);
                if (temp.length() > localLongest.length()) {
                    localLongest = temp;
                }
                left--;
                right++;
            }

            //compare localLongest with the maxPalindrome
            if (localLongest.length() > maxPalindrome.length()) {
                maxPalindrome = localLongest;
            }
        }
        return maxPalindrome;
    }
}