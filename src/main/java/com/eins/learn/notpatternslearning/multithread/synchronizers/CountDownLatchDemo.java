package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static final CountDownLatch START = new CountDownLatch(8);
    private static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i, (int) (Math.random() * 100 + 50))).start();
            Thread.sleep(1000);
        }

        while (START.getCount() > 3) //Check all cars are ready
            Thread.sleep(100);

        Thread.sleep(1000);
        System.out.println("3!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("2!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("1, GO!");
        START.countDown();
    }

    public static class Car implements Runnable {
        private int carNumber;
        private int carSpeed;

        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Car #%d arrived.\n", carNumber);
                START.countDown();
                // await while countdown reach 0 
                START.await();
                Thread.sleep(trackLength / carSpeed);
                System.out.printf("Car #%d finished!\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
