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
            int[] x1start = {xStart, y};
            int[] x1end = {x, y};
            makeHole(x1start, x1end, maze);

            int[] x2start = {x + 1, y};
            int[] x2end = {xEnd, y};
            makeHole(x2start, x2end, maze);

            int[] y1start = {x, yStart};
            int[] y1end = {x, y };
            makeHole(y1start, y1end, maze);

            int[] y2start = {x, y + 1};
            int[] y2end = {x, yEnd};
            makeHole(y2start, y2end, maze);

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

    //makes an extra hole when the wall is too long
    private void makeHole(int[] startPoint, int[] endPoint, char[][] maze){

        //in case of bad input
        if ((startPoint.length != 2) || (endPoint.length != 2)){
            return;
        }
        int[] newHole = new int[2];
        Random r = new Random();

        int constantAxis;
        int variableAxis;
        //which direction is the wall going?
        if (startPoint[0] == endPoint[0]){
            constantAxis = 0;
            variableAxis = 1;
        }
        else{
            constantAxis = 1;
            variableAxis = 0;
        }

        newHole[constantAxis] = startPoint[constantAxis];

        if (endPoint[variableAxis] == startPoint[variableAxis]){
            newHole[variableAxis] = endPoint[variableAxis];
        }
        else{
            newHole[variableAxis] = r.nextInt(endPoint[variableAxis] - startPoint[variableAxis]) + startPoint[variableAxis];
        }

        maze[newHole[0]][newHole[1]] = ' ';

        //recurse if the wall is longer than 5
        if (newHole[variableAxis] - startPoint[variableAxis] > 5){
            int[] end = new int[2];
            end[constantAxis] = newHole[constantAxis];
            end[variableAxis] = newHole[variableAxis] - 1;
            makeHole(startPoint, end, maze);
        }
        if (endPoint[variableAxis] - newHole[variableAxis] > 5){
            int[] start = new int[2];
            start[constantAxis] = newHole[constantAxis];
            start[variableAxis] = newHole[variableAxis] + 1;
            makeHole(start, endPoint, maze);
        }

        return;
    }

    public char[][] getFullMaze(){
        return fullMaze;
    }

    public char[][] getCurrentMaze(){
        return currentMaze;
    }

}
