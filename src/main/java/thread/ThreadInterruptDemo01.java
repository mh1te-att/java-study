package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadInterruptDemo
 * @Description: 打断方法
 * @Author: zhaoyc
 * @Date: 6/23/2020 11:50 AM
 */
@Slf4j(topic = "c.ThreadInterruptDemo01")
public class ThreadInterruptDemo01 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug(Thread.currentThread().getName() + " start");
            try {
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(0);
                    System.out.println(i);
                }
                log.debug(Thread.currentThread().getName() + " complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug(Thread.currentThread().getName() + " end");
            }
        }, "t1");

        t1.start();
        Thread.sleep(5);
        log.debug("interrupted");
        t1.interrupt();
    }
}
