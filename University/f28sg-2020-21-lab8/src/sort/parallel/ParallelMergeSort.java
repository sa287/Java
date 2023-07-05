package sort.parallel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import sort.sequential.SequentialMergeSort;
import sort.sequential.SortingCommon;
import utils.Benchmark;

/*
 * Merge Sort results
 * ~~~~~~~~~~~~~~~~~~
 * 
 * Before parallelisation: TODO
 * 1 thread:	3062ms	
 * 2 threads:	3035ms	
 * 4 threads:	2953ms	
 * 8 threads:	3663ms	
 * - <insert more lines as appropriate>
 * 
 * 
 * After parallelisation: TODO
 * 1 thread:	3199ms	
 * 2 threads:	2326ms	
 * 4 threads:	2143ms	
 * 8 threads:	2095ms	
 * - <insert more lines as appropriate>
 */

public class ParallelMergeSort extends RecursiveTask<LinkedList<Integer>> {
	LinkedList<Integer> arr;

	public ParallelMergeSort(LinkedList<Integer> arr) {
		this.arr = arr;
	}

	@Override
	protected LinkedList<Integer> compute() {
		int length = arr.size();
		// base case
		if (length < 2) {
			return arr;
		}
		// step case
		else {
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

			/*
			 * Q1A: profile this sequential implementation.
			 * 
			 * Q1B: replace this with a parallel fork/join approach.
			 */
			// Instantiated the ParralelMergeSort class, x deals with the left list, y the
			// right.
			ParallelMergeSort x = new ParallelMergeSort(left);
			ParallelMergeSort y = new ParallelMergeSort(right);

			// then introduce the fork() method of the instantiated class, to parallelise
			// the code

			x.fork();
			// we sort using compute and put that into the resultRight variable LinkedList.
			// we do it using compute
			// so that it happens in the current thread
			LinkedList<Integer> resultRight = y.compute();
			// we set a blocking call using join, that waits for the actions in x.fork() to
			// be done, i.e. the actions in another thread to finish
			// and then set the variable resultLeft equal to the output of that.
			LinkedList<Integer> resultLeft = x.join();
			// LinkedList<Integer> resultLeft = ParallelMergeSort.parallelMergeSort(left,
			// 4);
			// LinkedList<Integer> resultRight = ParallelMergeSort.parallelMergeSort(right,
			// 4);

			/* merge the sorted sub arrays */
			return merge(resultLeft, resultRight);
		}

	}

	// to assist mergeSort
	public static LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
		int lindex = 0;
		int rindex = 0;
		LinkedList<Integer> mergedList = new LinkedList<Integer>();

		/*
		 * step 1: compare elementwise left and right lists and write the smaller of the
		 * two values into the mergedList list
		 */
		while (lindex < left.size() && rindex < right.size()) {
			if (left.get(lindex) <= right.get(rindex)) {
				mergedList.add(left.get(lindex));
				lindex++;
			} else {
				mergedList.add(right.get(rindex));
				rindex++;
			}
		}

		// step 2: write any remaining values in the left list into the mergeList list
		while (lindex < left.size()) {
			mergedList.add(left.get(lindex));
			lindex++;
		}

		// step 3: write any remaining values in the right list into the mergeList list
		while (rindex < right.size()) {
			mergedList.add(right.get(rindex));
			rindex++;
		}
		return mergedList;
	}

	/**
	 * Parallel merge sort
	 * 
	 * @param numbers     the list of numbers to be sorted
	 * @param parallelism how many CPU cores to use
	 * @return the sorted list
	 */
	public static LinkedList<Integer> parallelMergeSort(LinkedList<Integer> numbers, int parallelism) {
		ForkJoinPool pool = new ForkJoinPool(parallelism);
		ParallelMergeSort mergeSortTask = new ParallelMergeSort(numbers);
		LinkedList<Integer> result = pool.invoke(mergeSortTask);
		return result;
	}

	/**
	 * Benchmarks parallel merge sort
	 */
	public static void main(String[] args) {
		/* generates a random list */
		LinkedList<Integer> numbers = SortingCommon.randomList(50000);

		/* gets the number of cores in this computer's CPU */
		int cpuCores = Runtime.getRuntime().availableProcessors();

		/*
		 * benchmarks the parallel merge sort for Q2 on 1, 2, 4, .. CPU cores
		 */
		for (int threads = 1; threads <= cpuCores; threads *= 2) {
			Benchmark.parallel(new ParallelMergeSort(numbers), threads);
		}
	}

}