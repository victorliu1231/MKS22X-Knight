public class VDriver{
    public static void main(String[]args){
        KnightBoard k1 = new KnightBoard(5,5);
        System.out.println(k1); System.out.println();
        System.out.println(k1.toStringMoves());
        KnightBoard k3 = new KnightBoard(8,8);
        System.out.println();
        System.out.println(k3.toStringMoves());
        /*
        System.out.println(k1.solve(0,0));
        System.out.println();
        System.out.println(k1);
        System.out.println();
        k1 = new KnightBoard(5,5);
        System.out.println(k1.countSolutions(0, 0));
        System.out.println(k1);
        System.out.println();
        System.out.println(k1.countSolutions(0, 4));
        System.out.println(k1);
        System.out.println();
        System.out.println(k1.countSolutions(4, 0));
        System.out.println(k1);
        System.out.println();
        System.out.println(k1.countSolutions(4, 4));
        System.out.println(k1);
        System.out.println();
        System.out.println(k1.countSolutions(2, 2));
        System.out.println(k1);
        System.out.println();
        */

        /*
        KnightBoard k2 = new KnightBoard(3,4);
        System.out.println(k2.solve(2,3)); System.out.println(k2);
        k2 = new KnightBoard(3,4);
        System.out.println(k2.countSolutions(2,3)); System.out.println(k2); System.out.println();
        */


        //test exceptions
    }
}
