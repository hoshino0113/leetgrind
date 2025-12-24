/*
Trie (sometimes called 'prefix tree' is a structure that looks like a tree but not a tree

A trie is usefull to match prefix, and autocorrection.

A trie never stores any character thus no word is stored. Each Trie node has a field called children, 
which is an array of TrieNode. The array length is 26, which corresponds to 26 alphabets in English.
Therefore, if it has seen a char in that specific order, at that char's index in the children array, 
its value will not be null, therefore, it knows we have seen that char before.

DO not forget to keep a current trie node and update it while traversing
*/

//In this question, we merge Trie and Trie Node into one Trie, but in production, people usually separate
//Trie and TrieNode:
/*
class Trie {
    TrieNode root;
}

class TrieNode {
    TrieNode[] children;
    boolean isWord;

    TrieNode() {
        this.children = new TrieNode(26);
        isWord = false;
    }
}
*/

class Trie {

    Trie[] children;
    boolean isWord;

    public Trie() {
        this.children = new Trie[26];
        this.isWord = false;
    }
    
    public void insert(String word) {
        char[] wordArray = word.toCharArray();
        Trie current = this;

        for (char c : wordArray) {
            int childIndex = c - 'a';
            if (current.children[childIndex] == null) {
                current.children[childIndex] = new Trie();
            }
            current = current.children[childIndex];
        }
        current.isWord = true;
    }
    
    public boolean search(String word) {
        char[] wordArray = word.toCharArray();
        Trie current = this;

        for (char c : wordArray) {
            int i = c - 'a';
            if (current.children[i] == null) return false;

            current = current.children[i];
        }

        //the following if and return ture can be combined into one line: return current.isWord
        //for for clarity of the logic, we keep our original works
        if (current.isWord == false) return false;

        return true;
    }
    
    public boolean startsWith(String prefix) {
        char[] wordArray = prefix.toCharArray();
        Trie current = this;

        for (char c : wordArray) {
            int i = c - 'a';

            if (current.children[i] == null) return false;

            current = current.children[i];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */