package Algorithams.Binary;

public class Practice {
    public static void main(String[] args) {
        int[] array = { 2, 3, 4, 10, 40 };
        int target = 10;
        int res = search(array,target);
        System.out.println("The ans is:"+res);
    }

    static int search(int[] arr,int target){
        int start=0;
        int end=arr.length-1;
         while(start<=end){
             int mid=start+(end-start)/2;
            if(arr[mid]==target) {
                return mid;
            }
            if(target>arr[mid]){
                start=mid+1;
            }else {
                end=mid-1;
            }
         }
         return -1;
    }
}
