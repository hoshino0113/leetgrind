// We loop through all the string(s) in the array, and for each of them:
// - convert the string to a array of chars O(K)
// - sort the array of chars in O(k(log(k)))
// - convert it back to string
// - add this to the hashmap

//- finally, simply add all the values in the hashmap to the list

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> dictionary = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for (String s : strs) {
            String key = "";
            if (!s.equals("")) {
                char[] charArray = s.toCharArray();
                Arrays.sort(charArray); // sort the char array
                key = new String(charArray); // convert it back to string
            }

            if (!dictionary.containsKey(key)) { // if does not contain key
                dictionary.put(key, new ArrayList<>());
            }
            dictionary.get(key).add(s);

            //an more elegant and efficient way to achieve the above would be:
            /*
            dictionary
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(word);
            */
        }
        // we now simply add all the List<String> in the dict to the res List, one by one
        for (Map.Entry<String, List<String>> entry : dictionary.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }
}