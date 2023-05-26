package leetcode.q1592_重新排列单词间的空格;

import java.util.ArrayList;
import java.util.List;

public interface Question {
    String reorderSpaces(String text);
}

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution1().reorderSpaces("a"));
    }
}

/**
 * 题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}    O()     O()
 */
class Solution1 implements Question {

    @Override
    public String reorderSpaces(String text) {
        int backspaceNum = 0;
        List<String> words = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                backspaceNum++;
                if (i > 0 && text.charAt(i - 1) != ' ') {
                    words.add(temp.toString());
                    temp.delete(0, temp.length() + 1);
                }
            } else {
                temp.append(text.charAt(i));
            }
        }
        if (temp.length() > 0) {
            words.add(temp.toString());
        }
        int properBackspaceNum = words.size() == 0 ? 0 : backspaceNum / (words.size() - 1);
        int leftBackspaceNum = words.size() == 0 ? backspaceNum : backspaceNum % (words.size() - 1);
        temp.delete(0, temp.length() + 1);
        for (int i = 0; i < words.size(); i++) {
            temp.append(words.get(i));
            if (i < words.size() - 1) {
                for (int j = 0; j < properBackspaceNum; j++) {
                    temp.append(' ');
                }
            }
        }
        for (int i = 0; i < leftBackspaceNum; i++) {
            temp.append(' ');
        }
        return temp.toString();
    }
}