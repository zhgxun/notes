package github.banana.vm;

public class JstackTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockClass(true));//建立一个线程
        Thread t2 = new Thread(new DeadLockClass(false));//建立另一个线程
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
    }
}

class DeadLockClass implements Runnable {
    public boolean falg;// 控制线程

    DeadLockClass(boolean falg) {
        this.falg = falg;
    }

    public void run() {
        if (falg) {
            while (true) {
                synchronized (Suo.o1) {
                    System.out.println("o1 " + Thread.currentThread().getName());
                    synchronized (Suo.o2) {
                        System.out.println("o2 " + Thread.currentThread().getName());
                    }
                }
            }
        } else {
            while (true) {
                synchronized (Suo.o2) {
                    System.out.println("o2 " + Thread.currentThread().getName());
                    synchronized (Suo.o1) {
                        System.out.println("o1 " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
}

class Suo {
    static Object o1 = new Object();
    static Object o2 = new Object();
}