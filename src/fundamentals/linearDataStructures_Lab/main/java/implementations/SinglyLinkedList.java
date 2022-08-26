package fundamentals.linearDataStructures_Lab.main.java.implementations;

import fundamentals.linearDataStructures_Lab.main.java.interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            Node<E> currentHead = head;

            head = newNode;
            head.next = currentHead;
        }

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            Node<E> last = head;

            while (last.next != null) {
                last = last.next;
            }

            last.next = newNode;
        }

        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();

        E firstValue = head.value;
        head = head.next;

        this.size--;
        return firstValue;
    }

    @Override
    public E removeLast() {
        ensureNonEmpty();

        Node<E> preLast = head;
        Node<E> lastNode = head.next;

        while (lastNode.next != null) {
            preLast = lastNode;
            lastNode = lastNode.next;
        }

        preLast.next = null;

        this.size--;
        return lastNode.value;
    }

    @Override
    public E getFirst() {
        ensureNonEmpty();

        return head.value;
    }

    @Override
    public E getLast() {
        ensureNonEmpty();

        Node<E> last = head;

        while (last.next != null) {
            last = last.next;
        }

        return last.value;
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
        if (this.isEmpty()) {
            throw new IllegalStateException("The list is empty!");
        }
    }
}
