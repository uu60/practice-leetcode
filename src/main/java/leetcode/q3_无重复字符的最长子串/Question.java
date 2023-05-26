package leetcode.q3_无重复字符的最长子串;



import java.util.HashSet;
import java.util.Set;

public interface Question {
    int lengthOfLongestSubstring(String s);
}

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution4().lengthOfLongestSubstring("pwwkewafdasfv"));
    }
}

class Solution4 implements Question {

    @Override
    public int lengthOfLongestSubstring(String s) {
        // 非空判断
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0;
        int ans = 1;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                ans = Math.max(ans, set.size());
            } else { // 包含了
                while (s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left++));
                }
                set.remove(s.charAt(left++));
            }
        }
        return ans;
    }
}

class Solution3 implements Question {

    @Override
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> current = new HashSet<>();
        int ans = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char ci = s.charAt(i);
            if (!current.contains(ci)) {
                current.add(ci);
                continue;
            }
            ans = Math.max(ans, current.size());
            while (s.charAt(left) != ci) {
                current.remove(s.charAt(left++));
            }
            left++;
        }
        ans = Math.max(ans, current.size());
        return ans;
    }
}

class Solution2 implements Question {

    @Override
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        int ans = 0;
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, set.size());
            } else {
                while (s.charAt(i) != s.charAt(j)) {
                    set.remove(s.charAt(i));
                    i++;
                }
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}

class Solution1 implements Question {

    @Override
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                while (s.charAt(i) != s.charAt(j)) {
                    set.remove(s.charAt(i++));
                }
                set.remove(s.charAt(i++));
            }
        }
        return Math.max(ans, j - i);
    }
}