public class Square{
  private int r;
  private int c;
  private int numJumpSpots;

  public Square(int r, int c, int[][] board){
    //for 4x4 boards or larger
    //we fill in all the squares and then reassign the squares to the correct value based on an if case
    numJumpSpots = 8;
    //the 6's
    if (r == 1 || r == board.length-2 ||
        c == 1 || c == board[r].length - 2){
          numJumpSpots = 6;
        }
    //edges of board
    if (r == 0 || r == board.length-1 ||
        c == 0 || c == board[r].length - 1){
          numJumpSpots = 4;          
        }
    //one square diagonal to each corner of the board
    if (r == 1 && c == 1 ||
        r == board.length-2 && c == 1 ||
        r == 1 && c == board[r].length-2 ||
        r == board.length-2 && c == board[r].length-2){
          numJumpSpots = 4;
        }
    if (r == 0 && c == 1 ||
        r == 0 && c == board[r].length-2 ||
        r == 1 && c == 0 ||
        r == 1 && c == board[r].length-1 ||
        r == board.length-2 && c == 0 ||
        r == board.length-2 && c == board[r].length-1 ||
        r == board.length-1 && c == 1 ||
        r == board.length-1 && c == board[r].length-2){
          numJumpSpots = 3;
        }
    if (r == 0 && c == 0 ||
        r == 0 && c == board[r].length-1 ||
        r == board.length-1 && c == 0 ||
        r == board.length-1 && c == board[r].length-1){
          numJumpSpots = 2;
    }
    this.r = r;
    this.c = c;
  }

  //returns the diff between this.numJumpSpots and other.numJumpSpots
  public int compareTo(Square other){
    return this.numJumpSpots - other.numJumpSpots;
  }

  public String toString(){
    return ""+numJumpSpots;
  }
}
