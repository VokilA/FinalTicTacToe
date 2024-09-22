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

    // Machine move: Greedy algorithm or random if first move
    public void machineMove(boolean isFirstMove) {
        if (isFirstMove || moveCount == 0) {
            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (board[row][col] != ' ');
            board[row][col] = machine;
        } else {
            // Basic greedy algorithm: first available spot
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = machine;
                        moveCount++;
                        return;
                    }
                }
            }
        }
        moveCount++;
    }

    // Check for win conditions
    public int checkGameState() {
        // Check rows and columns for winner
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return (board[i][0] == player) ? GAME_PLAYER : GAME_MACHINE;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return (board[0][i] == player) ? GAME_PLAYER : GAME_MACHINE;
            }
        }

        // Check diagonals for winner
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return (board[1][1] == player) ? GAME_PLAYER : GAME_MACHINE;
        }

        // Check for draw
        if (moveCount == 9) return GAME_DRAW;

        // Continue the game
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
}