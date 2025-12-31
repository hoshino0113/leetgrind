/*Introduction of Graph: A graph is a data structure consisting of nodes (vertices) connected by edges.
In this problem, we are given a connected undirected graph and asked to create a deep copy (clone) of it.
We started with a Node in the graph, the definition of the Node is given below:

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
neighbors is a list of all the nodes connected to this node by an edge.
We do not store edges explicitly, they are implicitly represented by the neighbors list of each node.
In this case, we are assuming that:
- The graph is connected, meaning there is a path between any two nodes in the graph.
- The graph can either be directed or undirected.
- There is no isolated node in the graph.

Problem explanation:
What is deep clone? It means we need to create a new graph that is structurally identical to the original graph,
but all the nodes and edges are new instances. In other words, we are not allowed to reference any node or edge 
from the original graph in the cloned graph.

This raise an issue:
The graph very likely contains cycles, meaning there are paths that lead back to previously visited nodes.
If we naively traverse the graph and create new nodes for each visited node, we may end
up in an infinite loop or create multiple copies of the same node.
To handle this, we need to keep track of the nodes that have already been cloned.
We can use a HashMap to map each original node to its corresponding cloned node.

Use Map<Node, Node> to keep track of cloned nodes: instead of using HashMap<Node, Node>, since Map is
an interface, we can use HashMap which implements Map interface. Its safer and more flexible.
The key is the original node, and the value is the cloned node.
If we every have cloned the node already, we simply return the cloned node from the map.
If not, we create a new node, immediately add it to the map, and then recursively clone its neighbors(all of them).
*/

class Solution {
    private Map<Node, Node> map = new HashMap<>();

    private Node dfsClone(Node node) {
        //if we have already cloned the node, we simply return that cloned node
        if (this.map.containsKey(node)) return this.map.get(node);

        //we have not cloned the node, create one and added to the map immediately
        Node cloneNode = new Node(node.val);
        this.map.put(node, cloneNode);

        //now we need to clone its neighbour, every one of them:
        for (Node n : node.neighbors) {
            cloneNode.neighbors.add(this.dfsClone(n));
        }

        return cloneNode;
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return this.dfsClone(node);
    }
}