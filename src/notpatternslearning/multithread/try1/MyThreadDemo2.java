package notpatternslearning.multithread.try1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import static notpatternslearning.multithread.try1.MyThread.sleepAndWait;

public class MyThreadDemo2 {

    private AtomicBoolean isTaskDone = new AtomicBoolean(false);

    public static void main(String[] args) {
        new MyThreadDemo2().go();
    }


    private void go() {
        MyThread<String> waiting = new MyThread<>("waiting", this.waiting);
        MyThread<String> makeTask = new MyThread<>("makeTask", this.makeTask);
        waiting.start();
        makeTask.start();
        try {
            waiting.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(makeTask.getResult());
    }

    private Runnable waiting = () -> {
        while(!isTaskDone.get()) {
            for (Step step : steps) {
                try {
                    step.doStep();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isTaskDone.get()) {
                   break;
               }
            }
        }
    };

    private Callable<String> makeTask = () -> {
        try {
            sleepAndWait(9000);
            return "DONE";
        } finally {
            isTaskDone.set(true);
        }
    };

    private interface Step {
        void doStep() throws InterruptedException;
    }

    private static final int timeout = 500;
    private static final char symbol = '*';

    private static Step[] steps = {
            () -> {
                System.out.print("" + symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol+symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol+symbol+symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol+symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            },
            () -> {
                System.out.print("" + symbol+symbol);
                sleepAndWait(timeout);
                System.out.print("\r");
            }
    };

}
