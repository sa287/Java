import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Accumulator {
	ReentrantLock lock = new ReentrantLock();
	private int sum = 0;
	
	void increment() {
		lock.lock();
		sum = sum + 1;
		lock.unlock();
		
	}
	
	long getSum() {
		lock.lock();
		long returnvalue;
		returnvalue = sum;
		lock.unlock();
		return returnvalue;
		
	};
}
