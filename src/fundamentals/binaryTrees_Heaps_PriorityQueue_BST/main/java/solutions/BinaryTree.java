package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private int key;
    private BinaryTree first;
    private BinaryTree second;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.first = first;
        this.second = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = new ArrayList<>();
        getPath(first, firstPath);

        List<Integer> secondPath = new ArrayList<>();
        getPath(second, secondPath);

        if (firstPath.size() < secondPath.size()) {
            return firstPath.get(firstPath.size() - 1);
        }

        return secondPath.get(secondPath.size() - 1);
    }

    public List<Integer> topView() {
        List<Integer> result = new ArrayList<>();

        BinaryTree leftSide = this;

        while (leftSide != null) {
            result.add(leftSide.key);
            leftSide = leftSide.first;
        }

        BinaryTree rightSide = this.second;

        while (rightSide != null) {
            result.add(rightSide.key);
            rightSide = rightSide.second;
        }

        return result;
    }

    private void getPath(int element, List<Integer> result) {
        ArrayDeque<BinaryTree> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            BinaryTree current = queue.poll();

            if (current.key == element) {
                return;
            }

            result.add(current.key);

            if (current.first != null) {
                queue.offer(current.first);
            }

            if (current.second != null) {
                queue.offer(current.second);
            }
        }
    }
}
