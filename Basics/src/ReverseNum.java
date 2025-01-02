import java.util.Scanner;

public class ReverseNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int num = in.nextInt();
        int rev=0;
        while (num>0){
            int last = num%10;
            rev= rev * 10 +last;
            num/=10;
        }
        System.out.printf("The reverse of %d is %d",num,rev);
    }
}
