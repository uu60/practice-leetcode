package leetcode.q735_行星碰撞;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public interface Question {
    int[] asteroidCollision(int[] asteroids);
}

class Test {
    public static void main(String[] args) {
        

    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1} 8      96      O(n)     O(n)       使用栈
 * {@link Solution2} 56     77      O(n)     O(n)       简化代码
 *
 */
class Solution2 implements Question {

    @Override
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean alive = true;
            while (alive && !deque.isEmpty() && deque.getLast() > 0 && asteroid < 0) {
                alive = deque.getLast() < -asteroid;
                if (deque.getLast() <= -asteroid) {
                    deque.removeLast();
                }
            }
            if (alive) {
                deque.offerLast(asteroid);
            }
        }
        return deque.stream().mapToInt(Integer::valueOf).toArray();
    }
}

class Solution1 implements Question {

    @Override
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            // 相同移动方向或栈顶向左
            if (stack.isEmpty() || stack.peek() < 0 || asteroid > 0) {
                stack.push(asteroid);
            } else { // 会发生碰撞 stack.peek() > 0 && ast[i] < 0
                boolean broken = false;
                while (!stack.isEmpty() && stack.peek() > 0) {
                    // 被撞碎了
                    if (stack.peek() > -asteroid) {
                        broken = true;
                        break;
                    } else if (stack.peek() == -asteroid) { // 两个都碎了
                        stack.pop();
                        broken = true;
                        break;
                    }
                    stack.pop();
                }
                if (!broken) {
                    stack.push(asteroid);
                }
            }
        }
        return stack.stream().mapToInt(Integer::valueOf).toArray();
    }
}