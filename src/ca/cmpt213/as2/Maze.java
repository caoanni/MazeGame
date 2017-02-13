package ca.cmpt213.as2;

/**
 * Created by anni on 2/12/17.
 */
public class Maze {

    private char[][] currentMaze;
    private char[][] fullMaze;

    public Maze(){
        //fullMaze = generateRandomMaze();
        //currentMaze = addFog(fullMaze);
    }

    //private static char[][] generateRandomMaze(char[][] maze){
    //}

    //return a maze with the unexplored areas hidden
    //private static char[][] addFog(char[][] maze){
    //}

    //public void updateMaze(GameObject[]){
    //}

    public char[][] getMaze(){
        return currentMaze;
    }

}
