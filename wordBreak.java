//In this question, we are required to determine if there exists a combination of the words in the dictionary,
//such that can be equal to string s.
//eg: s = nihao, wordDict = {"ni", "hao"};
/*
Idea: Use DP, break down the problem into smaller problem
- In order to get s, we need to consider this: For all the substrings in s, as long as there is one combo that can 
result s, then s is composable by the dictionary words.
For example, let s = "appleisbetter", wordDict = {"app", "le", "i", "s", "better"}
If s is composable, then it must mean there exists some combo of the substring, such that they can be composble too.
-> For example, "appleis", "better" is a combo of s. Therefore, we ask: Are they composable? If they are, then it means
s is also composable.
-> "app", "le", "i", "s" are the substring of "appleis", so are they compoable? Yes! We found them in the dictionary!
-> "better" is composible so yes! s is also composable!

Now the question has become, how can we find those substring? Obviously computer does not happen to know what substring
of the string s are composable, so it tries every possible combo!

- Starting from the substring of length 1, it is composable iff there exists a substring combo of it that is also composable.
What is the substring of the string of length 1? It is the string with length 0 and itself.
Well, string with length 0 is empty string, it is always composable. (trivial) Therefore, now we need to determine
if the string of length 1 can be composable.
- Then we move to substring of length 2, is it composable?...
- How about substring og length 20? It will be composable iff it has at least 1 substring combo that are composable.
Therefore, we iteratre its substring to get the answer:
-> For substring length 20, (i = 0; i < 20; i++); we initialize j = 0; j < i; j++.
-> We determine if str[0 ~ j] and str[j ~ i] are composable by:
  - Look up if str[0 ~ j] == truel && str[j ~ i] is in the dictionary.
  - If true, then mark str[i] as true.

-> Then at the end, we check whether str[n] is true or not.
*/


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //we are guarenteed that the string and the dictionary is not empty

        //First, we conver the dictionary into a hashset to enable O(1) lookup
        //we can initialze the set from the ArrayList collection directly:
        Set<String> words = new HashSet<>(wordDict);
        int stringLength = s.length() + 1;

        //composable[] stores the boolean value for whehter the substring with length i is composable.
        boolean[] composable = new boolean[stringLength];

        composable[0] = true;

        for (int i = 1; i < stringLength; i++) {
            for (int j = 0; j < i; j++) {
                if (composable[j] && words.contains(s.substring(j, i))) {
                    composable[i] = true;
                    break;
                }
            }
        }

        return composable[stringLength - 1];
    }
}