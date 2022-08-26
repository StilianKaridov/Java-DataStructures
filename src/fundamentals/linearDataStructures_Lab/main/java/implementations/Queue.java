package fundamentals.linearDataStructures_Lab.main.java.implementations;

import fundamentals.linearDataStructures_Lab.main.java.interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    public Queue() {
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.tail = newNode;
        } else {
            Node<E> current = this.tail;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();

        E toPoll = this.tail.value;

        if (this.size == 1) {
            this.tail = null;
        } else {
            Node<E> next = this.tail.next;
            this.tail.next = null;
            this.tail = next;
        }

        this.size--;

        return toPoll;
    }

    @Override
    public E peek() {
        ensureNonEmpty();

        return this.tail.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = tail;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E next = current.value;
                current = current.next;

                return next;
            }
        };
    }

    private void ensureNonEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty!");
        }
    }
}
