/* In this question, we are asked to determine if we can finish ALL the courses given their prerequisites 
Yes, ALL the courses, if we can't do one, we fail (yike)

We are given a matrix such that it contains the prerequisites for each course. 
prerequisites = [[CS350, CS240], [CS350, CS241], [CS 240, CS 136], [CS 241, CS 136]]
means to take CS350, we need to take CS240 and CS241 first, and to take CS240 or CS241, we need to take CS136 first.
The intuition would be build a graph and check if there is a cycle in the graph.
If there is a cycle, we can't finish all the courses, since we will be stuck in the cycle trying to take the prerequisite courses.
If there is no cycle, we can finish all the courses.

But how can we build the graph?
We can use an adjacency list to represent the graph.
An adjacency list is an array of lists, where the index of the array represents the course, 
and the list at that index represents the courses that depend on that course.
For example, if we have 4 courses, and the prerequisites are as above,
we can represent the graph as follows:
classGraph[136] = [240, 241]
classGraph[240] = [350]
classGraph[241] = [350]
classGraph[350] = []

Once we have the graph, we can use DFS to check if there is a cycle in the graph.

Idea:
- We keep track of the currently visiting nodes in a set called 'visiting'. This set helps us detect cycles.
because if we encounter a node that is already in the 'visiting' set, it means we have a cycle!
- We keep track of the completely visited nodes in a set called 'visited'. For this set, we are sure 
there is no cycle involving these nodes, therefore, only add the node to this list after recursively 
visiting all its neighbours and confirming no cycle. Also, we can skip this node if we encounter it again as
it is visited and no cycle involves it.
- Remember to remove the node from 'visiting' set once all its neighbours are processed and no cycle is found.
*/

class Solution {

    private boolean dfsCycle(List<Integer>[] classGraph, int node, Set<Integer> visiting, Set<Integer> visited ) {
        //if we are visiting a node that we are already visiting, a cycle!
        if (visiting.contains(node)) return false;
        //if not, we check if it has been visited:
        //we skip the node since we know there is no cycle for this node and no need to repeat work
        if (visited.contains(node)) return true;

        //Else, we mark the current node as visiting
        visiting.add(node);

        boolean noCycle = true;
        //we perform dfsCycle test against all the neighbours of the node:
        for (int i : classGraph[node]) {
            noCycle = noCycle && this.dfsCycle(classGraph, i, visiting, visited);
        }
        //if all its neighbour does not have cycle, mark the node as visited and remove it from visiting
        if (noCycle) {
            visited.add(node);
            visiting.remove(node);
        }
        return noCycle;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        List<Integer>[] classGraph = new ArrayList[numCourses];

        //construct the adjacency list:
        for (int i = 0; i < numCourses; i++){
            classGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int prerequisite = prerequisites[i][1];
            int higherClass = prerequisites[i][0];
            classGraph[prerequisite].add(higherClass);
        }

        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (dfsCycle(classGraph, i, visiting, visited) == false) return false;
        }
        return true;
    }
}