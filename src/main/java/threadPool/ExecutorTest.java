package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 往线程池中放任务
        for (int i = 0; i < 10; i++) {
            final int index = i; // 任务的序号
            executorService.execute(()-> {
                System.out.println("===task" + index);
            });
        }
        executorService.shutdown(); // 关闭线程池
    }
}
