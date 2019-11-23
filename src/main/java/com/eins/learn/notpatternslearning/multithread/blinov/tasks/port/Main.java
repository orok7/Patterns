package com.eins.learn.notpatternslearning.multithread.blinov.tasks.port;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String[] CONTENTS = { "Cars", "Shoes", "Clothes", "Beer", "Computers", "Glasses", "Fish",
            "Meat" };

    public static void main(String[] args) {
        Port odesa = new Port("Odesa", 20, 1);
        Port newYork = new Port("New York", 20, 2);
        Port london = new Port("London", 20, 2);

        List<Port> ports = new ArrayList<>();
        ports.add(odesa);
        ports.add(newYork);
        ports.add(london);

        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            int indexDep = random.nextInt(ports.size());
            while (ports.get(indexDep).getContainers().size() >= ports.get(indexDep).getCapacity() && indexDep == 3) {
                indexDep = random.nextInt(ports.size());
            }
            int indexDes = random.nextInt(ports.size());
            while (indexDes == indexDep) {
                indexDes = random.nextInt(ports.size());
            }
            Port dep = ports.get(indexDep);
            Port des = ports.get(indexDes);
            String content = CONTENTS[random.nextInt(CONTENTS.length)];
            dep.getContainers().add(new Container<>(content, dep, des));
        }

        Ship titanic = new Ship(5, "Titanic");
        Ship barzha = new Ship(1, "Barzha");
        Ship kokush = new Ship(3, "Kokush");
        Ship dido = new Ship(2, "Dido");
        Ship shipper = new Ship(4, "Shipper");

        titanic.go(odesa);
        barzha.go(newYork);
        kokush.go(newYork);
        dido.go(newYork);
        shipper.go(odesa);

    }

}
