package thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: FutureTaskDemo
 * @Description: FutureTask创建线程
 * @Author: zhaoyc
 * @Date: 6/5/2020 3:30 PM
 */
@Slf4j(topic = "c.test")
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);
                return 100;
            }
        });

        Thread t0 = new Thread(futureTask, "t0");
        t0.start();

        log.debug("{}", futureTask.get());
    }
}
