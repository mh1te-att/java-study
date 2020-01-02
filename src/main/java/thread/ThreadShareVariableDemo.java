package thread;

/**
 * 线程共享
 */
public class ThreadShareVariableDemo {
    public static void main(String[] args) {
        Runnable runnable = new ShareVariableThread();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(runnable, "Thread:" + i);
            //threads[i] = new ShareVariableThread("Thread:" + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class ShareVariableThread implements Runnable {

    private int i = 5;

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + " count: " + i--);
    }
}
