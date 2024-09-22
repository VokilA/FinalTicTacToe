package mechanicsBE;

import java.util.Scanner;
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
    private char machineChar = 'O';
    private int moveCount;

    private String[][] grid = new String[3][3];
    private String machine = "0";

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
        printBoard();
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (isGridFull()) {
                System.out.println("The grid is full. Game over.");
                break;
            }

            System.out.println("Enter the row and column (0-2) separated by a space (or 'q' to quit): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Quitting the game.");
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter two numbers separated by a space.");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter two numbers.");
                continue;
            }

            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                System.out.println("Position out of bounds. Please enter numbers between 0 and 2.");
                continue;
            }

            if (!grid[row][col].equals("-")) {
                System.out.println("This position is already occupied. Please try again.");
                continue;
            }

            grid[row][col] = "P";
            System.out.println("Position updated at: (" + row + ", " + col + ")");
            printGrid();
        }
        sc.close();
    }

    public void machineMove() {
        Random ran = new Random();
        int row = ran.nextInt(3);
        int col = ran.nextInt(3);
        grid[row][col] = machine;
    }

    public void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "-";
            }
        }
        printGrid();
    }

    private boolean isGridFull() {
        for (String[] row : grid) {
            for (String cell : row) {
                if (cell.equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
}