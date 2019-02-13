public class KnightBoard{
    int[][] board;

    //@throws IllegalArgumentException when either parameter is negative.
    public KnightBoard(int startingRows,int startingCols){
        if (startingRows < 0 || startingCols < 0){
            throw new IllegalArgumentException("You cannot make a negative sized board!");
        }
        board = new int[startingRows][startingCols];
    }


    private boolean addKnight(int row, int col){ //potentially will be written in that optimization class
        //if they try to add it to a square with a knight already on it...
        if (board[row][col] != 0){
            return false;
        }
        return true;
    }

    private boolean removeKnight(int row, int col){ //potentially will be written in that optimization class
        //if they try to remove a knight where there isn't one
        if (board[row][col] == 0){
            return false;
        }
        return true;
    }

    /**
    *@throws IllegalStateException when the board contains non-zero values.
    *@throws IllegalArgumentException when either parameter is negative 
    *or out of bounds.
    */
    public boolean solve(int startingRow, int startingCol){ //should work on boards less than 100x100 size
        if (startingRow < 0 || startingCol < 0){
            throw new IllegalArgumentException("You cannot start at a negative index of the board!");
        }
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if (board[r][c] != 0){
                    throw new IllegalStateException("Board is not clean! It contains non zero values.");
                }
            }
        }
        return solveH(startingRow,startingCol,0);
    }

    private boolean solveH(int row ,int col, int level){ // level is the # of the knight
        return true; //dummy value
    } 

    /**
    *@throws IllegalStateException when the board contains non-zero values. 
    *@throws IllegalArgumentException when either parameter is negative 
    *or out of bounds.
    */
    public int countSolutions(int startingRow, int startingCol){ //would only work on smaller boards! the # of computation for this is immense
        if (startingRow < 0 || startingCol < 0){
            throw new IllegalArgumentException("You cannot start at a negative index of the board!");
        }
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if (board[r][c] != 0){
                    throw new IllegalStateException("Board is not clean! It contains non zero values.");
                }
            }
        }
        return true; //dummy value
    }

    public String toString(){
        return ""; //dummy value
    }
    /*see format for toString below
    blank boards display 0's as underscores 
    you get a blank board if you never called solve or 
    when there is no solution */

    
}