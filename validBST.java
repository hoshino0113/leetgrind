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

//OK, we are stupid, we don't know how to do it with O(1) space, and we dont know how to do it in less than 2O(n) runtime
//fine, lets just solve it with our own intuitions:
/*
- An inorder BST traversal will return a sorted array that is strictly increasing.
- Therefore, we create a arraylist that store the result of the inorder traversal of the BST
- Then, we iterate the arraylist to see if it is strictly increasing, if not, we return false immediately (to save some time OK?:)
*/
class Solution {

    //in order traversal
    private void inOrder(TreeNode root, List<Integer> leaves) {
        if (root == null) return;

        inOrder(root.left, leaves);
        leaves.add(root.val);
        inOrder(root.right, leaves);
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> leaves = new ArrayList<>();

        inOrder(root, leaves);

        //leaves now should contain all the nodes

        int cur_max = leaves.get(0);
        for (int i = 0; i < leaves.size(); i++) {
            if (i == 0) continue;

            if (leaves.get(i) <= cur_max) return false;

            cur_max = leaves.get(i);

        }

        return true;
    }
}