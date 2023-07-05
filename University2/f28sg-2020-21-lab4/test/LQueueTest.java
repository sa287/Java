import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LQueueTest {

	LQueue q;
	@Before
	public void setup()
	{
		q = new LQueue();
	}
	
	/*
	 * 2: complete the following test methods as specified. 
	 */
	
	@Test
	public void testIsEmpty() {
		// test that q.isEmpty returns true
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		// add an element to the queue "q"
		int x = 4;
		q.enqueue(x);
		// then test that q is not an empty queue.
		assertFalse(q.isEmpty());
	}

	@Test
	public void testSizeEmpty() {
		// test the size of an empty queue is 0
		assertEquals(0,q.size());
	}
	
	@Test
	public void testSizeNonEmpty() {
		// add an element(s) to the queue
		int x = 4;
		q.enqueue(x);
		// then test the size of the queue
		assertEquals(1,q.size());
	}

	@Test
	public void testEnqueue() {
		// enqueue an element(s) to the queue
		int x = 4;
		q.enqueue(x);
		System.out.println(q.front());
		// then test that the correct element is at the front
		assertEquals(4,q.front());
	}
	
	@Test
	public void testDequeue() {
		// enqueue multiple elements to the queue
		int x = 4;
		int y = 6;
		q.enqueue(x);
		q.enqueue(y);
		// then check that they a returned in the
		// correct order with q.dequeue()
		assertEquals(4,q.dequeue());
		assertEquals(6,q.dequeue());
	}
	
	@Test(expected = QueueException.class)  
	public void testEmptyDequeue() {  
		// try to dequeue an empty queue
			q.dequeue();
		
	}
	
	@Test(expected = QueueException.class)  
	public void testEmptyFront() {
		// try to get the front value of an empty queue
			q.front();
	}
}
