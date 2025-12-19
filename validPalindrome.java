class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z0-9]", "");

        int head = 0;
        int tail = s.length() - 1;

        if (head == tail || tail < 0) { return true;}

        while (head != tail && head < tail) {
            if (s.charAt(head) == s.charAt(tail)) {
                head += 1;
                tail -= 1;
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}