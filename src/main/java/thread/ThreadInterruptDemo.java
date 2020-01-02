package thread;

/**
 * interrupt终止线程
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new InterruptThread("thread-1");
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}

class InterruptThread extends Thread {

    public InterruptThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " start");
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(0);
                System.out.println(i);
            }
            System.out.println(this.getName() + " complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(this.getName() + " interrupt");
    }
}