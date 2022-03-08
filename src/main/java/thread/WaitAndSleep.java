package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * wait和sleep方法对比
 *
 * @author zhaoyc
 * @since 2/28/22
 */
@Slf4j(topic = "c.test")
public class WaitAndSleep {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
//        illegalWait();
        waiting();
//        sleeping();
    }

    private static void illegalWait() throws InterruptedException {
        // 不能直接调用wait
        LOCK.wait();
    }

    /**
     * wait会释放锁
     *
     * @author zhaoyc
     * @since 2/28/22
     */
    private static void waiting() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    log.debug("waiting...");
                    LOCK.wait(5000L);
                } catch (InterruptedException e) {
                    log.debug("interrupted...");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(100);
        synchronized (LOCK) {
            log.debug("other...");
        }
    }

    /**
     * sleep不会释放锁
     *
     * @author zhaoyc
     * @since 2/28/22
     */
    private static void sleeping() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    log.debug("sleeping...");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    log.debug("interrupted...");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(100);
        synchronized (LOCK) {
            log.debug("other...");
        }
    }
}
