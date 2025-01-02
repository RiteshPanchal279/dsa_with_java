public class NoOfDigits {
    public static void main(String[] args) {
        int n = 10;
        int base=2;
        // here ans is  (10)base 2 => 1010 = 4 no of digit
        int ans = (int) (Math.log(n) / Math.log(base))+1;
        System.out.println(ans);
    }
}
