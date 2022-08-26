package fundamentals.linearDataStructures_Lab.main.java.implementations;

import fundamentals.linearDataStructures_Lab.main.java.interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private Node<E> top;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> prev;

        public Node(E element) {
            this.value = element;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        newNode.prev = top;
        top = newNode;

        this.size++;
    }

    @Override
    public E pop() {
        ensureNonEmpty();
        Node<E> currentTop = top;

        top = top.prev;
        this.size--;

        return currentTop.value;
    }

    @Override
    public E peek() {
        ensureNonEmpty();

        return top.value;
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

            private Node<E> current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.value;
                this.current = this.current.prev;
                return element;
            }
        };
    }

    private void ensureNonEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty!");
        }
    }
}
