public class Square{
  private int r;
  private int c;
  private int numJumpSpots;
  private int[] rowKnightIncrements;
  private int[] colKnightIncrements;

  public Square(int row, int col, int[][] board){
    rowKnightIncrements = new int[]{1,1,-1,-1,2,-2,2,-2};
    colKnightIncrements = new int[]{2,-2,2,-2,1,1,-1,-1};
    int rowBeingModified;
    int colBeingModified;
    for (int i = 0; i < 8; i++){
      rowBeingModified = row + rowKnightIncrements[i];
      colBeingModified = col + colKnightIncrements[i];
      //if on the board
      if (rowBeingModified < board.length && rowBeingModified >= 0 &&
          colBeingModified < board[row].length && colBeingModified >= 0){
            numJumpSpots++; //for every possible knight move from this square found, increment numJumpSpots
          }
      }
      r = row;
      c = col;
  }
  

  //returns the diff between this.numJumpSpots and other.numJumpSpots
  public int compareTo(Square other){
    return this.numJumpSpots - other.numJumpSpots;
  }

  public void subtract(){
    numJumpSpots--;
  }

  public void add(){
    numJumpSpots++;
  }

  public int r(){
    return r;
  }

  public int c(){
    return c;
  }

  public int getNumMoves(){
    return numJumpSpots;
  }
}
