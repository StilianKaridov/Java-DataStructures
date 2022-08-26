package fundamentals.linearDataStructures.main.java.implementations;

import fundamentals.linearDataStructures.main.java.interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < this.parentheses.length(); i++) {
            char current = parentheses.charAt(i);

            if (stack.isEmpty()) {
                stack.push(current);
            } else if (stack.peek() == '(' && current == ')'
                    || stack.peek() == '{' && current == '}'
                    || stack.peek() == '[' && current == ']') {
                stack.pop();
            } else {
                stack.push(current);
            }
        }

        return stack.isEmpty();
    }
}
