package com.arctos.mochabear.leetcode.medium;

import com.arctos.mochabear.leetcode.model.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * 解法： Recursive 注意return null代表当前二叉树没有对应的子节点，这是允许的
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/1306498/Easy-to-Understand-Java-Solution-Explanation-with-Comments-using-Recursion
 * 时间O(nlgn) 空间O(1)
 */
public class LowestCommonAncester {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode isInLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode isInRight = lowestCommonAncestor(root.right, p, q);
        if (isInLeft != null && isInRight != null) {
            return root;
        } else if (isInLeft != null) {
            return isInLeft;
        } else return isInRight;
    }
}
