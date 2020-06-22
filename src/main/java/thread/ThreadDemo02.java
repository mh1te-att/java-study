package thread;

/**
 * @Title: ThreadDemo02
 * @Description: runnable建立线程
 * @Author: zhaoyc
 * @Date: 6/3/2020 5:08 PM
 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("by runnable");
    }
}
