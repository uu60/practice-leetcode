package leetcode.q2100_适合打劫银行的日子;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Question {
    List<Integer> goodDaysToRobBank(int[] security, int time);

    public static void main(String[] args) {
        // Validator.exec(Question.class, 0, new int[]{1, 2, 3, 4, 4, 4}, 1);
    }
}

class Solution0 implements Question {

    @Override
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        ArrayList<Integer> res = new ArrayList<>();

        // 动态规划记录第i个左侧递减和右侧递增
        int left[] = new int[security.length];
        int right[] = new int[security.length];

        for (int i = 0; i < security.length - 1; i++) {
            left[i + 1] = security[i] >= security[i + 1] ? left[i] + 1 : 0;
            right[security.length - i - 2] =
                    security[security.length - i - 1] >= security[security.length - i - 2] ?
                            right[security.length - i - 1] + 1 : 0;
        }

        for (int i = time; i < security.length - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                res.add(i);
            }
        }

        return res;
    }
}

class Solution1 implements Question {
    @Override
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        // 保存输出结果
        Set<Integer> res = new HashSet<>();

        /*
            滑动窗口长度为 2time+1
            起始idx=time
            终止idx=sec.length-time-1
         */
        for (int i = time; i < security.length - time; i++) {
            boolean isGood = true;
            if (res.contains(i - 1) && security[i - 1] == security[i] && security[i + time] >= security[i + time - 1]) {
                res.add(i);
                continue;
            }
            for (int pos = i - time; pos < i + time; pos++) {
                if (pos < i ? security[pos] < security[pos + 1] : security[pos] > security[pos + 1]) {
                    isGood = false;
                    break;
                }
            }
            if (!isGood) {
                // 不是好日子
                continue;
            }
            res.add(i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(res);
        return list;
    }
}
