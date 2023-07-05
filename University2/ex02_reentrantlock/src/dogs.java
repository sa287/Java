
/*
 * Ten Pin Booker class
 * 
 * You must write code here so that this class satisfies the Coursework User Requirements (see CW specification on Canvas).
 * 
 * You may add private classes and methods to this file 
 * 
 * 
 * 
 ********************* IMPORTANT ******************
 * 
 * 1. You must implement TenPinManager using Java's ReentrantLock class and condition interface (as imported below).
 * 2. Other thread safe classes, e.g. java.util.concurrent MUST not be used by your TenPinManager class.
 * 3. Other thread scheduling classes and methods (e.g. Thread.sleep(timeout), ScheduledThreadPoolExecutor etc.) must not be used by your TenPinManager class..
 * 4. Busy waiting must not be used: specifically, when an instance of your TenPinManager is waiting for an event (i.e. a call to booklane() or playerLogin() ) it must not consume CPU cycles.
 * 5. No other code except that provided by you here (in by your TenPinManager.java file) will be used in the automatic marking.
 * 6. Your code must be reasonably responsive (e.g. no use of sleep methods etc.).
 * 
 * Failure to comply with the above will mean that your code will not be marked and you will receive a mark of 0.
 * 
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TenPinManagers {

	ReentrantLock lock = new ReentrantLock();
	Condition alleyMax = lock.newCondition();
	Condition alleyLive = lock.newCondition();

	Alley alley1 = new Alley();

	void bookLane(String name, int numP) {
		lock.lock();

		/*
		 * Booker man = new Booker(name, numP);
		 * 
		 * if (alley1.getQ().contains(man)) return; // singleton alley1.getQ().add(man);
		 */
		
		
		push(alley1, new Booker(name, numP));
		alleyLive.signalAll();

		lock.unlock();
	}

	void playerLogin(String name) {
		lock.lock();

		Booker man = alley1.getter(name);

		while (man.max || man.equals(null)) {

			try {
				alleyLive.await();
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}

			man = alley1.getter(name);

		}

		if (!man.max)
			man.acceptedP++;
			if (man.acceptedP== man.numP)
				man.max = true;

		while (!man.max) {
			try {
				alleyMax.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if ((man.acceptedP - 1) == 0) {
			pop(alley1, name);
		}

		alleyMax.signalAll();
		lock.unlock();
	}

	// You may add private classes and methods as below:

	private class Booker {

		int numP, acceptedP;
		String name;
		boolean max;

		Booker(String name, int numP) {
			this.name = name;
			this.numP = numP;
			this.acceptedP = 0;
		}

	}

	private class Alley {
		ArrayList<Booker> queue = new ArrayList<Booker>();

		ArrayList<Booker> getQ() {
			return queue;

		}

		Booker getter(String bookerName) {

			if (search(bookerName) < 0)
				return null;

			return getterPlace(search(bookerName));
		}

		Booker getterPlace(int placement) {

			int tempPlace = placement;

			if (tempPlace < 0)
				return null;
			else if (queue.isEmpty())
				return null;

			return queue.get(tempPlace);
		}

		int search(String bName) {
			int x = -1, y = -1;
			Iterator<Booker> iter = queue.iterator();
			if (queue.isEmpty())
				return x;
			while (iter.hasNext()) {
				Booker b = iter.next();

				y = y + 1;
				String temp = b.name;
				if (temp.equals(bName)) {
					x = y;
					break;
				}
			}

			return x;
		}

	}

	
	void push(Alley booking, Booker booker) {

		ArrayList<Booker> temp = booking.getQ();

		if (temp.contains(booker))
			return; // singleton

		temp.add(booker);
	}
	Booker pop(Alley alley, String bookerName) {
		ArrayList<Booker> temp = alley.getQ();

		System.out.println("Dog");
		if (temp.isEmpty())
			return null;
		else if (alley.getter(bookerName) == null)
			return null;

		return temp.remove(alley.search(bookerName));
	}

	Booker pop(Alley alley, int i) {

		ArrayList<Booker> temp = alley.getQ();

		if (i < 0)
			return null;

		else if (alley.getterPlace(i) == null)
			return null;
		else if (temp.isEmpty())
			return null;

		return temp.remove(i);
	}

}


