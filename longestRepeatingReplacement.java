/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. 
You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.

Core idea: We need to use sliding windows to solve this issue. The bigger the windows size is, the longer that substring is

We know that in order to get the longest substring that containing the same letter (after k of was subtituted from, for example:
ABADC becomes AAAAA due to k = 3, we substituted 1 B, 1 D and 1 C with 3 As), we notice that in the original substring before substitution, 
A is the most frequent char in this string. Therefore, an inferential deduction is that the longest substring that satisfied the condition
must be determined by the most frequent char in the window.

Therefore, we keep track of the most frequent char we have seen from the beginning, since it is the only factor that changes the window's size
*/



class Solution {
    public int characterReplacement(String s, int k) {
        int[] lookUp = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxSub = 0;

        for (int right = 0; right < s.length(); right++) {
            int i = s.charAt(right) - 'A'; //index for the new char in the array
            lookUp[i]++; //increase its frequency by 1
            maxFreq = Math.max(maxFreq, lookUp[i]); //what is the max frequency of a char that we have seen all the way from the beginning?

            //If we havent been seening the char we have seen repeatively, we need to shrink the window.
            // > k means that k can no longer hold the growth of the window without the support from the maxFreq char we have seen
            while ((right - left + 1) - maxFreq > k) {
                lookUp[s.charAt(left) - 'A']--;
                left++;
            }

            maxSub = Math.max(maxSub, right - left + 1); 
        }
        return maxSub;
    }
}

/* Nice try:
    public int characterReplacement(String s, int k) {
        int maxSub = 1;
        int left = 0;
        int right = 0;

        while (left < s.length()) {
            int localMax = 1;
            int oldLeft = left;
            if (left < s.length() - 1) {
                while (s.charAt(left) == s.charAt(left + 1)) {
                    left++;
                    right = left;
                    localMax++;
                    if (left == s.length() - 1) {
                        if (s.charAt(left) == s.charAt(oldLeft)){
                            localMax++;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                right = left + k + 1;
                if (right > s.length() - 1) {
                    localMax = s.length() - oldLeft;
                } else {
                    localMax += k; 
                    while (s.charAt(right) == s.charAt(left)) {
                        localMax++;
                        if (right < s.length() -  1) {
                            right++;
                        } else {
                            break;
                        }
                    }
                    //localMax now should be the max substring for the oldLeft
                }
                
            }
            maxSub = Math.max(localMax, maxSub);
            left++;
            right = left;
        }

        return maxSub;
    } */