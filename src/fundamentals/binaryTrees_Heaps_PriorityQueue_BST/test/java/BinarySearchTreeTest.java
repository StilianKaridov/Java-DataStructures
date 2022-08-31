package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.test.java;

import fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        this.bst = new BinarySearchTree<>(new BinarySearchTree.Node<>(5));
        bst.insert(2);
        bst.insert(6);
        bst.insert(4);
    }

    @Test
    public void test_TreeRoot_ShouldBe_5() {
        assertEquals(Integer.valueOf(5), bst.getRoot().getValue());
    }

    @Test
    public void test_Tree_Insert() {
        assertEquals(Integer.valueOf(2), bst.getRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(6), bst.getRoot().getRight().getValue());
        assertEquals(Integer.valueOf(4), bst.getRoot().getLeft().getRight().getValue());
    }

    @Test
    public void test_Contains_ShouldReturnTrue() {
        assertTrue(bst.contains(6));
    }

    @Test
    public void test_Contains_ShouldReturnFalse() {
        assertFalse(bst.contains(1));
    }

    @Test
    public void test_Search_ReturnsEmptyTree() {
        BinarySearchTree<Integer> search = bst.search(1);

        assertNull(search.getRoot());
    }

    @Test
    public void test_Search_ReturnsTree_WithRoot_2() {
        BinarySearchTree<Integer> search = bst.search(2);

        assertEquals(Integer.valueOf(2), search.getRoot().getValue());
        assertEquals(Integer.valueOf(4), search.getRoot().getRight().getValue());
    }

    @Test
    public void test_Range_Successfully() {
        List<Integer> expected = Arrays.asList(5, 2, 4);
        List<Integer> actual = bst.range(2, 5);

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void test_Floor_ShouldReturn_2() {
        Integer floor = bst.floor(4);

        assertEquals(Integer.valueOf(2), floor);
    }

    @Test
    public void test_Ceil_ShouldReturn_4(){
        Integer ceil = bst.ceil(2);

        assertEquals(Integer.valueOf(4), ceil);
    }
}
