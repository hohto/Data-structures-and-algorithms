import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class QuickSort {
	//快速排序
/*	public static void quickSort(int[] array, int start, int end) {
		if(start>=end) {
			return;
		}
		
		int pivotIndex = partition(array, start, end);
		quickSort(array, start, pivotIndex-1);
		quickSort(array, pivotIndex+1, end);
	}*/
	
	//“挖坑法”
	public static int partition(int[] array,int start,int end){
		int left = start;
		int right = end;
		int index = left;
		int pivot = array[index];
		while(right>left) {
			while(right>left) {
				if(pivot > array[right]) {
					array[left] = array[right];
					index = right;
					left++;
					break;
				}
				right--;
			}
			while(right>left) {
				if(pivot < array[left]) {
					array[right] = array[left];
					index = left;
					right--;
					break;
				}
				left++;
			}
		}
		array[index] = pivot;
		return index;
	}
	
	
	//交换指针
/*	public static int partition(int[] array, int start, int end) {
		int pivot = array[start];
		int left = start;
		int right = end;
		while(left!=right) {
			while(left<right && pivot<=array[right]) {
				right--;
			}
			while(left<right && pivot>=array[left]) {
				left++;
			}
			if(left<right) {
				int temp = array[right];
				array[right] = array[left];
				array[left] = temp;
			}
		}
		int temp = array[left];
		array[left] = array[start];
		array[start] = temp;
		return left;
	}*/
	
	//非递归实现：栈
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public static void quickSort(int[] array, int start, int end) {
		// 用一个集合栈来代替递归的函数栈
		Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();
		Map rootParam = new HashMap();
		rootParam.put("start", start);
		rootParam.put("end", end);
		quickSortStack.push(rootParam);
		
		while(!quickSortStack.isEmpty()) {
			Map<String, Integer> param = quickSortStack.pop();
			int pivotIndex = partition(array, param.get("start"), param.get("end"));
			if(param.get("start") < pivotIndex-1) {
				Map<String, Integer> leftParam = new HashMap<String, Integer>();
				leftParam.put("start", param.get("start"));
				leftParam.put("end", pivotIndex-1);
				quickSortStack.push(leftParam);
			}
			if(pivotIndex + 1 < param.get("end")) {
				Map<String, Integer> rightParam = new HashMap<String, Integer>();
				rightParam.put("start", pivotIndex+1);
				rightParam.put("end", param.get("end"));
				quickSortStack.push(rightParam);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {3,44,38,5,47,15,26,27,48,2,4,19,46,50};
		quickSort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
}
