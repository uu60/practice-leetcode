package leetcode.q824_山羊拉丁文;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface Question {
    String toGoatLatin(String sentence);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}
 */
class Solution1 implements Question {

    @Override
    public String toGoatLatin(String sentence) {
        final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        StringBuilder builder = new StringBuilder(sentence);

        String[] strs = sentence.split(" ");
        int idx = 0;
        for (int i = 0; i < strs.length; i++) {
            // 如果第一个字是辅音
            String s = strs[i];
            if (!vowels.contains(s.charAt(0))) {
                builder.deleteCharAt(idx).insert(idx + s.length() - 1, s.charAt(0));
            }
            builder.insert(idx + s.length(), "ma");
            for (int j = 0; j <= i; j++) {
                builder.insert(idx + s.length() + 2, 'a');
            }
            idx += s.length() + 4 + i;
        }

        return builder.toString();
    }
}