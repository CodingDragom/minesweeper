import java.util.Random;
import java.util.Scanner;

class Board extends GameObject {
    private final int mines;
    private final Cell[][] grid;

    public Board(int rows, int cols, int mines) {
        super(rows, cols);
        this.mines = mines;
        this.grid = new Cell[rows][cols];
        initialize();
        placeMines();
        calculateNeighboringMines();
    }

    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine();
                minesPlaced++;
            }
        }
    }

    private void calculateNeighboringMines() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine()) {
                    for (int k = 0; k < 8; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && grid[ni][nj].isMine()) {
                            grid[i][j].incrementNeighboringMines();
                        }
                    }
                }
            }
        }
    }

    public void printBoard(boolean revealAll) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealAll || grid[i][j].isRevealed()) {
                    if (grid[i][j].isMine()) {
                        System.out.print("* ");
                    } else {
                        int neighboringMines = grid[i][j].getNeighboringMines();
                        System.out.print(neighboringMines + " ");
                    }
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isRevealed() && grid[i][j].isMine()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void revealCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Invalid cell!");
            return;
        }

        Cell cell = grid[row][col];
        if (!cell.isRevealed()) {
            cell.reveal();
            if (cell.getNeighboringMines() == 0) {
                int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
                int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
                for (int i = 0; i < 8; i++) {
                    int ni = row + dx[i];
                    int nj = col + dy[i];
                    if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                        revealCell(ni, nj);
                    }
                }
            }
        }
    }
}
