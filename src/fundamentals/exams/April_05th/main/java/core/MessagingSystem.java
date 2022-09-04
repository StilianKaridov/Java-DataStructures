package fundamentals.exams.April_05th.main.java.core;

import fundamentals.exams.April_05th.main.java.model.Message;
import fundamentals.exams.April_05th.main.java.shared.DataTransferSystem;

import java.util.ArrayList;
import java.util.List;

public class MessagingSystem implements DataTransferSystem {

    private Node<Message> root;
    private int size;

    public MessagingSystem() {
        this.root = null;
        this.size = 0;
    }

    private static class Node<Message> {
        private Message value;
        private Node<Message> left;
        private Node<Message> right;

        public Node(Message key) {
            this.value = key;
        }

        public Message getValue() {
            return value;
        }

        public Node<Message> getLeft() {
            return left;
        }

        public Node<Message> getRight() {
            return right;
        }
    }

    @Override
    public void add(Message message) {
        Node<Message> current = getRoot();

        if (current == null) {
            this.root = new Node<>(message);
            this.size++;
            return;
        }

        addRecursive(current, message);
        this.size++;
    }

    @Override
    public Message getByWeight(int weight) {
        Message result = findByWeight(weight, getRoot());

        if (result == null) {
            throw new IllegalArgumentException();
        }

        return this.root == null ? null : result;
    }

    @Override
    public Message getLightest() {
        ensureNonEmpty();

        return findLightest(getRoot());
    }

    @Override
    public Message getHeaviest() {
        ensureNonEmpty();

        return findHeaviest(getRoot());
    }

    @Override
    public Message deleteLightest() {
        ensureNonEmpty();

        Node<Message> current = getRoot();
        Node<Message> prev = current;

        Message result;

        if (this.size == 1) {
            result = this.root.getValue();
            this.root = null;
        } else {
            while (current.getLeft() != null) {
                prev = current;
                current = current.getLeft();
            }

            result = current.getValue();
            prev.left = null;
        }

        this.size--;
        return result;
    }

    @Override
    public Message deleteHeaviest() {
        ensureNonEmpty();

        Node<Message> current = getRoot();
        Node<Message> prev = current;

        Message result;

        if (this.size == 1) {
            result = this.root.getValue();
            this.root = null;
        } else {
            while (current.getRight() != null) {
                prev = current;
                current = current.getRight();
            }

            result = current.getValue();
            prev.right = null;
        }

        this.size--;
        return result;
    }

    @Override
    public Boolean contains(Message message) {
        if(this.root == null){
            return false;
        }

        return findByWeight(message.getWeight(), getRoot()) != null;
    }

    @Override
    public List<Message> getOrderedByWeight() {
        List<Message> result = new ArrayList<>();

        inOrder(getRoot(), result);
        return result;
    }

    @Override
    public List<Message> getPostOrder() {
        List<Message> result = new ArrayList<>();

        postOrder(getRoot(), result);
        return result;
    }

    @Override
    public List<Message> getPreOrder() {
        List<Message> result = new ArrayList<>();

        preOrder(getRoot(), result);
        return result;
    }

    @Override
    public List<Message> getInOrder() {
        List<Message> result = new ArrayList<>();

        inOrder(getRoot(), result);
        return result;
    }

    @Override
    public int size() {
        return this.size;
    }

    public Node<Message> getRoot() {
        return this.root;
    }

    private void addRecursive(Node<Message> node, Message message) {
        if (isGreater(message.getWeight(), node)) {
            if (node.getRight() == null) {
                node.right = new Node<>(message);
            } else {
                addRecursive(node.getRight(), message);
            }
        } else if (isLess(message.getWeight(), node)) {
            if (node.getLeft() == null) {
                node.left = new Node<>(message);
            } else {
                addRecursive(node.getLeft(), message);
            }
        } else if (areEqual(message.getWeight(), node)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean areEqual(int weight, Node<Message> node) {
        return weight == node.getValue().getWeight();
    }

    private boolean isLess(int weight, Node<Message> node) {
        return weight < node.getValue().getWeight();
    }

    private boolean isGreater(int weight, Node<Message> node) {
        return weight > node.getValue().getWeight();
    }

    private Message findByWeight(int weight, Node<Message> node) {
        if (isGreater(weight, node)) {
            findByWeight(weight, node.getRight());
        } else if (isLess(weight, node)) {
            findByWeight(weight, node.getLeft());
        } else if (areEqual(weight, node)) {
            return node.getValue();
        }

        return null;
    }

    private Message findLightest(Node<Message> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node.getValue();
    }

    private Message findHeaviest(Node<Message> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node.getValue();
    }

    private void ensureNonEmpty() {
        if (this.root == null) {
            throw new IllegalStateException();
        }
    }

    private void inOrder(Node<Message> node, List<Message> result) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeft(), result);
        result.add(node.getValue());
        inOrder(node.getRight(), result);
    }

    private void postOrder(Node<Message> node, List<Message> result) {
        if (node == null) {
            return;
        }

        postOrder(node.getLeft(), result);
        postOrder(node.getRight(), result);
        result.add(node.getValue());
    }

    private void preOrder(Node<Message> node, List<Message> result) {
        if (node == null) {
            return;
        }

        result.add(node.getValue());
        preOrder(node.getLeft(), result);
        preOrder(node.getRight(), result);
    }
}
