package leetcode.q6_Z字形变换;

public interface Question {
    String convert(String s, int numRows);
}

class Solution1 implements Question {

    /*
        row = 3
        P   A   H   N
        A P L S I I G
        Y   I   R
        一组3 + 1 = 4

        row = 4
        P     I    N
        A   L S  I G
        Y A   H R
        P     I
        一组4 + 2 = 6
        group = 2 row - 2;

        第一行的都是 i % group = 0 && i % group = group
        第二行的都是 i % group = 1 && i % group = group - 1
        第三行的都是 i % group = 2 && i % group = group - 2
        ...
        第row - 1行的都是 i % group = row / 2 + 1


     */
    @Override
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        char[] chars = s.toCharArray();
        int groupSize = 2 * numRows - 2;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i++) {
            int targetRowIdx = i % groupSize;
            if (targetRowIdx < numRows) {
                rows[targetRowIdx].append(chars[i]);
            } else {
                rows[groupSize - i % groupSize].append(chars[i]);
            }
        }
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, "PAYPALISHIRING", 3);
    }
}
