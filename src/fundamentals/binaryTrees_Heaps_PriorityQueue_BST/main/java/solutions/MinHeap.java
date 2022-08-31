package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.solutions;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.interfaces.Decrease;
import fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.interfaces.Heap;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(E element) {

    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public void decrease(E element) {

    }
}
