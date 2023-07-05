# Multi-thread Processing Demo #

Multi-thread versus single thread processing demo.

Computation task is to calculate lots of additions (same for both implementations).

### What will you learn?
How using multiple threads on multiple CPUs AND logical processors can significantly speed up computation - but can get the wrong answer!.

### Why do this exercise?
1. It is examinable.
2. It will make you think about protecting your shared variables (shared state) in your coursework.

## Task 0 - Understanding Hyperthreading
Today most computers have multiple *physical CPU cores* which are enhanced by *hyperthreading* to provide double the number of *logical cores*. We will be running our code in both single-threaded and multi-threaded forms to *try* to exploit multi-core machines. See <https://www.agner.org/optimize/blog/read.php?i=6&v=t> for a description of hyperthreading.

## Task 1 - Run the application

**IMPORTANT** You will be editing this lab so please create your own copy in your own remote repository by **forking** it to your own private GitLab space. Then clone a local copy from your own remote repository to your computer.


Examine the `ExecutionBlock` class in `CompareMltiAndSingleThreading.java` and note that it simply increments an `Accumulator` instance a set number of times.

Also note that depending on the value of the boolean `multithreaded`, we will either run the `ExecutionBlock` directly in the `main` thread, or concurrently by creating several threads as below:

	for (int i = 0; i < nExecutionBlocks; i++) {
		ExecutionBlock executionBlock = new ExecutionBlock(i);
		threadArray[i] = new Thread(executionBlock);
		threadArray[i].start();  	
	}

1. For your computer determine speed (GHz), number of physical cores, and number of logical processors.
2. Run this Java application. Record the two execution times and the two sum values.
3. Examine the the console output: you should notice three main differences between the single threaded (MULTITHREADED = false) and the multithreaded implementations (MULTITHREADED = true). Make a note of these differences here: (in this Readme.md) and commit and push the change to your own remote repository.
4. Note times and sum values etc. and fill out form at https://forms.office.com/r/SGKmAqbbSX

## Task 2 - investigating the difference between 'run' and 'start'

### Changing `start` to `run` (in multithreaded implementation)
1. In the multithreaded part of the code in `CompareMltiAndSingleThreading.java`, find the following line of code: `threadArray[i].start();`.
2. Change the `start` to `run` - execute code. 
3. Determine how many threads are now being executed in the *multithreaded* part of the implementation. Record your answer here: ___ in the README file. 
4. Do you understand why the number of threads has changed?  Put your answer in the commit message, then push and commit your changes.

### Changing `run` to `start` (in single threaded implementation)
**NOTE we do not normally call a `run` method directly** - it's only used here to implement a single thread solution.
1. Undo the last change and run the code to make sure that it is executing as before.
2. In the single-threaded implementation find the line: `executionBlock.run();`
3. Change to call to `run` to call the method `start`.
4. Compile/execute the code.
5. Do you understand why you get a compile time error?  Make a note of when you should use `run` and when you should use `start` and put this in the commit message for this push/commit.

## Task 3 - Using a Lock to put a *critical regions* around access to `sum`
1. Undo the last edits so that the application reverts back to its original form.
2. Now run the application several times to convince yourself that the multithreaded implementation is not thread-safe.
2. Add an instance of `ReentrantLock` to the `Accumulator` class.
3. Make the `Accumulator` class thread-safe by using this `ReentrantLock` to *lock* access to `sum` while you increment it or read it. (Add calls to `reentrantLock.Lock()` and `reentrantLock.unLock()` to the beginning and end of the `increment` method respectively, do same for `getSum()`). Check that the result is correct for the multithreaded version and note the execution time (t1). 
5. Enter t1 at https://forms.office.com/r/ZKFjv8dWus
6. Why do you think that this version is so much slower? And can you think of a way of making it faster while still being thread-safe? Commit and Push with answer as comment.



## Solutions
Note that there are no conventional solutions provided for this lab. 

The solution for creating a critical region around the write access to `sum` should be very obvious from the F29OC notes or via recommended readings. Post in class if you are not sure or ask in the labs.

If you add the correct code, then your result will be *thread-safe* and the final *sum* will be the same as the single-threaded (MULTITHREADED = false) version (240000000). 

## API links
1. ReentrantLock: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html#lock() and https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html#unlock()







### Example run of MainMultiThread.java:

This is the unedited version: so the multi-threaded implementation will contain multiple *races* to update the shared state variable `sum`.

