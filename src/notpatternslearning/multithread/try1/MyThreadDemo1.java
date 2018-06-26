package notpatternslearning.multithread.try1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;

public class MyThreadDemo1 {

    private static final String[] CITIES = { "Lviv", "Kyiv", "Kharkiv", "Ternopil", "Rivne", "Frankivsk", "Uzhgorod",
            "Chernivci", "Khmelnytckyy", "Vinnytcya", "Zhytomyr", "Sumy", "Chernihiv", "Cherkasy", "Odesa", "Mykolayiv",
            "Dnipro" };
    private final Integer writerRunCount = 10;
    private final AtomicReference<String> data = new AtomicReference<>();
    private boolean isWriterDone = false;
    private AtomicLong readCounter = new AtomicLong(0);

    private Callable<String> read = () -> {
        String res;
        synchronized (data) {
            data.wait();
            res = data.get();
            data.notify();
        }
        return res;
    };

    private Runnable reader = () -> {
        while (!isWriterDone) {
            MyThread<String> response = new MyThread<>("read" + readCounter.incrementAndGet(), read);
            response.start();
            try {
                response.join();
                System.out.println(response.getResult());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable writer = () -> {
        isWriterDone = false;
        for (int i = 0; i < writerRunCount; i++) {
            Condition condition;
            MyThread.sleepAndWait(500);
            synchronized (data) {
                data.set(CITIES[(int) (Math.random() * CITIES.length)]);
                data.notify();
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        isWriterDone = true;
    };

    private MyThread<String> writerThread = new MyThread<>("writer", this.writer);
    private MyThread<String> readerThread = new MyThread<>("reader", this.reader);

    public static void main(String[] args) {
        new MyThreadDemo1().go();
    }

    private void go() {
        // writerThread.start();
        // readerThread.start();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(writer);
        executor.execute(reader);
        executor.shutdown();
    }

}
