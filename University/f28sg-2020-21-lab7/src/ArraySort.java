import java.util.ArrayList;

import java.util.Iterator;

public class ArraySort {

	/** Insertion sort of an array
	 * @param arr the array to be sorted in-place
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > cur) {
				arr[j + 1] = arr[j--];
				arr[j + 1] = cur;
			}
		}
	}

	/** Insertion sort of an array
	 * 
	 * This is Question 3
	 * 
	 * @param arr the array to be sorted in-place, sorted by using bubble algorithm template, which goes through an array swapping values,
	 * once it has iterated through the array, sorting it, it will turn the swaps boolean = true, and thus, turn off the function.
	 */

	public static void bubbleSort(int[] arr) {
		boolean swaps = true;
		int temp;
		while (swaps) {
			swaps = false;
			for(int i = 0; i<arr.length-1;i++) {
				if (arr[i+1]<arr[i]) {
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					swaps = true;
					
					
					
				}
			}
		}

	}

	// Part 5 : complete implementation
	
	/**
	 * @param S
	 * @return S, the sorted version of S given in at the start. Using quicksort, and following guidelines in the question, creates a quicksort
	 * algorithm.
	 */
	public static ArrayList<Integer> quickSort(ArrayList<Integer> S) {
		// if the input list is empty or has one element only, return it as its technically already sorted.
		if (S.size()<=1) {
			return S;
		}
		// create pivot value which is integral to quicksort comparisons. in this case we use first element of the S list.
		int pivot = S.get(0);
		// Creates 3 Array lists L E G , one for less than pivot, equal to pivot and greater than pivot values.
		ArrayList<Integer> L = new ArrayList();
		ArrayList<Integer> E = new ArrayList();
		ArrayList<Integer> G = new ArrayList();
		
		// creates loop that doesnt stop till S is empty of all elements.
		while(S.size()!=0) {
			// get first element of S list and set it to temp variable
			int temp = S.get(0);
			// remove it so as too maintain order of sorting and that we can actually iterate through and sort.
			S.remove(0);
			// if the temp variable is less than the pivot add it to the L list.
			if (temp < pivot) {
				L.add(temp);
			}
			// if the temp variable is equal to the pivot, add it to the E list.
			else if (temp == pivot){
				E.add(temp);
			}
			// if the temp varaible is greater than the pivot, add it to the G list.
			else {
				G.add(temp);
			}
		}
		// recursively do quicksorts using this function again, but with L and G taking the places of S. I.e. they are now the input.
		quickSort(L);
		quickSort(G);
		// combine all the lists but in order, this gives us a sorted list S.
		S.addAll(L);
		S.addAll(E);
		S.addAll(G);
		// return the sorted list S.
		return S; // TODO
	}

	
	/** predicate to check if array is sorted
	 * @param arr the array to be checked
	 * @return true if the array is sorted, false otherwise
	 */
	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			if (arr[i] > arr[i + 1])
				return false;
		return true;
	}

	
	/** predicate to check if arrayList is sorted.
	 *  Useful for checking ArrayList<Integer> lists returned
	 *  from Quick Sort.
	 * 
	 * @param arr the array to be checked
	 * @return true is the aray is sorted, flalse otherwise
	 */
	public static boolean isSorted(ArrayList<Integer> arr) {
		Iterator i = arr.iterator();
		int val;
		if (i.hasNext())
			val = (int) i.next();
		else
			return true;
		while (i.hasNext()) {
			int nv = (int) i.next();
			if (val > nv)
				return false;
			val = nv;
		}
		return true;
	}

	
	/** Helper printing methods for testing
	 * @param arr the array to print
	 */
	private static void printIntArray(int[] arr) {
		System.out.print("[ ");
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" ]");
	}

	private static void printIntArrayList(ArrayList<Integer> arr) {
		System.out.print("[ ");
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" ]");
	}

	public static void main(String[] args) {
		// testing part1
		int[] arr1 = { 5, 4, 3, 2, 1 };
		bubbleSort(arr1);
		printIntArray(arr1);

		// testing part2
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(3);
		arr2.add(1);
		arr2.add(6);
		arr2.add(5);
		ArrayList<Integer> arr2_sorted = quickSort(arr2);
		printIntArrayList(arr2_sorted);
		// {5,4,3,5,1};

	}

}
