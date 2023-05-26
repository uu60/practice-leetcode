package leetcode.q17_电话号码的字母组合;



import java.util.*;

public interface Question {
    List<String> letterCombinations(String digits);
}

class Test {
    public static void main(String[] args) {
        String s = "542343";
    }
}

class Solution2 implements Question {

    /**
     * 官方回溯法复现
     * @param digits
     * @return
     */
    @Override
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, phoneMap, digits, 0, new StringBuffer());
        return res;
    }

    private void backtrack(List<String> res, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        // 探索到最后
        if (index == digits.length()) {
            res.add(combination.toString());
            return;
        }
        // 继续探索
        String s = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < s.length(); i++) {
            backtrack(res, phoneMap, digits, index + 1, combination.append(s.charAt(i)));
            combination.deleteCharAt(index);
        }
    }
}

class Solution1 implements Question {
    private static final char[][] BTNS = new char[][]{
            {}, // 0
            {}, // 1
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    /**
     * 试试递归
     * time: 16.85%
     * mem: 22.90%
     * @param digits
     * @return
     */
    @Override
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        // 获取当前按钮对应的集合
        char[] set =
                BTNS[Integer.parseInt(digits.charAt(0) + "")];
        int len = set.length;
        List<String> res = new ArrayList<>();
        // 对每一个选择继续拼接
        List<String> rights = letterCombinations(digits.substring(1));
        // 从末尾返回
        if (rights.size() == 0) {
            rights = new ArrayList<>(Arrays.asList(""));
        }
        for (int i = 0; i < len; i++) {
            for (String right : rights) {
                res.add(set[i] + right);
            }
        }
        return res;
    }
}

class Solution0 implements Question {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
