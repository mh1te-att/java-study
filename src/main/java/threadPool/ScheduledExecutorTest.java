package threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorTest {

    static Logger logger = LoggerFactory.getLogger(ScheduledExecutorTest.class);

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        // 往线程池中放任务
        executorService.scheduleAtFixedRate(()-> {
            logger.info("===scheduled run");
        },1,3, TimeUnit.SECONDS); // 延迟一秒，每隔三秒执行任务

        executorService.schedule(()-> {
            logger.info("===scheduled run");
        },3, TimeUnit.SECONDS);

        executorService.shutdown();// 关闭线程池
    }
}
