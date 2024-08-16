package com.roomsmanager.solutions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class SolutionTest {

    @Test
    void success_twoSum() {
        final var sln = new Solution();

        assertThat(sln.twoSum(new int[]{2, 7, 11, 15}, 9))
                .isEqualTo(new int[]{0, 1});
        assertThat(sln.twoSum(new int[]{3, 2, 4}, 6))
                .isEqualTo(new int[]{1, 2});
        assertThat(sln.twoSum(new int[]{3, 3}, 6))
                .isEqualTo(new int[]{0, 1});
    }


    @Test
    void success_NodeToInt() {
        final var node = new Solution.ListNode(2, new Solution.ListNode(4, new Solution.ListNode(3)));
        final var sln = new Solution();

        for (int i = 0; i <= 500000; i++) {
            assertThat(sln.toInt(node)).isEqualTo(BigInteger.valueOf(342));
        }
    }

    @Test
    void success_toListNode() {
        final var node = new Solution.ListNode(2, new Solution.ListNode(4, new Solution.ListNode(3)));
        final var sln = new Solution();

        assertThat(sln.toListNode(BigInteger.valueOf(342))).isEqualTo(node);
    }

    @Test
    void success_addTwoNumbers() {
        final var l1 = new Solution.ListNode(2, new Solution.ListNode(4, new Solution.ListNode(3)));
        final var l2 = new Solution.ListNode(5, new Solution.ListNode(6, new Solution.ListNode(4)));
        final var expected = new Solution.ListNode(7, new Solution.ListNode(0, new Solution.ListNode(8)));

        final var sln = new Solution();

        assertThat(sln.addTwoNumbers(l1, l2)).isEqualTo(expected);
    }

    @Test
    void success_lengthOfLongestSubstring() {
        final var sln = new Solution();

        for (int i = 0; i < 10000; i++) {
            assertThat(sln.lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
            assertThat(sln.lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
            assertThat(sln.lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
            assertThat(sln.lengthOfLongestSubstring("aab")).isEqualTo(2);
            assertThat(sln.lengthOfLongestSubstring("abcb")).isEqualTo(3);
            assertThat(sln.lengthOfLongestSubstring("dvdf")).isEqualTo(3);
            assertThat(sln.lengthOfLongestSubstring("")).isEqualTo(0);
            assertThat(sln.lengthOfLongestSubstring("abcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbbabcabcbb")).isEqualTo(3);
        }

    }


}
