import java.util.Arrays;

public class MergeSort {
	public static void mergeSort(int[] array, int start, int end) {
		int mid = (start+end)/2;
		if(start<end) {
			mergeSort(array,start,mid);
			mergeSort(array,mid+1,end);
			merge(array,start,mid,end);
		}
	}
	
	public static void merge(int[] array, int start, int mid, int end) {
		int[] temp = new int[end-start+1];
		int i = start;
		int j = mid+1;
		int k=0;
		
		while(i<=mid && j<=end) {
			if(array[i]<array[j]) {
				temp[k++] = array[i++];
			}else {
				temp[k++] = array[j++];
			}
		}
		
		//剩余的
		while(i<=mid) {
			temp[k++] = array[i++];
		}
		
		while(j<=end) {
			temp[k++] = array[j++];
		}
		
		for(int x=0;x<temp.length;x++) {
			array[x+start] = temp[x];
		}
	}
	
	public static void main(String[] args) {
		int[] array = {3,44,38,5,47,15,26,27,48,2,4,19,46,50};
		mergeSort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
}
