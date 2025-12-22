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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {return true;} //if they are null, meaning they are either empty trees or
        // they have reach the leaves, it is the base condition
        if (p == null && q != null) {return false;}
        if (q == null && p != null) {return false;}
        if (p.val != q.val) {return false;}

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
//we recursively check if the current nodes are equal and then check their left and right subtrees. 
// If all are equal, the trees are the same.