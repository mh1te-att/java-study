package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: RunnableDemo01
 * @Description: runnable建立线程
 * @Author: zhaoyc
 * @Date: 6/3/2020 5:30 PM
 */
@Slf4j(topic = "c.test")
public class RunnableDemo01 {
    public static void main(String[] args) {

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

        Thread t0 = new Thread(r0, "t0");
        Thread t1 = new Thread(r1, "t1");

        t0.start();
        t1.start();
    }
}
