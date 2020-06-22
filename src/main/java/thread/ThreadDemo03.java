package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadDemo03
 * @Description: thread线程创建
 * @Author: zhaoyc
 * @Date: 6/3/2020 5:21 PM
 */
@Slf4j(topic = "c.test")
public class ThreadDemo03 {

    public static void threadTest() {
        // 常规写法
        Thread t0 = new Thread() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t0.setName("t0");

        // 常规写法加命名
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running");
            }
        };

        // lambda表达式
        Thread t2 = new Thread(() -> log.debug("running"));
        t2.setName("t2");

        // lambda表达式加命名
        Thread t3 = new Thread(() -> log.debug("running"), "t3");

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        log.debug("running");
    }

    public static void runnableTest() {
        //常规写法
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };

        // lambda表达式
        /** Runnable接口中只有一个方法,可以加上FunctionalInterface注解,是lambda表达式更简洁 */
        Runnable r1 = () -> log.debug("running");

        Thread t0 = new Thread(r0, "r0");
        Thread t1 = new Thread(r1, "r1");

        t0.start();
        t1.start();
        log.debug("running");
    }

    public static void main(String[] args) {
        threadTest();
        runnableTest();
    }
}
