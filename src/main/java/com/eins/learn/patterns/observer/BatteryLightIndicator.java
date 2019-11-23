package com.eins.learn.patterns.observer;

public class BatteryLightIndicator implements IObserver {
    private Battery battery;

    public BatteryLightIndicator(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        if (battery.isConnectedToCharger()) {
            if (battery.getCharged() == 100) {
                System.out.println("Indicator is green");
            } else {
                System.out.println("Indicator is red");
            }
        }
    }
}
