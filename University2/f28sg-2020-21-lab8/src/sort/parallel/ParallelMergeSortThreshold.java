package sort.parallel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import sort.sequential.SequentialMergeSort;
import sort.sequential.SortingCommon;
import utils.Benchmark;

/*
 * Merge Sort results with thresholding
 * ~~~~~~~~~~~~~~~~~~
 *
 * After parallelisation:
 * - 1 thread
 *   - no threshold:	3128ms
 *   - threshold=128:	2970ms
 *   - threshold=512:	2943ms
 *   - threshold=2048:	3323ms	
 *   - threshold=8192:  3433ms
 *
 * - 2 threads
 *   - no threshold: 	2278ms
 *   - threshold=128:	2291ms
 *   - threshold=512:	2248ms
 *   - threshold=2048:	2220ms
 *   - threshold=8192:	2261ms
 *
 *   <insert more if you have more than 2 CPU cores>
 *
 * Parameters of the shortest runtime:
 * - runtime:	2220ms
 * - how many threads:	2 threads
 * - threshold value:	2048
 * 
 * Best parallel speedup: 3128/2220 = 1.41
 * 
 * Parallelism efficiency: 1.41/2 = 0.705 = 70%
*/

public class ParallelMergeSortThreshold extends RecursiveTask<LinkedList<Integer>> {
	LinkedList<Integer> arr;
	int threshold;

	public ParallelMergeSortThreshold(LinkedList<Integer> arr, int threshold) {
		this.arr = arr;
		this.threshold = threshold;
	}

	@Override
	protected LinkedList<Integer> compute() {
		int length = arr.size();

		// Q2: rewrite the base case condition and body of this if statement,
		// so that you run:
		//
		// sequential merge sort for small inputs (the "base case")
		// by using SequentialMergeSort.mergeSort(..)
		//
		// or run
		//
		// parallel merge sort in parallel for large inputs (the "recursive" case)
		// if the length of the array is smaller than the threshold do it sequentially.
		if (length < threshold) {
			return SequentialMergeSort.mergeSort(arr);
		}

		// if it is bigger or equal to, do it in parallel
		else { // parallel case

			/* compute the size of the two sub arrays */
			int halfSize = length / 2;

			/* declare these as `left` and `right` arrays */
			LinkedList<Integer> left = new LinkedList<Integer>();
			LinkedList<Integer> right = new LinkedList<Integer>();

			/* populate the left array with values */
			Iterator<Integer> it = arr.iterator();
			int index = 0;
			while (index < halfSize) {
				left.add(it.next());
				index++;
			}

			/* populate the right array with values */
			index = 0;
			while (index < length - halfSize) {
				right.add(it.next());
				index++;
			}

			// TODO replace this to use the parallel fork/join approach but this
			// time using this ParallelMergeSoftThreshold class to create the two tasks,
			// rather than the ParallelMergeSort class that you used in Q1B. Remember
			// that this time you also need to pass the threshold as the 2nd argument
			// to the constructor.

			/* merge the sorted sub arrays */

			// instantiated like last time but with the new threshold class, you have to
			// pass in the value for the threshold to meet the constructor requirements.
			// x again deals with the left list, y with the right
			ParallelMergeSortThreshold x = new ParallelMergeSortThreshold(left, threshold);
			ParallelMergeSortThreshold y = new ParallelMergeSortThreshold(right, threshold);
			// we make x be done in another thread, using the fork().
			x.fork();
			// like last time we execute y in the current thread and set the resultRight
			// variable to it
			// resultLeft is the output of the above x.fork(). like last time.
			LinkedList<Integer> resultRight = y.compute();
			LinkedList<Integer> resultLeft = x.join();
			/* merge the sorted sub arrays */
			// only sequential part in this, is the merging of the lists.
			return SequentialMergeSort.merge(resultLeft, resultRight);
		}
	}

	/**
	 * Threshold based parallel merge sort
	 * 
	 * @param numbers     the input list
	 * @param threshold   when to switch from parallel divide-and-conquer to
	 *                    sequential divide-and-conquer
	 * @param parallelism how many threads to use in the ForkJoin workpool
	 * @return the sorted list
	 */
	public static LinkedList<Integer> parallelMergeSortThreshold(LinkedList<Integer> numbers, int threshold,
			int parallelism) {
		ForkJoinPool pool = new ForkJoinPool(parallelism);
		ParallelMergeSortThreshold mergeSortTask = new ParallelMergeSortThreshold(numbers, threshold);
		LinkedList<Integer> result = pool.invoke(mergeSortTask);
		return result;
	}

	/**
	 * Benchmarks threshold based parallel merge sort
	 */
	public static void main(String[] args) {
		/* generates a random list */
		LinkedList<Integer> numbers = SortingCommon.randomList(50000);

		/* gets the number of cores in this computer's CPU */
		int cpuCores = Runtime.getRuntime().availableProcessors();

		/*
		 * 1. prints the runtime for the parallel merge sort from Q1B.
		 * 
		 * 2. prints the runtime for the threshold based parallel merge sort for the
		 * implementation in Q2.
		 */
		for (int threads = 1; threads <= cpuCores; threads *= 2) {
			System.out.print("mergeSort\t no threshold\t\t");
			Benchmark.parallel(new ParallelMergeSort(numbers), threads);
			for (int threshold = 128; threshold <= 8192; threshold *= 4) {
				System.out.print("mergeSort\t threshold=" + threshold + "\t\t");
				Benchmark.parallel(new ParallelMergeSortThreshold(numbers, threshold), threads);
			}
		}
	}

}