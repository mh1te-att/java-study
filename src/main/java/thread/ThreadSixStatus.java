package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadSixStatus
 * @Description: Java API的六种状态
 * @Author: zhaoyc
 * @Date: 7/10/2020 3:20 PM
 */
@Slf4j(topic = "c.ThreadSixStatus")
public class ThreadSixStatus {
    public static void main(String[] args) {
        // t1创建没被调用为新建状态
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running");
            }
        };

        // t2一直在运行,为可运行状态
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        t2.start();

        // t3运行完结束,为终止状态
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (ThreadSixStatus.class) {
                    try {
                        // t4线程睡眠,timed_waiting状态
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    // t2一直运行不结束,t5为等待状态
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                // t4线程调用锁,t6拿不到锁,处于阻塞状态
                synchronized(ThreadSixStatus.class) {
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("t1 status is {}", t1.getState());
        log.debug("t2 status is {}", t2.getState());
        log.debug("t3 status is {}", t3.getState());
        log.debug("t4 status is {}", t4.getState());
        log.debug("t5 status is {}", t5.getState());
        log.debug("t6 status is {}", t6.getState());

    }
}