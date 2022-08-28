package fundamentals.trees_BFS_DFS.main.java.implementations;

import java.util.ArrayDeque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char startChar;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.startChar = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        ArrayDeque<Integer> row = new ArrayDeque<>();
        ArrayDeque<Integer> col = new ArrayDeque<>();

        row.offer(startRow);
        col.offer(startCol);
        this.matrix[this.startRow][this.startCol] = fillChar;


        while (!row.isEmpty()) {
            Integer currentRow = row.poll();
            Integer currentCol = col.poll();

            if (isInBounds(currentRow + 1, currentCol) && matrix[currentRow + 1][currentCol] == startChar) {
                matrix[currentRow + 1][currentCol] = fillChar;
                row.offer(currentRow + 1);
                col.offer(currentCol);
            }

            if (isInBounds(currentRow, currentCol + 1) && matrix[currentRow][currentCol + 1] == startChar) {
                matrix[currentRow][currentCol + 1] = fillChar;
                row.offer(currentRow);
                col.offer(currentCol + 1);
            }

            if (isInBounds(currentRow - 1, currentCol) && matrix[currentRow - 1][currentCol] == startChar) {
                matrix[currentRow - 1][currentCol] = fillChar;
                row.offer(currentRow - 1);
                col.offer(currentCol);
            }

            if (isInBounds(currentRow, currentCol - 1) && matrix[currentRow][currentCol - 1] == startChar) {
                matrix[currentRow][currentCol - 1] = fillChar;
                row.offer(currentRow);
                col.offer(currentCol - 1);
            }
        }
    }

    public String toOutputString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sb.append(matrix[row][col]);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[row].length;
    }
}
