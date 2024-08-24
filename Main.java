import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        System.out.print("Enter number of mines: ");
        int mines = scanner.nextInt();

        Board board = new Board(rows, cols, mines);
        boolean gameOver = false;

        while (!gameOver) {
    System.out.print("Enter row and column to reveal (0-indexed, separated by space): ");
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    board.revealCell(row, col);
    board.printBoard(false); // Print board after the player's move
    if (board.isGameOver()) {
        System.out.println("Game Over! You hit a mine.");
        gameOver = true;
    }
}
        scanner.close();
    }
}
