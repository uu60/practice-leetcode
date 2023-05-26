package leetcode.q7_整数反转;

public interface Question {
    int reverse(int x);

    public static void main(String[] args) {
        // Validator.exec(Question.class, 0, 1234);}
    }

    class Solution0 implements Question {

        @Override
        public int reverse(int x) {
            int rev = 0;
            while (x != 0) {
                if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int digit = x % 10;
                x /= 10;
                rev = rev * 10 + digit;
            }
            return rev;
        }
    }

    class Solution1 implements Question {
        @Override
        public int reverse(int x) {
            String s = String.valueOf(x);
            char[] chars = s.toCharArray();
            // 负数则从idx=1开始
            int start = chars[0] == '-' ? 1 : 0;

            for (int i = start; i < (s.length() - start) / 2 + start; i++) {
                swap(chars, i, s.length() - i - 1 + start);
            }

            String max = String.valueOf(Integer.MAX_VALUE);
            String min = String.valueOf(Integer.MIN_VALUE);

//        if (s.length() >= (start == 0 ? max.length() : min.length())) {
//            for (int i = start; i < s.length(); i++) {
//                int i1 = Integer.parseInt(chars[i] + "");
//                int i2 = Integer.parseInt((start == 0 ? max.charAt(i) : min.charAt(i)) + "");
//                if (i1 < i2) {
//                    break;
//                } else if (i1 > i2) {
//                    return 0;
//                }
//            }
//        }
            if ((start == 0 && max.compareTo(String.valueOf(chars)) < 0)
                    || (start == 1 && min.compareTo(String.valueOf(chars)) > 0)) {
                return 0;
            }
            return Integer.parseInt(String.valueOf(chars));
        }

        private static void swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }
}
