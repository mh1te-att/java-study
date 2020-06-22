package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreaJoinDemo
 * @Description: join方法
 * @Author: zhaoyc
 * @Date: 6/22/2020 5:59 PM
 */
@Slf4j(topic = "c.test")
public class ThreadJoinDemo {

    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        log.debug("start");
        Thread t1 = new Thread(() -> {
            log.debug("start");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("end");
            r = 10;
        }, "t1");
        t1.start();
        // 等待t1结束
        t1.join();
        log.debug("value is: {}", r);
        log.debug("end");
    }
}
