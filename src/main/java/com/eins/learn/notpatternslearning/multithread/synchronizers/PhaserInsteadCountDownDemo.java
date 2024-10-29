package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.concurrent.Phaser;

public class PhaserInsteadCountDownDemo {
    private static final Phaser START = new Phaser(8);
    private static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i, (int) (Math.random() * 100 + 50))).start();
        }

        while (START.getRegisteredParties() > 3) //Check all cars are ready
            Thread.sleep(100);                  

        Thread.sleep(100);
        System.out.println("3");
        START.arriveAndDeregister();

        Thread.sleep(100);
        System.out.println("2");
        START.arriveAndDeregister();

        Thread.sleep(100);
        System.out.println("1, GO");
        START.arriveAndDeregister();
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
                START.arriveAndDeregister();
                START.awaitAdvance(0);
                Thread.sleep(trackLength / carSpeed);
                System.out.printf("Car #%d finished.\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
