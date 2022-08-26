package fundamentals.trees_BFS_DFS_Lab.main.java.implementations;

import fundamentals.trees_BFS_DFS_Lab.main.java.interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E element;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E element, Tree<E>... children) {
        this.element = element;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            if (current.element != null) {
                result.add(current.element);

                for (Tree<E> child : current.children) {
                    queue.offer(child);
                }
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();

        this.dfs(this, result);

        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> parent = findBfs(parentKey);

        if (parent == null) {
            throw new IllegalArgumentException();
        }

        parent.children.add(child);
        child.parent = parent;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = findBfs(nodeKey);

        if (toRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : toRemove.children) {
            child.parent = null;
        }

        toRemove.children.clear();

        Tree<E> parent = toRemove.parent;
        if (parent != null) {
            parent.children.remove(toRemove);
        }

        toRemove.element = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findBfs(firstKey);
        Tree<E> secondNode = findBfs(secondKey);

        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstParent == null) {
            swapRoot(secondNode);
            return;
        } else if (secondParent == null) {
            swapRoot(firstNode);
            return;
        }

        firstNode.parent = secondParent;
        secondNode.parent = firstParent;

        int firstIndex = firstParent.children.indexOf(firstNode);
        int secondIndex = secondParent.children.indexOf(secondNode);

        firstParent.children.set(firstIndex, secondNode);
        secondParent.children.set(secondIndex, firstNode);
    }

    private void dfs(Tree<E> tree, List<E> result) {
        for (Tree<E> child : tree.children) {
            this.dfs(child, result);
        }

        result.add(tree.element);
    }

    private Tree<E> findBfs(E nodeKey) {
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            if (current.element.equals(nodeKey)) {
                return current;
            }

            for (Tree<E> currentChild : current.children) {
                queue.offer(currentChild);
            }
        }

        return null;
    }

    private void swapRoot(Tree<E> swapWith) {
        this.element = swapWith.element;
        this.parent = null;
        this.children = swapWith.children;
        swapWith.parent = null;
    }
}