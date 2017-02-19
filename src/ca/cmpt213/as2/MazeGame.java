package ca.cmpt213.as2;

/**
 * Created by anni on 2/12/17.
 */
public class MazeGame {

    private static Maze maze;
    private static GameObject[] objects;
    private static Mouse mouse;
    private static Cat cat1;
    private static Cat cat2;
    private static Cat cat3;
    private static Cheese cheese;
    private static final int row = 20;
    private static final int column = 15;

    public static void main(String[] args){

        maze = new Maze();
        UI.printMaze(maze.getFullMaze());
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
        //UI.printWelcome();
        //UI.printHelp();
        while (true) {
            boolean isValid = false;
            while (!isValid) {
                UI.printMaze(generateMazeWithObjects(maze.getFullMaze()));
                input = UI.getInput();
                isValid = mouse.move(input, maze);
                if (isValid) {
                    mouse.isCheese(cheese, maze);
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
                UI.printWinOrLoseScreen(false, mazeWithObjects);
                return true;
            }
        }
        if (mouse.getCheeseFound() >= 5) {
            UI.printWinOrLoseScreen(true, mazeWithObjects);
            return true;
        }
        return false;
    }


    private static char[][] generateMazeWithObjects(char[][] maze){
        char[][] mazeWithObjects = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                mazeWithObjects[i][j] = maze[i][j];
            }
        }
        for(int k = 0; k <= 4; k++) {
            int x = objects[k].getPosition()[0];
            int y = objects[k].getPosition()[1];
            mazeWithObjects[x][y] = objects[k].getSymbol();
        }
        return mazeWithObjects;
    }
}
