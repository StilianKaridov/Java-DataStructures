package fundamentals.linearDataStructures.src.main.java.implementations;

import fundamentals.linearDataStructures.src.main.java.interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value) {
            this.element = value;
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            Node<E> oldHead = this.head;

            this.head.prev = newNode;
            this.head = newNode;
            this.head.next = oldHead;
        }

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            Node<E> oldTail = this.tail;
            this.tail = newNode;
            this.tail.prev = oldTail;
        }

        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        E element = this.head.element;

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node<E> newHead = this.head.next;
            this.head.next = null;
            this.head = newHead;
            this.head.prev = null;
        }

        this.size--;
        return element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        if (this.size == 1) {
            return removeFirst();
        }

        Node<E> oldTail = this.tail;
        Node<E> preLast = this.tail.prev;

        this.tail.prev.next = null;
        this.tail = oldTail.prev;
        this.tail.prev = preLast.prev;

        this.size--;

        return oldTail.element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return this.tail.element;
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
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal remove for empty LinkedList");
        }
    }
}
