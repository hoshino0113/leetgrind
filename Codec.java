/*
This question asked us to convert a list of string into a single string, and later on that single string can be
converted back to the exact same list of string. (Encoder and decoder)
The hard part of this question is:
- How can we do the conversion from list of strings to a single string? 
Since the string can contains all 256 ASCII characters, we need to be very careful about the 'separation' of the string

Solution:
- Use a deliminator
- Use Chunked Transfer Encoding

We used Chunked Transfer Encoding:
- For each word in the string, we first get its length, and append the length to the result string first. Followed
by a character, so that when the decoder reads the number and hit that character, it knows the number char for the
numbers has hit an end.
- Then we simply append the word to the result string

For decoder, i think its very simple. Just watch out for the index incrementations!!
GOod job with one try and passed all the test cases!
Keep going!! :>
*/

import java.util.ArrayList;
import java.util.List;

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        //use string builder to dynamically change the string content
        StringBuilder res = new StringBuilder(200);
        //we choose to start every word by its length, then followed by π, a unicode
        //character that is not used in this question. (it is for decoder to know where the number for length stop)

        for (String s : strs) {
            res.append(s.length());
            res.append('π');
            res.append(s);
        }

        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            StringBuilder len = new StringBuilder();
            while (s.charAt(i) != 'π') {
                len.append(s.charAt(i));
                i++;
            }
            //now we have the length
            int wordLen = Integer.parseInt(len.toString());
            StringBuilder word = new StringBuilder(wordLen);
            for (int c = 0; c < wordLen; c++){
                i++;
                word.append(s.charAt(i));
            }
            i++;
            //attach it to the string list
            res.add(word.toString());
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));