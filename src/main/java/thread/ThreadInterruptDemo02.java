package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadInterruptDemo02
 * @Description: 打断方法
 * @Author: zhaoyc
 * @Date: 6/23/2020 2:21 PM
 */
@Slf4j(topic = "c.ThreadInterruptDemo02")
public class ThreadInterruptDemo02 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("end");
                    break;
                }
            }
        }, "t1");
        
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupted");
        t1.interrupt();
    }
}