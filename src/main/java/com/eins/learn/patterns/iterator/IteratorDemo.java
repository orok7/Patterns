package com.eins.learn.patterns.iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        SomeCollection<String> collection = new SomeCollection<>();

        collection.add("qwe");
        collection.add("asd");
        collection.add("zxc");

        MyIterator<String> iterator = collection.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        collection.remove("asd");

        System.out.println("\n'asd' - removed\n");

        iterator = collection.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
