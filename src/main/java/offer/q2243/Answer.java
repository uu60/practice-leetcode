package offer.q2243;

/**
 * @author Du
 * @description
 * @time 2022/1/6 15:48
 */
public class Answer implements Question {
    @Override
    public String reverseLeftWords(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = n; i < n + s.length(); i++) {
            stringBuilder.append(chars[i % s.length()]);
        }
        return stringBuilder.toString();
    }
}
