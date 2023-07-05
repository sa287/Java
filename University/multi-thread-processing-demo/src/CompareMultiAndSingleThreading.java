import java.util.concurrent.atomic.AtomicLong;

public class CompareMultiAndSingleThreading {

	public void performCalculations(boolean multithreaded) {

		final int nIterations = 10000000; //Size of compute task	
		final int nExecutionBlocks = 24; // Number of times to repeat the Execution Block or thread instantiation
		final long startTime = System.currentTimeMillis();

		Accumulator accumulator = new Accumulator();
			
		//This where we define what work will be done by each thread
		class ExecutionBlock implements Runnable {		
			int executionBlockNumber;	//Number so we know which EB is which	
			
			//Constructor: set the EB number
			ExecutionBlock (int executionBlockNumber) {
				this.executionBlockNumber = executionBlockNumber;
			};			

			@Override
		    public void run() {
				//The code in this method is the 'job of work' 
				//or the 'Execution Block' of code that a thread will execute
				String name = Thread.currentThread().getName();
				System.out.println("thread " + name + ": Execution Block " + executionBlockNumber + " started - sum = "  + accumulator.getSum()); 
				
				//Now do the compute task (add lots of numbers)
				for(int i = 0; i < nIterations; i++) {
					accumulator.increment(); 
				}
				System.out.println("thread " + name + ": Execution Block " + executionBlockNumber + " completed - sum = "  + accumulator.getSum()); 		
		    }
		}
		
		
		System.out.println("\nMULTITHREADED = " + multithreaded );
			
		if (!multithreaded) {		
			//If single-threaded just run all the execution blocks in this 'main' thread	
			// one after the other:
			for (int i = 0; i < nExecutionBlocks; i++) {
				ExecutionBlock executionBlock = new ExecutionBlock(i);
				//NOTE: we do not normally call the 'run' method of an EB directly as this
				// will cause the the EB to be run in the calling thread (in this case the main thread).
  				executionBlock.run(); 	
  			}
 		}
		
		if (multithreaded) {
			//If multithreaded create and execute 'n' execution blocks 'n' in separate threads.
			//Now create array of Thread pointers:
			Thread threadArray[] = new Thread[nExecutionBlocks];			
			
			for (int i = 0; i < nExecutionBlocks; i++) {
				//For each execution of this loop, create a new instance of the execution block:
	 			ExecutionBlock executionBlock = new ExecutionBlock(i);
	 			//Then create a new thread instance, pass it the Runnable execution block, and load reference to new thread into array:
  				threadArray[i] = new Thread(executionBlock);
  				//Then 'start' the new thread (which, in turn, will call the 'run' method of the execution block):
  				threadArray[i].start();  	
	 		}
			
 			//Wait for all the compute threads to finish
 			for (int i =0; i<nExecutionBlocks; i++)
				try {threadArray[i].join();} catch (InterruptedException e) {e.printStackTrace();} 
 		}
		
		final long endTime = System.currentTimeMillis();
		System.out.println("\nMULTITHREADED = " + multithreaded + ", TOTAL EXECUTION TIME: " + (endTime - startTime) + "mS, sum = "  + accumulator.getSum() + "\n");
	}
}
