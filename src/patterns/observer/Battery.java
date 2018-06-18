package patterns.observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Battery implements IObservable{
    private List<IObserver> observers = new ArrayList<>();
    private int charged;
    private boolean connectedToCharger;
    private boolean turnedOn;
    private Runnable charging = () -> {
        do {
            charged++;
            if (charged > 100) {
                charged = 100;
            }
            notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (connectedToCharger);
    };

    private Runnable uncharging = () -> {
        do {
            charged--;
            if (charged < 1) {
                turnedOn = false;
            }
            notifyObservers();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!connectedToCharger && turnedOn);
    };

    public Battery(int charged) {
        this.charged = charged;
    }

    public void connectToCharger() {
        connectedToCharger = true;
        notifyObservers();
        Thread thread = new Thread(charging);
        thread.setDaemon(true);
        thread.start();
    }

    public void disconnectFromCharger() {
        connectedToCharger = false;
        notifyObservers();
        Thread thread = new Thread(uncharging);
        thread.setDaemon(true);
        thread.start();
    }

    public void turnOn() {
        turnedOn = true;
        notifyObservers();
        Thread thread = new Thread(uncharging);
        thread.setDaemon(true);
        thread.start();
    }

    public void turnOff() {
        turnedOn = false;
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.parallelStream().forEach(IObserver::update);
    }

    public int getCharged() {
        return charged;
    }

    public boolean isConnectedToCharger() {
        return connectedToCharger;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }
}
