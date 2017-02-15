package ca.cmpt213.as2;

/**
 * Created by anni on 2/12/17.
 */
public class UI {

    private String introduction;

    private String instructions;

    private void printWelcome(){
    }

    public void printHelp(){
    }

    public void printCurrentMaze(char[][] maze){
    }

    public static void printFullMaze(char[][] maze){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    //public char getInput(){
    //}

    public void printWinScreen(char[][] maze){
    }

    public void printLoseScreen(char[][] maze){
    }

}
