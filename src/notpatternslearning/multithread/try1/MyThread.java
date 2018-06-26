package notpatternslearning.multithread.try1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyThread<T> extends Thread {

    private Callable<T> task;
    private Runnable voidTask;
    private T result;

    public MyThread(String name, Callable<T> task) {
        super(name);
        this.task = task;
    }

    public MyThread(String name, Runnable task) {
        super(name);
        this.voidTask = task;
    }

    @Override
    public void run() {
        try {
            if (task != null) {
                result = task.call();
            } else {
                voidTask.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T getResult() {
        return result;
    }

    public static void sleepAndWait(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
