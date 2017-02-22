package ca.cmpt213.as2.mazegame;

import java.util.Random;
import java.util.ArrayList;

/**
 * Cats are the enemies of the game, they will wander the
 * maze in hopes of catching the player.
 * They move around randomly.
 *
 * @authors Anni Cao and Amritpaul Gill
 */
public class Cat extends GameObject {

    private int[] lastPosition;
    private final Integer[][] MOVES = { {0, -1},
                                    {-1, 0},
                                    {0, 1},
                                    {1, 0}};

    public Cat(int[] initialPosition, char symbol) {
        super(initialPosition, symbol);
        lastPosition = new int[2];
    }

    @Override
    public boolean move(int[] direction, Maze maze){
        int[] randomPos = createRandomPosition(maze);
        lastPosition[0] = currentPos[0];
        lastPosition[1] = currentPos[1];
        currentPos[0] += randomPos[0];
        currentPos[1] += randomPos[1];
        return true;
    }


    private int[] createRandomPosition(Maze maze) {

        ArrayList<Integer[]> validMoves = new ArrayList<>();
        for (int i = 0; i < MOVES.length; i++ ) {
            if (isValid(MOVES[i], maze)) {
                validMoves.add(MOVES[i]);
            }
        }
        int[] result = new int[2];
        if (validMoves.size() > 1) {
            Random rand = new Random();
            int n = rand.nextInt(validMoves.size());
            result[0] = validMoves.get(n)[0];
            result[1] = validMoves.get(n)[1];
        } else if (validMoves.size() == 1) {
            result[0] = validMoves.get(0)[0];
            result[1] = validMoves.get(0)[1];
        } else {
            result[0] = lastPosition[0] - currentPos[0];
            result[1] = lastPosition[1] - currentPos[1];
        }
        return result;
    }

    private boolean isValid(Integer[] direction, Maze maze) {
        int row = currentPos[0] + direction[0];
        int col = currentPos[1] + direction[1];
        char[][] fullMaze = maze.getFullMaze();
        if (fullMaze[row][col] == '#') {
            return false;
        }
        if (row == lastPosition[0] && col == lastPosition[1]) {
            return false;
        }
        return true;
    }

}
