public class SudokuSolver {

    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 3, 7, 0, 0, 2, 0},
                {0, 9, 0, 0, 8, 5, 7, 0, 0},
                {3, 0, 0, 9, 0, 0, 0, 0, 5},
                {1, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 9, 0, 0, 0, 7},
                {2, 0, 0, 6, 0, 0, 0, 0, 1},
                {0, 4, 8, 0, 0, 0, 6, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 4, 0}
        };

        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("Not a Valid puzzle.");
        }
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false; // no valid number found, trigger backtrack
                }
            }
        }
        return true; // board solved
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
