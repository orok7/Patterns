package com.eins.learn.collections;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eins.learn.collections.TestHashMapHashSet.Key.key;

public class TestHashMapHashSet {
	public static void main(String[] args) {
		testHashMap();
		testHashSet();
	}

	private static void testHashSet() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("\t\tTest HashSet");
		HashSet<Key> set = new HashSet<>();
		Key a1 = key("A", 1);
		Key a2 = key("A", 2);
		System.out.println(set.add(a1));
		System.out.println(set.add(a2));

		System.out.println("Initial values:");
		printSet(set);
		System.out.println("------------------------------------");

		a1 = key("A", 1);
		System.out.println(set.add(a1));
		printSet(set);
		System.out.println("------------------------------------");

		a2.setVal1("B");
		a2.setVal2(3);
		printSet(set);
		System.out.println("------------------------------------");

		Key a3 = key("A", 1);
		System.out.println(set.add(a3));
		printSet(set);
		System.out.println("------------------------------------");

		Key a4 = key("B", 2);
		System.out.println(set.add(a4));
		printSet(set);
		System.out.println("------------------------------------");

		Key a5 = key("B", 3);
		System.out.println(set.add(a5));
		printSet(set);
		System.out.println("------------------------------------");

		Key a6 = key("B", 2);
		a6.setVal2(3);
		System.out.println(set.add(a6));
		printSet(set);
	}

	private static <T> void printSet(Set<T> set) {
		System.out.println(set.stream().map(Objects::toString).collect(Collectors.joining(", ")));
	}

	private static void testHashMap() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("\t\tTest HashMap");
		HashMap<Key, String> map = new HashMap<>();

		Key a1 = key("A", 1);
		Key a2 = key("A", 2);

		map.put(a1, "A1");
		map.put(a2, "A2");

		System.out.println("Initial values:");
		System.out.println(map.get(a1) + "\t\t" + a1);
		System.out.println(map.get(a2) + "\t\t" + a2);

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println();

		a1 = key("A", 1);
		System.out.println(map.get(a1) + "\t\t" + a1);

		a2.setVal1("B");
		a2.setVal2(3);
		System.out.println(map.get(a2) + "\t\t" + a2);

		Key a3 = key("A", 1);
		System.out.println(map.get(a3) + "\t\t" + a3);

		Key a4 = key("B", 2);
		System.out.println(map.get(a4) + "\t\t" + a4);

		Key a5 = key("B", 3);
		System.out.println(map.get(a5) + "\t\t" + a5);

		Key a6 = key("B", 2);
		a6.setVal2(3);
		System.out.println(map.get(a6) + "\t\t" + a6);

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println();
		System.out.println(map.put(a1, "nA1") + "\t\t" + map.get(a1) + "\t\t" + a1);
		System.out.println(map.put(a2, "nA2") + "\t\t" + map.get(a2) + "\t\t" + a2);
		System.out.println(map.put(a3, "nnA1") + "\t\t" + map.get(a3) + "\t\t" + a3);
		System.out.println(map.put(a4, "nnull") + "\t\t" + map.get(a4) + "\t\t" + a4);
		System.out.println(map.put(a5, "nnull") + "\t\t" + map.get(a5) + "\t\t" + a5);
		System.out.println(map.put(a6, "nnA2") + "\t\t" + map.get(a6) + "\t\t" + a6);
	}

	@Getter
	@Setter
	public static class Key {
		private String val1;
		private Integer val2;
		private int hash;

		public static Key key(String val1, Integer val2) {
			Key key = new Key();
			key.val1 = val1;
			key.val2 = val2;
			key.hash = val2;
			return key;
		}

		@Override
		public String toString() {
			return "K[" + val1 + "-" + val2 + "]";
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Key key = (Key) o;
			return Objects.equals(val1, key.val1) && Objects.equals(val2, key.val2);
		}

		@Override
		public int hashCode() {
			return hash;//Objects.hash(val1, val2);
		}
	}
}
