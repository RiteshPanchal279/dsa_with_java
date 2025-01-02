import java.util.Scanner;

public class OddEven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int num = in.nextInt();
//        1001001'0' => even
//        100100'1' => odd
        if((num & 1)==1){
            System.out.println("number is odd");
        }else {
            System.out.println("Number is even");
        }
    }
}
