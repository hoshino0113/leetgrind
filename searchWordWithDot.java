/*
So the question is write a class that supports adding words and searching for words with dots.
Words with dots can be like: ..a..b.. which means any character can be there in place of dot.

We definetely want to use trie for this question. 
addWord is the same as normal trie insert.
search is modified to support dot character.

Idea to support dot character:
When we see a dot character, we need to try all possible children of current trie node. (Ya there
is no way around it, we have to try all possible children).
But the runtime will still be acceptable because the number of children is limited to 26.
And most children will be null as expected in a trie.
And we are only interested in the chilren that are not null in the dot case.
Therefore, we have the code writen below
*/



class WordDictionary {
    public WordDictionary[] children;
    public boolean isWord;

    public WordDictionary() {
        this.children = new WordDictionary[26];
        this.isWord = false;
    }
    
    public void addWord(String word) {
        char[] wordArray = word.toCharArray();
        WordDictionary current = this;
        int c_index = 0;

        for (char c : wordArray) {
            c_index = c - 'a';
            if (current.children[c_index] == null) {
                current.children[c_index] = new WordDictionary();
            }
            current = current.children[c_index];
        }
        current.isWord = true;
    }
    
    public boolean dps(WordDictionary node, String word, int index) {
        //We have to check if we have reached the end of the word first, otherwise, we may get index out of bound exception
        if (index == word.length()) {
            return node.isWord;
        }

        //we check if current char is dot first, you will see why
        if (word.charAt(index) == '.') {
            for (WordDictionary child : node.children) {
                if (child != null && dps(child, word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            //else, we are guranteed its a normal char, no need to do if (c >= a and c <= z)
            int c_index = word.charAt(index) - 'a';
            
            //perform normal search

            if (node.children[c_index] == null) return false;

            return dps(node.children[c_index], word, index + 1);
        }

    }
    
    public boolean search(String word) {
        int index = 0;
        return dps(this, word, index);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */