package com.roomsmanager.solutions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        var history = new HashMap<Integer, Integer>();
        for (var i = 0; i < nums.length; i++) {
            final var key = target - nums[i];
            if (history.containsKey(key)) {
                return new int[]{history.get(key), i};
            }
            history.put(nums[i], i);
        }
        return new int[2];
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListNode listNode = (ListNode) o;
            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public BigInteger toInt(ListNode list) {
        return new BigInteger(toInt2(list));
    }

    public String toInt2(ListNode list) {
        if (list == null) return "";
        return toInt2(list.next) + list.val;
    }

    public ListNode toListNode(BigInteger num) {
        ListNode node = null;
        final var r = num.toString();
        for (int i = 0; i < r.length(); i++) {
            node = new ListNode(Character.getNumericValue(r.charAt(i)), node);
        }
        return node;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return toListNode(toInt(l1).add(toInt(l2)));
    }

    public int lengthOfLongestSubstring(String s) {
        var maxLength = 0;
        var currentSubstr = new LinkedHashSet<Character>();

        for (var i = 0; i < s.length(); i++) {
            final var chr = s.charAt(i);
            while (currentSubstr.contains(chr)) {
                currentSubstr.removeFirst();
            }

            currentSubstr.add(chr);
            maxLength = Math.max(maxLength, currentSubstr.size());
        }

        return Math.max(maxLength, currentSubstr.size());
    }

}
