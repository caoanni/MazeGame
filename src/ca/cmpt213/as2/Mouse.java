package ca.cmpt213.as2;

/**
 * Created by anni on 2/12/17.
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
            updateMaze(maze, currentPos);
            return true;
        }
        return false;
    }


    private boolean isValidMove(int[] direction, Maze maze) {
        int row = currentPos[0] + direction[0];
        int col = currentPos[1] + direction[1];
        char[][] fullMaze = maze.getFullMaze();
        if (fullMaze[row][col] == '#') {
            return false;
        }
        return true;
    }

    private void updateMaze(Maze maze, int[] newPosition) {
        //maze.updateMaze(newPosition);
    }
}