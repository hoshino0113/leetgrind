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

//the core idea is to understand why we can construct a tree from knowing:
/*- inorder traversal AND preorder traversal
preorder traversal follows the patter: Root->Left->Right, which means, the first element is always the Root
inorder traversal follows: Left -> Root -> Right, which means, if we know where the root is, we can know the 
left and right of the trees.
Therefore, we are basically repeating the process, during the repetition, we figure out the new idexes for the next
recursion.

We repeat this process until we run out of things in either (or both, since they are the same length) traversals,
and return null for that case.

We will need to keep track of the roots positions in the inorder traversal array, since they can tell us the structure
of the tree. Therefore, we use a hashmap to map all the elements value to its position in the array for later use
*/

class Solution {
    private TreeNode build(HashMap<Integer, Integer> pos, int preStart, int preEnd, int inStart, int inEnd, int[] preorder, int[] inorder) {

        if (preStart > preEnd || inStart > inEnd) return null;
                
        if (inEnd == inStart) {
            TreeNode res = new TreeNode(inorder[inEnd]);
            return res;
        }

        int posRoot = pos.get(preorder[preStart]); //the position of root in the inOrder array
        int leftSize = posRoot - inStart; //the size of the left tree block

        //the new left preorder traversal array is simply original + 1, since the first is always guranteed to be the
        //new root, and its for the leftsubtree
        //leftPreEnd is simply the prestart + the size of the left block
        //since the posRoot is the position of the current root, its left is the new leftInEnd
        int leftPreStart = preStart + 1;
        int leftInStart = inStart;
        int leftPreEnd = preStart + leftSize;
        int leftInEnd = posRoot - 1;

        int rightPreStart = preStart + leftSize + 1;
        int rightInStart = posRoot + 1;
        int rightPreEnd = preEnd;
        int rightInEnd = inEnd;

        TreeNode res = new TreeNode(preorder[preStart]);
        res.left = build(pos, leftPreStart, leftPreEnd, leftInStart, leftInEnd, preorder, inorder);
        res.right = build(pos, rightPreStart, rightPreEnd, rightInStart, rightInEnd, preorder, inorder);
        return res;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> pos = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            pos.put(inorder[i], i);
        }

        return build(pos, 0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder);
    }
}