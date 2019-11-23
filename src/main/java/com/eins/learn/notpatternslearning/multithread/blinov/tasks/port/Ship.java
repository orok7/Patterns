package com.eins.learn.notpatternslearning.multithread.blinov.tasks.port;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ship {
    private final String shipName;
    private Integer loadCapacity;
    private List<Container<?>> containers = new ArrayList<>();

    public Ship(Integer loadCapacity, String shipName) {
        this.loadCapacity = loadCapacity;
        this.shipName = shipName;
    }

    public void go(Port port) {
        new Thread(() -> {
            port.docking(this);
            while (!containers.isEmpty()) {
                containers.get(0).getDestination().docking(this);
            }
        }).start();
    }

    @Override public String toString() {
        return shipName + "[" + containers.size() + "]";
    }
}
