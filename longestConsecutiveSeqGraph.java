import java.util.HashSet;
import java.util.Set;
//Using graph DFS to solve longest consecutive sequence problem
class Solution {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // Store all unique numbers as graph nodes
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        // Tracks which nodes have been visited in DFS
        Set<Integer> visited = new HashSet<>();

        int longest = 0;

        // Try DFS from every unvisited node
        for (int x : set) {
            if (!visited.contains(x)) {
                int size = dfs(x, set, visited);
                longest = Math.max(longest, size);
            }
        }

        return longest;
    }

    // DFS that explores the entire connected component
    private int dfs(int x, Set<Integer> set, Set<Integer> visited) {
        // Mark current node as visited
        visited.add(x);

        int count = 1;

        // Explore left neighbor (x - 1)
        if (set.contains(x - 1) && !visited.contains(x - 1)) {
            count += dfs(x - 1, set, visited);
        }

        // Explore right neighbor (x + 1)
        if (set.contains(x + 1) && !visited.contains(x + 1)) {
            count += dfs(x + 1, set, visited);
        }

        return count;
    }
}
