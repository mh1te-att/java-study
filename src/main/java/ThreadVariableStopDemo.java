public class ThreadVariableStopDemo {
    public static void main(String[] args) throws InterruptedException {
        VariableStopThread thread = new VariableStopThread("Thread:1");
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}

class VariableStopThread extends Thread {

    private boolean interrupt = true;

    public VariableStopThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Thread start...");
        int i = 0;
        while(!isInterrupted()) {
            System.out.println(i++);
        }
        System.out.println(Thread.currentThread().getName() + " Thread has been stopped " + System.currentTimeMillis());
    }

    @Deprecated
    public void Stop() {
        System.out.println(Thread.currentThread().getName() + " Thread stop..." + System.currentTimeMillis());
        interrupt = false;
    }
}
