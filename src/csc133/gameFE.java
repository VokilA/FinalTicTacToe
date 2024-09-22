import java.util.Scanner;

public class gameFE {
    private slTTTBoard board;

    public gameFE(slTTTBoard board)
    {
        this.board = board;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        int exit_status;

        // Welcome message
        System.out.println("Welcome to Tic-Tac-Toe! Enter 'q' to quit.");

        // Game loop
        while (true) {
            // Display the board
            board.displayBoard();

            // Ask for player's move
            System.out.print("Enter your move (row col) or 'q' to quit: ");
            String input = scanner.nextLine();

            // Quit the game if the player inputs 'q'
            if (input.equalsIgnoreCase("q")) {
                exit_status = board.GAME_QUIT;
                break;
            }

            // Parse player input (row and column as space-separated)
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter in the format 'row col'.");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0].trim());
                col = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for row and column.");
                continue;
            }

            // Player makes a move
            if (!board.playerMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Check the game state after player's move
            exit_status = board.checkGameState();
            if (exit_status != board.GAME_CONTINUE) break;

            // Machine's move
            board.machineMove();

            // Check the game state after machine's move
            exit_status = board.checkGameState();
            if (exit_status != board.GAME_CONTINUE) break;
        }

        // Print the final game message based on the game state
        switch (exit_status) {
            case 0:
                System.out.println("Sorry to see you go; come again!");
                break;
            case 1:
                System.out.println("Congratulations! you have won!");
                break;
            case 2:
                System.out.println("Sorry, you did not win; try again!");
                break;
            case 3:
                System.out.println("Hey, you almost beat me, let's try again!");
                break;
            default:
                break;
        }

        scanner.close();
    }
}
