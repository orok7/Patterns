package notpatternslearning.multithread.asynchronous.mystack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class StackDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        AtomicLong counter = new AtomicLong(0);
//        MyStack<String> myStack = new MyLinkedStack<>();
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//
//        Set<Callable<String>> tasks = new HashSet<>();
//
//        int writeCount = 20;
//        for (int i = 0; i < writeCount; i++) {
//            tasks.add(() -> {
//                myStack.push("String#" + counter.incrementAndGet());
//                return "";
//            });
//        }
//
//        int readCount = 10;
//        for (int i = 0; i < readCount; i++) {
//            tasks.add(myStack::pop);
//        }
//
//        List<Future<String>> results = executor.invokeAll(tasks);
//        for (Future<String> result : results) {
//            if (result == null) {
//                System.out.println("NULL");
//                continue;
//            }
//            String x = result.get();
//            if (x != null && x.isEmpty()) continue;
//            System.out.println(x);
//        }
//
//        executor.shutdown();
//        System.out.println(myStack.size());
    }

}
