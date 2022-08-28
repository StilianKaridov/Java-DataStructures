package fundamentals.trees_BFS_DFS.test.java.implementations;

import fundamentals.trees_BFS_DFS.main.java.implementations.Tree;
import fundamentals.trees_BFS_DFS.main.java.implementations.TreeFactory;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeTest {

    @Test
    public void testTreeCreation() {
        String[] input = {
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6",
                "7 19",
                "7 21",
                "7 14"
        };


        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        assertEquals(Integer.valueOf(7), tree.getKey());
    }

    @Test
    public void testTreeAsString() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };


        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        assertEquals("7\n" +
                "  19\n" +
                "    1\n" +
                "    12\n" +
                "    31\n" +
                "  21\n" +
                "  14\n" +
                "    23\n" +
                "    6", tree.getAsString());
    }

    @Test
    public void testLeafKeys() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };

        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<Integer> leafKeys = tree.getLeafKeys();
        Collections.sort(leafKeys);

        assertEquals(List.of(1, 6, 12, 21, 23, 31), leafKeys);
    }

    @Test
    public void testMiddleNodes() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };

        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<Integer> leafKeys = tree.getMiddleKeys();
        Collections.sort(leafKeys);

        assertEquals(List.of(14, 19), leafKeys);
    }

    @Test
    public void testDeepestLeftmostNode() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };

        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        Tree<Integer> deepestLeftmostNode = tree.getDeepestLeftmostNode();

        assertEquals(Integer.valueOf(1), deepestLeftmostNode.getKey());
    }

    @Test
    public void testLongestPath() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };
        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<Integer> longestPath = tree.getLongestPath();

        assertEquals(List.of(7, 19, 1), longestPath);
    }

    @Test
    public void testPathsWithGivenSum() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };
        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<List<Integer>> lists = tree.pathsWithGivenSum(27);

        List<List<Integer>> expected =
                List.of(List.of(7, 19, 1), List.of(7, 14, 6));

        for (int i = 0; i < lists.size(); i++) {
            assertEquals(expected.get(i), lists.get(i));
        }
    }

    @Test
    public void testTreesWithGivenSum() {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"
        };

        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<Tree<Integer>> trees = tree.subTreesWithGivenSum(43);

        String asString = trees.get(0).getAsString();
        assertTrue(asString.contains("14"));
        assertTrue(asString.contains("23"));
        assertTrue(asString.contains("6"));
    }

    @Test
    public void testTreesWithGivenSum2(){
        String[] input = {
                "0 1",
                "0 2",
                "1 3"
        };

        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);

        List<Tree<Integer>> trees = tree.subTreesWithGivenSum(6);

        String asString = trees.get(0).getAsString();
        assertTrue(asString.contains("0"));
        assertTrue(asString.contains("1"));
        assertTrue(asString.contains("2"));
        assertTrue(asString.contains("3"));
    }
}