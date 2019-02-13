public class KnightBoard{
    int[][] board;

    //@throws IllegalArgumentException when either parameter is negative.
    public KnightBoard(int startingRows,int startingCols){
        if (startingRows <= 0 || startingCols <= 0){
            throw new IllegalArgumentException("You cannot make a negative or 0 sized board!");
        }
        board = new int[startingRows][startingCols];
    }


    private boolean addKnight(int row, int col, int moveNum){ //potentially will be written in that optimization class
        //if they try to add it to a square with a knight already on it...
        if (board[row][col] != 0){
            return false;
        }
        board[row][col] = moveNum;
        return true;
    }

    private boolean removeKnight(int row, int col){ //potentially will be written in that optimization class
        //if they try to remove a knight where there isn't one
        if (board[row][col] == 0){
            return false;
        }
        board[row][col] = 0;
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
        return solveH(startingRow,startingCol,1);
    }

    private boolean solveH(int row ,int col, int moveNum){
      if (moveNum == board.length * board[0].length){
        return true;
      } else {
        if (board[row][col] == 0 &&
            row < board.length && row >= 0 &&
            col < board[row].length && col >= 0){ //only branches down the tree if it is possible to place a queen here
          addKnight(row,col,moveNum);
          return (solveH(row+1 ,col+2, moveNum+1) || solveH(row+1, col-2, moveNum+1) ||
                  solveH(row-1 ,col+2, moveNum+1) || solveH(row-1, col-2, moveNum+1) ||
                  solveH(row+2 ,col+1, moveNum+1) || solveH(row+2, col-1, moveNum+1) ||
                  solveH(row-2 ,col+1, moveNum+1) || solveH(row-2, col-1, moveNum+1));
        }
        else {
          removeKnight(row,col);
          return false;
        }
      }
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
        return 1; //dummy value
    }

    //prints the path that the knight went on to get to the solution
    public String toString(){
      String ans = "";
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          //below if case is so the first column doesn't have an extra whitespace
          if (c != 0){
            ans+= " ";
          }
          if (board[r][c] < 10){ //if single digit #
            if (board[r][c] == 0){
              ans+= " _";
            } else {
              ans+= " "+board[r][c];
            }
          } else {
            ans+= ""+board[r][c];
          }
        }
        //so the last row doesn't print a \n
        if (r != board.length - 1){
          ans+="\n";
        }
      }
      return ans;
    }


}
