import java.util.Random;

public class slTTTBoard {
    // Variables to represent game states
    public int GAME_QUIT = 0;
    public int GAME_PLAYER = 1;
    public int GAME_MACHINE = 2;
    public int GAME_DRAW = 3;
    public int GAME_CONTINUE = 4;

    private char[][] board;
    private char player = 'X';
    private char machine = 'O';
    private int moveCount;

    // Constructor initializes the board
    public slTTTBoard() {
        board = new char[3][3];
        moveCount = 0;
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
    public void machineMove() {
        if (moveCount == 0) {
            Random ran = new Random();
            int row = ran.nextInt(3);
            int col = ran.nextInt(3);
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
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return GAME_PLAYER;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return GAME_PLAYER;
            if (board[i][0] == machine && board[i][1] == machine && board[i][2] == machine) return GAME_MACHINE;
            if (board[0][i] == machine && board[1][i] == machine && board[2][i] == machine) return GAME_MACHINE;
        }

        // Check diagonals for winner
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return GAME_PLAYER;
        }
        if ((board[0][0] == machine && board[1][1] == machine && board[2][2] == machine) ||
                (board[0][2] == machine && board[1][1] == machine && board[2][0] == machine)) {
            return GAME_MACHINE;
        }

        // Check for draw
        if (moveCount == 9) return GAME_DRAW;

        // Continue the game
        return GAME_CONTINUE;
    }
}
