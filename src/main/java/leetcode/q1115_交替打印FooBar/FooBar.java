package leetcode.q1115_交替打印FooBar;

public class FooBar {
    boolean first = true;
    final Object lock1 = new Object();
    final Object lock2 = new Object();
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (!first) {
                synchronized (lock1) {
                    lock1.wait();
                }
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            first = false;
            synchronized (lock2) {
                lock2.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock2) {
                lock2.wait();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            synchronized (lock1) {
                lock1.notify();
            }
        }
    }
}

