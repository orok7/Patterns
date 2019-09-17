package notpatternslearning.multithread.waitnotifyexmpl;

import java.util.List;

class Producer implements Runnable {
    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(List<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run() {
        int counter = 1;
        while (true) {
            try {
                produce(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() == MAX_CAPACITY) {
//                System.out.println(
//                        "Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue
//                                .size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.print("\t\tProducer:\t\t[ ");
            taskQueue.forEach(e -> System.out.print(e + " "));
            System.out.println("]");
            taskQueue.notify();
        }
    }
}