package thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Title: ThreadInterruptDemo04
 * @Description: interrupted清除打断标记测试
 * @Author: zhaoyc
 * @Date: 6/23/2020 5:55 PM
 */
@Slf4j(topic = "c.ThreadInterruptDemo04")
public class ThreadInterruptDemo04 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park");
            LockSupport.park();
            log.debug("unpark");
            // interrupted清除打断标记
            log.debug("打断状态: {}", Thread.interrupted());

            // park继续生效
            LockSupport.park();
            log.debug("unpark");
        });

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}