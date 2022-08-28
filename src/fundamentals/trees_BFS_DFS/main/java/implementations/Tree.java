package fundamentals.trees_BFS_DFS.main.java.implementations;

import fundamentals.trees_BFS_DFS.main.java.interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {

    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return parent;
    }

    @Override
    public E getKey() {
        return value;
    }

    @Override
    public String getAsString() {
        return printTree(this, 0, new StringBuilder()).trim();
    }

    private String printTree(Tree<E> current, int indent, StringBuilder builder) {

        builder.append(String.join("",
                        Collections.nCopies(indent * 2, " ")))
                .append(current.value)
                .append("\n");

        for (Tree<E> child : current.children) {
            printTree(child, indent + 1, builder);
        }

        return builder.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        List<Tree<E>> nodes = traverseWithBfs();

        List<E> leafs = new ArrayList<>();
        for (Tree<E> node : nodes) {
            if (node.children.size() == 0) {
                leafs.add(node.value);
            }
        }

        return leafs;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<Tree<E>> nodes = traverseWithBfs();

        List<E> result = new ArrayList<>();
        for (Tree<E> current : nodes) {
            if (current.parent != null && current.children.size() > 0) {
                result.add(current.value);
            }
        }

        return result;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> nodes = traverseWithBfs();

        int deepest = 0;
        Tree<E> deepestNode = null;

        for (Tree<E> node : nodes) {
            if (isLeaf(node) && checkLevel(node) > deepest) {
                deepestNode = node;
                deepest = checkLevel(node);
            }
        }

        return deepestNode;
    }

    @Override
    public List<E> getLongestPath() {
        List<E> longestPath = new ArrayList<>();
        Tree<E> deepestLeaf = getDeepestLeftmostNode();

        while (deepestLeaf.parent != null) {
            longestPath.add(deepestLeaf.value);
            deepestLeaf = deepestLeaf.parent;
        }
        longestPath.add(deepestLeaf.value);

        Collections.reverse(longestPath);
        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> paths = new ArrayList<>();

        ArrayDeque<Tree<E>> stack = new ArrayDeque<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            Tree<E> current = stack.pop();

            for (Tree<E> child : current.children) {
                stack.push(child);
            }

            int currentSum = 0;

            if (isLeaf(current)) {
                List<E> currentPath = new ArrayList<>();

                while (current != null) {
                    currentSum += (Integer) current.value;
                    currentPath.add(current.value);
                    current = current.parent;
                }

                if (currentSum == sum) {
                    Collections.reverse(currentPath);
                    paths.add(currentPath);
                }
            }
        }

        Collections.reverse(paths);
        return paths;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result = new ArrayList<>();

        ArrayDeque<Tree<E>> stack = new ArrayDeque<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            Tree<E> current = stack.pop();

            int currentSum = (Integer) current.value;

            for (Tree<E> child : current.children) {
                stack.push(child);
                currentSum += (Integer) child.value;

                if (child.children.size() > 0) {
                    for (Tree<E> eTree : child.children) {
                        currentSum += (Integer) eTree.value;
                    }
                }

            }

            if (currentSum == sum) {
                result.add(current);
            }
        }

        Collections.reverse(result);
        return result;
    }

    private List<Tree<E>> traverseWithBfs() {
        List<Tree<E>> result = new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

            result.add(current);
        }

        return result;
    }

    private int checkLevel(Tree<E> node) {

        int counter = 0;
        Tree<E> leaf = node;

        while (leaf.parent != null) {
            counter++;
            leaf = leaf.parent;
        }

        return counter;
    }

    private boolean isLeaf(Tree<E> node) {
        return node.children.size() == 0 && node.parent != null;
    }
}



