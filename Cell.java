import java.util.Random;
import java.util.Scanner;

class Cell extends GameObject {
    private boolean isMine;
    private boolean isRevealed;
    private int neighboringMines;

    public Cell() {
        super(0, 0);
        this.isMine = false;
        this.isRevealed = false;
        this.neighboringMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine() {
        this.isMine = true;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;
    }

    public int getNeighboringMines() {
        return neighboringMines;
    }

    public void incrementNeighboringMines() {
        neighboringMines++;
    }
}
