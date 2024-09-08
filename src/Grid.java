import java.util.Scanner;

public class Grid {
    public String[][] grid = new String[3][3];

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

}
