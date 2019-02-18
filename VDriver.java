public class VDriver{
    public static void main(String[]args){
        KnightBoard k1 = new KnightBoard(5,5);
        /*
        System.out.println(k1); System.out.println(); //blank board
        System.out.println(k1.toStringMoves()); //optimization board
        KnightBoard k3 = new KnightBoard(7,7);
        System.out.println();
        System.out.println(k3.toStringMoves()); //optimization board
        */
        
        /*
        k1 = new KnightBoard(5,5);
        System.out.println(k1.countSolutions(0, 0)); //304
        System.out.println(k1); //blank board
        System.out.println();
        System.out.println(k1.countSolutions(0, 4)); //304
        System.out.println();
        System.out.println(k1.countSolutions(4, 0)); //304
        System.out.println();
        System.out.println(k1.countSolutions(4, 4)); //304
        System.out.println();
        System.out.println(k1.countSolutions(2, 2)); //64
        System.out.println();
        System.out.println(k1.countSolutions(0, 1)); //0
        System.out.println();
        System.out.println(k1.countSolutions(0, 2)); //56
        System.out.println();
        */
        
        int numExceptions = 0;

        KnightBoard ktest = new KnightBoard(1,1);
        try {
            ktest = new KnightBoard(0,1);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(1,0);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(0,0);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(0,-1);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(-1,1);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(-2,-1);
        } catch(IllegalArgumentException e){
            numExceptions++;
        }
        //batch of 6

        ktest = new KnightBoard(5,6);
        try {
            ktest.solve(0,0);
            ktest.solve(0,4);
        } catch (IllegalStateException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(5,6);
            ktest.solve(-1,0);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.solve(1,-2);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.solve(5,0);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.solve(0,6);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        //now numExceptions is 11
        
        ktest = new KnightBoard(5,6);
        try {
            ktest.solve(0,0);
            ktest.countSolutions(0,4);
        } catch (IllegalStateException e){
            numExceptions++;
        }
        try {
            ktest = new KnightBoard(5,6);
            ktest.countSolutions(-1,0);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.countSolutions(1,-2);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.countSolutions(5,0);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        try {
            ktest.countSolutions(0,6);
        } catch (IllegalArgumentException e){
            numExceptions++;
        }
        
        /*
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.addKnight(0, 0, 1);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.addKnight(2, 2, 2);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.addKnight(1, 3, 3);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.removeKnight(1, 3);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.removeKnight(2, 2);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        k1.removeKnight(0, 0);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves()); System.out.println();
        */

        System.out.println(k1.solve(0,0)); //true
        System.out.println(k1); //path board
        System.out.println();

        KnightBoard k2 = new KnightBoard(3,4);
        System.out.println(k2.solve(2,3)); System.out.println(k2); System.out.println();//true, path board HUH
        k2 = new KnightBoard(3,4);
        System.out.println(k2.countSolutions(2,3)); System.out.println(k2); System.out.println(); //2, blank board
        

        if (numExceptions == 16){
            System.out.println("Hurray! You caught every exception");
        } else {
            System.out.println("You missed an exception code case somewhere!");
        }
        System.out.println();


        //test the solve = false board size cases (when u optimized regular solve)
        /*
        solve(startRow,StartCol) : 
    should work on boards where the number of squares is under 100. 

countSolutions(startRow,StartCol) : 
    would only work on smaller boards! The exact sizes will be determined later. 
    */

    }
}
