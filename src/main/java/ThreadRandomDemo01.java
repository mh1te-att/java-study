public class ThreadRandomDemo01 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new ThreadRandom("Thread:" + i);
        }
        for (Thread  thread : threads) {
            thread.start();
        }
    }
}

class ThreadRandom extends Thread {

    public ThreadRandom(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
