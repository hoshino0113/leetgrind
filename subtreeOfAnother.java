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

//we need the helper function isSameTree to check if two trees are identical
//we do that for every node in the main tree. But we stop once we find a match.
//the base case is:
// - when the main tree node is null, and the subRoot is not, we return false
// - when both are null, we return true
// - if one is null, but the other is not, we return false
// - if the values are different, we return false

//using DFS traversal, we can check each node in the main tree to see if it matches the subRoot

class Solution {
    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == subRoot) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //assert root is not null based on the question constraint
        //however, there is no guarantee that the child wont be empty!
        if (root == null) return false;
        
        if (isSameTree(root, subRoot)) return true;

        return this.isSubtree(root.left, subRoot) || this.isSubtree(root.right, subRoot);
    }
}