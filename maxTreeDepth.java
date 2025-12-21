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
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) { return 0;}

        int leftMax = this.maxDepth(root.left);
        int rightMax = this.maxDepth(root.right);

        return 1 + Math.max(leftMax, rightMax);
    }
}

//we simply call the function recursively on left and right subtrees and return the maximum depth 
//between them and plus one for the current node. 
//If the node is null, we return 0 as the base case.
