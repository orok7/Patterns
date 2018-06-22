package collections.tree.bitree;

import java.util.Arrays;
import java.util.Comparator;

public class DemoBiTree {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < 15; i++) {
            tree.add((int) (Math.random() * 100));
        }
        tree.printTree();
        tree.add(18);
        tree.add(18);
        tree.printTree();
        tree.remove(18);
        tree.printTree();
        System.out.println(tree.toList());
        System.out.println(Arrays.toString(tree.toArray()));
    }
}
