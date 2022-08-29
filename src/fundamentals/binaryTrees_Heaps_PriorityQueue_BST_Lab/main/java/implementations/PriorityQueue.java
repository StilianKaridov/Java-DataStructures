package fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.implementations;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue() {
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
        ensureNonEmpty();

        return this.elements.get(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();

        E element = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);

        return element;
    }

    private void heapifyDown(int index) {
        while(index < this.size() / 2){
            int child = 2 * index + 1;

            if(child + 1 < this.size() && isLess(this.elements.get(child), this.elements.get(child + 1))){
                child += 1;
            }

            if(isLess(this.elements.get(child), this.elements.get(index))){
                break;
            }

            Collections.swap(this.elements, index , child);
            index = child;
        }
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

    private boolean isLess(E first, E second) {
        return first.compareTo(second) < 0;
    }

    private E parent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private boolean ensureNonEmpty(){
        if (this.size() == 0) {
            throw new IllegalStateException("The heap is empty!");
        }

        return true;
    }
}
