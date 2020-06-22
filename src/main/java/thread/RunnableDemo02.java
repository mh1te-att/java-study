package thread;

public class RunnableDemo02 implements Runnable {

    private Thread t;
    private final String threadName;

    RunnableDemo02(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running" + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("thread: " + threadName + "," + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread" + threadName + " exiting");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

class TestThread {
    public static void main(String[] args) {
        RunnableDemo02 R1 = new RunnableDemo02("Thread-1");
        R1.start();

        RunnableDemo02 R2 = new RunnableDemo02("Thread-2");
        R2.start();
    }
}
