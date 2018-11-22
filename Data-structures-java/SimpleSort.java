import java.util.Arrays;

public class Sort {
	//冒泡排序
	public static void BubbleSort(int[] array){
		int n = array.length;
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j<n-1-i; j++) {
				if(array[j]>array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	
	//选择排序
	public static void SelectSort(int[] array) {
		int n = array.length;
		for(int i = 0; i < n-1 ; i++) {
			int minIndex = i;
			for(int j = i+1; j < n; j++) {
				if(array[j]<array[minIndex]) {
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}
	
	/**
	 * 插入排序
	 * in-place排序（即只需用到O(1)的额外空间的排序）
	 * @param array
	 * @return
	 */
	public static void InsertSort(int[] array) {
		int n = array.length;
		for(int i = 1; i < n; i++) {
			int preIndex = i-1;
			int current = array[i];
			while(preIndex >= 0 && array[preIndex] > current) {
				array[preIndex+1] = array[preIndex];
				preIndex--;
			}
			array[preIndex+1] = current;//preIndex+1>=0
		}
	}
	
	//希尔排序，第一个突破O(n2)的排序算法，是简单插入排序的改进版
	public static void ShellSort(int[] array) {
		for(int gap = array.length/2; gap >= 1; gap = gap/2) {
			for(int i = gap; i < array.length; i++) {
				//插入排序
				int temp = array[i];
				int preIndex = i-gap;
				while(preIndex >= 0 && temp < array[preIndex]) {
					array[preIndex+gap] = array[preIndex];
					preIndex -= gap;
				}
				array[preIndex+gap] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {3,44,38,5,47,15,26,27,48,2,4,19,46,50};
		//BubbleSort(array);
		//SelectSort(array);
		//InsertSort(array);
		ShellSort(array);
		System.out.println(Arrays.toString(array));
	}
}
