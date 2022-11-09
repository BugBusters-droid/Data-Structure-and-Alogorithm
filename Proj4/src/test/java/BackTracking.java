import org.testng.annotations.Test;

import java.util.concurrent.ThreadPoolExecutor;

public class BackTracking {

    boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        int N = 4;
        //check in the same row left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
        //Check the above cells if queen is present
        for (i = 1; i <= row && row != 0; i++) {
            if (board[row - i][col] == 1) {
                return false;
            }
        }
        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper diagonal on right side
        for (i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        //Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }

    boolean nQueen(int[][] board, int row, int n) {
        if (row == n) {
            return true;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (nQueen(board, row + 1, n)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    void print(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }

    @Test(description = "N-Queen Probelum")
    public void test1() {
        int N = 4;
        int board[][] = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        int row = 0;
        System.out.println(nQueen(board, row, N));
        //for printing the board
        print(board, N);
    }


    boolean isSudokuSafe(int[][] board, int row, int col, int num) {
        int i, j;
        for (i = 0; i < board.length; i++) // row clash check
            if (board[row][i] == num)
                return false;

        for (i = 0; i < board.length; i++) // column clash check
            if (board[i][col] == num)
                return false;

        int sqrt = (int) Math.sqrt(board.length); // box (3*3) clash check
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean solveSudoku(int[][] board, int N) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= N; num++) {
            if (isSudokuSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, N)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    @Test(description = "Sudoku")
    public void test2() {
        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        int N = board.length;
        if (solveSudoku(board, N)) {
            print(board, N);
        } else {
            System.out.println("No solution");
        }
    }

}
