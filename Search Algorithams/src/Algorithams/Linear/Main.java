package Algorithams.Linear;

public class Main {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,12,6,7,8};
        int target=12;
        int index=LinearSearch(arr,target);
        System.out.printf("The %d is in array of index %d  ",target,index);
    }

    static int LinearSearch(int[] arr,int target){
        if(arr.length==0){
            return -1;
        }

        for (int i=0;i<arr.length;i++){
            if(arr[i]==target){
                return i;
            }
        }
//       this line will execute if none of the return statement above have executed
//        hence the target not found
    return -1;
    }
}