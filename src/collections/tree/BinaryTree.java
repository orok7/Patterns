package collections.tree;

import lombok.EqualsAndHashCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private final Comparator<T> comparator;
    private int size;
    private Node root;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T element) {
        Node newNode = new Node();
        newNode.value = element;
        if (root == null) {
            this.root = newNode;
            size = 1;
        } else {
            addNew(newNode, root);
            size++;
        }
    }

    public boolean remove(T element) {
        List<Node> removed = new ArrayList<>();
        walkAroundTree(root, node -> {
            if (node.value.equals(element)) {
                removed.add(node);
            }
        });
        if (!removed.isEmpty()) {
            removeNode(removed.get(0));
            size--;
            return true;
        }
        return false;
    }

    public void printTree() {
        walkAroundTree(root, node -> System.out.print(node.value + " "));
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        walkAroundTree(root, node -> list.add(node.value));
        return list;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        if (root == null) {
            return null;
        }
        T[] array = (T[]) Array.newInstance(root.value.getClass(), size);
        final int[] index = { 0 };
        walkAroundTree(root, node -> array[index[0]++] = node.value);
        return array;
    }

    private void walkAroundTree(Node current, Consumer<Node> consumer) {
        if (current.hasLeft()) {
            walkAroundTree(current.left, consumer);
        }
        consumer.accept(current);
        if (current.hasRight()) {
            walkAroundTree(current.right, consumer);
        }
    }

    private void addNew(Node newNode, Node current) {
        if (comparator.compare(newNode.value, current.value) > 0) {
            if (current.hasRight()) {
                addNew(newNode, current.right);
            } else {
                current.right = newNode;
                newNode.top = current;
            }
        } else {
            if (current.hasLeft()) {
                addNew(newNode, current.left);
            } else {
                current.left = newNode;
                newNode.top = current;
            }
        }
    }

    private void removeNode(Node removed) {
        if (removed.hasLeft()) {
            getLastRight(removed.left).right = removed.right;
        } else if (removed.hasRight()) {
            if (removed.top.hasLeft() && removed.top.left.equals(removed)) {
                removed.top.left = removed.right;
            } else {
                removed.top.right = removed.right;
                ;
            }
        } else {
            if (removed.top.hasLeft() && removed.top.left.equals(removed)) {
                removed.top.left = null;
            } else {
                removed.top.right = null;
            }
        }
    }

    private Node getLastRight(Node current) {
        while (current.hasRight()) {
            current = current.right;
        }
        return current;
    }

    @EqualsAndHashCode
    private class Node {
        Node top;
        Node left;
        Node right;
        T value;

        boolean hasLeft() {
            return left != null;
        }

        boolean hasRight() {
            return right != null;
        }
    }
}
