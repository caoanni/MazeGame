package ca.cmpt213.as2;

/**
 * This is the superclass for the mouse, cat, and cheese objects.
 * It provides fields and methods for movement and the object's representation
 * in the game.
 *
 * @authors Anni Cao and Amritpaul Gill
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
