package ca.cmpt213.as2;

import java.util.Random;
import java.util.ArrayList;

/**
 * Created by anni on 2/12/17.
 */
public class Maze {

    private char[][] currentMaze;
    private char[][] fullMaze;

    private static final int width = 20;
    private static final int height = 15;

    //20 spaces wide, including walls

    //15 tall, including walls

    //so the maze itself is 18x13

    // #  = wall
    //" " = open
    // . = foggy

    public Maze(){
        fullMaze = generateRandomMaze();
        //currentMaze = addFog(fullMaze);
    }

    private char[][] generateRandomMaze(){
        char[][] newMaze = new char[width][height];

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if ((i == 0) || (i == height-1)){
                    newMaze[j][i] = '#';
                }
                else if ((j == 0) || (j == width-1)){
                    newMaze[j][i] = '#';
                }
                else newMaze[j][i] = ' ';
            }
        }

        recursiveDivision(newMaze, 1, width-2, 1, height-2);

        return newMaze;
    }

    private void recursiveDivision(char[][] maze, int xStart, int xEnd, int yStart, int yEnd){
    	/*
    	System.out.println("xStart = " + xStart);
    	System.out.println("xEnd = " + xEnd);
    	System.out.println("yStart = " + yStart);
    	System.out.println("yEnd = " + yEnd);
    	*/
        //base case
        if (xEnd <= xStart + 2) return;
        if (yEnd <= yStart + 2) return;


        //make two lines
        Random r = new Random();
        //x: random value from xStart+1 to xEnd-1
        ArrayList<Integer> possibleXs = new ArrayList<>();
        for (int i = xStart+1; i <= xEnd-1; i++){
            if ((maze[i][yStart-1] == '#') && (maze[i][yEnd+1] == '#')){
                possibleXs.add(i);
                //System.out.println("possible x = " + i);
            }
        }

        ArrayList<Integer> possibleYs = new ArrayList<>();
        for (int i = yStart+1; i <= yEnd-1; i++){
            if ((maze[xStart-1][i] == '#') && (maze[xEnd+1][i] == '#')){
                possibleYs.add(i);
                //System.out.println("possible y = " + i);
            }
        }


        boolean foundX = false;
        boolean foundY = false;
        int x = 0;
        int y = 0;

        //System.out.println("possibleXs = " + possibleXs.size());
        //System.out.println("possibleYs = " + possibleYs.size());

        if (possibleXs.size() > 0){
            x = r.nextInt(possibleXs.size());
            x = possibleXs.get(x);
            //System.out.println("x = " + x);
            foundX = true;

            for (int i = yStart; i <= yEnd; i++){
                maze[x][i] = '#';
            }
        }

        if (possibleYs.size() > 0){
            y = r.nextInt(possibleYs.size());
            y = possibleYs.get(y);
            //System.out.println("y = " + y);
            foundY = true;

            for (int i = xStart; i <= xEnd; i++){
                maze[i][y] = '#';
            }
        }

        //punch holes (try two on each side of intersection)

        //x range is xStart to X, X to xEnd


        if (foundX && foundY){
            //get random from xStart to x-1
            int xHole1 = r.nextInt((x) - xStart) + xStart;


            //make hole at [that][y]
            maze[xHole1][y] = ' ';
            //System.out.println("xHole1 = " + xHole1);

            //get random from x+1 to xEnd
            int xHole2 = r.nextInt(xEnd+1 - (x+1)) + (x+1);
            //make hole at [that][y]
            maze[xHole2][y] = ' ';
            //System.out.println("xHole2 = " + xHole2);

            //get random from yStart to y-1
            int yHole1 = r.nextInt((y) - yStart) + yStart;
            //System.out.println("yHole1 = " + yHole1);
            //make hole at [x][that]
            maze[x][yHole1] = ' ';

            //get random from y+1 to yEnd
            int yHole2 = r.nextInt(yEnd+1 - (y+1)) + (y+1);
            //System.out.println("yHole2 = " + yHole2);
            //make hole at [x][that]
            maze[x][yHole2] = ' ';
        }
        else if (foundX){
            int xHole = r.nextInt((yEnd+1) - yStart) + yStart;
            //make hole at [x][that]
            maze[x][xHole] = ' ';
        }
        else if (foundY){
            int yHole = r.nextInt((xEnd+1) - xStart) + xStart;
            //make hole at [x][that]
            maze[yHole][y] = ' ';
        }

        //System.out.println("x = " + x);
        //System.out.println("y = " + y);

        recursiveDivision(maze, xStart, x-1, yStart, y-1);
        recursiveDivision(maze, xStart, x-1, y+1, yEnd);
        recursiveDivision(maze, x+1, xEnd, yStart, y-1);
        recursiveDivision(maze, x+1, xEnd, y+1, yEnd);

    }

    //return a maze with the unexplored areas hidden
    //private static char[][] addFog(char[][] maze){
    //}

    //reveal the adjacent 8 cells around the newPosition
    //public void updateMaze(int[] newPosition){
    //}

    public char[][] getFullMaze(){
        return fullMaze;
    }

    public char[][] getCurrentMaze(){
        return currentMaze;
    }

}
