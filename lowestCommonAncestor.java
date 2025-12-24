/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//core idea: the lowest common acestors has value sitting between p and q's
//therefore, we keep traversing the tree in the following manners:
//- if root > both p and q, we look at its LHS
//- if root < both p and q, we look at its RHS
//- if p < root < q, return root as the common ancestor
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root; //meaning that either p or q does not exist

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        //meaning the root must be sitting between p and q, hence the lowest
        return root;
    }
}