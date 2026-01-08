/*Use Dynanmic Programing, reuse previous work*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> initialRow = new ArrayList<>();
        initialRow.add(1);
        res.add(initialRow);
        
        //be careful for the index handling
        for (int r = 1; r < numRows; r++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i < r; i++) {
                row.add(res.get(r - 1).get(i - 1) + res.get(r - 1).get(i));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}