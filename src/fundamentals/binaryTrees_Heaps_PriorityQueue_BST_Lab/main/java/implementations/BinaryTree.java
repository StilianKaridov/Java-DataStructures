package fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.implementations;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST_Lab.main.java.interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E key;
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;

    public BinaryTree(E key, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightChild;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();

        result.append(createPadding(indent)).append(getKey());

        if (getLeft() != null) {
            result.append(System.lineSeparator()).append(getLeft().asIndentedPreOrder(indent + 2));
        }

        if (getRight() != null) {
            result.append(System.lineSeparator()).append(getRight().asIndentedPreOrder(indent + 2));
        }

        return result.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        result.add(this);

        if (getLeft() != null) {
            result.addAll(getLeft().preOrder());
        }

        if (getRight() != null) {
            result.addAll(getRight().preOrder());
        }

        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        if (getLeft() != null) {
            result.addAll(getLeft().inOrder());
        }

        result.add(this);

        if (getRight() != null) {
            result.addAll(getRight().inOrder());
        }

        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        if (getLeft() != null) {
            result.addAll(getLeft().postOrder());
        }

        if (getRight() != null) {
            result.addAll(getRight().postOrder());
        }

        result.add(this);

        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (getLeft() != null) {
            getLeft().forEachInOrder(consumer);
        }

        consumer.accept(getKey());

        if (getRight() != null) {
            getRight().forEachInOrder(consumer);
        }
    }

    private String createPadding(int indent) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
