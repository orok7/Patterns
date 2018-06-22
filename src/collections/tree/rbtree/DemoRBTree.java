package collections.tree.rbtree;

import java.util.Arrays;
import java.util.Comparator;

public class DemoRBTree {
    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < 15; i++) {
            tree.add((int) (Math.random() * 100));
//            tree.add(i);
        }
        tree.printTree();
        System.out.println(tree.getSize());
        //        tree.add(18);
//        tree.add(18);
//        tree.printTree();
//        tree.remove(18);
//        tree.printTree();
        System.out.println(tree.toList());
        System.out.println(Arrays.toString(tree.toArray()));
    }
}
