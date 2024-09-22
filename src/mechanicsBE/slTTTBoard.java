package mechanicsBE;

import java.util.Random;

public class slTTTBoard {
    // Variables to represent game states
    public static final int GAME_QUIT = 0;
    public static final int GAME_PLAYER = 1;
    public static final int GAME_MACHINE = 2;
    public static final int GAME_DRAW = 3;
    public static final int GAME_CONTINUE = 4;

    private char[][] board;
    private char player = 'X';
    private char machine = 'O';
    private int moveCount;
    private Random random;

    // Constructor initializes the board
    public slTTTBoard() {
        board = new char[3][3];
        moveCount = 0;
        random = new Random();
        // Fill the board with spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the current board state
    public void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    System.out.print('-');
                } else {
                    System.out.print(board[i][j]);
                }
                if (j < 2) {
                    System.out.print(" ");  // Print space between columns
                }
            }
            System.out.println();
        }
    }

    // player move
    public boolean playerMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = player;
            moveCount++;
            return true;
        }
        return false;
    }

    // Machine move: Improved strategy
    public void machineMove(boolean isFirstMove) {
        if (isFirstMove || moveCount == 0) {
            makeRandomMove();
        } else {
            // Check if machine can win
            if (makeWinningMove(machine)) return;

            // Block player's winning move
            if (makeWinningMove(player)) return;

            // Make a strategic move
            makeStrategicMove();
        }
        moveCount++;
    }

    private boolean makeWinningMove(char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = symbol;
                    if (checkWin(symbol)) {
                        board[i][j] = machine;
                        return true;
                    }
                    board[i][j] = ' ';
                }
            }
        }
        return false;
    }

    private void makeStrategicMove() {
        // Try to take center
        if (board[1][1] == ' ') {
            board[1][1] = machine;
            return;
        }

        // Try to take corners
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                board[corner[0]][corner[1]] = machine;
                return;
            }
        }

        // Take any available space
        makeRandomMove();
    }

    private void makeRandomMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = machine;
    }

    private boolean checkWin(char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    // Check for win conditions
    public int checkGameState() {
        if (checkWin(player)) return GAME_PLAYER;
        if (checkWin(machine)) return GAME_MACHINE;
        if (moveCount == 9) return GAME_DRAW;
        return GAME_CONTINUE;
    }

    // Reset the board for a new game
    public void resetBoard() {
        moveCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // New method to get the current move count
    public int getMoveCount() {
        return moveCount;
    }
}