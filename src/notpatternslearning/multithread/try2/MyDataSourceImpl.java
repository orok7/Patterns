package notpatternslearning.multithread.try2;

import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDataSourceImpl extends MyDataSource {

    private ReentrantLock lock = new ReentrantLock();
    private Condition isEmpty = lock.newCondition();

    private Integer consumersCount = 0;

    private Map<Integer, Queue<Integer>> data;

    public MyDataSourceImpl(File file) {
        super(file);
        data = new HashMap<>();
        data.put(0, new ArrayDeque<>());
    }

    @Override
    public Integer getNextData(Integer consumerKey) {
        Integer returnData = null;
        try {
            lock.lock();
            while (data.get(consumerKey).isEmpty()) {
                isEmpty.await();
            }
            returnData = data.get(consumerKey).remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return returnData;
    }

    @Override
    public void setNextData(Integer ch) {
        try {
            lock.lock();
            for (int i = 1; i <= consumersCount; i++) {
                data.get(i).add(ch);
            }
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Integer registerNewConsumer() {
        consumersCount++;
        data.put(consumersCount, new ArrayDeque<>());
        return consumersCount;
    }
}
