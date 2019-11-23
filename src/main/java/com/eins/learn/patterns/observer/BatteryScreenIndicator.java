package com.eins.learn.patterns.observer;

public class BatteryScreenIndicator implements IObserver {
    private Battery battery;

    public BatteryScreenIndicator(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        if (battery.isTurnedOn()) {
            System.out.println(battery.getCharged() + "%" + (battery.isConnectedToCharger() ? "..charging" : ""));
        }
    }
}
