import java.util.ArrayList;

public class KnightBoard{
    private int[][] board;
    private Square[][] boardMoves;
    private int numSolutions;
    private boolean solved;
    private int[] rowKnightIncrements;
    private int[] colKnightIncrements;

    //@throws IllegalArgumentException when either parameter is negative or 0.
    public KnightBoard(int startingRows,int startingCols){
        if (startingRows <= 0 || startingCols <= 0){
            throw new IllegalArgumentException("You cannot make a negative or 0 sized board!");
        }
        solved = false;
        board = new int[startingRows][startingCols];
        boardMoves = new Square[startingRows][startingCols];
        numSolutions = 0;
        rowKnightIncrements = new int[]{1,1,-1,-1,2,-2,2,-2};
        colKnightIncrements = new int[]{2,-2,2,-2,1,1,-1,-1};
        instantiateBoardMoves();
    }

    private void instantiateBoardMoves(){
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          boardMoves[r][c] = new Square(r,c, board);
        }
      }
    }


    private boolean addKnight(int row, int col, int moveNum){
        //if they try to add it to a square with a knight already on it...
        if (board[row][col] != 0){
            return false;
        }
        board[row][col] = moveNum;
        //pertaining to optimization board
        int rowBeingModified;
        int colBeingModified;
        for (int i = 0; i < 8; i++){
          rowBeingModified = row + rowKnightIncrements[i];
          colBeingModified = col + colKnightIncrements[i];
          //if on the board
          if (rowBeingModified < board.length && rowBeingModified >= 0 &&
              colBeingModified < board[row].length && colBeingModified >= 0){
                //every knight move that could have led to this tile will have one less possible move on the optimization board
                boardMoves[rowBeingModified][colBeingModified].subtract();
              }
        }
        return true;
    }

    private boolean removeKnight(int row, int col){
        //if they try to remove a knight where there isn't one
        if (board[row][col] == 0){
            return false;
        }
        board[row][col] = 0;
        //pertaining to optimization board
        int rowBeingModified;
        int colBeingModified;
        for (int i = 0; i < 8; i++){
          rowBeingModified = row + rowKnightIncrements[i];
          colBeingModified = col + colKnightIncrements[i];
          //if on the board
          if (rowBeingModified < board.length && rowBeingModified >= 0 &&
              colBeingModified < board[row].length && colBeingModified >= 0){
                //every knight move that could have led to this tile will have one more possible move on the optimization board
                boardMoves[rowBeingModified][colBeingModified].add();
              }
        }
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
        if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[startingRow].length){
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
              boolean pathHasSolution = false;
              for (int i = 0; i < 8; i++){
                pathHasSolution = pathHasSolution || solveH(row + rowKnightIncrements[i], col + colKnightIncrements[i], moveNum+1);
              }
              if (!pathHasSolution){ //if no solution in this path, backtrack
                removeKnight(row,col);
              }
              return pathHasSolution;
            } else {
              return false;
            }
      }
    }

    private Square optimizeNextMove(int row, int col){
      ArrayList<Square> possibleMoves = new ArrayList<>();
      int potentialRow;
      int potentialCol;
      //generates a list of all the possible moves
      for (int i = 0; i < 8; i++){
        potentialRow = row + rowKnightIncrements[i];
        potentialCol = col + colKnightIncrements[i];
        //if on the board
        if (potentialRow < board.length && potentialRow >= 0 &&
            potentialCol < board[row].length && potentialCol >= 0 &&
            board[potentialRow][potentialCol] == 0){
              possibleMoves.add(boardMoves[potentialRow][potentialCol]);
            }
      }
      //if there are no possible moves, return null
      if (possibleMoves.size() == 0){
        return null;
      }
      //now to sort the possibleMoves to get best move
      Square bestMove = possibleMoves.get(0);
      for (Square temp: possibleMoves){
        if (temp.getNumMoves() < bestMove.getNumMoves()){
          bestMove = temp;
        }
      }
      return bestMove;
    }

    
    /**
    *@throws IllegalStateException when the board contains non-zero values.
    *@throws IllegalArgumentException when either parameter is negative
    *or out of bounds.
    */
    public int countSolutions(int startingRow, int startingCol){ //would only work on smaller boards! the # of computation for this is immense
        if (startingRow < 0 || startingCol < 0  || startingRow >= board.length || startingCol >= board[startingRow].length){
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
              for (int i = 0; i < 8; i++){
                countSolHelp(row + rowKnightIncrements[i] ,col + colKnightIncrements[i], moveNum + 1, row, col);
              }
              solved = false; //solved is a variable that prevents other recursive calls from duplicating solutions
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

    //prints the optimization board
    public String toStringMoves(){
      String s = "";
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          if (c != 0){
            s+= " ";
          }
          s+= ""+boardMoves[r][c].getNumMoves();
        }
        if (r != boardMoves.length - 1){
          s+= "\n";
        }
      }
      return s;
    }

}
