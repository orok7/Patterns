package patterns.observer;

public class ObserversDemo {
    public static void main(String[] args) {
        Battery battery = new Battery(95);
        BatteryLightIndicator lightIndicator = new BatteryLightIndicator(battery);
        battery.registerObserver(lightIndicator);
        BatteryScreenIndicator screenIndicator = new BatteryScreenIndicator(battery);
        battery.registerObserver(screenIndicator);

        try {
            battery.turnOn();
            Thread.sleep(5000);
            battery.connectToCharger();
            Thread.sleep(10000);
            battery.disconnectFromCharger();
            Thread.sleep(10000);
            battery.connectToCharger();
            Thread.sleep(10000);
            battery.disconnectFromCharger();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
