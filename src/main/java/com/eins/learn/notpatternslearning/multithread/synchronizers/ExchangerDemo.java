package com.eins.learn.notpatternslearning.multithread.synchronizers;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[] { "{parcel A->D}", "{parcel A->C}" };// form the cargo for the 1st truck
        String[] p2 = new String[] { "{parcel B->C}", "{parcel B->D}" };// form the cargo for the 2st truck
        new Thread(new Truck(1, "A", "D", p1)).start();// 1st truck from А to D
        Thread.sleep(100);
        new Thread(new Truck(2, "B", "C", p2)).start();// 2nd truck from В to С
    }

    public static class Truck implements Runnable {
        private int number;
        private String dep;
        private String dest;
        private String[] parcels;

        public Truck(int number, String departure, String destination, String[] parcels) {
            this.number = number;
            this.dep = departure;
            this.dest = destination;
            this.parcels = parcels;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Truck #%d was loaded: %s and %s.\n", number, parcels[0], parcels[1]);
                System.out.printf("Truck #%d departed %s to %s.\n", number, dep, dest);
                Thread.sleep(1000 + (long) (Math.random() * 5000));
                System.out.printf("Truck #%d arrived.\n", number);
                parcels[1] = EXCHANGER.exchange(parcels[1]);// When calling exchange(), the thread blocks and waits
                // until another thread calls exchange(), after which the exchange of parcels will occur
                System.out.printf("Truck #%d was loaded %s.\n", number, dest);
                Thread.sleep(1000 + (long) (Math.random() * 5000));
                System.out.printf("Truck #%d arrived to %s: %s and %s.\n", number, dest, parcels[0],
                        parcels[1]);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
