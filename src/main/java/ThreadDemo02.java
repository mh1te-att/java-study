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
