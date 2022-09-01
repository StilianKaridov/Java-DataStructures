package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.solutions;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.interfaces.Decrease;
import fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.elements.size() - 1);
    }

    @Override
    public E peek() {
        ensureNonEmpty();

        return this.elements.get(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();

        Collections.swap(this.elements, 0, this.elements.size() - 1);
        E toRemove = this.elements.remove(this.elements.size() - 1);

        this.heapifyDown();

        return toRemove;
    }

    @Override
    public void decrease(E element) {
        int elementIndex = this.elements.indexOf(element);
        E heapElement = this.elements.get(elementIndex);

        heapElement.decrease();
        this.heapifyUp(elementIndex);
    }

    private void heapifyDown() {
        int index = 0;
        int swapIndex = getLeftChildIndex(index);

        while (swapIndex < this.elements.size()) {
            int rightChildIndex = getRightChildIndex(index);

            if (rightChildIndex < this.elements.size()) {
                swapIndex = isLess(swapIndex, rightChildIndex) ? swapIndex : rightChildIndex;
            }

            if (isLess(index, swapIndex)) {
                break;
            }

            Collections.swap(this.elements, index, swapIndex);
            index = swapIndex;
            swapIndex = getLeftChildIndex(index);
        }
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private void heapifyUp(int index)  {
        int parentIndex = getParentAt(index);

        while (index > 0 && isLess(index, parentIndex)) {
            Collections.swap(this.elements, index, parentIndex);
            index = parentIndex;
            parentIndex = this.getParentAt(index);
        }
    }

    private boolean isLess(int parent, int child) {
        return this.elements.get(parent).compareTo(this.elements.get(child)) < 0;
    }

    private int getParentAt(int index) {
        E parent = parent(index);

        return this.elements.indexOf(parent);
    }

    private E parent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("The heap is empty!");
        }
    }
}
