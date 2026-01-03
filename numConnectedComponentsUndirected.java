/*Similar idea to counting number of island
but the graph is undirected
pay close attention to neighbour handling*/

class Solution {
    private void exploreComponent(int i, List<Integer>[] list, boolean[] visiting, boolean[] visited, int comingFrom) {
        //base condition 1: Visiting?
        if (visiting[i]) return;
        //base condition 2: visited?
        if (visited[i]) return;

        visiting[i] = true;

        //explore all the neighbours
        for (int nei : list[i]) {
            if (nei == comingFrom) continue;
            exploreComponent(nei, list, visiting, visited, i);
        }

        visited[i] = true;
        visiting[i] = false;
    }

    public int countComponents(int n, int[][] edges) {
        //create adjacency list first
        List<Integer>[] list = new ArrayList[n];

        //initialize the adjacency list
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        //build the adjacency list
        for (int i = 0; i < edges.length; i++) {
            int node = edges[i][0];
            int nodeNei = edges[i][1];
            list[node].add(nodeNei);
            list[nodeNei].add(node);
        }

        //visited and visiting lsit
        boolean[] visited = new boolean[n];
        boolean[] visiting = new boolean[n];

        int countComponent = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            countComponent++;
            exploreComponent(i, list, visiting, visited, -1);
        }

        return countComponent;
    }
}