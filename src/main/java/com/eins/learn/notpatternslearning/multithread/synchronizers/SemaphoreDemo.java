package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    // Parking space occupied - true, free - false
    private static final boolean[] PARKING_PLACES = new boolean[5];
    // We set the "fair" flag, in which case the
    // aсquire() method will distribute permissions in order of the queue
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("Car #%d pulled up to the parking lot.\n", carNumber);
            try {
                // acquire() requests access to the block of code following the call to this method,
                // if access is not granted, the thread calling this method is blocked until the semaphore grants access
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                // look for a free space and park.
                synchronized (PARKING_PLACES){
                    for (int i = 0; i < 5; i++)
                        if (!PARKING_PLACES[i]) {      // if the space is free
                            PARKING_PLACES[i] = true;  // occupy it
                            parkingNumber = i;         // Availability of free space is guaranteed by a semaphore
                            System.out.printf("Car #%d parked in place %d.\n", carNumber, i);
                            break;
                        }
                }

                Thread.sleep(5000);

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false;// free space
                }

                //release(), on the other hand, frees the resource
                SEMAPHORE.release();
                System.out.printf("Car #%d left the parking lot.\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}