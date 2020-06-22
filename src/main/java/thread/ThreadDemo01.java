package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ThreadDemo01
 * @Description: thread建立线程
 * @Author: zhaoyc
 * @Date: 6/3/2020 5:00 PM
 */
@Slf4j(topic = "c.test")
public class ThreadDemo01 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        log.debug("running");
    }
}

@Slf4j(topic = "c.test")
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("hello");
        log.debug("hello");
    }
}
