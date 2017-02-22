package ca.cmpt213.as2.mazegame;

import java.util.Random;

/**
 * The target object of the game.
 * Will teleport to another random part of the maze to
 * pretend another piece of cheese has been laid down
 * after it has been eaten.
 *
 * @authors Anni Cao and Amritpaul Gill
 */
public class Cheese extends GameObject {

    public Cheese(int[] initialPosition, char symbol, Maze maze) {
        super(initialPosition, symbol);
        move(currentPos, maze);
    }

    @Override
    public boolean move(int[] direction, Maze maze){
        boolean isValid = false;
        int[] randomPos = direction;
        while (!isValid) {
            randomPos = createRandomPosition();
            isValid = isValidMove(randomPos, maze);
        }
        currentPos[0] = randomPos[0];
        currentPos[1] = randomPos[1];
        return true;
    }

    private boolean isValidMove(int[] direction, Maze maze){
        int row = direction[0];
        int col = direction[1];
        char[][] fullMaze = maze.getFullMaze();
        if (fullMaze[row][col] == '#') {
            return false;
        }
        if (row == currentPos[0] && col == currentPos[1]) {
            return false;
        }
        return true;
    }

    private int[] createRandomPosition(){
        int[] result = new int[2];
        Random rand = new Random();
        result[0] = rand.nextInt(18) + 1;
        result[1] = rand.nextInt(13) + 1;
        return result;
    }
}
