package fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.implementations;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.copy(root);
    }

    @Override
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.root == null) {
            this.root = newNode;
        } else {
            Node<E> current = this.root;
            Node<E> prev = current;

            while (current != null) {
                prev = current;

                if (isLess(element, current.value)) {
                    current = current.leftChild;
                } else if (isGreater(element, current.value)) {
                    current = current.rightChild;
                } else {
                    return;
                }
            }

            if (isLess(element, prev.value)) {
                prev.leftChild = newNode;
            } else if (isGreater(element, prev.value)) {
                prev.rightChild = newNode;
            }
        }
    }

    @Override
    public boolean contains(E element) {
        Node<E> node = getRoot();

        while (node != null) {
            if (isLess(element, node.value)) {
                node = node.leftChild;
            } else if (isGreater(element, node.value)) {
                node = node.rightChild;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        AbstractBinarySearchTree<E> result = new BinarySearchTree<>();
        Node<E> root = getRoot();

        while (root != null) {
            if (isLess(element, root.value)) {
                root = root.leftChild;
            } else if (isGreater(element, root.value)) {
                root = root.rightChild;
            } else {
                return new BinarySearchTree<>(root);
            }
        }

        return result;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.root.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.root.rightChild;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }

    private boolean isGreater(E current, E other) {
        return current.compareTo(other) > 0;
    }

    private boolean isLess(E current, E other) {
        return current.compareTo(other) < 0;
    }

    private void copy(Node<E> node) {
        if(node != null){
            this.insert(node.value);
            this.copy(node.leftChild);
            this.copy(node.rightChild);
        }
    }
}
