package ca.cmpt213.as2.mazegame;

import ca.cmpt213.as2.userinterface.UI;

/**
 * This class is the main game controller for MazeGame.
 * It holds the other objects and gets them to do their work,
 * and also controls the flow the game.
 *
 * @authors Anni Cao and Amritpaul Gill
 *
 */
public class MazeGame {

    public static Maze maze;
    private static GameObject[] objects;
    private static Mouse mouse;
    private static Cat cat1;
    private static Cat cat2;
    private static Cat cat3;
    private static Cheese cheese;
    private static final int ROW = 20;
    private static final int COLUMN = 15;
    private static final int CHEESE_GOAL = 5;

    public static void main(String[] args){

        maze = new Maze();
        objects = new GameObject[5];
        mouse = new Mouse(new int[]{1,1}, '@');
        cat1 = new Cat(new int[]{18,1}, '!');
        cat2 = new Cat(new int[]{1,13}, '!');
        cat3 = new Cat(new int[]{18,13}, '!');
        cheese = new Cheese(new int[]{0,0}, '$', maze);
        objects[0] = mouse;
        objects[1] = cheese;
        objects[2] = cat1;
        objects[3] = cat2;
        objects[4] = cat3;
        startGame();
    }

    private static void startGame(){
        int[] input;
        UI.printWelcome();
        UI.printHelp();
        while (true) {
            maze.updateMaze(mouse.getPosition());
            UI.printMaze(generateMazeWithObjects(maze.getCurrentMaze()));
            UI.showProgress(mouse.getCheeseFound(), CHEESE_GOAL);
            boolean isValid = false;
            while (!isValid) {
                input = UI.getInput();
                isValid = mouse.move(input, maze);
                if (isValid) {
                    mouse.checkCheese(cheese, maze);
                }
            }
            if (checkWinOrLose()) {
                System.exit(0);
            }
            for (int i = 2; i <= 4; i++) {
                objects[i].move(objects[i].getPosition(), maze);
            }
            if (checkWinOrLose()) {
                System.exit(0);
            }
        }
    }

    private static boolean checkWinOrLose(){
        char[][] mazeWithObjects = generateMazeWithObjects(maze.getFullMaze());
        for (int i = 2; i <= 4; i++) {
            if (mouse.currentPos[0] == objects[i].currentPos[0] &&
                    mouse.currentPos[1] == objects[i].currentPos[1]) {
                UI.printWinOrLoseScreen(false, mazeWithObjects, mouse.getCheeseFound(), CHEESE_GOAL);
                return true;
            }
        }
        if (mouse.getCheeseFound() >= CHEESE_GOAL) {
            UI.printWinOrLoseScreen(true, mazeWithObjects, mouse.getCheeseFound(), CHEESE_GOAL);
            return true;
        }
        return false;
    }


    public static char[][] generateMazeWithObjects(char[][] maze){
        char[][] mazeWithObjects = new char[ROW][COLUMN];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                mazeWithObjects[i][j] = maze[i][j];
            }
        }
        for(int k = 0; k <= 4; k++) {
            int row = objects[k].getPosition()[0];
            int col = objects[k].getPosition()[1];
            mazeWithObjects[row][col] = objects[k].getSymbol();
        }
        return mazeWithObjects;
    }
}
