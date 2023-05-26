package leetcode.q1114_按序打印;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo1 extends Foo {
    AtomicInteger a1 = new AtomicInteger();
    AtomicInteger a2 = new AtomicInteger();

    @Override
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        a1.incrementAndGet();
    }

    @Override
    public void second(Runnable printSecond) throws InterruptedException {
        while (!a1.compareAndSet(1, 0)) {}
        printSecond.run();
        a2.incrementAndGet();
    }

    @Override
    public void third(Runnable printThird) throws InterruptedException {
        while (!a2.compareAndSet(1, 0)) {}
        printThird.run();
    }
}
