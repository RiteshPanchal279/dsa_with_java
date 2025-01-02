package Algorithams.Linear;

public class SearchInRange {
    public static void main(String[] args) {
        int[] arr={9,8,7,6,5,4,3,2,1};
        int ans = rangeSearch(arr,5,2,6);
        System.out.println(ans);
    }

    static int rangeSearch(int[] arr,int target,int start,int end){
        if(arr.length==0){
            return -1;
        }
        for(int i=start;i<=end;i++){
            int element=arr[i];
            if(element==target){
                return i;
            }
        }
        return -1;
    }
}
