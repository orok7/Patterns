package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class PhaserDemo {
    private static final Phaser PHASER = new Phaser(1);//immediately register the main flow
    //Phases 0 and 6 are the bus depot, stops 1 - 5

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {           //Generate passengers at stops
            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, i + 1));//This passenger gets off at the next one.

            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, 5));    //This passenger gets off at the final stop.
        }

        for (int i = 0; i < 11; i++) {
            switch (i) {
            case 0:
                System.out.println("The bus left the park.");
                PHASER.arriveAndAwaitAdvance();//In phase 0 there is only 1 participant - a bus
                break;
            case 10:
                System.out.println("The bus left for the park.");
                PHASER.arriveAndDeregister();//remove the main flow, break the barrier
                break;
            default:
                int currentBusStop = PHASER.getPhase();
                System.out.println("Stop # " + currentBusStop);

                for (Passenger p : passengers)          //Checking if there are passengers at the stop
                    if (p.departure == currentBusStop) {
                        PHASER.register();// register the flow that will participate in the phases
                        p.start();        // and launch
                    }

                PHASER.arriveAndAwaitAdvance();// notify about readiness
            }
        }
    }

    public static class Passenger extends Thread {
        private int departure;
        private int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " wait on stop # " + this.departure);
        }

        @Override
        public void run() {
            try {
                System.out.println(this + " got on the bus.");

                while (PHASER.getPhase() < destination) { //Until the bus arrives at the required stop (phase)
                    PHASER.arriveAndAwaitAdvance();     //declare our readiness at each stage and wait
                }

                Thread.sleep(1000);
                System.out.println(this + " left the bus.");
                PHASER.arriveAndDeregister();   //cancel registration at the required phase
            } catch (InterruptedException e) {
            }
        }

        @Override
        public String toString() {
            return "Passenger{" + departure + " -> " + destination + '}';
        }
    }
}
