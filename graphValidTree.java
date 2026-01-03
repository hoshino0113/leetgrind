/*A graph is a valid tree iff:
- There is no disconnected components, which means, only one big component
- There is no cycle

A graph is connected iff:
a. #edges > number of nodes - 2
b. There is no cycle
*/

class Solution {

    private boolean dfsCycle(List<Integer>[] neighbours, int i, Set<Integer> visiting, Set<Integer> visited, int comingFrom) {
        //base condition 1: if the node is on the visiting list, it means we have a cycle!
        if (visiting.contains(i)) return false;

        //base condition 2: if the node has been visited, it means its good and we skip it:
        if (visited.contains(i)) return true;

        //now, the node is not in visiting list, and is has not been visited (not proven to be safe for now)
        //add the current node to the visiting list (we start officially visiting this node)
        visiting.add(i);

        //we need to check all of its neighbour to see if they are good
        boolean noCycle = true;
        
        for (int nei : neighbours[i]) {
            if (nei == comingFrom) continue;
            noCycle = noCycle && dfsCycle(neighbours, nei, visiting, visited, i);
        }

        if (noCycle) {
            visited.add(i);
            visiting.remove(i);
        }
        
        return noCycle;
    }

    public boolean validTree(int n, int[][] edges) {
        //First, we check the first condition for connectiveness: #edges > number of nodes - 2
        if (edges.length <= n - 2) return false;

        //Now, we know #edges > number of nodes - 2, we need to determine if there is a cycle in the graph
        //To do that, we build an adjacency list first, where a -> b, c means a has neighbours b and c
        List<Integer>[] neighbours = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            neighbours[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int visitingNode = edges[i][0];
            int visitingNodeConWith = edges[i][1];
            //since the graph is undirected, we need to add these nodes to each of their neighbour nodes
            neighbours[visitingNode].add(visitingNodeConWith);
            neighbours[visitingNodeConWith].add(visitingNode);
        }
        //now we should have the complete adjacency list
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        //we perform dfs search to find any cycle on every node:
        for (int i = 0; i < n; i++) {
            if (!this.dfsCycle(neighbours, i, visiting, visited, -1)) return false;
        }
        return true;
    }
}