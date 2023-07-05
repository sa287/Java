import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class StackTest {

	Stack st;
	@Before
	public void setup()
	{
		st = new Stack(2);
	}
	
	/*
	 * Part1: complete the following test methods as specified. 
	 * Note that you will need to add new methods in order to test that exceptions are thrown
	 */
	
	@Test
	public void testPushEmptySize() {
		// test the size of an empty stack (hint: test st.size() )
		
		assertEquals(0, st.size());
		System.out.println("Stack has a size of 0.");
		
	}
	
	@Test
	public void testPushNonEmptySize() {
		st.push("A");
		st.push("B");
		// test the size of a non-empty stack
		assertEquals(2, st.size());
		
	}
	
	@Test
	public void testPopTwo() {
		st.push("A");
		st.push("B");
		// step 1: test what st.pop() returns
		assertEquals("B", st.pop());
		// step 2: test what st.pop() returns
		assertEquals("A", st.pop());
	}
	
	@Test
	public void testTopTwo() {
		st.push("A");
		// step 1: test what st.top() returns
		assertEquals("A", st.top());
		// step 2: test what st.top() returns
		assertEquals("A", st.top());
		
	}
	
	
	@Test(expected = StackException.class)
	public void testEmptyPop() {
		// try popping from an empty stack
		if (st.size() == 0) {
			st.pop();
			
		}
	}

	@Test(expected = StackException.class)
	public void testFullPush() {
		// try pushing too many elements to the stack
		// (which has a capacity of 2 elements)
		st.push("A");
		st.push("B");
		st.push("C");
		
	}

}
