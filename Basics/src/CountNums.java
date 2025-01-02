import java.util.Scanner;

public class CountNums {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int num = in.nextInt();
        int find=3;
        int count=0;
        while (num>0){
            int last = num % 10;
            if(last==find){
                count++;
            }
            num = num/10;
        }
        System.out.printf("THe count of %d is %d",find,count);
    }
}
