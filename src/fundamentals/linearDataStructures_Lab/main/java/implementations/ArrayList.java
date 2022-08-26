package fundamentals.linearDataStructures_Lab.main.java.implementations;

import fundamentals.linearDataStructures_Lab.main.java.interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int INITIAL_SIZE = 4;

    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        growIfNeeded();

        this.elements[this.size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValid(index)) {
            return false;
        }

        growIfNeeded();
        shiftRight(index);

        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public E get(int index) {
        if (!isValid(index)) {
            throw new IndexOutOfBoundsException("The index is not in range of 0 to " + (this.size - 1));
        }

        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (!isValid(index)) {
            throw new IndexOutOfBoundsException("The index is not in range of 0 to " + (this.size - 1));
        }

        E prevElement = get(index);

        this.elements[index] = element;

        return prevElement;
    }

    @Override
    public E remove(int index) {
        if (!isValid(index)) {
            throw new IndexOutOfBoundsException("The index is not in range of 0 to " + (this.size - 1));
        }

        E removedElement = get(index);

        shiftLeft(index);
        this.size--;

        return removedElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void growIfNeeded() {
        if (this.size == this.elements.length) {
            Object[] tmp = new Object[this.size * 2];

            System.arraycopy(elements, 0, tmp, 0, elements.length);

            elements = tmp;
        }
    }

    private boolean isValid(int index) {
        return index >= 0 && index < this.size;
    }

    private void shiftRight(int index) {
        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.elements[this.size - 1] = null;
    }
}
