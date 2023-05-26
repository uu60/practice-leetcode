package offer.q2243;

/**
 * @author Du
 * @description
 * @time 2022/1/6 15:44
 */
public class Solution1 implements Question {
    @Override
    public String reverseLeftWords(String s, int n) {
        String s1 = s.substring(0, n);
        String s2 = s.substring(n);
        return s2 + s1;
    }
}
