package fundamentals.linearDataStructures.main.java.implementations;

import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {

    private static final int INITIAL_CAPACITY = 2;

    private int size;
    private int capacity;
    private Object[] elements;

    public ReversedList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    public void add(E element) {
        if (this.size == this.capacity) {
            grow();
        }

        this.elements[this.size++] = element;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }


    public E get(int index) {
        if (isValid(index)) {
            throw new IndexOutOfBoundsException();
        }

        return (E) this.elements[this.size - 1 - index];
    }

    public void removeAt(int index) {
        if (isValid(index)) {
            throw new IndexOutOfBoundsException();
        }

        int elementIndex = this.size - 1 - index;

        for (int i = elementIndex; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.size--;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size() - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return get(index--);
            }
        };
    }

    private void grow() {
        this.capacity *= 2;
        Object[] tmp = new Object[this.capacity];

        System.arraycopy(this.elements, 0, tmp, 0, this.elements.length);

        this.elements = tmp;
    }

    private boolean isValid(int index) {
        return index < 0 || index > this.size;
    }
}
