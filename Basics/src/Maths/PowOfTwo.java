package Maths;

public class PowOfTwo {
    public static void main(String[] args) {
        int n=0;
        boolean ans=(n & (n-1)) == 0; ;
        System.out.println(ans);

        int num =110;
        int as = 1010 >> 2;
        System.out.println(as);
    }
}
