//return the max length of substring that without repeating characters
/*
Use sliding window technique, we can reuse the works that we have already done, hence reduce the runtime to O(n) 
We need a hashmap to keep track of the char that we have already encounter for the current substring, once we find
a repeating char, we keep removing the char from the hashmap until we remove the last seen repeated char.
If we havent seen the char before, we add it to the hashmap
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        HashMap<Character, Integer> dictionary = new HashMap<>();
        int left = 0; //left pointer

        //We use the right side of the window as the iteration pointer, because that is which direction the window would grow
        for (int right = 0; right < s.length(); right++) {

            //check if the new char already encountered, if so, keep removing the leftmost char of the subarray from the hashmap
            //until we remove char that we just encountered. Because, there is no way we can form a longer subarray before that char.
            //however, we still want to reuse the result that we got for that part that comes after the repeating char
            //since, we keep a track of the maxLength, we are not losing the previous Max length that we got earlier despite we remove some of the chars
            while (dictionary.containsKey(s.charAt(right))) {
                //remove it from the dic
                dictionary.remove(s.charAt(left));
                left++;
            }
            
            //new char has not been seen, so we add it to the hashmap
            dictionary.put(s.charAt(right), 1);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;

    }
}