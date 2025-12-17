//determine if two string s and t are anagrams of each other
//An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
//typically using all the original letters exactly once.

//the core idea is to use a hashmap to store the frequency of each character in string s
//then iterate through string t, for each character, decrease its frequency in the hashmap
//if a character in t is not found in the hashmap or its frequency is already 0, return false
//if we can finish iterating through t without issues, return true

class Solution {
    public boolean isAnagram(String s, String t) {
        
        //if two strings have different length, return false
        if (s.length() != t.length()) { return false; }

        HashMap<Character, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int value = 0;
            if (dictionary.containsKey(s.charAt(i))) {
                value = dictionary.get(s.charAt(i));
                value += 1;
                dictionary.put(s.charAt(i), value);
            } else {
                value = 1;
                dictionary.put(s.charAt(i), value);
            } //construct the dictionary
        }


        for (int i = 0; i < t.length(); i++) {
            int value = 0;
            if (dictionary.containsKey(t.charAt(i)) == false) {
                return false;
            } else {
                value = dictionary.get(t.charAt(i));
                if (value == 0) {
                    return false;
                } else {
                    value -= 1;
                    dictionary.put(t.charAt(i), value);
                }
            }
        }

        return true;
    }
}