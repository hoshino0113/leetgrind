class Solution {
    public boolean isValid(String s) {

        //not a good idea
        //HashMap<Character, Integer> dictionary = new HashMap<>();

        //use stack: First time!!
        Deque<Character> track = new ArrayDeque<>();

        //good, but we want to simplify the code
        //char[] paraArray = s.toCharArray();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (track.isEmpty()) {return false;}
                if (track.peek() == '(') {
                    track.pop();
                } else {
                    return false;
                }
            } else if (c == '}') {
                if (track.isEmpty()) {return false;}
                if (track.peek() == '{') {
                    track.pop();
                } else {
                    return false;
                }
            } else if (c == ']') {
                if (track.isEmpty()) {return false;}
                if (track.peek() == '[') {
                    track.pop();
                } else {
                    return false;
                }
            } else {
                track.push(c);
            }
        }

        return track.isEmpty();
    }
}