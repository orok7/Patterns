package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
    // Initialize a barrier for three threads and a task that will be executed
    // when three threads have gathered at the barrier. After that, they will be released.

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
//            Thread.sleep(400);
        }
    }

    // Task that will be executed when the sides reach the barrier
    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
//            try {
//                Thread.sleep(500);
                System.out.println("The ferry transported the cars!");
//            } catch (InterruptedException e) {
//            }
        }
    }

    // Parties that will reach the barrier
    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Car #%d arrived.\n", carNumber);
                // To indicate to a thread that it has reached the barrier, you need to call the await() method
                // After this, this thread is blocked and waits for the other parties to reach the barrier
                BARRIER.await();
                System.out.printf("Car #%d continue moving.\n", carNumber);
            } catch (Exception e) {
            }
        }
    }
}