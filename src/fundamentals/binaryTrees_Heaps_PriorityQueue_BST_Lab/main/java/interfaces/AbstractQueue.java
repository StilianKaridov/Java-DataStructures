package fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.interfaces;

public interface AbstractQueue<E extends Comparable<E>> {
    int size();
    void add(E element);
    E peek();
    E poll();
}
