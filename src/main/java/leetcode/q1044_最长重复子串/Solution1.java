package leetcode.q1044_最长重复子串;

import java.util.HashSet;

/**
 * @description:1044. 最长重复子串
 * 难度 困难
 *
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 *
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * @author: Du
 * @time: 2021/12/23 00:40
 */
public class Solution1 implements Question {

    // my answer
    @Override
    public String longestDupSubstring(String s) {
        int length = s.length();
        for (int len = length - 1; len > 0; len--) {
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i <= length - len; i++) {
                String subString = s.substring(i, i + len);
                if (set.contains(subString)) {
                    return subString;
                }
                set.add(subString);
            }
        }
        return "";
    }
}
