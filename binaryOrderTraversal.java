/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/* We used the BFS to achive this goal. Things to rememeber:
- We need to track which level we are manually
- We need to use Queue (FIFO) to store the nodes that we have visited, and pop them once we are done
- Remember to initialize the inner ArrayList for this question too!!
 */

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> theTree = new LinkedList<>();
        if (root == null) return res;

        theTree.offer(root);
        int level = 0;

        while(theTree.isEmpty() == false) {
            int size = theTree.size();
            res.add(new ArrayList<Integer>());

            //The size of the current tree is exactly the number of nodes we need to add to the arrayList on the level
            //Therefore, we iterate size - 1 times until, add the val to the same level list, then we remove the node we are done with from the queue
            for (int i = 0; i < size; i++) {
                TreeNode node = theTree.poll();
                int val = node.val;
                res.get(level).add(val); // add this to the place it should be

                //of course, the node might have children, we add the to the tree. and increase the level by 1 later on outside the loop
                if (node.left != null) {
                    theTree.offer(node.left);
                }
                if (node.right != null) {
                    theTree.offer(node.right);
                }
            }
            
            //next level
            level++;
        }  
        return res;
    }
}