package fundamentals.binaryTrees_Heaps_PriorityQueue_BST.main.java.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {

    public Integer solve(int k, int[] cookies) {
        Queue<Integer> queue = new PriorityQueue<>();

        for (int cookie : cookies) {
            queue.offer(cookie);
        }

        int currentMinSweetness = queue.peek();
        int steps = 0;
        while(currentMinSweetness < k && queue.size() > 1){
            int leastSweetCookie = queue.poll();
            int secondLeastSweetCookie = queue.poll();

            int combinedSweetness = leastSweetCookie + 2 * secondLeastSweetCookie;

            queue.add(combinedSweetness);

            currentMinSweetness = queue.peek();
            steps++;
        }

        return currentMinSweetness > k ? steps : -1;
    }
}
