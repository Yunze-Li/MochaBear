package com.arctos.mochabear.leetcode.medium

import com.arctos.mochabear.leetcode.model.TreeNode

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
class Solved236 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        } else if (root == p || root == q) {
            return root
        }

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)
        if (left != null && right != null) {
            return root
        } else if (left != null) {
            return left
        } else if (right != null) {
            return right
        } else {
            return null
        }
    }
}