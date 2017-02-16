package ca.cmpt213.as2;

import java.util.Scanner;

/**
 * Created by anni on 2/12/17.
 */
public class UI {

    private String introduction;

    private String instructions;


    public static void printWelcome(){
    }

    public static void printHelp(){
    }

    public static void printMaze(char[][] maze){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[] getInput(){
        boolean isValid = false;
        Scanner scan = new  Scanner(System.in);
        int[] result = new int[2];
        while (!isValid) {
            System.out.println("Please enter one of the four letter(W, A, S, D):");
            char input = scan.next().toUpperCase().charAt(0);
            switch (input) {
                case 'W':
                    result[0] = -1;
                    result[1] = 0;
                    isValid = true;
                    break;
                case 'A':
                    result[0] = 0;
                    result[1] = -1;
                    isValid = true;
                    break;
                case 'S':
                    result[0] = 1;
                    result[1] = 0;
                    isValid = true;
                    break;
                case 'D':
                    result[0] = 0;
                    result[1] = 1;
                    isValid = true;
                    break;
                default:
                    System.out.println("The input is not valid.");
            }
        }
        return result;
    }

    public static void printWinOrLoseScreen(boolean isWin, char[][] maze){
        if (isWin) {
            System.out.println("Mouse win the game!");
        } else {
            System.out.println("Mouse lose the game!");
        }
        System.out.println();
        printMaze(maze);
    }
}
