package offer.q2183;

/**
 * @author Du
 * @description
 * @time 2022/1/6 15:37
 */
public class Answer implements Question {
    @Override
    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = s.toCharArray();
        char[] chars = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = array[i];
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = c;
            }
        }
        return new String(chars, 0, size);
    }
}
