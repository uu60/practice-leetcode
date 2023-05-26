package offer.q2183;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Du
 * @description
 * @time 2022/1/6 15:21
 */
public class Solution1 implements Question {
    @Override
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c + "");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(" ")) {
                list.remove(i);
                list.add(i, "%20");
            }
        }
        String res = "";
        for (String s1 : list) {
            res += s1;
        }
        return res;
    }
}
