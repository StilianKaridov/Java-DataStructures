package fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.implementations;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException("The heap is empty!");
        }

        return this.elements.get(0);
    }

    private void heapifyUp(int index) {
        while (hasParent(index) && isLess(parent(index), elements.get(index))) {
            int parentAt = getParentAt(index);
            Collections.swap(this.elements, parentAt, index);
            index = parentAt;
        }
    }

    private int getParentAt(int index) {
        E parent = this.elements.get((index - 1) / 2);

        return this.elements.indexOf(parent);
    }

    private boolean isLess(E parent, E child) {
        return parent.compareTo(child) < 0;
    }

    private E parent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private boolean hasParent(int index) {
        return index > 0;
    }
}
