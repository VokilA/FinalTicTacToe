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
