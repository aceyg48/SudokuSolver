package org.example.sudokusolver;


import java.util.Arrays;

public class SudokuStarter {

    public static void main(String[] args) {
        // To instantiate the puzzle you are going to have to do it by hand
        int[][] puzzle = {
                {0, 0, 0, 0, 0, 0, 6, 5, 1},
                {0, 4, 0, 0, 0, 0, 0, 0, 0},
                {7, 1, 6, 0, 0, 2, 0, 0, 0},
                {0, 9, 0, 7, 1, 6, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 0, 4, 9, 3, 0, 8, 0},
                {0, 0, 0, 5, 0, 0, 9, 7, 2},
                {0, 0, 0, 0, 0, 0, 0, 3, 0},
                {4, 3, 9, 0, 0, 0, 0, 0, 0}};
        /*int[][] puzzle = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        /*int[][] puzzle = {
                {0, 6, 0, 3, 1, 0, 0, 2, 8},
                {1, 0, 0, 0, 0, 0, 7, 0, 3},
                {0, 0, 9, 0, 7, 0, 0, 0, 0},
                {0, 0, 0, 4, 8, 0, 0, 0, 0},
                {0, 7, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 0, 6, 2, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 3, 0, 0},
                {8, 0, 4, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 2, 9, 0, 8, 0}};*/


        //  Commented out to show error
        // A list of all the permanent numbers that can not be changed by the program
        //int[][] coordinates = new int[81][2];
        //for (int i = 0; i < 81; i++) {
        //    for (int j = 0; j < 2; j++) {
        //        coordinates[i][j] = 0;
        //    }
        //}
        //coordinates = inputCords(startPuzzle, coordinates);

        //int[][] solvedPuzzle;

        
       // If the puzzle doesn't get solved then there is not a solution :)
        if(sudokuSolver(puzzle)){
            printPuzzle(puzzle);
        }else {
            System.out.println("No Solution");
        }
        /*System.out.println(solvedPuzzle);

        for (int i = 0; i < 81; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(coordinates[i][j]);
            }
            System.out.println();
        }
*/
          System.out.println("Does work?");
    }

    /*private static int[][] inputCords(int[][] puzzle, int[][] cords){
        // memory for the coordinates array because it's not the same shape as startPuzzle
        int r = 0;
        int c = 0;
        int[][] newCords = cords;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (puzzle[i - 1][j - 1] != 0){
                    newCords[r][c] = i;
                    newCords[r][c + 1] = j;
                    r++;
                }
            }
        }
        return newCords;
    }*/

    private static boolean sudokuSolver(int[][] puzzle) {
        // Go through every row
        for (int i = 0; i < 9; i++) {
            // Go through every column
            for (int j = 0; j < 9; j++) {
                // check to see if its blank, 0 is blank
                if (puzzle[i][j] == 0) {
                    //  If it is blank change the current cell to  the next number from 1 -> 9
                    for (int k = 1; k < 10; k++) {
                        // Check to see if the puzzle is valid after making this move
                        if (checkRow(puzzle, i, k) && checkColumn(puzzle, j, k) && checkBox(puzzle, i, j, k)) {
                            // If the puzzle is valid assign the number to the cell
                            puzzle[i][j] = k;

                            // Recursive call to start the process again
                            if (sudokuSolver(puzzle)) {
                                // Eventually at the end of the puzzle there will no longer be any empty cells
                                // This if statement is very important because it allows for the tree to get deeper
                                return true;
                            // if it has a conflict anywhere in the tree set the cell back to 0 and try the next one
                            } else {
                                // back track
                                puzzle[i][j] = 0;
                            }
                        }
                    }
                    // If there is ever a point to which the number 1-9 cannot be placed into the cell
                    // then the puzzle is unsolvable.
                    return false;
                }
            }
        }
        // End of the puzzle
        return true;
    }
    private static boolean checkRow(int[][] puzzle, int r, int num) {
        // iterate through the row
        for (int i = 0; i < 9; i++) {
            // check to see if any of the numbers in the row equal
            // the number we want to put into our cell
            if (puzzle[r][i] == num) {
                return false;
            }
        }
        // If none of the cells combat ours we're good
        return true;
    }

    private static boolean checkColumn(int[][] puzzle, int c, int num) {
        // iterate through the column
        for (int i = 0; i < 9; i++) {
            // check to see if any of the numbers in the column equal
            // the number we want to put into our cell
            if (puzzle[i][c] == num){
                return false;
            }
        }
        // If none of the cells combat ours we're good
        return true;
    }

    private static boolean checkBox(int[][] puzzle, int r, int c, int num) {
        // In order to check the smaller 3 by 3 grids in the puzzle we will
        // have to find out which 3x3 grid its in.
        int subGridRow = r / 3 * 3;
        int subGridCol = c / 3 * 3;
        // since they aren't doubles int / int results in the same as a %
        // so when we multiply by 3 we will get the index of the row and the column
        // of the top right cell in each of the sub grids. The only numbers that can
        // be obtained are 0, 3, 6 for both the rows and the columns

        // these subgrids are only 3x3's
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(puzzle[subGridRow + i][subGridCol + j] == num){
                    return false;
                }
            }
        }
        return true;
    }

    private static void printPuzzle(int[][] puzzle){
        // prints the puzzle row by row to the console
        for (int[] row : puzzle){
            System.out.println(Arrays.toString(row));
        }
    }

}