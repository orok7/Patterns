package notpatternslearning.multithread.asynchronous.mystack;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLinkedStack<T> implements MyStack<T> {

    private Node<T> top;
    private int size = 0;

    @Override
    public int size() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        try {
            return size;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void push(T t) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            top = new Node<>(t, top);
            size++;
            System.out.println("[push]: " + t + "\t\t|" + size + "|");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T pop() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            if (top == null) {
                return null;
            }
            Node<T> oldTop = this.top;
            this.top = this.top.below;
            size--;
            return oldTop.value;
        } finally {
            lock.unlock();
        }
    }

    private class Node<E> {
        private E value;
        private Node<E> below;

        public Node(E value, Node<E> below) {
            this.value = value;
            this.below = below;
        }
    }
}
