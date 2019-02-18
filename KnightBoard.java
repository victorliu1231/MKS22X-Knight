public class KnightBoard{
    private int[][] board;
    private int[][] boardMoves;
    private int numSolutions;
    private boolean solved = false;

    //@throws IllegalArgumentException when either parameter is negative.
    public KnightBoard(int startingRows,int startingCols){
        if (startingRows <= 0 || startingCols <= 0){
            throw new IllegalArgumentException("You cannot make a negative or 0 sized board!");
        }
        board = new int[startingRows][startingCols];
        boardMoves = new int[startingRows][startingCols];
        numSolutions = 0;
        instantiateBoardMoves();
    }

    private void instantiateBoardMoves(){
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          if (r == 0 && c == 0 ||
              r == 0 && c == board[r].length - 1 ||
              r == board.length - 1 && c == 0 ||
              r == board.length - 1 && c == board[r].length - 1){
                board[r][c] = 2;
              }
            else if (r == 0 || r == board.length - 1 ||
                c == 0 || c == board[r].length - 1){
                  board[r][c] = 3;
                }
        }
      }
    }

    public String toStringMoves(){
      String s = "";
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          if (c != 0){
            s+= " ";
          }
          s+= boardMoves[r][c];
        }
        if (r != boardMoves.length - 1){
          s+= "\n";
        }
      }
      return s;
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
    /*Any m × n board with m ≤ n, a closed knight's tour is always possible unless one or more of these three conditions are met:
    m and n are both odd
    m = 1, 2, or 4
    m = 3 and n = 4, 6, or 8.
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
      //the +1 in base case is here b/c our moveNum starts at 1 instead of 0 (because of how toString is formatted)
      if (moveNum == board.length * board[0].length +1){
        return true;
      } else {
        if (row < board.length && row >= 0 &&
            col < board[row].length && col >= 0 &&
            board[row][col] == 0){ //earlier cases shortcircuit if index out of bounds
              addKnight(row,col,moveNum);
              if (solveH(row+1 ,col+2, moveNum+1) || solveH(row+1, col-2, moveNum+1) ||
                  solveH(row-1 ,col+2, moveNum+1) || solveH(row-1, col-2, moveNum+1) ||
                  solveH(row+2 ,col+1, moveNum+1) || solveH(row+2, col-1, moveNum+1) ||
                  solveH(row-2 ,col+1, moveNum+1) || solveH(row-2, col-1, moveNum+1)){
                    return true;
                  } else {
                    removeKnight(row,col);
                    return false;
                  }
            }
        else {
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
        numSolutions = 0;
        countSolHelp(startingRow, startingCol, 1, startingRow, startingCol);
        return numSolutions;
    }

    //lastKnightR and lastKnightC stores the memory of the last placed Knight's position
    private void countSolHelp(int row, int col, int moveNum, int lastKnightR, int lastKnightC){
      //the +1 in base case is here b/c our moveNum starts at 1 instead of 0 (because of how toString is formatted)
      if (moveNum == board.length * board[0].length +1 && !solved){
        solved = true; //solved is a variable that prevents other recursive calls from duplicating solutions
        removeKnight(lastKnightR, lastKnightC); //backtracks and adds a solution when you find a working configuration
        numSolutions++;
      } else {
        if (row < board.length && row >= 0 &&
            col < board[row].length && col >= 0 &&
            board[row][col] == 0){ //utilizes short circuiting; only branches down the tree if it is possible to place a Knight here
              addKnight(row,col,moveNum);
              countSolHelp(row+1 ,col+2, moveNum+1,row,col);
              countSolHelp(row+1, col-2, moveNum+1,row,col); //this is a problem i can fix later
              countSolHelp(row-1 ,col+2, moveNum+1,row,col);
              countSolHelp(row-1, col-2, moveNum+1,row,col);
              countSolHelp(row+2 ,col+1, moveNum+1,row,col);
              countSolHelp(row+2, col-1, moveNum+1,row,col);
              countSolHelp(row-2 ,col+1, moveNum+1,row,col);
              countSolHelp(row-2, col-1, moveNum+1,row,col);
              solved = false;
              //once finishing parsing through the jump spots, then backtrack to explore any new solutions
              removeKnight(row, col);
        }
      }
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
