package collections.tree.rbtree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class RBTree<T> {
    private final Comparator<T> comparator;
    private Node root;
    private int size;

    public RBTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T element) {
        Node newNode = new Node();
        newNode.value = element;
        if (root == null) {
            newNode.color = Color.BLACK;
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
                newNode.isLeft = false;
                current.right = newNode;
                newNode.parent = current;
                balancing(newNode);
            }
        } else {
            if (current.hasLeft()) {
                addNew(newNode, current.left);
            } else {
                newNode.isLeft = true;
                current.left = newNode;
                newNode.parent = current;
                balancing(newNode);
            }
        }
    }

    private void balancing(Node node) {
        Case analysis = analysis(node);
        if (analysis == null) {
            return;
        }

        switch (analysis) {
        case CASE1:
            node.parent.inverseColor();
            node.getGrandParent().inverseColor();
            node.getUncle().inverseColor();
            if (root.isRed()) {
                root.color = Color.BLACK;
            }
            balancing(node.getGrandParent());
            break;

        case CASE2: {
            Node grandParent = node.getGrandParent();
            Node parent = node.parent;
            node.parent = grandParent;
            if (node.isLeft) {
                node.isLeft = false;
                grandParent.right = node;
                Node right = node.right;
                node.right = parent;
                parent.parent = node;
                parent.left = right;
                if (right != null) {
                    right.parent = parent;
                    right.isLeft = true;
                }
            } else {
                node.isLeft = true;
                grandParent.left = node;
                Node left = node.left;
                node.left = parent;
                parent.parent = node;
                parent.right = left;
                if (left != null) {
                    left.parent = parent;
                    left.isLeft = false;
                }
            }
            balancing(parent);
            break;
        }
        case CASE3: {
            Node grandParent = node.getGrandParent();
            Node parent = node.parent;
            if (root.equals(grandParent)) {
                root = parent;
                parent.parent = null;
            }
            Node greatGrandParent = grandParent.parent;
            if (greatGrandParent != null) {
                if (grandParent.isLeft) {
                    greatGrandParent.left = parent;
                    parent.isLeft = true;
                } else {
                    greatGrandParent.right = parent;
                    parent.isLeft = false;
                }
                parent.parent = greatGrandParent;
            }
            grandParent.parent = parent;
            if (node.isLeft) {
                Node right = parent.right;
                parent.right = grandParent;
                grandParent.left = right;
                if (right != null) {
                    right.parent = grandParent;
                    right.isLeft = true;
                }
                parent.inverseColor();
                grandParent.inverseColor();
                grandParent.isLeft = false;
            } else {
                Node left = parent.left;
                parent.left = grandParent;
                grandParent.right = left;
                if (left != null) {
                    left.parent = grandParent;
                    left.isLeft = false;
                }
                parent.inverseColor();
                grandParent.inverseColor();
                grandParent.isLeft = true;
            }
            break;
        }
        }
        if (root.isRed()) {
            root.color = Color.BLACK;
        }
    }

    private boolean validateNodeParent(Node node) {
        if (node != null && node.parent != null) {
            if (node.isLeft) {
                return node.equals(node.parent.left);
            } else {
                return node.equals(node.parent.right);
            }
        }
        return true;
    }

    private Case analysis(Node newNode) {
        Node parent = newNode.parent;
        if (parent == null || !parent.isRed()) {
            return null;
        }
        Node uncle = newNode.getUncle();
        if (uncle == null || !uncle.isRed()) {
            if (parent.isLeft ^ newNode.isLeft) {
                return Case.CASE2;
            } else {
                return Case.CASE3;
            }
        }
        return Case.CASE1;
    }

    private void removeNode(Node removed) {
        if (removed.hasLeft()) {
            getLastRight(removed.left).right = removed.right;
        } else if (removed.hasRight()) {
            if (removed.parent.hasLeft() && removed.parent.left.equals(removed)) {
                removed.parent.left = removed.right;
            } else {
                removed.parent.right = removed.right;
//                balancing(newNode);
            }
        } else {
            if (removed.parent.hasLeft() && removed.parent.left.equals(removed)) {
                removed.parent.left = null;
            } else {
                removed.parent.right = null;
            }
        }
    }

    private Node getLastRight(Node current) {
        while (current.hasRight()) {
            current = current.right;
        }
        return current;
    }

    private class Node {
        Node left;
        Node right;
        Node parent;
        Color color = Color.RED;
        boolean isLeft;
        T value;

        boolean hasLeft() {
            return left != null;
        }

        boolean hasRight() {
            return right != null;
        }

        boolean isRed() {
            return Color.RED.equals(color);
        }

        Node getUncle() {
            Node grandParent = getGrandParent();
            if (parent == null || grandParent == null) {
                return null;
            }
            if (this.parent.isLeft) {
                return grandParent.right;
            }
            return grandParent.left;
        }

        Node getGrandParent() {
            return this.parent != null ? this.parent.parent : null;
        }

        void inverseColor() {
            if (Color.RED.equals(color)) {
                color = Color.BLACK;
            } else {
                color = Color.RED;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Node node = (Node) o;

            if (isLeft != node.isLeft)
                return false;
            if (left != null ? !left.equals(node.left) : node.left != null)
                return false;
            if (right != null ? !right.equals(node.right) : node.right != null)
                return false;
            if (color != node.color)
                return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            int result = left != null ? left.hashCode() : 0;
            result = 31 * result + (right != null ? right.hashCode() : 0);
            result = 31 * result + color.hashCode();
            result = 31 * result + (isLeft ? 1 : 0);
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        protected boolean canEqual(Object other) {
            return other instanceof RBTree.Node;
        }
    }

    private enum Color {
        RED, BLACK
    }

    private enum Case {
        CASE1, CASE2, CASE3
    }

}
