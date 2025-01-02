package Maths;

public class SetBits {
    public static void main(String[] args) {
        int num = 46;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(setBit(num));
        System.out.println(isEven(num));

    }
    private static int setBit(int n){
        int count=0;
        while(n>0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    static boolean isEven(int n){
        if((n & 1)==0){
            return true;
        }
        return false;
    }


}
