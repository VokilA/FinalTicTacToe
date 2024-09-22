public class csc133Driver {
    public static void main(String[] args) {
        slTTTBoard my_board = new slTTTBoard();
        (new gameFE(my_board)).startGame();
    }
}
