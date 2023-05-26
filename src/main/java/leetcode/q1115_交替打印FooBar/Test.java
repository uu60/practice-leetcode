package leetcode.q1115_交替打印FooBar;

public class Test {
    public static void main(String[] args) {
        FooBar1 fooBar = new FooBar1(50);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.println("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
