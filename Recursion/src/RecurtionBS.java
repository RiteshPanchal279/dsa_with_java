public class RecurtionBS {
    public static void main(String[] args) {
    int[] arr={1,2,3,4,5,6};
    int target=5;
    int ans=recursionBS(arr,target,0,arr.length-1);
        System.out.println(ans);
    }

    static int recursionBS(int[] arr,int target,int start,int end){
        if(start>end){
            return -1;
        }
        int mid =start+(end-start)/2;
        if(arr[mid]==target){
            return mid;
        }
        if(arr[mid] > target){
            return recursionBS(arr,target,start,mid-1);
        }
        return recursionBS(arr,target,mid+1,end);
    }
}
