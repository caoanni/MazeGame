package ca.cmpt213.as2;

/**
 *
 * Created by anni on 2/12/17.
 */
public abstract class GameObject {

    protected int[] currentPos; //[0] stores row position, [1] stores column position
    protected char symbol;

    public GameObject(int[] startPosition, char symbol) {
        this.currentPos = new int[2];
        this.currentPos[0] = startPosition[0];
        this.currentPos[1] = startPosition[1];
        this.symbol = symbol;
    }

    public int[] getPosition(){
        return currentPos;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract boolean move(int[] direction, Maze maze);

}