```


MULTITHREADED = false
thread main: Execution Block 0 started - sum = 0
thread main: Execution Block 0 completed - sum = 10000000
thread main: Execution Block 1 started - sum = 10000000
thread main: Execution Block 1 completed - sum = 20000000
thread main: Execution Block 2 started - sum = 20000000
thread main: Execution Block 2 completed - sum = 30000000
thread main: Execution Block 3 started - sum = 30000000
thread main: Execution Block 3 completed - sum = 40000000
thread main: Execution Block 4 started - sum = 40000000
thread main: Execution Block 4 completed - sum = 50000000
thread main: Execution Block 5 started - sum = 50000000
thread main: Execution Block 5 completed - sum = 60000000
thread main: Execution Block 6 started - sum = 60000000
thread main: Execution Block 6 completed - sum = 70000000
thread main: Execution Block 7 started - sum = 70000000
thread main: Execution Block 7 completed - sum = 80000000
thread main: Execution Block 8 started - sum = 80000000
thread main: Execution Block 8 completed - sum = 90000000
thread main: Execution Block 9 started - sum = 90000000
thread main: Execution Block 9 completed - sum = 100000000
thread main: Execution Block 10 started - sum = 100000000
thread main: Execution Block 10 completed - sum = 110000000
thread main: Execution Block 11 started - sum = 110000000
thread main: Execution Block 11 completed - sum = 120000000
thread main: Execution Block 12 started - sum = 120000000
thread main: Execution Block 12 completed - sum = 130000000
thread main: Execution Block 13 started - sum = 130000000
thread main: Execution Block 13 completed - sum = 140000000
thread main: Execution Block 14 started - sum = 140000000
thread main: Execution Block 14 completed - sum = 150000000
thread main: Execution Block 15 started - sum = 150000000
thread main: Execution Block 15 completed - sum = 160000000
thread main: Execution Block 16 started - sum = 160000000
thread main: Execution Block 16 completed - sum = 170000000
thread main: Execution Block 17 started - sum = 170000000
thread main: Execution Block 17 completed - sum = 180000000
thread main: Execution Block 18 started - sum = 180000000
thread main: Execution Block 18 completed - sum = 190000000
thread main: Execution Block 19 started - sum = 190000000
thread main: Execution Block 19 completed - sum = 200000000
thread main: Execution Block 20 started - sum = 200000000
thread main: Execution Block 20 completed - sum = 210000000
thread main: Execution Block 21 started - sum = 210000000
thread main: Execution Block 21 completed - sum = 220000000
thread main: Execution Block 22 started - sum = 220000000
thread main: Execution Block 22 completed - sum = 230000000
thread main: Execution Block 23 started - sum = 230000000
thread main: Execution Block 23 completed - sum = 240000000

MULTITHREADED = false, TOTAL EXECUTION TIME: 46mS, sum = 240000000


MULTITHREADED = true
thread Thread-0: Execution Block 0 started - sum = 0
thread Thread-1: Execution Block 1 started - sum = 128128
thread Thread-2: Execution Block 2 started - sum = 2097282
thread Thread-3: Execution Block 3 started - sum = 3176913
thread Thread-4: Execution Block 4 started - sum = 3300577
thread Thread-6: Execution Block 6 started - sum = 5076625
thread Thread-0: Execution Block 0 completed - sum = 8779363
thread Thread-5: Execution Block 5 started - sum = 8789282
thread Thread-1: Execution Block 1 completed - sum = 10847715
thread Thread-8: Execution Block 8 started - sum = 12615683
thread Thread-4: Execution Block 4 completed - sum = 12821363
thread Thread-9: Execution Block 9 started - sum = 12257360
thread Thread-12: Execution Block 12 started - sum = 14352067
thread Thread-2: Execution Block 2 completed - sum = 14883283
thread Thread-7: Execution Block 7 started - sum = 12409059
thread Thread-3: Execution Block 3 completed - sum = 16459539
thread Thread-11: Execution Block 11 started - sum = 17561315
thread Thread-15: Execution Block 15 started - sum = 17889683
thread Thread-5: Execution Block 5 completed - sum = 19204948
thread Thread-13: Execution Block 13 started - sum = 19862355
thread Thread-8: Execution Block 8 completed - sum = 22978596
thread Thread-16: Execution Block 16 started - sum = 24622149
thread Thread-9: Execution Block 9 completed - sum = 24151252
thread Thread-12: Execution Block 12 completed - sum = 25668469
thread Thread-7: Execution Block 7 completed - sum = 25664245
thread Thread-17: Execution Block 17 started - sum = 26325076
thread Thread-20: Execution Block 20 started - sum = 27056677
thread Thread-10: Execution Block 10 started - sum = 26395155
thread Thread-6: Execution Block 6 completed - sum = 14817330
thread Thread-11: Execution Block 11 completed - sum = 26976372
thread Thread-14: Execution Block 14 started - sum = 16721011
thread Thread-15: Execution Block 15 completed - sum = 29976708
thread Thread-13: Execution Block 13 completed - sum = 30122581
thread Thread-19: Execution Block 19 started - sum = 31711253
thread Thread-21: Execution Block 21 started - sum = 31673141
thread Thread-16: Execution Block 16 completed - sum = 34139991
thread Thread-17: Execution Block 17 completed - sum = 35847252
thread Thread-18: Execution Block 18 started - sum = 34361668
thread Thread-23: Execution Block 23 started - sum = 37972147
thread Thread-20: Execution Block 20 completed - sum = 38968359
thread Thread-14: Execution Block 14 completed - sum = 39818999
thread Thread-10: Execution Block 10 completed - sum = 36666565
thread Thread-19: Execution Block 19 completed - sum = 44196023
thread Thread-21: Execution Block 21 completed - sum = 40323734
thread Thread-22: Execution Block 22 started - sum = 38115844
thread Thread-18: Execution Block 18 completed - sum = 41150549
thread Thread-23: Execution Block 23 completed - sum = 42747653
thread Thread-22: Execution Block 22 completed - sum = 48677380

MULTITHREADED = true, TOTAL EXECUTION TIME: 7mS, sum = 48677380



```

Above run under Windows 10 on an 2.9GHz i7 with 4 cores, and 8 logical processors (Windows 10 > System info.)
