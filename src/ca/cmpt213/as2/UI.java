package ca.cmpt213.as2;

import java.util.Scanner;

/**
 * Created by anni on 2/12/17.
 */
public class UI {

    private static String introduction =
        "----------------------------------------\n"
     + "Welcome to Cat and Mouse Maze Adventure!\n"
     +  "by Anni Cao and Amritpaul Gill\n"
     +  "----------------------------------------";

    private static String instructions =
        "DIRECTIONS:\n"
     +  "\tFind 5 cheese before a cat eats you!\n"
     +  "LEGEND:\n"
     +  "\t#: Wall\n"
     +  "\t@: You (a mouse)\n"
     +  "\t!: Cat\n"
     +  "\t$: Cheese\n"
     +  "\t.: Unexplored space\n"
     +  "MOVES:\n"
     +  "\tUse W (up), A (left), S (down) and D (right) to move.\n"
     +  "\t(You must press enter after each move).";


    public static void printWelcome(){
        System.out.println(introduction);
    }

    public static void printHelp(){
        System.out.println(instructions);
    }

    public static void printMaze(char[][] maze){
        System.out.println("\nMaze:");
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void showProgress(int found, int goal){
        System.out.println("Cheese collected: " + found + " of " + goal);
    }

    public static int[] getInput(){
        boolean isValid = false;
        Scanner scan = new  Scanner(System.in);
        int[] result = new int[2];
        while (!isValid) {
            System.out.print("Enter your move [WASD?]: ");
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
                case '?':
                    printHelp();
                    break;
                case 'M':
                    printMaze(MazeGame.generateMazeWithObjects(MazeGame.maze.getFullMaze()));
                    break;
                default:
                    System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
            }
        }
        return result;
    }

    public static void printWinOrLoseScreen(boolean isWin, char[][] maze, int cheeseFound, int goal){
        if (isWin) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("I'm sorry, you have been eaten!");
        }
        System.out.println();
        printMaze(maze);
        showProgress(cheeseFound, goal);
        if (!isWin){
            System.out.println("GAME OVER; please try again.");
        }
    }
}
