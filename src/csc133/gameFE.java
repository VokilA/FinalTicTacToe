package csc133;

import mechanicsBE.slTTTBoard;
import java.util.Scanner;

public class gameFE {
    private slTTTBoard board;
    private Scanner scanner;

    public gameFE(slTTTBoard board) {
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Welcome to Tic-Tac-Toe! Enter 'q' to quit at any time.");
            System.out.print("Do you want to go first? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("q")) {
                printExitMessage(slTTTBoard.GAME_QUIT);
                return;
            }
            boolean playerFirst = input.equals("y");

            if (!playerFirst) {
                board.machineMove(true);
            }

            int exit_status = playGame(playerFirst);

            if (exit_status != slTTTBoard.GAME_QUIT) {
                System.out.print("Do you want to play again? (y/n): ");
                input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("q") || !input.equals("y")) {
                    printExitMessage(slTTTBoard.GAME_QUIT);
                    playAgain = false;
                } else {
                    board.resetBoard();
                }
            } else {
                playAgain = false;
            }
        }
        scanner.close();
    }

    private int playGame(boolean playerFirst) {
        int exit_status;
        while (true) {
            board.displayBoard();

            if (playerFirst) {
                if (!getPlayerMove()) return slTTTBoard.GAME_QUIT;
                exit_status = board.checkGameState();
                if (exit_status != slTTTBoard.GAME_CONTINUE) break;

                if (board.getMoveCount() < 9) {
                    board.machineMove(false);
                    exit_status = board.checkGameState();
                    if (exit_status != slTTTBoard.GAME_CONTINUE) break;
                }
            } else {
                if (!getPlayerMove()) return slTTTBoard.GAME_QUIT;
                exit_status = board.checkGameState();
                if (exit_status != slTTTBoard.GAME_CONTINUE) break;

                if (board.getMoveCount() < 9 && board.checkGameState() == slTTTBoard.GAME_CONTINUE) {
                    board.machineMove(false);
                    exit_status = board.checkGameState();
                    if (exit_status != slTTTBoard.GAME_CONTINUE) break;
                }
            }
        }

        board.displayBoard();
        printExitMessage(exit_status);
        return exit_status;
    }

    private boolean getPlayerMove() {
        while (true) {
            System.out.print("Enter your move (row col) or 'q' to quit: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("q")) {
                printExitMessage(slTTTBoard.GAME_QUIT);
                return false;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter in the format 'row col'.");
                continue;
            }
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if (board.playerMove(row, col)) {
                    return true;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for row and column.");
            }
        }
    }

    private void printExitMessage(int exit_status) {
        switch (exit_status) {
            case slTTTBoard.GAME_QUIT:
                System.out.println("Sorry to see you go; come again!");
                break;
            case slTTTBoard.GAME_PLAYER:
                System.out.println("Congratulations! You have won!");
                break;
            case slTTTBoard.GAME_MACHINE:
                System.out.println("Sorry, you lost; better luck next time!");
                break;
            case slTTTBoard.GAME_DRAW:
                System.out.println("It's a draw! Good game!");
                break;
            default:
                break;
        }
    }
}