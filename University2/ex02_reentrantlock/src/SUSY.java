


/*
 * Ten Pin Manager class
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


public class TenPinManager {

	Booking gBooking = new Booking();
	final ReentrantLock lock = new ReentrantLock();
	final Condition bookingFull = lock.newCondition();
	final Condition bookingExists = lock.newCondition();
	
	
	void bookLane(String bookersName, int nPlayers) {
		lock.lock();
		gBooking.push(new Booker(bookersName, nPlayers));
		bookingExists.signalAll();
			
		// DEBUG
		gBooking.sysout();
		lock.unlock();
	}; 

	void playerLogin(String bookersName) {
		lock.lock();
		Booker b = gBooking.getByName(bookersName);
		
		// handle booking not in queue
		while(b == null || b.isCapped()) {
			try {
				// DEBUG
				System.out.println(bookersName + " DNE");
				
				bookingExists.await();
				
				// check updated queue
				b = gBooking.getByName(bookersName);
			} catch (InterruptedException e) {
				// TODO: Handle exception
				e.printStackTrace();
			}
		}
			
		b.login();
			
		// DEBUG
		System.out.println(b.getName() + " (" + b.getNLogged() + "/" + b.getNPlayers() + ")");
			
		while(!b.isCapped()) {
			try {
				bookingFull.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
				// TODO: Handle exception
			}
		}
		
		// remove from queue (FIFO)
		if (b.accept() == 0) {
			gBooking.pop(bookersName);
		}
		
		bookingFull.signalAll();
		lock.unlock();
	}; 
	

	
	
	
	// You may add private classes and methods as below:
	private class Booker {
		String name;
		int nPlayers;
		int nLogged;
		boolean capped = false;
		
		Booker(String name, int nPlayers) {
			this.name = name;
			this.nPlayers = nPlayers;
			this.nLogged = 0;
		}
		
		String getName() { return this.name; }
		int getNPlayers() { return this.nPlayers; }
		int getNLogged() { return this.nLogged; }
		boolean isCapped() { return this.capped; }
		int accept() { return --nLogged; }
		
		void login() { 
			if (!capped) {
				if (increment() == nPlayers) {
					capped = true;
					
				}
			}
		}
		
		int increment() {
			return ++nLogged;
		}
	}
	
	private class Booking {
		ArrayList<Booker> queue = new ArrayList<Booker>();
		
		void push(Booker booker) {
			if(queue.contains(booker)) {
				return;	// singleton
			}
			
			queue.add(booker);
		}
		
		Booker pop(String bookerName) {
			if (queue.isEmpty() || this.getByName(bookerName) == null) {
				return null;
			}
			
			// DEBUG
			System.out.println("#" + this.findIndex(bookerName) + " : " + bookerName + " removed");
			
			return queue.remove(this.findIndex(bookerName));
		}
		
		Booker pop(int i) {
			if (i < 0 || queue.isEmpty() || this.getByIndex(i) == null) {
				return null;
			}
			
			return queue.remove(i);
		}
		
		Booker getByName(String bookerName) {
			int index = findIndex(bookerName);
			
			if (index == -1) {
				return null;
			}
			
			return getByIndex(index);
		}
		
		Booker getByIndex(int i) {
			if (i < 0 || queue.isEmpty()) {
				return null;
			}
			
			return queue.get(i);
		}
		
		// returns -1 if not found
		int findIndex(String bookerName) {
			int i = -1;
			int j = -1;
			
			if (queue.isEmpty()) {
				return i;
			}
			
			Iterator<Booker> it = queue.iterator();
			boolean found = false;
			
			while(it.hasNext() && !found) {
				Booker b = it.next();
				j++;
				
				if(b.getName().equals(bookerName)) {
					found = true;
					i = j;
				}
			}

			return i;
		}
		
		// DEBUG purposes
		void sysout() {
			Iterator<Booker> it = queue.iterator();
			int id = 0;
			
			while(it.hasNext()) {
				Booker b = it.next();
				System.out.println("#" + id++ + " : " + b.getName() + " (" + b.getNPlayers() + ")");
			}
		}
	}
}
