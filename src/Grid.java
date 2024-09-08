import java.util.Scanner;

public class Grid {
    public String[][] grid = new String[3][3];

    public void printGrid() {
        //printing grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); //move to the next line
        }
    }

    public void makeMove() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (isGridFull()) {
                System.out.println("The grid is full. Game over.");
                break;
            }

            System.out.println("Enter the array position (or 'q' to quit): ");
            String number = sc.nextLine();

            if (number.equalsIgnoreCase("q")) {
                System.out.println("Quitting the game.");
                break;
            }

            if (number.length() != 3 || !Character.isDigit(number.charAt(0)) || !Character.isDigit(number.charAt(2))) {
                System.out.println("Invalid input. Please enter two digits.");
                continue;
            }

            int position[] = new int[3];

            for (int i = 0; i < number.length(); i++) {
                position[i] = Character.getNumericValue(number.charAt(i));
            }

            if (position[0] >= grid.length || position[2] >= grid[0].length) {
                System.out.println("Position out of bounds. Please try again.");
                continue;
            }

            grid[position[0]][position[2]] = "P";
            System.out.println("Position updated at: (" + position[0] + ", " + position[2] + ")");
            printGrid();
        }
        sc.close();
    }

    public void printBoard() {


        //storing "-" in grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "-";
            }
        }

        //printing grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); //move to the next line
        }

    }

    public boolean isGridFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void play() {
        makeMove();
        printGrid();
    }
}
