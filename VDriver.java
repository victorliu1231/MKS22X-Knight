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
        System.out.println(k1.solve(0,0)); //true
        System.out.println(k1); //path board
        System.out.println();
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

        /*
        KnightBoard k2 = new KnightBoard(3,4);
        System.out.println(k2.solve(2,3)); System.out.println(k2); System.out.println();//true, path board HUH
        k2 = new KnightBoard(3,4);
        System.out.println(k2.countSolutions(2,3)); System.out.println(k2); System.out.println(); //2, blank board
        */


        //test exceptions
        //test the solve = false board size cases
        /*
        solve(startRow,StartCol) : 
    should work on boards where the number of squares is under 100. 

countSolutions(startRow,StartCol) : 
    would only work on smaller boards! The exact sizes will be determined later. 
    */

    }
}
