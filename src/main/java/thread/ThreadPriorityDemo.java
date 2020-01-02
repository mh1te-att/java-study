package thread;

/**
 * 线程优先级
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread thread_1 = new ThreadPriority("thread-1");
        Thread thread_2 = new ThreadPriority("thread-2");
        thread_1.setPriority(Thread.MIN_PRIORITY);
        thread_2.setPriority(Thread.MAX_PRIORITY);
        thread_1.start();
        thread_2.start();
    }
}
 class ThreadPriority extends Thread {

    public ThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + ",number: " + i + " priority: " + this.getPriority());
        }
    }
}
