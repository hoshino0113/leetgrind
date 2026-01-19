class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //we are guarenteed that the string and the dictionary is not empty

        //First, we conver the dictionary into a hashset to enable O(1) lookup
        //we can initialze the set from the ArrayList collection directly:
        Set<String> words = new HashSet<>(wordDict);
        int stringLength = s.length() + 1;
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