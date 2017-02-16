package ca.cmpt213.as2;

import java.util.Random;

/**
 * Created by anni on 2/12/17.
 */
public class Cat extends GameObject {

    private int[] lastPosition;

    public Cat(int[] initialPosition, char symbol) {
        super(initialPosition, symbol);
        lastPosition = new int[2];
    }

    @Override
    public boolean move(int[] direction, Maze maze){
        boolean isValid = false;
        int[] randomPos = direction;
        while (!isValid) {
            randomPos = createRandomPosition();
            isValid = isValidMove(randomPos, maze);
        }
        lastPosition[0] = currentPos[0];
        lastPosition[1] = currentPos[1];
        currentPos[0] += randomPos[0];
        currentPos[1] += randomPos[1];
        return true;
    }

    private int[] createRandomPosition() {
        Random rand = new Random();
        int n = rand.nextInt(4) + 1;
        int[] result = new int[2];
        switch (n) {
            case 1 : // move left
                result[0] = 0;
                result[1] = -1;
                break;
            case 2 : // move up
                result[0] = -1;
                result[1] = 0;
                break;
            case 3 : // move right
                result[0] = 0;
                result[1] = 1;
                break;
            case 4 : // move down
                result[0] = 1;
                result[1] = 0;
            default:
                assert false;
        }
        return result;
    }

    private boolean isValidMove(int[] direction, Maze maze) {
        int row = currentPos[0] + direction[0];
        int col = currentPos[1] + direction[1];
        if (lastPosition[0] == row && lastPosition[1] == col) {
            return false;
        }
        char[][] fullMaze = maze.getFullMaze();
        if (fullMaze[row][col] == '#') {
            return false;
        }
        return true;
    }

}
