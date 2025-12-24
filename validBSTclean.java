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

//OK, we are not stupid, lets figure out a way that runs on O(n) and O(1) space
//the BST has the property that LHS < root < RHS. Therefore, the solution would be keep checking whether 
//the LHS < root < RHS

//For LHS, all of its value should be: Min < self < root
//For RHS, all of its values should be: root < self < Max
//and what should the min and max bee?
//we can start with LONG MIN and LONG MAX, change them as we traverse down the tree

class Solution{
    private boolean isBST(TreeNode root, long treeMin, long treeMax) {
        //if we have reach null, meaning everything before is good, return true
        if (root == null) return true;
        //the value of the node must be within treeMin and treeMax strictly
        if (root.val <= treeMin || root.val >= treeMax) return false;

        //if the value is good, we recursively check its children
        return isBST(root.left, treeMin, root.val) && isBST(root.right, root.val, treeMax);

    }
        
    public boolean isValidBST(TreeNode root) {
        //Avoid edge bugs when node values can be Integer.MIN_VALUE / Integer.MAX_VALUE
        long treeMin = Long.MIN_VALUE;
        long treeMax = Long.MAX_VALUE;
        return isBST(root, treeMin, treeMax);
    }
}