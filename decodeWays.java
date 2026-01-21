class Solution {
    public int numDecodings(String s) {
        //guarenteed s has length >= 1
        int len = s.length();

        //dp[i] represents the #of ways to decode length i
        int[] dp = new int[len + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') return 0;
        dp[1] = 1;
        

        for (int i = 2; i <= len; i++) {
            if (s.charAt(i - 1) == '0') {
                if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2') {
                    dp[i] += dp[i - 2];
                } else {
                    return 0;
                }
            } else {
                dp[i] += dp[i - 1];
                if ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0' <= 26 &&
                    (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0' >= 10) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len];
    }
}

// Correct but lengthy and fragile solution: Good job anyways!
// class Solution {
//     public int numDecodings(String s) {
//         //guarenteed s has length >= 1
//         char[] sentence = s.toCharArray();

//         boolean isBorrowedBefore = false;
//         int len = sentence.length;
//         int[] combo = new int[len];
//         int[] individual = new int[len];

//         if (sentence[0] != '0') {
//             combo[0] = 1;
//             individual[0] = 1;
//         } else {
//             return 0;
//         }

//         for (int i = 1; i < len; i++) {
//             int combine = (sentence[i - 1] - '0') * 10 + sentence[i] - '0';
//             if (sentence[i] == '0') {
//                 if (sentence[i - 1] == '0') return 0;
//                 if (combine > 26) {
//                     return 0;
//                 } else {
//                     if (isBorrowedBefore) {
//                         combo[i] = Math.max(individual[i - 1], 1);
//                     } else {
//                         combo[i] = combo[i - 1];
//                     }
//                     individual[i] = 0;
//                     isBorrowedBefore = true;
//                 }
//                 continue;
//             }
            
//             if (sentence[i - 1] == '0' || combine > 26) {
//                 //means no borrow
//                 isBorrowedBefore = false;
//                 combo[i] = combo[i - 1];
//                 individual[i] = combo[i - 1];
//             } else {
//                 //means there is a borrow
//                 isBorrowedBefore = true;
//                 individual[i] = combo[i - 1];
//                 combo[i] = combo[i - 1] + individual[i - 1];
//             }
//         }

//         return combo[len - 1];
//     }
// }