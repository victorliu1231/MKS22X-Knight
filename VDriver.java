public class VDriver{
    public static void main(String[]args){
        KnightBoard k1 = new KnightBoard(5,5);
        System.out.println(k1);
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
        //test exceptions
    }
}
