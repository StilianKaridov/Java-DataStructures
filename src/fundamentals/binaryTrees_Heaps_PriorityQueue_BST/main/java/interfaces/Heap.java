package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.interfaces;

public interface Heap<E extends Comparable<E> & Decrease<E>> {
    int size();
    void add(E element);
    E peek();
    E poll();
    void decrease(E element);
}
