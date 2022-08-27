package fundamentals.trees_BFS_DFS.main.java.implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String s : input) {
            int[] nodeValues = Arrays.stream(s.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Tree<Integer> parent = createNodeByKey(nodeValues[0]);
            Tree<Integer> children = createNodeByKey(nodeValues[1]);

            nodesByKeys.put(nodeValues[0], parent);
            nodesByKeys.put(nodeValues[1], children);


            addEdge(nodeValues[0], nodeValues[1]);
        }

        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (var node : nodesByKeys.entrySet()) {
            if (node.getValue().getParent() == null) {
                return node.getValue();
            }
        }

        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentNode = nodesByKeys.get(parent);
        Tree<Integer> childNode = nodesByKeys.get(child);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
    }
}



