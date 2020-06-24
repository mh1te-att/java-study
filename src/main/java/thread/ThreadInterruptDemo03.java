package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadInterruptDemo03
 * @Description: 两阶段终止模式
 * @Author: zhaoyc
 * @Date: 6/23/2020 3:16 PM
 */
@Slf4j(topic = "c.ThreadInterruptDemo03")
public class ThreadInterruptDemo03 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3000);
        tpt.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {

    private Thread monitor;

    // 启动线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("end");
                    break;
                }
                try {
                    Thread.sleep(1000); // 情况1:睡眠中被打断抛出异常,打断标记清除
                    log.debug("running..."); // 情况2:运行中被设置打断标记,打断标记不会被清除
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 重新设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    // 停止线程
    public void stop() {
        monitor.interrupt();
    }
}