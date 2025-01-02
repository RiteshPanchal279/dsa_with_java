public class FindUnique {
    public static void main(String[] args) {
        int[] arr={1,3,4,6,4,3,2,2,1};
        System.out.println(ans(arr));
    }

    public static int ans(int[] arr){
        int unique=0;
        for(int i:arr){
//            this is called xOR operator
            unique ^= i;
        }
        return unique;
    }
    /*
    0   0  -> 0
    0   1  -> 1
    1   0  -> 1
    1   1  -> 0

    * */
}
