package com.arctos.mochabear.leetcode.model;

public class ListNode {
    public ListNode next;
    int val;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}