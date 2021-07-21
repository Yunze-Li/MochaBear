package com.arctos.mochabear.leetcode.model;

public class LinkedListBuilder {

    public ListNode buildLinkedList(int[] nums) {
        if (nums.length == 0) return null;

        ListNode head = new ListNode(nums[0]);
        ListNode current = head;
        for (int i = 1; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return head;
    }

}
