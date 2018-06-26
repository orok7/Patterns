package notpatternslearning.multithread.blinov.tasks.port;

import notpatternslearning.multithread.try1.MyThread;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Port {
    private final String id;
    private final int capacity;
    private final int numberOfDocks;
    private final Dock[] docks;
    private List<Container<?>> containers = new ArrayList<>();

    private Lock lock = new ReentrantLock();
    private Condition isDockFull = lock.newCondition();
    private Condition isPortFull = lock.newCondition();

    public Port(String id, int capacity, int numberOfDocks) {
        if (capacity < 1 || numberOfDocks < 1) {
            throw new RuntimeException("Number of docks and capacity must be greater than 1");
        }
        this.id = id;
        this.capacity = capacity;
        this.numberOfDocks = numberOfDocks;
        docks = new Dock[numberOfDocks];
        for (int i = 0; i < numberOfDocks; i++) {
            docks[i] = new Dock("D:" + (i + 1));
        }
    }

    public void docking(Ship ship) {
        Dock freeDock;
        lock.lock();
        try {
            while ((freeDock = getFirstFreeDock()) == null) {
                System.out.println("## " + ship + " is waiting for free dock in " + this);
                isDockFull.await();
            }
            freeDock.setDocked(ship);
            System.out
                    .println("-> " + ship + " docked to " + freeDock.getDockId() + " in port " + this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        MyThread.sleepAndWait((long) (Math.random() * 100) + 1000);
        unloading(ship);
        MyThread.sleepAndWait((long) (Math.random() * 100) + 1000);
        loading(ship);
        MyThread.sleepAndWait((long) (Math.random() * 100) + 1000);
        undocking(freeDock);
    }

    private void loading(Ship ship) {
        List<Container<?>> target = filterByDestination(containers, this, true);
        if (target == null || target.isEmpty()) {
            return;
        }
        Port destination;
        if (ship.getContainers().isEmpty()) {
            destination = target.get(0).getDestination();
        } else {
            destination = ship.getContainers().get(0).getDestination();
        }
        lock.lock();
        try {
            int vacantPlaces = ship.getLoadCapacity() - ship.getContainers().size();
            target = filterByDestination(target, destination, false).stream().limit(vacantPlaces).collect(toList());
            ship.getContainers().addAll(target);
            containers.removeAll(target);
            isPortFull.signalAll();
            System.out.println("++ " + ship + " loading to port " + destination);
        } finally {
            lock.unlock();
        }
    }

    private void unloading(Ship ship) {
        List<Container<?>> target = filterByDestination(ship.getContainers(), this, false);
        if (target != null && !target.isEmpty()) {
            lock.lock();
            try {
                while ((capacity - containers.size()) < target.size()) {
                    isPortFull.await();
                }
                containers.addAll(target);
                ship.getContainers().removeAll(target);
                System.out.println("-- " + ship + " unloading in port " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private Dock getFirstFreeDock() {
        return Arrays.stream(docks).filter(dock -> dock.getDocked() == null).findFirst().orElse(null);
    }

    private void undocking(Dock dock) {
        lock.lock();
        try {
            System.out.println(
                    "<- " + dock.getDocked().getShipName() + " leave dock " + dock.getDockId() + " in port " + this);
            dock.setDocked(null);
            isDockFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private List<Container<?>> filterByDestination(List<Container<?>> containers, Port destination, boolean except) {
        Predicate<Container<?>> predicate = except ? container -> !container.getDestination().equals(destination)
                : container -> container.getDestination().equals(destination);
        return containers.stream().filter(predicate).collect(toList());
    }

    @Override public String toString() {
        return id + "[" + (capacity - containers.size()) + "]";
    }
}
