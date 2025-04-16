import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class MazeSolver {
    
    private int rows;
    private int cols;
    private char[][] maze;
    private List<int[]> path;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private final Random random = new Random();

    public MazeSolver(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Maze dimensions must be positive integers.");
        }
        this.rows = rows;
        this.cols = cols;
        this.maze = generateMaze(); 
        this.path = new ArrayList<>();
        
}

    public char[][] generateMaze() {
        maze = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(maze[i], '#');
        }

        startRow = 1;
        startCol = 1;

        endRow = rows - 2;
        endCol = cols - 2;
        
        startRow = Math.min(Math.max(startRow, 1), rows - 2);
        startCol = Math.min(Math.max(startCol, 1), cols - 2);
        endRow = Math.min(Math.max(endRow, 1), rows - 2);
        endCol = Math.min(Math.max(endCol, 1), cols - 2);
        if (startRow % 2 == 0) startRow++;
        if (startCol % 2 == 0) startCol++;
        if (endRow % 2 == 0) endRow++;
        if (endCol % 2 == 0) endCol++;

        recursiveBacktracking(startRow, startCol, maze);

        maze[startRow][startCol] = 'S';
        maze[endRow][endCol] = 'E';

        return maze;
    }
    
    private void recursiveBacktracking(int row, int col, char[][] maze) {
        maze[row][col] = ' '; 

        List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{0, 2});  
        directions.add(new int[]{2, 0});  
        directions.add(new int[]{0, -2}); 
        directions.add(new int[]{-2, 0}); 
        Collections.shuffle(directions, random);
