package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(Node<E> other) {
            this.value = other.value;

            if(other.getLeft() != null){
                this.leftChild = new Node<>(other.getLeft());
            }

            if(other.getRight() != null){
                this.rightChild = new Node<>(other.getRight());
            }
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.root = new Node<>(root);
    }

    public void insert(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.root == null) {
            this.root = newNode;
        }

        recursiveInsert(newNode, this.root);
    }

    public void eachInOrder(Consumer<E> consumer) {
        eachInOrderRecursive(this.root, consumer);
    }

    public boolean contains(E element) {
        Node<E> current = this.getRoot();

        while (current != null) {
            if (isGreater(element, current.getValue())) {
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                current = current.getLeft();
            } else {
                return true;
            }
        }

        return false;
    }

    public BinarySearchTree<E> search(E element) {
        Node<E> current = this.getRoot();

        while (current != null) {
            if (isGreater(element, current.getValue())) {
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                current = current.getLeft();
            } else {
                return new BinarySearchTree<>(current);
            }
        }

        return new BinarySearchTree<>();
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();

        ArrayDeque<Node<E>> queue = new ArrayDeque<>();

        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }

            if(isLess(first, current.getValue()) && isGreater(second, current.getValue())){
                result.add(current.getValue());
            } else if (isEqual(first, current.getValue()) || isEqual(second, current.getValue())){
                result.add(current.getValue());
            }
        }

        return result;
    }

    public void deleteMin() {
        ensureRootNotNull(this);

        Node<E> current = this.root;
        Node<E> prev = current;
        while (current.getLeft() != null) {
            prev = current;
            current = current.getLeft();
        }

        if (prev.getLeft().getRight() != null) {
            prev.leftChild = prev.getLeft().getRight();
        }
    }

    public void deleteMax() {
        ensureRootNotNull(this);

        Node<E> current = this.root;
        Node<E> prev = current;
        while (current.getRight() != null) {
            prev = current;
            current = current.getRight();
        }

        if (prev.getRight().getLeft() != null) {
            prev.rightChild = prev.getRight().getLeft();
        }
    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> nearestSmaller = null;

        while (current != null) {
            if (isGreater(element, current.getValue())) {
                nearestSmaller = current;
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                current = current.getLeft();
            } else if (isEqual(element, current.getValue())) {
                Node<E> left = current.getLeft();

                if (left != null && nearestSmaller != null) {
                    nearestSmaller = isGreater(left.getValue(), nearestSmaller.getValue()) ? left : nearestSmaller;
                } else if (nearestSmaller == null) {
                    nearestSmaller = left;
                }

                break;
            }
        }

        return nearestSmaller.getValue();
    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> nearestLarger = null;

        while (current != null) {
            if (isGreater(element, current.getValue())) {
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                nearestLarger = current;
                current = current.getLeft();
            } else if (isEqual(element, current.getValue())) {
                Node<E> right = current.getRight();

                if (right != null && nearestLarger != null) {
                    nearestLarger = isLess(right.getValue(), nearestLarger.getValue()) ? right : nearestLarger;
                } else if (nearestLarger == null) {
                    nearestLarger = right;
                }

                break;
            }
        }

        return nearestLarger.getValue();
    }

    public Node<E> getRoot() {
        return root;
    }

    private boolean isEqual(E first, E second) {
        return first.compareTo(second) == 0;
    }

    private boolean isLess(E first, E second) {
        return first.compareTo(second) < 0;
    }

    private boolean isGreater(E first, E second) {
        return first.compareTo(second) > 0;
    }

    private void recursiveInsert(Node<E> toInsert, Node<E> current) {

        if (isGreater(toInsert.getValue(), current.getValue())) {
            if (current.getRight() == null) {
                current.rightChild = toInsert;
                return;
            }

            recursiveInsert(toInsert, current.getRight());
        } else if (isLess(toInsert.getValue(), current.getValue())) {
            if (current.getLeft() == null) {
                current.leftChild = toInsert;
                return;
            }

            recursiveInsert(toInsert, current.getLeft());
        }
    }

    private void eachInOrderRecursive(Node<E> node, Consumer<E> consumer) {
        if(node == null){
            return;
        }

        eachInOrderRecursive(node.getLeft(), consumer);
        consumer.accept(node.getValue());
        eachInOrderRecursive(node.getRight(), consumer);
    }

    private void ensureRootNotNull(BinarySearchTree<E> tree) {
        if (tree.root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
    }
}
