import java.util.Arrays;

public class HeapSort {
	public static void main(String[] args) {
		int[] arr = {3,1,4,2,8,5,9,7,6};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void heapSort(int[] arr) {
		//构建大顶堆
		for(int i=arr.length/2-1; i>=0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		for(int j=arr.length-1; j>0; j--) {
			swap(arr, 0, j);
			adjustHeap(arr, 0, j);
		}
	}
	
	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		for(int k=i*2+1; k<length; k=k*2+1) {
			if(k+1<length && arr[k]<arr[k+1]) {
				k++;
			}
			if(arr[k]>temp) {
				arr[i] = arr[k];
				i = k;
			}else {
				break;
			}
		}
		arr[i] = temp;
	}
	
	public static void swap(int[] arr, int a, int b) {
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
	}
}
