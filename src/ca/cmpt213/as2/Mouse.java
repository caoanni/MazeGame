package ca.cmpt213.as2;

/**
 * Created by anni on 2/12/17.
 */
public class Mouse {

    private int cheeseFound;

    public int getCheeseFound(){
        return cheeseFound;
    }

    //needs to override
    //public boolean move(Maze maze){
    //}

    private void incrementCheese(){
        cheeseFound++;
    }

    //private boolean isValidMove(Maze maze){
    //}

    //private void updateMaze(Maze maze){
    //    maze.updateMaze();
    //}

}
