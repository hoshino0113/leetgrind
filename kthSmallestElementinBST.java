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

 //idea: We need to keep a list that only need to be the size of 3.
 //once we hit size of 3, we return the element at index 2;
class Solution {
    private void threeLeavesBuilder(TreeNode root, List<Integer> threeTreeLeaves, int k) {
        if (root == null || threeTreeLeaves.size() >= k) return;

        this.threeLeavesBuilder(root.left, threeTreeLeaves, k);

        //to prevent extra add, we check if the length still smaller than k after the previous line was executed
        if (threeTreeLeaves.length < k) {
            threeTreeLeaves.add(root.val);
        }

        this.threeLeavesBuilder(root.right, threeTreeLeaves, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> threeTreeLeaves = new ArrayList<>();

        this.threeLeavesBuilder(root, threeTreeLeaves, k);

        return threeTreeLeaves.get(k - 1);
    }
}