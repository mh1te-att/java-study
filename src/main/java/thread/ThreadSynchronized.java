package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadSynchronized
 * @Description: 线程安全问题
 * @Author: zhaoyc
 * @Date: 7/10/2020 5:07 PM
 */
@Slf4j(topic = "c.ThreadUnsafe")
public class ThreadSynchronized {

    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (lock) {
                        counter++;
                    }
                }
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (lock) {
                        counter--;
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }
    
}