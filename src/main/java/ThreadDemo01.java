public class ThreadDemo01 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("hello");
    }
}
