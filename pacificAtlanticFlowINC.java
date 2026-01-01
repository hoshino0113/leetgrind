/*This is an incomplete solution to the question of pacific and atlantic water flow question

We were thinking using adjacency list to achieve the goal, while it works, it is slow and very fragile.

Therefore, we chose to abandoned this method and embraced the other solution that is cleaner and more robust
However, i figure to keep this copy as it is a good practice and exploration
*/
class Solution {
    private void dfsDual(int[][] heights, Map<List<Integer>, List<List<Integer>>> map, List<Integer> key, List<List<Integer>> neighbours, Set<List<Integer>> visiting, List<List<Integer>> dual) {

    }


    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //initialze the adjacency list for map it will be look like:
        // [0, 0] -> [[0, 1], [1, 0]] meaning that height[0][0] will have neigbhours of height[0][1] and height[1][0]
        Map<List<Integer>, List<List<Integer>>> map = new HashMap<>();

        //construct the adjacency list
        for (int row = 0; row < heights.length; row++) {
            for (int col = 0; col < heights[row].length; col++) {
                //create the key
                //Must not mutate the key after initialization, there is a way to make it immutable but for now we are skipping it
                List<Integer> key = new ArrayList<>();
                key.add(row);
                key.add(col);
                map.put(key, new ArrayList<>());

                //create its neighbours
                //for every grid, we need to check its top, right, bottom, left neighbours
                //top:
                if (row - 1 >= 0) {
                    List<Integer> nei = new ArrayList<>();
                    nei.add(row - 1);
                    nei.add(col);
                    map.get(key).add(nei);
                }
                //right: 
                if (col + 1 < heights[row].length) {
                    List<Integer> nei = new ArrayList<>();
                    nei.add(row);
                    nei.add(col + 1);
                    map.get(key).add(nei);
                }
                //bottom:
                if (row + 1 < heights.length) {
                    List<Integer> nei = new ArrayList<>();
                    nei.add(row + 1);
                    nei.add(col);
                    map.get(key).add(nei);
                }
                //left:
                if (col - 1 >= 0) {
                    List<Integer> nei = new ArrayList<>();
                    nei.add(row);
                    nei.add(col - 1);
                    map.get(key).add(nei);
                }
            }
        }
        //now we have the adjacency list, we now can call the helper function to do dfs traversing for every grid
        //we need to keep a visiting list to prevent infinite loop
        Set<List<Integer>> visiting = new HashSet<>();

        //Initialze the return list that contains all the grids that can flow into both oceans
        List<List<Integer>> dual = new ArrayList<>();

        //call the dfs function for every grid:
        for (Map.Entry<List<Integer>, List<List<Integer>>> gridSet : map.entrySet()) {
            List<Integer> key = gridSet.getKey();
            List<List<Integer>> neighbours = gridSet.getValue();
            dfsDual(heights, map, key, neighbours, visiting, dual);
        }

        return dual;
    }
}