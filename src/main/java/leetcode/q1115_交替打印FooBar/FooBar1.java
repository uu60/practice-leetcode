package leetcode.q1115_交替打印FooBar;

import java.util.concurrent.atomic.AtomicInteger;

public class FooBar1 {
    private int n;
    AtomicInteger a1 = new AtomicInteger(1);
    AtomicInteger a2 = new AtomicInteger();

    public FooBar1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!a1.compareAndSet(1, 0)) ;
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            a2.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!a2.compareAndSet(1, 0)) ;
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            a1.incrementAndGet();
        }
    }
}
