package leetcode.q1114_按序打印;

public class Foo {
    final Object lock2 = new Object();
    final Object lock3 = new Object();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        synchronized (lock2) {
            lock2.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock2) {
            lock2.wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        synchronized (lock3) {
            lock3.notify();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock3) {
            lock3.wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
