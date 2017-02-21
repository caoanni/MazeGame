package ca.cmpt213.as2;

/**
 * Represents the player in the game.
 * Will move around in accordance to input that
 * is given to it.
 *
 * @authors Anni Cao and Amritpaul Gill
 */
public class Mouse extends GameObject {

    private int cheeseFound;

    public Mouse(int[] direction, char symbol) {
        super(direction,symbol);
        this.cheeseFound = 0;
    }

    public int getCheeseFound() {
        return cheeseFound;
    }

    public void checkCheese(Cheese cheese, Maze maze) {
        if (this.currentPos[0] == cheese.currentPos[0] &&
                this.currentPos[1] == cheese.currentPos[1]) {
            cheeseFound++;
            cheese.move(cheese.getPosition(), maze);
        }
    }

    @Override
    public boolean move(int[] direction, Maze maze) {
        if (isValidMove(direction, maze)) {
            currentPos[0] += direction[0];
            currentPos[1] += direction[1];
            return true;
        }
        return false;
    }

    private boolean isValidMove(int[] direction, Maze maze) {
        int row = currentPos[0] + direction[0];
        int col = currentPos[1] + direction[1];
        char[][] fullMaze = maze.getFullMaze();
        if (fullMaze[row][col] == '#') {
            System.out.println("Invalid move: you cannot move through walls!");
            return false;
        }
        System.out.println("Mouse new position: " + row + ',' + col);
        return true;
    }
}
