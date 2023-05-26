package offer.q2183;

/**
 * @author Du
 * @description
 * @time 2022/1/6 15:32
 */
public class Solution2 implements Question {
    @Override
    public String replaceSpace(String s) {
        String res = s.replace(" ", "%20");
        return res;
    }
}
