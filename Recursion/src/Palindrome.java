public class Palindrome {
   static int rev(int n){
      int digits=(int)(Math.log10(n))+1;
      return helper(n,digits);
   }
   private static int helper(int n, int digits) {
      if(n%10==n){
         return n;
      }
      int rem=n%10;
      return rem * (int)(Math.pow(10,digits-1))+helper(n/10,digits-1);
   }
   static boolean plain(int n){
      return n==rev(n);
   }
   public static void main(String[] args) {
      boolean ans=plain(12321);
      if(ans){
         System.out.println("number is palindrome");
      }else {
         System.out.println("number is not palindrome");
      }
   }
}
